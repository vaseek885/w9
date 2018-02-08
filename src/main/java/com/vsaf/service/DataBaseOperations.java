package com.vsaf.service;

import com.vsaf.common.AreaChecker;
import com.vsaf.common.Point;
import com.vsaf.service.RegisterService;
import com.vsaf.service.PointService;

import java.util.ArrayList;
import java.sql.*;


public class DataBaseOperations {

    public DataBaseOperations(String username){
        this.username = username;
    }



    private Connection connection = null;

    private String username;

    PreparedStatement add_statement = null;
    PreparedStatement delete_statement = null;
    PreparedStatement readall_statement = null;
    PreparedStatement updateall_statement = null;
    PreparedStatement update_statement = null;



    PreparedStatement adduser_statement = null;
    PreparedStatement finduser_statement = null;
    PreparedStatement readallusers_statement = null;
    PreparedStatement deleteuser_statement = null;

    private void open_connection(){

        if(connection == null){

            try {   

                Class.forName("org.postgresql.Driver");

            } catch (ClassNotFoundException e) {

                System.out.println("No PostgreSQL JDBC Driver?");
                e.printStackTrace();
                return;

            }

            System.out.println("PostgreSQL JDBC Driver registering");

            try {
                connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:6090/postgres", "s208070","yxu697");
                String insertTableSQL = "INSERT INTO " + username 
                        + " (x, y, r, contained) VALUES "
                        + "(?,?,?,?)";

                add_statement = connection.prepareStatement(insertTableSQL);  
                delete_statement = connection.prepareStatement("DELETE FROM " + username);  

                readall_statement = connection.prepareStatement("SELECT * FROM " + username); 
                update_statement = connection.prepareStatement("UPDATE " + username + " SET contained = ? WHERE x = ? AND y = ?"); 
                updateall_statement = connection.prepareStatement("UPDATE " + username + " SET r = ?"); 

                adduser_statement = connection.prepareStatement("INSERT INTO " + username + " (name,password) VALUES (?,?)");  
                finduser_statement = connection.prepareStatement("SELECT * FROM " + username + " WHERE name = ? AND password = ?");  

            } catch (SQLException e) {

                System.out.println("Connection Failed! Check output console");
                e.printStackTrace();
                return;

            }
        }
    }

    Boolean tableExists(String tablename){
        try {
            PreparedStatement table_exists = null;
            ResultSet rs;
            table_exists = connection.prepareStatement("SELECT * FROM information_schema.tables WHERE table_name = '" + tablename + "'");
            rs = table_exists.executeQuery();
            if (!rs.next() ) {
                return false;
            } else {
                return true;
            }
		} catch (SQLException e) {
            e.printStackTrace();
            return false;
		}
    }

    public int updatepoints(Float r){
        open_connection();
        if(updateall_statement != null && update_statement != null && readall_statement != null){
            try {
                ResultSet rs;
                System.out.println("Updating points to " + r);
                updateall_statement.setFloat(1, r);
                updateall_statement.executeUpdate();
                rs = readall_statement.executeQuery();
                while(rs.next()){
                    System.out.println("point: x " + rs.getFloat("x") + " y " + rs.getFloat("y") + " r " +  rs.getFloat("r"));
                    Point np = new Point(rs.getFloat("x"),rs.getFloat("y"),rs.getFloat("r"));
                    np.setIncluded(false);
                    AreaChecker.checkPoint(np);
                    update_statement.setString(1,np.isIncluded().toString());
                    update_statement.setFloat(2,np.getX());
                    update_statement.setFloat(3,np.getY());
                    update_statement.executeUpdate();
                }
                return PointService.POINT_SUCSESS;

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return PointService.POINT_FALIURE;
    }

    public int addPoint(Point point){

        open_connection();
        if (connection != null) {

            try {
            
                if(add_statement != null){
                    if(!tableExists(username)){
                        Statement create_table = connection.createStatement();
                        create_table.execute("CREATE TABLE " + username + " (x FLOAT, y FLOAT, r FLOAT, contained VARCHAR(20));");
                    }
                    System.out.println("Adding values x:" + point.getX() + "y:" + point.getY() + "r:" + point.getRadius());
                    add_statement.setFloat(1, point.getX());//x
                    add_statement.setFloat(2, point.getY());//y
                    add_statement.setFloat(3, point.getRadius());//r
                    add_statement.setBoolean(4, point.isIncluded());//included
                    add_statement.executeUpdate();
                    return PointService.POINT_SUCSESS;
                }
                else {
                    System.out.println("no statement");
                    return PointService.POINT_FALIURE;    
                }

            }catch (Exception e){
                System.out.println("Exception in addpoint: ");
                e.printStackTrace();
                return PointService.POINT_FALIURE;
            }

        } else {
            System.out.println("Failed to make connection");
            return PointService.POINT_FALIURE;
        }


    }

    public int deleteAllPoints(){
        open_connection();
        if (connection != null) {

            try {
            
                if(delete_statement != null){
                    delete_statement.executeUpdate();
                    return PointService.POINT_SUCSESS;
                }
                else {
                    System.out.println("no statement");
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            return PointService.POINT_FALIURE;

        } else {
            System.out.println("Failed to make connection");
        }
        return PointService.POINT_FALIURE;
    }


    public ArrayList<Point> readAllTable(){

        open_connection();
        ArrayList<Point> points = new ArrayList<Point>();
        if (connection != null) {

            try {
            
                if(readall_statement != null){
                    ResultSet rs = null;
                    
                    rs = readall_statement.executeQuery();
                    while(rs.next()){
                        points.add(new Point(rs.getFloat("x"),rs.getFloat("y"),rs.getFloat("r"), Boolean.parseBoolean(rs.getString("contained")) ));

                    }
                }
                else {
                    System.out.println("no statement");
                }

            }catch (Exception e){
                e.printStackTrace();
            }

        } else {
            System.out.println("Failed to make connection");
        }
        return points;
    }

    public int add_user(String username, String password){
        open_connection();
        if(connection != null){
            try{
                if(adduser_statement!= null){

                    
                    adduser_statement.setString(1,username);
                    adduser_statement.setString(2,password);
                    adduser_statement.executeUpdate();

                }
            }catch(Exception e){
                System.out.println("Exception in addusr: ");
                e.printStackTrace();
                
                return RegisterService.REGISTER_FALIURE;
            }
            return RegisterService.REGISTER_SUCSESS;
        }
        return RegisterService.REGISTER_FALIURE;
    }

    public Boolean user_exists(String username, String password){
        open_connection();
        if(connection != null){
            try{
                if(finduser_statement!= null){
                    ResultSet rs = null;
                    finduser_statement.setString(1,username);
                    finduser_statement.setString(2,password);
                    System.out.println("Findusr :"+finduser_statement.toString());  
                    rs = finduser_statement.executeQuery();
                    System.out.println("After queury");  
                    if (!rs.next() ) {
                        return false;
                    } else {
                        return true;
                    }

                }
            }catch(Exception e){
                System.out.println("Exception in findusr: ");
                e.printStackTrace();
                return false;
            }
        }
        System.out.println("in findusr: no statement");
        return false;
    }

	public Float getR() {
		open_connection();
        if(connection != null){
            try{
                if(readall_statement != null){
                    ResultSet rs;
                    rs = readall_statement.executeQuery();
                    if(rs.next())
                        return rs.getFloat("r");
                    
                }
            }catch(Exception e){
                System.out.println("Exception in get r: ");
                e.printStackTrace();
            }
        }
        return null;
	}

}

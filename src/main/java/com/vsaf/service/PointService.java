package com.vsaf.service;
import javax.ejb.Stateless;
import com.vsaf.common.Point;
import com.vsaf.service.LoginService;
import javax.ejb.EJB;
import com.vsaf.service.DataBaseOperations;
import java.util.ArrayList;


@Stateless
public class PointService {

    public static final int POINT_SUCSESS = 0;
    public static final int POINT_FALIURE = 1;

    @EJB 
	LoginService logserv;

    private DataBaseOperations dbOps;

    public int addpoint(String username, String password, Point point) {
        if(logserv.login(username, password) == LoginService.LOGIN_SUCSESS){
            System.out.println("Adding point to User" + username + " With password " + password + "point(x,y)" + point.getX() + "," + point.getY());
            dbOps = new DataBaseOperations(username);
            int result = dbOps.addPoint(point);
            System.out.println("The result of adding new point is" + result);
            return result;
        }
        return POINT_FALIURE;
    }

    public int update_points(String username, String password, Float r) {
        if(logserv.login(username, password) == LoginService.LOGIN_SUCSESS){
            System.out.println("Updating points for" + username);
            dbOps = new DataBaseOperations(username);
            int result = dbOps.updatepoints(r);
            System.out.println(" Update result" + result);
            return result;
        }
        return POINT_FALIURE;
    }

	public int delete_points(String username, String password) {
		if(logserv.login(username, password) == LoginService.LOGIN_SUCSESS){
            System.out.println("deleting points for" + username);
            dbOps = new DataBaseOperations(username);
            int result = dbOps.deleteAllPoints();
            System.out.println(" Update result" + result);
            return result;
        }
        return POINT_FALIURE;
	}

	public ArrayList<Point> get_points(String username, String password) {
        if(logserv.login(username, password) == LoginService.LOGIN_SUCSESS){
            System.out.println("getting points for" + username);
            dbOps = new DataBaseOperations(username);

            ArrayList<Point> points = dbOps.readAllTable();
            System.out.println("Finished getting points");
            return points;
        }
        return null;
	}

	public Float get_r(String username, String password) {
        if(logserv.login(username, password) == LoginService.LOGIN_SUCSESS){
            System.out.println("getting r for" + username);
            dbOps = new DataBaseOperations(username);

            Float result = dbOps.getR();
            System.out.println("Finished getting r: " + result);
            return result;
        }
		return null;
	}

}
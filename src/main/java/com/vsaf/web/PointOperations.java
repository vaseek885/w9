package com.vsaf.web;

import com.vsaf.service.PointService;
import com.vsaf.common.Point;
import com.vsaf.common.AreaChecker;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ejb.Stateless;
import javax.ejb.EJB;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;

// import java.util.List;

@Stateless
@Path("/point")
public class PointOperations {

	@EJB 
    PointService pointsrv;
    
	@OPTIONS
	@Path("/addpoint")
	public Response getOptions(){
		System.out.printf("getopt addpoint");
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
		.header("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With")
		.header("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
		.header("Access-Control-Max-Age", "3600")
		.build();
	}

	@OPTIONS
	@Path("/update")
	public Response getOpt(){
		System.out.printf("getopt update");
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
		.header("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With")
		.header("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
		.header("Access-Control-Max-Age", "3600")
		.build();
	}

	@OPTIONS
	@Path("/get")
	public Response getOp(){
		System.out.printf("getopt get");
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
		.header("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With")
		.header("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
		.header("Access-Control-Max-Age", "3600")
		.build();
	}

	@OPTIONS
	@Path("/deleteall")
	public Response getO(){
		System.out.printf("getopt deleteall");
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
		.header("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With")
		.header("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
		.header("Access-Control-Max-Age", "3600")
		.build();
	}

	@OPTIONS
	@Path("/r")
	public Response get(){
		System.out.printf("getopt deleteall");
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
		.header("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With")
		.header("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
		.header("Access-Control-Max-Age", "3600")
		.build();
	}

	@POST
	@Path("/addpoint")
	public Response add_point(PntRequestBody req) {
        System.out.println("Addpoint");
		if(req.username != null && req.password != null) {
            Point new_point = new Point();
            new_point.setX(req.x);
            new_point.setY(req.y);
            new_point.setRadius(req.r);
			AreaChecker.checkPoint(new_point);

			int result = pointsrv.addpoint(req.username,req.password,new_point);
			switch(result){
				case 0:
					return Response.status(200).header("Access-Control-Allow-Origin", "*")
					.entity(new_point.toString()).build();
					
				
				case 1:
					return Response.status(500)
					.entity("registration failed - username is taken").build();
					
				
				default:
					return Response.status(500)
					.entity("registration failed - code of error is " + result).build();
			}
		} else {
			return Response.status(500)
			.entity("registration failed - make sure that both username and password are not empty\n").build();	
		}
	}


	@POST
	@Path("/update")
	public Response update(PntRequestBody req) {
		System.out.println("update");
		int result = pointsrv.update_points(req.username, req.password, req.r);
		switch(result){
			case 0:
				return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.build();
				
			
			case 1:
				return Response.status(500)
				.entity("Internal error").build();
				
			
			default:
				return Response.status(500)
				.entity("registration failed - code of error is " + result).build();
		}
	}

	@POST
	@Path("/get")
	public Response getpoints(GetRequestBody req) {
		System.out.println("get points");
		ArrayList<Point> result = pointsrv.get_points(req.username, req.password);
		if(result != null){
			if(!result.isEmpty()){
				String resp = "[";
				for(Point pt: result){
					resp = resp + pt.toString() + ",";
					
				}
				resp = resp.substring(0, resp.length() - 1);
				resp += ']';
				return Response.status(200).entity(resp).header("Access-Control-Allow-Origin", "*").build();
			} else {
				return Response.status(200).header("Access-Control-Allow-Origin", "*").build();
			}
			
		}
		return Response.status(200).header("Access-Control-Allow-Origin", "*").build();
		
	}

	@POST
	@Path("/deleteall")
	public Response delete_all(GetRequestBody req) {
		System.out.println("delete points");
		int result = pointsrv.delete_points(req.username, req.password);
		switch(result){
			case 0:
				return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.build();
				
			
			case 1:
				return Response.status(500)
				.entity("Internal error").build();
				
			
			default:
				return Response.status(500)
				.entity("registration failed - code of error is " + result).build();
		}
	}

	@POST
	@Path("/r")
	public Response get_r(GetRequestBody req) {
		System.out.println("get r");
		Float result = pointsrv.get_r(req.username, req.password);
		
		if(result != null){
			return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(result.toString())
			.build();
		}
	
		return Response.status(500)
		.entity("Internal error").build();
	
	}

}

@XmlRootElement
final class PntRequestBody {
    @XmlElement String username;
    @XmlElement String password;
	@XmlElement Float x;
    @XmlElement Float y;
    @XmlElement Float r;
}

@XmlRootElement
final class GetRequestBody {
    @XmlElement String username;
    @XmlElement String password;
}
package com.vsaf.web;

import com.vsaf.service.LoginService;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ejb.Stateless;
import javax.xml.bind.annotation.*;
import javax.ejb.EJB;


// import java.util.List;

@Stateless
@Path("/auth")
public class LoginEndpoint {

	@EJB 
	LoginService logserv;
	



	@OPTIONS
	@Path("/login")
	public Response getOptions(){
		System.out.printf("Getopt login");
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
		.header("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With")
		.header("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
		.header("Access-Control-Max-Age", "3600")
		.build();
	}

	@POST
	@Path("/login")
	@Produces({ MediaType.TEXT_PLAIN })
	// public Response register(
	public Response register(LogRequestBody req) {
		System.out.printf("login name:" + req.username + "pwd + " + req.password);
		if(req.username != null && req.password != null) {
			int result = logserv.login(req.username, req.password);
			switch(result){
				case 0:
					//sucsess
					return Response.status(200).header("Access-Control-Allow-Origin", "*")
					.entity("login sucsessful").build();
					
				
				case 1:
					//faliure
					return Response.status(500)
					.entity("login failed").build();
					
				
				default:
					//failed to register for whatever reason
					return Response.status(500)
					.entity("login failed - code of error is " + result).build();
			}
		} else {
			return Response.status(500)
			.entity("login failed - make sure that both username and password are not empty\n").build();	
		}
	}

}

@XmlRootElement
final class LogRequestBody {
	@XmlElement String password;
    @XmlElement String username;
}

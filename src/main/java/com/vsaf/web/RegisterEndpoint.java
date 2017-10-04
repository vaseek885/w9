package com.vsaf.web;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ejb.*;
import javax.xml.bind.annotation.*;

// import java.util.List;

@Stateless
@Path("/auth")
public class RegisterEndpoint {
	@OPTIONS
	@Path("/register")
	public Response getOptions(){
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
		.header("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With")
		.header("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
		.header("Access-Control-Max-Age", "3600")
		.build();
	}
	

	@POST
	@Path("/register")
	@Produces({ MediaType.TEXT_PLAIN })
	// public Response register(
	public Response register(RequestBody req) {
		System.out.printf("Req name:" + req.username + "pwd + " + req.password);
		if(req.username != null && req.password != null) {
			// short result = regservice.register(username, password);
			// switch(result){
			// 	case 0:
			// 		//registered
					return Response.status(200).header("Access-Control-Allow-Origin", "*")
					.entity("User " + req.username + " has been sucsessfully registered with password " + req.password).build();
			// 		break;
				
			// 	case 1:
			// 		//already registered
			// 		return Response.status(500)
			// 		.entity("registration failed - username is taken").build();
			// 		break;
				
			// 	default:
			// 		//failed to register for whatever reason
			// 		return Response.status(500)
			// 		.entity("registration failed - code of error is " + result).build();
			// 		break;
				
			// }
			
		} else {
			return Response.status(500)
			.entity("registration failed - make sure that both username and password are not empty\n").build();	
		}
	}

}

@XmlRootElement
final class RequestBody {
	@XmlElement String password;
    @XmlElement String username;
}
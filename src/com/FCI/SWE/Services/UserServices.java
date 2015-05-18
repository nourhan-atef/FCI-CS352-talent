package com.FCI.SWE.Services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.FCI.SWE.Controller.Command;
import com.FCI.SWE.Models.User;
import com.FCI.SWE.ServicesModels.UserEntity;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

/**
 * This class contains REST services, also contains action function for web
 * application
 * 
 * @author nada essam 
 * @version 1.0
 * @since 2014-02-12
 *
 */
@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class UserServices {
	
	
	/*@GET
	@Path("/index")
	public Response index() {
		return Response.ok(new Viewable("/jsp/entryPoint")).build();
	}*/

	public Command c ;
	
	//c.excute();
		/**
	 * Registration Rest service, this service will be called to make
	 * registration. This function will store user data in data store
	 * 
	 * @param uname
	 *            provided user name
	 * @param email
	 *            provided user email
	 * @param pass
	 *            provided password
	 * @return Status json
	 */
	//refator
	@POST
	@Path("/RegistrationService")
	public static String registrationService(@FormParam("uname") String uname,
			@FormParam("email") String email, @FormParam("password") String pass) {
		UserEntity user = new UserEntity(uname, email, pass);
		if(user.saveUser())
		{
			JSONObject object = new JSONObject();
		    object.put("Status", "OK");
		    return object.toString();}
		    else{
			    JSONObject object = new JSONObject();
			    object.put("Status", "failed");
		         return object.toString();
		    }
		
	}
	
	
	@POST
	@Path("/excutemessageNotificationService")
	public String excuteService(String n) {
		
	    Command comand=new MessageNotification();
	    System.out.print("service");
	    return comand.excute(n);
		
	}
	@POST
	@Path("/excuteacceptNotificationService")
	public String excuteService2(String n) {
	    Command comand1=new FriendAcceptanceNotification();
	 return  comand1.excute(n);
		 
	}
	
	
	@POST
	@Path("/excutemessageNotificationService")
	public String excuteService(String n) {
		
	    Command comand=new MessageNotification();
	    System.out.print("service");
	    return comand.excute(n);
		
	}
	@POST
	@Path("/excuteacceptNotificationService")
	public String excuteService2(String n) {
	    Command comand1=new FriendAcceptanceNotification();
	 return  comand1.excute(n);
		 
	}
	
	
	@POST
	@Path("/SignOutService")
	public static String SignOutService() {
		
		User.setCurrentActiveUsernull();
		JSONObject object = new JSONObject();
		object.put("Status", "OK");
		return object.toString();
	}
	
	
	
	
	

	/**
	 * Login Rest Service, this service will be called to make login process
	 * also will check user data and returns new user from datastore
	 * @param uname provided user name
	 * @param pass provided user password
	 * @return user in json format
	 */
	@POST
	@Path("/LoginService")
	public static String loginService(@FormParam("uname") String uname,
			@FormParam("password") String pass) {
		JSONObject object = new JSONObject();
		UserEntity user = UserEntity.getUser(uname, pass);
		if (user == null) {
			object.put("Status", "Failed");

		} else {
			object.put("Status", "OK");
			object.put("name", user.getName());
			object.put("email", user.getEmail());
			object.put("password", user.getPass());
			object.put("id", user.getId());
		}
		return object.toString();

	}
	

	
	
	/**
	 * Action function to render sendrequest page, this function will be executed
	 * using url like this /rest/sendrequest
	 * 
	 * @return sendrequest page
	 */
	@POST
	@Path("/sendrequestService")
	public static String sendrequestService(@FormParam("uname") String uname) {
		JSONObject object = new JSONObject();
		UserEntity user = UserEntity.getFriend(uname);
		if (user == null) {
			object.put("Status", "Failed");

		} else {
			object.put("Status", "OK");
			object.put("name", user.getName());
			user.savefriendrequest(uname);
			
			
			
		}
		//return"successful sending friend request";
		return object.toString();   //after testing
	}
	
	/**
	 * Action function to render makefriend page, this function will be executed
	 * using url like this /rest/makefriend
	 * 
	 * @return makefriend page
	 */
	@POST
	@Path("/makefriendService")
	public static String makefriendService(@FormParam("uname")String uname, @FormParam("fname")String fname) {
		JSONObject object = new JSONObject();
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("request");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("fname").toString().equals(fname)&&entity.getProperty("name").toString().equals(uname)) {
				
				long key = entity.getKey().getId();
				Entity user_friendrequest = new Entity("request",key );
			       
				user_friendrequest.setProperty("fname",entity.getProperty("fname").toString());
				user_friendrequest.setProperty("name",entity.getProperty("name").toString());
				
				user_friendrequest.setProperty("status","accepted");
				
				
				datastore.put(user_friendrequest);
			}
		}

		return null;
		
		
	
	}
	
<<<<<<< HEAD
	/**
	 * Action function to render sendmessage page, this function will be executed
	 * using url like this /rest/sendmessage
	 * 
	 * @return sendmessage page
	 */
=======
	
	
	
	
	
>>>>>>> 531298b31204a14398cc27ecdf1a91c6e3fb4e55
	@POST
	@Path("/sendmessageService")
	public String sendrequestService(@FormParam("fname") String fname,@FormParam("message") String message) {
		JSONObject object = new JSONObject();
		UserEntity user1 = UserEntity.getFriend(fname);
		if (user1 == null) {
			object.put("Status", "Failed");

		} else {
			object.put("Status", "OK");
			object.put("fname", user1.getName());
			object.put("message", user1.getmessage());
			user1.savemessage(fname,message);
			
		}

		return "successful sending friend message";

	}	
	
	
	
<<<<<<< HEAD
	/**
	 * Action function to response to chatgroup request, This function will act as
	 * a controller part and it will calls chatgroupService to make
	 * message to your friends
	 * 
	 * @param fname1
	 *            provided friend name
	  * @param fname2
	 *            provided friend name
	 * @param fname3
	 *            provided friend name
	 * @param fname4
	 *            provided friend name
	 * @param fname5
	 *            provided friend name
	 * @param conversationname
	 *            provided conversationname
	 
	 * @return Status string
	 */

	@POST
	@Path("/chatgroupService")
	public static String chatgroupService(@FormParam("fname1") String fname1,
=======
	
	@POST
	@Path("/chatgroupService")
	public String chatgroupService(@FormParam("fname1") String fname1,
>>>>>>> 531298b31204a14398cc27ecdf1a91c6e3fb4e55
			@FormParam("fname2") String fname2,@FormParam("fname3") String fname3,
			@FormParam("fname4") String fname4,@FormParam("fname5") String fname5,
			@FormParam("conversationname") String conversationname) {
		JSONObject object = new JSONObject();
	UserEntity user2 = UserEntity.getFriend(fname1);
	 user2 = UserEntity.getFriend(fname2);
     user2 = UserEntity.getFriend(fname3);
     user2 = UserEntity.getFriend(fname4);
     user2 = UserEntity.getFriend(fname5);
     user2 = UserEntity.getFriend(conversationname);
		if (user2 == null) {
			object.put("Status", "Failed");

		} else {
			object.put("Status", "OK");
			object.put("fname1", user2.getName());
			object.put("fname2", user2.getName());
			object.put("fname3", user2.getName());
			object.put("fname4", user2.getName());
			object.put("fname5", user2.getName());
			object.put("conversationname", user2.getconversationname());
		    System.out.println("noura");
			user2.savechatgroup(fname1,fname2,fname3,fname4,fname5,conversationname);
			
		}

<<<<<<< HEAD
		//return "successful chat group";
		return object.toString();
=======
		return "successful chat group";
>>>>>>> 531298b31204a14398cc27ecdf1a91c6e3fb4e55

	}
	
	
	
<<<<<<< HEAD
	/**
	 * Action function to response to writeMessage request, This function will act as
	 * a controller part and it will calls writeMessageService to make
	 * message
	 * 
	 * @param message
	 *            provided message
	  
	 * @param conversationname
	 *            provided conversationname
	 
	 * @return Status string
	 */
	@POST
	@Path("/writeMessageService")
	public static String writeMessageService(
=======
	
	@POST
	@Path("/writeMessageService")
	public String writeMessageService(
>>>>>>> 531298b31204a14398cc27ecdf1a91c6e3fb4e55
			@FormParam("message") String message,
			@FormParam("conversationname") String conversationname) {
		JSONObject object = new JSONObject();
	UserEntity user2 = UserEntity.getFriend(message);
     user2 = UserEntity.getFriend(conversationname);
		if (user2 == null) {
			object.put("Status", "Failed");

		} else {
			object.put("Status", "OK");
			object.put("message", user2.getmessage());
			object.put("conversationname", user2.getconversationname());
			user2.savewriteMessage(message,conversationname);
			
		}
<<<<<<< HEAD
	//	return "successful chat group";
		return object.toString();
=======
		return "successful chat group";
>>>>>>> 531298b31204a14398cc27ecdf1a91c6e3fb4e55

	}

}
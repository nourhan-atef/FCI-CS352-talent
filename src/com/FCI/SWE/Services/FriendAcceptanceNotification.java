package com.FCI.SWE.Services;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;

import com.FCI.SWE.Controller.Command;
import com.FCI.SWE.Models.User;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class FriendAcceptanceNotification extends Command {

	public String excute(String n){
		
		
		Vector<String> v=new Vector<String> ();
		
		JSONObject object = new JSONObject();
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
       User uname=User.getCurrentActiveUser();
		Query gaeQuery = new Query("request");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("fname").toString().compareTo(uname.getName()) ==0 &&entity.getProperty("status").toString().compareTo("bending") ==0) {
				System.out.println(entity.getProperty("fname").toString());
				long key = entity.getKey().getId();
				Entity user_friendrequest = new Entity("request",key );
			       
				 String fname=entity.getProperty("name").toString();
				// JSONObject obj = new JSONObject();
				// obj.put("name", fname);
				 v.add(fname);
	       }
       }
	   System.out.println(v.toString());
	   return v.toString();
	}
				 
				 
}

		
		
		
		


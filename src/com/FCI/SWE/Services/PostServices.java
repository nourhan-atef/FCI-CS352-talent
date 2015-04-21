package com.FCI.SWE.Services;
import java.util.Vector;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.FCI.SWE.Models.Post;
import com.FCI.SWE.Models.User;
import com.FCI.SWE.ServicesModels.PostEntity;
import com.FCI.SWE.ServicesModels.UserEntity;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;



@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class PostServices {

		@POST
		@Path("/createpostService")
		public String createpostService(@FormParam("postcontent") String postcontent, 
				@FormParam("pagename") String pagename,
				@FormParam("feeling") String feeling
				,@FormParam("postprivacy") String postprivacy) throws JSONException {
			PostEntity post = new PostEntity(postcontent,"","",feeling,pagename,postprivacy);
			
			post.savePost(postcontent, pagename,feeling,postprivacy);
			JSONObject object = new JSONObject();
			object.put("Status", "OK");
			return object.toString();
		}
		
		/*
		@POST
		@Path("/showpostService")
		public String showpostService(){
			//Entity user_friendrequest = new Entity("request",key ); 
			Vector<String> v=new Vector<String> ();
			JSONObject object = new JSONObject();
			DatastoreService datastore = DatastoreServiceFactory
					.getDatastoreService();
	       User uname=User.getCurrentActiveUser();
			Query gaeQuery = new Query("post");
			PreparedQuery pq = datastore.prepare(gaeQuery);
			for (Entity entity : pq.asIterable()) {
				long key = entity.getKey().getId();    
			     String ownername=entity.getProperty("ownername").toString();
			     String time=entity.getProperty("time").toString();
			     String postcontent=entity.getProperty("postcontent").toString();
			     
					 v.add(ownername);//+" "+time+"  "+postcontent);
		       }
	       
		   System.out.println(v.toString());
		   return v.toString();
		}*/
			
		
		@POST
		@Path("/shareService")
		public String shareService(@FormParam("ownername") String ownername,@FormParam("time") String time) throws JSONException {
			JSONObject object = new JSONObject();
			PostEntity post = Post.getPost(ownername, time);
			if (post == null) {
				object.put("Status", "Failed");

			} else {
			
				post.savePost(post.getpostcontent(),"","","");
		
				
				
			}

			return "successful share";

		}	


		@POST
		@Path("/showpostService")
		public String showpostService() throws JSONException  {
			System.out.print("service");
	        Vector<String> v=new Vector<String> ();
			JSONObject object = new JSONObject();
			DatastoreService datastore = DatastoreServiceFactory
					.getDatastoreService();
	       User uname=User.getCurrentActiveUser();
			Query gaeQuery = new Query("post");
			PreparedQuery pq = datastore.prepare(gaeQuery);
			for (Entity entity : pq.asIterable()) {
				if (entity.getProperty("ownername").toString().compareTo(uname.getName()) ==0 ){
					long key = entity.getKey().getId();
					Entity user = new Entity("post",key );
					 String postcontent=entity.getProperty("postcontent").toString();
					 JSONObject obj = new JSONObject();
					 obj.put("postcontent", postcontent);
					// v.add(obj.toString());
					 v.add(entity.getProperty("postcontent").toString());
		       }
	       }
		   System.out.println("timeline"+v.toString());
		   return v.toString();
	}
		
		@POST
		@Path("/timelineService")
		public String timelineService() throws JSONException  {
	        Vector<String> v=new Vector<String> ();
			JSONObject object = new JSONObject();
			DatastoreService datastore = DatastoreServiceFactory
					.getDatastoreService();
	       User uname=User.getCurrentActiveUser();
			Query gaeQuery = new Query("post");
			PreparedQuery pq = datastore.prepare(gaeQuery);
			for (Entity entity : pq.asIterable()) {
				if (entity.getProperty("ownername").toString().compareTo(uname.getName()) ==0 ){
					long key = entity.getKey().getId();
					Entity user = new Entity("post",key );
					 String postcontent=entity.getProperty("postcontent").toString();
					 JSONObject obj = new JSONObject();
					 obj.put("postcontent", postcontent);
					// v.add(obj.toString());
					 v.add(entity.getProperty("postcontent").toString());
		       }
	       }
		   System.out.println("timeline"+v.toString());
		   return v.toString();
	}
		
		@POST
		@Path("/hashtagtrendsService")
		public String hashtagstatisticService() throws JSONException  {
			JSONObject object = new JSONObject();
			DatastoreService datastore = DatastoreServiceFactory
					.getDatastoreService();
           Vector<String>vec=new Vector<String>();
           Vector<Integer>vecnum=new Vector<Integer>();
           Vector<Integer>countvec=new Vector<Integer>();
           Vector<Integer>vecnum1=new Vector<Integer>();
           Vector<String>result=new Vector<String>();
           Vector<String>vecword=new Vector<String>();
			Query gaeQuery = new Query("post");
			String hash="";
			PreparedQuery pq = datastore.prepare(gaeQuery);
			for (Entity entity : pq.asIterable()) {
				if (entity.getProperty("postcontent").toString().indexOf("#")!=-1) {
					int index=entity.getProperty("postcontent").toString().indexOf("#");
					String postconent=entity.getProperty("postcontent").toString();
					while(postconent.charAt(index)!=' '){
						hash+=postconent.charAt(index);
						index++;
						
					}
					vec.add(hash);
			    }
			}
			int temp=2;
			for(int i=0;i<vec.size();i++){
				int count=1;
				for(int j=i+1;j<vec.size();j++){
					if(vec.get(i)==vec.get(j))
					{
						count++;
					}
				}
				for(int n=0;n<vecword.size();n++){
					if(vecword.get(n)!=vec.get(i))
					{
					 temp=0;
					}
					else{
						temp=-1;
					}
					if(temp==0){
						vecword.add(vec.get(i));
						vecnum.add(count);
						vecnum1.add(count);
					}
				}
			}
			
			
			for(int n=0;n<vecnum1.size();n++){
				for(int h=n+1;h<vecnum1.size();h++){
				    	if(vecnum1.get(n)>vecnum1.get(h)){
				    		int temp1=vecnum1.get(n);
				    		vecnum1.set(n,vecnum1.get(h));
				    		vecnum1.set(h,temp);
				    	
				    	}
				}
			}
			if(vecnum1.size()<10){
				 System.out.println("hashtage"+vecword.toString());
				   return vecword.toString();
			}
			else{
				for(int n=0;n<10;n++){
					 int in=vecnum1.get(n);	
					for(int h=n+1;h<vecnum.size();h++){
					   if(in==vecnum.get(h)){
						   String value=vecword.get(h);
						   result.add(value);
						  }
					}
				}
				System.out.println("hashtage"+result.toString());
				 return result.toString();
			}
			
		}
		
		@POST
		@Path("/hashtagstatisticService")
		public String hashtagstatisticService(@FormParam("hashtagname") String hashtagname) throws JSONException  {
			JSONObject object = new JSONObject();
			//PostEntity post = Post.getpost(hashtagname);
			
			DatastoreService datastore = DatastoreServiceFactory
					.getDatastoreService();
           Vector<String>vec=new Vector<String>();
			Query gaeQuery = new Query("post");
			PreparedQuery pq = datastore.prepare(gaeQuery);
			for (Entity entity : pq.asIterable()) {
				if (entity.getProperty("postcontent").toString().indexOf(hashtagname)!=-1) {
					vec.add(entity.getProperty("postcontent").toString());
				}
			}
			if (vec.size() == 0) {
				object.put("Status", "Failed");

			} 
			
			else {
				String s = "";
				s=Integer.toString(vec.size());
				//getInteger(s,vec.size());
				  int size=vec.size();
				  vec.add(s);
				  System.out.println("size"+size);
				  System.out.println(vec.toString());
				   return vec.toString();
			}

			return "successful share";

		}
				 
 	
}
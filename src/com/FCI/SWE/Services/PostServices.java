package com.FCI.SWE.Services;
import java.util.Vector;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

<<<<<<< HEAD
import org.json.simple.JSONArray;

=======
>>>>>>> 531298b31204a14398cc27ecdf1a91c6e3fb4e55
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


<<<<<<< HEAD
/**
 * This class contains REST services, also contains action function for web
 * application
 * 
 * @author hoda tawakl
 * @version 1.0
 * @since 2014-02-12
 *
 */
@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class PostServices {
	/**
	 * Action function to response to createpost request, This function will act as
	 * a controller part and it will calls createpostService to create
	 * post
	 * 
	 * @param postcontent
	 *            provided post content
	 * @param feeling
	 *            provided feeling
	 * @param postprivacy
	 *            provided post privacy
	 * @param pagename
	 *            provided page name
	 * @return Status string
	 */
@POST
@Path("/createpostService")
public static String createpostService(@FormParam("postcontent") String postcontent, 
		@FormParam("pagename") String pagename,
		@FormParam("feeling") String feeling
		,@FormParam("postprivacy") String postprivacy) throws JSONException {
	PostEntity post = new PostEntity(postcontent,"","",feeling,pagename,postprivacy);
	
	post.savePost(postcontent, pagename,feeling,postprivacy);
	JSONObject object = new JSONObject();
	object.put("Status", "OK");
	return object.toString();
}

/**
 * Action function to response to showpost request, This function will act as
 * a controller part and it will calls showpostService to show
 * all posts
 
 */
@POST
@Path("/showpostService")
public static String showpostService() throws JSONException{
	/* Vector<String> v=new Vector<String> ();
	JSONObject object = new JSONObject();
	DatastoreService datastore = DatastoreServiceFactory
			.getDatastoreService();
   User uname=User.getCurrentActiveUser();
	Query gaeQuery = new Query("post");
	PreparedQuery pq = datastore.prepare(gaeQuery);
	JSONArray arr=new JSONArray(); 
	for (Entity entity : pq.asIterable()) {
		if (entity.getProperty("ownername").toString().compareTo(uname.getName()) ==0 ){
			long key = entity.getKey().getId();
			Entity user = new Entity("post",key );
			 String postcontent=entity.getProperty("postcontent").toString();
			 JSONObject obj = new JSONObject();
			 obj.put("postcontent", postcontent);
			// v.add(obj.toString());
			 v.add(entity.getProperty("postcontent").toString());
			arr.add(obj);
       }*/
	//Entity user_friendrequest = new Entity("request",key ); 
	Vector<PostEntity> v=new Vector<PostEntity> ();
	JSONObject object = new JSONObject();
	DatastoreService datastore = DatastoreServiceFactory
			.getDatastoreService();
   User uname=User.getCurrentActiveUser();
	Query gaeQuery = new Query("post");
	PreparedQuery pq = datastore.prepare(gaeQuery);
	JSONArray arr=new JSONArray(); 
	 JSONObject obj = new JSONObject();

	for (Entity entity : pq.asIterable()) {
		long key = entity.getKey().getId();    
	     
	     String postprivacy="";
	     String feeling="";
	     String pagename="";
	     String likes="";
	     String seen="";
	     String time="";
	     String postcontent="";
	     String ownername="";
	     if(entity.getProperty("ownername") !=null)
	      ownername=entity.getProperty("ownername").toString();
	     if(entity.getProperty("time") !=null)
	     time=entity.getProperty("time").toString();
	     //System.out.print(ownername);
	     
		if(entity.getProperty("postcontent") !=null)
	         postcontent=entity.getProperty("postcontent").toString();
	     if(entity.getProperty("postprivacy") !=null)
	        postprivacy=entity.getProperty("postprivacy").toString();
	     
	     if(entity.getProperty("feeling") !=null)
	    feeling=entity.getProperty("feeling").toString();
	    // System.out.print(feeling);
	     if(entity.getProperty("pagename") !=null)
	    pagename=entity.getProperty("pagename").toString();
	     //System.out.print(pagename);
	     if(entity.getProperty("likes") !=null)
	        likes=entity.getProperty("likes").toString();
	     if(entity.getProperty("seen") !=null)
	        seen=entity.getProperty("seen").toString();
	     PostEntity p=new PostEntity(postcontent,time,ownername,feeling,pagename,postprivacy);
		 obj.put("postcontent", postcontent);
	     if(postprivacy.equals("public")){
			 v.add(p);
			 System.out.print("hoda");
			 PostEntity.saveseen(ownername,  postcontent, time, postprivacy, feeling, pagename, likes, seen );
	     }
	     else if (postprivacy.equals("private")){
	    	 System.out.print("nourhan");
	    	 Query gaeQuery1 = new Query("request");
			PreparedQuery pq1 = datastore.prepare(gaeQuery1);
			for (Entity entity1 : pq1.asIterable()) {
					long key1 = entity1.getKey().getId();
					String name=entity1.getProperty("name").toString();
				    String fname=entity1.getProperty("fname").toString();
				    String status=entity1.getProperty("status").toString();

					if((status.equals("accept"))&&(name.equals(uname)||fname.equals(uname))&&
							(name.equals(ownername)||fname.equals(ownername))){
						 v.add(p);
						 PostEntity.saveseen(ownername,  postcontent, time, postprivacy, feeling, pagename, likes, seen );	
					}
			} 
	     }
	     else if ((!postprivacy.equals("private"))&&(!postprivacy.equals("public"))){
	    	 System.out.print("nada");
	    	 if(postprivacy.indexOf(uname.getName())==-1){
	    		 if(p!=null){
	    		 v.add(p);
	    		 }
	    		 PostEntity.saveseen(ownername,  postcontent, time, postprivacy, feeling, pagename, likes, seen );
	    	 }
	     } 
	     else{
	    	 System.out.print("nada");
	     }
	 
       }
	
   System.out.println(v.toString());
   return v.toString();
}
	
/**
 * Action function to response to share request, This function will act as
 * a controller part and it will calls shareService to create
 * post
 * 
 * @param ownername
 *            provided owner name
 * @param time
 *            provided time
 * @return Status string
 */
@POST
@Path("/shareService")
public static String shareService(@FormParam("ownername") String ownername,@FormParam("time") String time) throws JSONException {
	JSONObject object = new JSONObject();
	PostEntity post = Post.getPost(ownername, time);
	if (post == null) {
		object.put("Status", "Failed");

	} else {
	
		post.savePost(post.getpostcontent(),"","","");
		object.put("Status", "ok");
		
		
	}

	//return "successful share";
	return object.toString();

}	


/**
 * Action function to response to timeline_data request, This function will act as
 * a controller part and it will calls timelineService to show
 * your posts only
 
 */
@POST
@Path("/timelineService")
public static String timelineService() throws JSONException  {
    Vector<String> v=new Vector<String> ();
	JSONObject object = new JSONObject();
	DatastoreService datastore = DatastoreServiceFactory
			.getDatastoreService();
   User uname=User.getCurrentActiveUser();
	Query gaeQuery = new Query("post");
	PreparedQuery pq = datastore.prepare(gaeQuery);
	JSONArray arr=new JSONArray(); 
	for (Entity entity : pq.asIterable()) {
		if (entity.getProperty("ownername").toString().compareTo(uname.getName()) ==0 ){
			long key = entity.getKey().getId();
			Entity user = new Entity("post",key );
			 String postcontent=entity.getProperty("postcontent").toString();
			 JSONObject obj = new JSONObject();
			 obj.put("postcontent", postcontent);
			// v.add(obj.toString());
			 v.add(entity.getProperty("postcontent").toString());
			arr.add(obj);
       }
		
   }
	 
	 
   System.out.println("timeline"+v.toString());
	   return v.toString();
}
/**
 * Action function to response to hashtagtrends1 request, This function will act as
 * a controller part and it will calls hashtagtrends1 to check on hash name found or not 
 */	
@POST
@Path("/hashtagtrendsService")
public String hashtagstatisticService() throws JSONException  {
	JSONObject object = new JSONObject();
	System.out.print("hashtrendsservice");
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
			while(postconent.charAt(index)!=' ' ||index<postconent.length()){
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
/**
 * Action function to response to hashtagtrends1 request, This function will act as
 * a controller part and it will calls hashtagtrends1 to return the most 10 hash tage 
 */
@POST
@Path("/hashtagstatisticService")
public static String hashtagstatisticService(@FormParam("hashtagname") String hashtagname) throws JSONException  {
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
		object.put("Status", "ok");
		
		  int size=vec.size();
		  vec.add(s);
		  System.out.println("size"+size);
		  System.out.println(vec.toString());
		   return vec.toString();
	}

	//return "successful share";
	 return vec.toString();

		}
=======

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
				 
 	
>>>>>>> 531298b31204a14398cc27ecdf1a91c6e3fb4e55
}
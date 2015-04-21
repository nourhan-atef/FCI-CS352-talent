package com.FCI.SWE.Models;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.FCI.SWE.ServicesModels.PostEntity;
import com.FCI.SWE.ServicesModels.UserEntity;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class Post {
	private static String ownername;
	private static String postcontent;
	private static String time;	
	private static String hashtagname;
	private static String feeling;
	private static String postprivacy;
	private static Page page;
    private Post(String postcontent,String  feeling) {
			this.postcontent = postcontent;
			this.feeling=feeling;
	}
    private void setpostprivacy(String postprivacy) {
		this.postprivacy = postprivacy;
    }
    private void sethashtagname(String hashtagname) {
		this.hashtagname = hashtagname;
    }
    private void setownername(String ownername) {
		this.ownername = ownername;
    }
		
    private void setpostfeeling(String feeling){
			this.feeling = feeling;
	}
    private void setpostcontent(String postcontent){
		this.postcontent = postcontent;
   }
    
    private void settime(String postcontent){
		this.time = time;
    }
    public static  String gettime() {
	     return time;
    }
    public static  String getownername() {
		return ownername;
    }
    
   public static  String gethashtagname() {
		return hashtagname;
   }
	
	public static String getpostcontent() {
			return postcontent;
	}
	public static String getpostfeeling() {
		return feeling;
   }
	
	public static  String getpostprivacy() {
		return postprivacy;
}
	
	public static PostEntity getPost(String ownername, String time) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("post");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("ownername").toString().compareTo(ownername)==0){
					//&& entity.getProperty("time").toString().equals(time)) {
				PostEntity returnedPost = new PostEntity(entity.getProperty("postcontent").toString(),
						entity.getProperty("time").toString(),
				entity.getProperty("ownername").toString(),
				entity.getProperty("feeling").toString(),entity.getProperty("pagename").toString(),
				entity.getProperty("postprivacy").toString());
				System.out.print("ll   "+ownername);	
				return returnedPost;
			}
		}

		return null;
	}
	
	
	
	/*public static Post getPost(String json) {

		JSONParser parser = new JSONParser();
		try {
			JSONObject object = (JSONObject) parser.parse(json);
			Post post = new Post(object.get("postcontent").toString());
			//post.setId(Long.parseLong(object.get("id").toString()));
			return post;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}*/

	
	
	
	

}


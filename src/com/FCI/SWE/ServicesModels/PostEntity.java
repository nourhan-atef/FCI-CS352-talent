package com.FCI.SWE.ServicesModels;

import java.sql.Date;
import java.util.List;

import javax.ws.rs.FormParam;

import com.FCI.SWE.Models.User;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class PostEntity {
	private  String postcontent;
	private static String ownername;
	private String time;
	private long ID;
	private static String hashtagname;
	private static String feeling;
	private static String pagename;
	private static String postprivacy;
    public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public PostEntity(String postcontent, String time, String ownername,String feeling,String  pagename,String postprivacy) {
			this.postcontent = postcontent;
			this.time = time;
			this.ownername = ownername;
			this.feeling = feeling;
			this.pagename=pagename;
			this.postprivacy=postprivacy;
	}
		
    public static String getFeeling() {
		return feeling;
	}

	public static void setFeeling(String feeling) {
		PostEntity.feeling = feeling;
	}

	private void setownername(String ownername) {
		this.ownername = ownername;
    }
		
    private void setpostcontent(String postcontent){
			this.postcontent = postcontent;
	}
    
    private void settime(String postcontent){
		this.time = time;
    }
    public   String gettime() {
	     return time;
    }
    public static   String getownername() {
		return ownername;
}
	
	public static String getpagename() {
		return pagename;
	}

	public static void setpagename(String pagename) {
		PostEntity.pagename = pagename;
	}

	public static String getpostprivacy() {
		return postprivacy;
	}

	public static void setpostprivacy(String postprivacy) {
		PostEntity.postprivacy = postprivacy;
	}

	public  String getpostcontent() {
			return postcontent;
	}
	

	public Boolean savePost(String postcontent, String pagename,String feeling,String postprivacy) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("post");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity post = new Entity("post", list.size() + 1);

		post.setProperty("ownername",User.getCurrentActiveUser().getName());
		post.setProperty("postcontent",postcontent);
		post.setProperty("likes",0);
		post.setProperty("seen",0);
		post.setProperty("feeling",feeling);
		post.setProperty("postprivacy",postprivacy);
		post.setProperty("pagename",pagename);
		System.out.print("feeling"+feeling);
		
	//	post.setProperty("time",Date.getTime());
		
		datastore.put(post);

		return true;

	}

	
	

}

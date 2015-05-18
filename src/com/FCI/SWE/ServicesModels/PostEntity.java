package com.FCI.SWE.ServicesModels;

import java.sql.Date;
import java.util.List;
<<<<<<< HEAD
import java.util.Vector;

import javax.ws.rs.FormParam;

import org.json.simple.JSONArray;

=======

import javax.ws.rs.FormParam;

>>>>>>> 531298b31204a14398cc27ecdf1a91c6e3fb4e55
import com.FCI.SWE.Models.User;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
<<<<<<< HEAD
import com.google.appengine.labs.repackaged.org.json.JSONObject;
/**
 * <h1>Post Entity class</h1>
 * <p>
 * This class will act as a model for user, it will holds user data
 * </p>
 *
 * @author noura azzam
 * @version 1.0
 * @since 2014-02-12
 */
=======

>>>>>>> 531298b31204a14398cc27ecdf1a91c6e3fb4e55
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
<<<<<<< HEAD
	/**
	 * Constructor accepts PostEntity data
	 * 
	 * @param postcontent
	 *            post content
	 * @param feeling
	 *            feeling
	 * @param time
	 *            time
	 * @param postprivacy
	 *             post privacy
	 * @param pagename
	 *             page name
	 */
=======

>>>>>>> 531298b31204a14398cc27ecdf1a91c6e3fb4e55
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
	
<<<<<<< HEAD
	/**
	 * This method will be used to save Post object in datastore
	 * 
	 * @return boolean if post is saved correctly or not
	 */
=======

>>>>>>> 531298b31204a14398cc27ecdf1a91c6e3fb4e55
	public Boolean savePost(String postcontent, String pagename,String feeling,String postprivacy) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("post");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity post = new Entity("post", list.size() + 1);
<<<<<<< HEAD
=======

>>>>>>> 531298b31204a14398cc27ecdf1a91c6e3fb4e55
		post.setProperty("ownername",User.getCurrentActiveUser().getName());
		post.setProperty("postcontent",postcontent);
		post.setProperty("likes",0);
		post.setProperty("seen",0);
		post.setProperty("feeling",feeling);
		post.setProperty("postprivacy",postprivacy);
		post.setProperty("pagename",pagename);
<<<<<<< HEAD
=======
		System.out.print("feeling"+feeling);
		
>>>>>>> 531298b31204a14398cc27ecdf1a91c6e3fb4e55
	//	post.setProperty("time",Date.getTime());
		
		datastore.put(post);

		return true;

	}
<<<<<<< HEAD
	/**
	 * This method will be used to save seen object in datastore
	 * 
	 * @return boolean if post is saved correctly or not
	 */
	public static Boolean saveseen(String ownername, String postcontent, String time, String postprivacy, String feeling, String  pagename, String likes, String seen){
		 User uname=User.getCurrentActiveUser();
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery1 = new Query("post");
		PreparedQuery pq1 = datastore.prepare(gaeQuery1);
		List<Entity> list1 = pq1.asList(FetchOptions.Builder.withDefaults());
		Entity post1 = new Entity("post", list1.size() + 1);
		Query gaeQuery = new Query("seenpost");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		for (Entity entity : pq.asIterable()) {
			long key = entity.getKey().getId();
			String seenname=entity.getProperty("seenname").toString();
		    String postcontent1=entity.getProperty("postcontent").toString();
		    String time1=entity.getProperty("time").toString();
		    String ownername1=entity.getProperty("ownername").toString();
			if(seenname.equals(uname.getName())&&postcontent1.equals(postcontent)&&time1.equals(time)&&
					ownername1.equals(ownername)){
				return null;
				
			}
			else{
				Entity post = new Entity("seenpost", list.size() + 1);
				post.setProperty("seenname",User.getCurrentActiveUser().getName());
				post.setProperty("postcontent",postcontent);
				post.setProperty("time",time);
				post.setProperty("ownername",ownername);
				datastore.put(post);
				for (Entity entity1 : pq1.asIterable()) {
					long key1 = entity1.getKey().getId();
					String ownername2=entity.getProperty("ownername").toString();
				    String postcontent2=entity.getProperty("postcontent").toString();
				    String time2=entity.getProperty("time").toString();
					if(postcontent2.equals(postcontent)&&time2.equals(time)&&
							ownername2.equals(ownername)){
						int countSeen=Integer.parseInt(seen)+1;
						String s = Integer.toString(countSeen);
						post1.setProperty("ownername",ownername);
						post1.setProperty("postcontent",postcontent);
						post1.setProperty("seen",s);
						post1.setProperty("feeling",feeling);
						post1.setProperty("postprivacy",postprivacy);
						post1.setProperty("pagename",pagename);
						post1.setProperty("likes",likes);
						post1.setProperty("time",time);
						datastore.put(post);

						
					}
				}
				return true;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return "PostEntity [postcontent=" + postcontent + ", time=" + time
				+ ", ID=" + ID + "]";
	}
	
=======

	
	

>>>>>>> 531298b31204a14398cc27ecdf1a91c6e3fb4e55
}

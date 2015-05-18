package com.FCI.SWE.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.FCI.SWE.Models.Page;
import com.FCI.SWE.Models.Post;
import com.FCI.SWE.Models.User;
import com.FCI.SWE.ServicesModels.PageEntity;
import com.FCI.SWE.ServicesModels.PostEntity;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
/**
 * This class contains REST services, also contains action function for web
 * application
 * 
 * @author Mohamed Samir
 * @version 1.0
 * @since 2014-02-12
 *
 */
@Path("/")
@Produces("text/html")

public class PostController {
	/**
	 * Action function to render createpost page, this function will be executed
	 * using url like this /rest/createpost
	 * 
	 * @return createpost page
	 */
	@GET
	@Path("/createpost")
	public Response createpost() {
		return Response.ok(new Viewable("/jsp/createpost")).build();
	}
	/**
	 * Action function to render hashtagstatistic page, this function will be executed
	 * using url like this /rest/hashtagstatistic
	 * 
	 * @return hashtag statistic page
	 */
	@GET
	@Path("/hashtagstatistic")
	public Response hashtagstatistic() {
		return Response.ok(new Viewable("/jsp/hashtagstatistic")).build();
	}
	/**
	 * Action function to render hashtagtrends page, this function will be executed
	 * using url like this /rest/hashtagtrends
	 * 
	 * @return hashtag trends page
	 */
	@GET
	@Path("/hashtagtrends")
	public Response hashtagtrends() {
		System.out.print("hashtrendscotroller1");
		return Response.ok(new Viewable("/jsp/hashtagtrends")).build();
	}
	/**
	 * Action function to render share page, this function will be executed
	 * using url like this /rest/share
	 * 
	 * @return share page
	 */
	@GET
	@Path("/share")
	public Response share() {
		return Response.ok(new Viewable("/jsp/share")).build();
	}
	
	/**
	 * Action function to render showpost page, this function will be executed
	 * using url like this /rest/showpost
	 * 
	 * @return showpost page
	 */
	@GET
	@Path("/showpost")
	public Response showpost() {
		System.out.print("showpost");
		return Response.ok(new Viewable("/jsp/showpost")).build();
	}
	
	/**
	 * Action function to render timeline page, this function will be executed
	 * using url like this /rest/timeline
	 * 
	 * @return timeline page
	 */
	@GET
	@Path("/timeline")
	public Response timeline() {
		return Response.ok(new Viewable("/jsp/timeline")).build();
	}
	/**
	 * Action function to response to timeline_data request, This function will act as
	 * a controller part and it will calls timelineService to show
	 * your posts only
	 
	 */
	
	@GET
	@Path("/timeline_data")
	@Produces("text/html")
	public Response timeline1() {
		
		String retJson = Connection.connect(
				"http://localhost:8888/rest/timelineService","",
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");
	
		System.out.println("retJson: "+retJson);

			Map<String, String> map = new HashMap<String, String>();
			map.put("list", retJson);
			
			return Response.ok(new Viewable("/jsp/timeline",map)).build();

	}
	/**
	 * Action function to response to showpost request, This function will act as
	 * a controller part and it will calls showpostService to show
	 * all posts
	 
	 */
	
	@POST
	@Path("/showpost")
	@Produces("text/html")
	public Response showpost1() {
		String retJson = Connection.connect(
				"http://localhost:8888/rest/showpostService","",
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");
		System.out.println("retJson: "+retJson);
			Map<String, String> map = new HashMap<String, String>();
			map.put("list", retJson);
			
			return Response.ok(new Viewable("/jsp/showpost",map)).build();
	}
	
	/**
	 * Action function to response to hashtagtrends1 request, This function will act as
	 * a controller part and it will calls hashtagtrends1 to check on hash name found or not 
	 */
	@POST
	@Path("/hashtagtrends1")
	@Produces("text/html")
	public Response hashtagtrends1() {
		System.out.print("hashtrendscotroller");
		String retJson = Connection.connect(
				"http://localhost:8888/rest/hashtagtrendsService","",
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");

		JSONParser parser = new JSONParser();
		Object obj;
		try {	
			Map<String, String> map = new HashMap<String, String>();
			return Response.ok(new Viewable("/jsp/hashtagtrends",map)).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Action function to response to hashtagtrends1 request, This function will act as
	 * a controller part and it will calls hashtagtrends1 to return the most 10 hash tage 
	 */
	@POST
	@Path("/hashtagstatistic")
	@Produces("text/html")
	public Response hashtagstatistic(@FormParam("hashtagname") String hashtagname) {
	
		String urlParameters = "hashtagname=" + hashtagname;
		
		String retJson = Connection.connect(
				"http://localhost:8888/rest/hashtagstatisticService", urlParameters,
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");

		JSONParser parser = new JSONParser();
		Object obj;
		try {	
			Map<String, String> map = new HashMap<String, String>();
			map.put("hashtagname", retJson);
			return Response.ok(new Viewable("/jsp/timeline",map)).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
	
	/*@POST
	@Path("/showpost1")
	@Produces("text/html")
	public Response showpost() {
		
        User user= User.getCurrentActiveUser();
		String urlParameters = user.getName();
		String retJson = Connection.connect(
				"http://localhost:8888/rest/showpostService", urlParameters,
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");

		JSONParser parser = new JSONParser();
		Object obj;
		try {	
			Map<String, String> map = new HashMap<String, String>();
			map.put("name", retJson);
			return Response.ok(new Viewable("/jsp/share",map)).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}*/


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
	@Path("/createpost")
	@Produces("text/html")
	public Response createpost(@FormParam("postcontent") String postcontent,@FormParam("pagename") String pagename
			,@FormParam("feeling") String feeling, @FormParam("postprivacy") String postprivacy) {
		String urlParameters = "&postcontent=" + postcontent + "&pagename=" + pagename+
				"&feeling=" + feeling +"&postprivacy=" + postprivacy;

		String retJson = Connection.connect(
				"http://localhost:8888/rest/createpostService", urlParameters,
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");
        
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("Failed"))
				return null;
			Map<String, String> map = new HashMap<String, String>();
			//Page Page = Page.getPage(pagename);
			map.put("pagename",Page.getPagename());
			map.put("postcontent", Post.getpostcontent());
			map.put("feeling", Post.getpostfeeling());
			map.put("hashtagname", Post.gethashtagname());
			map.put("pagename",Post.getpostprivacy());
			return Response.ok(new Viewable("/jsp/hello", map)).build();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

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
	@Path("/share")
	@Produces("text/html")
	public Response share(@FormParam("ownername") String ownername ,@FormParam("time")String time) {
		String urlParameters = "ownername=" + ownername 
				+"&time=" + time;
		String retJson = Connection.connect(
				"http://localhost:8888/rest/shareService", urlParameters,
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");

		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("Failed"))
				return null;
			Map<String, String> map = new HashMap<String, String>();
			map.put("ownername", PostEntity.getownername());
			map.put("time", Post.gettime());
			return Response.ok(new Viewable("/jsp/hello", map)).build();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}
	
	
		
	

}

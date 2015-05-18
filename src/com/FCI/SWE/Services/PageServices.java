package com.FCI.SWE.Services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

import com.FCI.SWE.Models.Page;
import com.FCI.SWE.ServicesModels.PageEntity;
import com.FCI.SWE.ServicesModels.PostEntity;
import com.FCI.SWE.ServicesModels.UserEntity;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.labs.repackaged.org.json.JSONException;

/**
 * This class contains REST services, also contains action function for web
 * application
 * 
 * @author nourhan atef
 * @version 1.0
 * @since 2014-02-12
 *
 */
@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class PageServices {
	/**
	 * Action function to response to createpage request, This function will act as
	 * a controller part and it will calls createpageServicee to create page
	 * 
	 * @param pagename
	 *            provided page name
	 * @param type
	 *            provided type
	 * @param category
	 *            provided category
	 * @return String
	 */
	@POST
	@Path("/createpageServicee")
	public static String createpostService(@FormParam("pagename") String pagename, @FormParam("type") String type,@FormParam("category")String category
			) throws JSONException {
		//System.out.print("nourffhhjyhjtja");
		PageEntity page =new PageEntity(pagename, type, category);
		page.savePage(pagename, type, category);
		JSONObject object = new JSONObject();
		object.put("Status", "OK");
		return object.toString();
	}
	/**
	 * Action function to response to likePage request, This function will act as
	 * a controller part and it will calls likepageService to increase
	 * num of likes
	 * 
	 * @param pagename
	 *            provided page name
	 * @return Status string
	 */
		@POST
	@Path("/likepageService")
	public static String likepageService(@FormParam("pagename")String pagename) {
		JSONObject object = new JSONObject();
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
        
		Query gaeQuery = new Query("page");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("pagename").toString().equals(pagename)) {
				long key = entity.getKey().getId();
				Entity page = new Entity("page",key );
				String likes=entity.getProperty("likes").toString();
				int countlikes=Integer.parseInt(likes)+1;
				String s = Integer.toString(countlikes);
				page.setProperty("ownername",entity.getProperty("ownername").toString());
				page.setProperty("pagename",entity.getProperty("pagename").toString());
				page.setProperty("likes",s);
				page.setProperty("reach",s);
				datastore.put(page);
				PageEntity.savePageLiker(pagename);
			}
		}

		//return null;
		return object.toString();
		
	
	}
	
/*@POST
	@Path("/likepageService")
	public String likepageService(@FormParam("pagename")String pagename) {
		JSONObject object = new JSONObject();
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("page");
		Query gaeQuery1 = new Query("pageliker");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		PreparedQuery pq1 = datastore.prepare(gaeQuery1);
		for (Entity entity1 : pq1.asIterable()) {
			if (entity1.getProperty("pagename").toString().equals(pagename)&&
					entity1.getProperty("likername").toString().equals
					(User.getCurrentActiveUser().getName())) {
				return null;
			}
			else{
				for (Entity entity : pq.asIterable()) {
					if (entity.getProperty("pagename").toString().equals(pagename)) {
						long key = entity.getKey().getId();
						Entity page = new Entity("page",key );
						String likes=entity.getProperty("likes").toString();
						int countlikes=Integer.parseInt(likes)+1;
						String s = Integer.toString(countlikes);
						page.setProperty("pagename",entity.getProperty("pagename").toString());
						page.setProperty("likes",s);
						page.setProperty("reach",s);
						datastore.put(page);
						PageEntity.savePageLiker(pagename);
						return null;
					}
				}
			}
		}
		return null;
	}*/	
	
}

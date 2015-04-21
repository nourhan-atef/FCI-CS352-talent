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


@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class PageServices {
	
	@POST
	@Path("/createpageServicee")
	public String createpostService(@FormParam("pagename") String pagename, @FormParam("type") String type,@FormParam("category")String category
			) throws JSONException {
		//System.out.print("nourffhhjyhjtja");
		PageEntity page =new PageEntity(pagename, type, category);
		page.savePage(pagename, type, category);
		JSONObject object = new JSONObject();
		object.put("Status", "OK");
		return object.toString();
	}
	
		@POST
	@Path("/likepageService")
	public String likepageService(@FormParam("pagename")String pagename) {
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

		return null;
		
		
	
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

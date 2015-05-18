package com.FCI.SWE.ServicesModels;

import java.util.List;

import com.FCI.SWE.Models.User;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
/**
 * <h1>User Entity class</h1>
 * <p>
 * This class will act as a model for user, it will holds user data
 * </p>
 *
 * @author hoda tawakl
 * @version 1.0
 * @since 2014-02-12
 */
public class PageEntity {
		private String pagename;
		private String type;
		private String category;
		
		/**
		 * Constructor accepts page data
		 * 
		 * @param pagename
		 *            page name
		 * @param type
		 *            type
		 * @param category
		 *           category
		 */
		public PageEntity(String pagename, String type, String category) {
			super();
			this.pagename = pagename;
			this.type = type;
			this.category = category;
		}
		
		
		public String getPagename() {
			return pagename;
		}
		public void setPagename(String pagename) {
			this.pagename = pagename;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		
		public static PageEntity getPage(String pagename, String type, String category) {
			DatastoreService datastore = DatastoreServiceFactory
					.getDatastoreService();

			Query gaeQuery = new Query("page");
			PreparedQuery pq = datastore.prepare(gaeQuery);
			for (Entity entity : pq.asIterable()) {
				if (entity.getProperty("pagename").toString().equals(pagename)) {
					PageEntity returnedUser = new PageEntity(entity.getProperty(
							"pagename").toString(), entity.getProperty("type")
							.toString(), entity.getProperty("category").toString());
					//returnedUser.setId(entity.getKey().getId());
					return returnedUser;
				}
			}

			return null;
		}
		
		
		

		
		/**
		 * This method will be used to save page object in datastore
		 * 
		 * @return boolean if page is saved correctly or not
		 */
		public static Boolean savePage(String pagename, String type, String category) {
			DatastoreService datastore = DatastoreServiceFactory
					.getDatastoreService();
			Query gaeQuery = new Query("page");
			PreparedQuery pq = datastore.prepare(gaeQuery);
			List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
			Entity page = new Entity("page", list.size() + 1);

			page.setProperty("ownername",User.getCurrentActiveUser().getName());
			page.setProperty("pagename",pagename);
			page.setProperty("type",type);
			page.setProperty("category",category);
			page.setProperty("reach",0);
			page.setProperty("likes",0);
			datastore.put(page);
			System.out.print("save" );
         
			return true;

		}
		/**
		 * This method will be used to save pageliker object in datastore
		 * 
		 * @return boolean if pageliker is saved correctly or not
		 */
		public static Boolean savePageLiker(String pagename) {
			DatastoreService datastore = DatastoreServiceFactory
					.getDatastoreService();
			Query gaeQuery = new Query("pageliker1");
			PreparedQuery pq = datastore.prepare(gaeQuery);
			List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
			Entity page = new Entity("pageliker1", list.size() + 1);
			page.setProperty("likername",User.getCurrentActiveUser().getName());
			page.setProperty("pagename",pagename);
			datastore.put(page);

			return true;

		}
		
}

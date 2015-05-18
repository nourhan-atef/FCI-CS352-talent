package com.FCI.SWE.Services;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.FCI.SWE.Controller.Connection;
import com.FCI.SWE.Models.Page;
import com.FCI.SWE.Models.Post;
import com.google.appengine.labs.repackaged.org.json.JSONException;

public class PageServicesTestintegration {




		public void createpage() {
			String urlParameters = "pagename=" + "manage" + "&type=" + "education" + "&category=" + "education" ;
			
			String retJson = Connection.connect(
					"http://localhost:8888/rest/createpageServicee", urlParameters,
					"POST", "application/x-www-form-urlencoded;charset=UTF-8");
			JSONParser parser = new JSONParser();
			Object obj;
			try {
				obj = parser.parse(retJson);
				JSONObject object = (JSONObject) obj;
				 Assert.assertEquals(object.get("Status"), "OK");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		///////////////////////////////////////////////////////////////////////////////////
		 @Test(dependsOnMethods={"savePageLiker"})
		public void likepage() {
			String urlParameters = "pagename=" + "sw";

			String retJson = Connection.connect(
					"http://localhost:8888/rest/likepageService", urlParameters,
					"POST", "application/x-www-form-urlencoded;charset=UTF-8");
			System.out.print("likersss");
			JSONParser parser = new JSONParser();
			Object obj;
			try {
				obj = parser.parse(retJson);
				JSONObject object = (JSONObject) obj;
				Assert.assertEquals(object.get("Status"), "OK");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//////////////////////////////////////////////////////////////////////////////////
		
		
		}


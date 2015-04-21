package com.FCI.SWE.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.FCI.SWE.Models.Page;
import com.FCI.SWE.Models.User;
@Path("/")
@Produces("text/html")
public class PageController {
	
	
	@GET
	@Path("/createpage")
	public Response createpage() {
		//System.out.print("noura");
		return Response.ok(new Viewable("/jsp/createpage")).build();
	}
	
	@GET
	@Path("/likePage")
	public Response likepage() {
		System.out.print("liker");
		return Response.ok(new Viewable("/jsp/likepage")).build();
	}
	
	
	@POST
	@Path("/createpage")
	@Produces("text/html")
	public Response createpage(@FormParam("pagename") String pagename,
			@FormParam("type") String type, @FormParam("category") String category ) {
		String urlParameters = "pagename=" + pagename + "&type=" + type + "&category=" + category ;
		
		String retJson = Connection.connect(
				"http://localhost:8888/rest/createpageServicee", urlParameters,
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");
		 //System.out.print("noura");
		System.out.print("hhhhhhh"+pagename+type+category);
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("Failed"))
				return null;
			Map<String, String> map = new HashMap<String, String>();
			//Page Page = getPage(object.toJSONString());
			map.put("pagename",Page.getPagename());
			map.put("type", Page.getType());
			map.put("category", Page.getCategory()) ;
			System.out.print("--"+Page.getPagename()+"   "+Page.getType() );
			return Response.ok(new Viewable("/jsp/hello", map)).build();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * UserEntity user = new UserEntity(uname, email, pass);
		 * user.saveUser(); return uname;
		 */
		return null;

	}
 
	
	@POST
	@Path("/likePage")
	@Produces("text/html")
	public Response likepage(@FormParam("pagename") String pagename) {
		String urlParameters = "pagename=" + pagename;

		String retJson = Connection.connect(
				"http://localhost:8888/rest/likepageService", urlParameters,
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");
		System.out.print("likersss");
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("Failed"))
				return null;
			Map<String, String> map = new HashMap<String, String>();
			//Page Page = com.FCI.SWE.Models.Page.getPage(object.toJSONString());
			map.put("pagename",Page.getPagename());
			return Response.ok(new Viewable("/jsp/hello", map)).build();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * UserEntity user = new UserEntity(uname, email, pass);
		 * user.saveUser(); return uname;
		 */
		return null;

	}
 

}

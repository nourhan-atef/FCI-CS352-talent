package com.FCI.SWE.Services;

import java.util.Vector;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.FCI.SWE.Controller.Connection;
import com.FCI.SWE.Models.User;
import com.google.appengine.labs.repackaged.org.json.JSONException;

public class FriendAcceptanceNotificationTest {

	/*@DataProvider(name="excute")
	  public static Object[][] excute(){
		  Vector<String>vec=new Vector<String>();
		  vec.add("kkkknlnl");
	        return new Object[][]{{vec,"h"},{vec,"n"}};
	  }
	  @Test(dataProvider = "excute")
	  public void excuteService(Vector vec,String Name) throws JSONException {
		    Assert.assertEquals(vec, FriendAcceptanceNotification.excute(Name));
	  }*/
	
	  @Test
	    public void acceptFriendNotification() {
	        User user= User.getCurrentActiveUser();
			String urlParameters = "a";
			String retJson = Connection.connect(
					"http://localhost:8888/rest/excuteacceptNotificationService", urlParameters,
					"POST", "application/x-www-form-urlencoded;charset=UTF-8");

			JSONParser parser = new JSONParser();
			Object obj;
			try {
				
				obj = parser.parse(retJson);
				JSONObject object = (JSONObject) obj;
				Assert.assertEquals(object.get("Status"), "OK");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
}

package com.FCI.SWE.Models;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.FCI.SWE.Models.Post;
import com.FCI.SWE.Models.User;
import com.FCI.SWE.ServicesModels.PageEntity;
import com.FCI.SWE.ServicesModels.PostEntity;
import com.google.appengine.labs.repackaged.org.json.JSONException;

public class UserTest {
	@DataProvider(name="getUser")
	  public static Object[][] getUser(){
		User user = new User("n","n","n");
	        return new Object[][]{{user,"n"},{user,"k"}};
	  }
	
	  @Test(dataProvider = "getUser")
     public void getUser(User user, String name) {
	  Assert.assertEquals(user, User.getUser(name));
  }
}

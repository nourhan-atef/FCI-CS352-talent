package com.FCI.SWE.Models;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.FCI.SWE.ServicesModels.PageEntity;
import com.FCI.SWE.ServicesModels.PostEntity;
import com.google.appengine.labs.repackaged.org.json.JSONException;

public class PostTest {

	@DataProvider(name="getPost")
	  public static Object[][] getPost(){
		PostEntity returnedPage = new PostEntity("","","n","","","");
	        return new Object[][]{{returnedPage,"j","03:00"},{returnedPage,"n",""}};
	  }
	  @Test(dataProvider = "getPost")
	  public void getPost(PageEntity returnedPage,String ownerName,String time) throws JSONException {
		    Assert.assertEquals(returnedPage, Post.getPost(ownerName,time));
	  }
}

package com.FCI.SWE.Models;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.FCI.SWE.Services.PageServices;
import com.FCI.SWE.ServicesModels.PageEntity;
import com.google.appengine.labs.repackaged.org.json.JSONException;

public class PageTest {
//////////////////////////////
	@DataProvider(name="getPage")
	  public static Object[][] getPage(){
		PageEntity returnedPage = new PageEntity("n","","");
	        return new Object[][]{{returnedPage,"j"},{returnedPage,"n"}};
	  }
	  @Test(dataProvider = "getPage")
	  public void getPage(PageEntity returnedPage,String ownerName) throws JSONException {
		    Assert.assertEquals(returnedPage, Page.getPage(ownerName));
	  }
}
	///////////////////////
 

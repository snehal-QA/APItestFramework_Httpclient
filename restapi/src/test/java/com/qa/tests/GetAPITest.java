package com.qa.tests;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLOutput;
import java.util.HashMap;

import com.qa.APIhandlers.ResponseHandler;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.base.Base;
import com.qa.APIhandlers.APImethods;

public class GetAPITest extends Base {
	
	Base base;
	String url;
	APImethods restclient;
	CloseableHttpResponse response;

	@BeforeMethod
	public void setup()
	{
		base=new Base();
		url=prop.getProperty("url")+prop.getProperty("resource");
		System.out.println("URL in before method: "+url);
	}
	
	@Test
	public void getAPI() throws ClientProtocolException, IOException, URISyntaxException {
		restclient=new APImethods();
		//response
		response=restclient.get(url);
		ResponseHandler responsehandler=new ResponseHandler();

		//Converting re
		JSONObject responsejson=responsehandler.getresponsejson(response);

		System.out.println(responsejson);
		System.out.println("Total: "+responsejson.getInt("total"));
		//JSONArray jarray=responsejson.getJSONArray("data");

		//System.out.println("Total data set received= "+jarray.length());
		System.out.println("Total data set: "+(responsehandler.getjsonarray(responsejson,"data")).length());
		Assert.assertEquals(responsehandler.getstauscode(response),200);
		Assert.assertEquals((responsehandler.getjsonarray(responsejson,"data").length()),6,"totaldata");

		//Get header data for assertion
		System.out.println(responsehandler.getresponseheaders(response).get("Server"));
		Assert.assertEquals(responsehandler.getresponseheaders(response).get("Server"),"cloudflare","Server name macthes as expected");
	}
	

}
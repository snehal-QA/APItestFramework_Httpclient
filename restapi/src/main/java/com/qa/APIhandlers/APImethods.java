package com.qa.APIhandlers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class APImethods {
	
	//GET Method
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException, URISyntaxException {
		CloseableHttpClient httpclient=HttpClients.createDefault(); //Create connection
		HttpGet httpget=new HttpGet(url);  //http get request

		//to build request with parameters
		URI uri=new URIBuilder(httpget.getURI())
				.addParameter("page","2")
			    .build();
		((HttpRequestBase)httpget).setURI(uri);

		CloseableHttpResponse getresponse = httpclient.execute(httpget); //Response
		return getresponse;
	}
}

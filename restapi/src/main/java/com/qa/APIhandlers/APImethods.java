package com.qa.APIhandlers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.data.Userpayload;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
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

	//Post method
	public CloseableHttpResponse postmethod(String url) throws IOException {
		Userpayload payload=new Userpayload();
		CloseableHttpClient httpClient=HttpClients.createDefault();
		HttpPost httppost=new HttpPost(url);
//		String jsonbody="{\n" +
//				"    \"name\": \"morpheus\",\n" +
//				"    \"job\": \"leader\"\n" +
//				"}";
		payload.setName("Newton");
		payload.setJob("QA");
		//Convert java object to json >> json to string (Used jackson library ObjectMapper class)
		ObjectMapper mapper=new ObjectMapper();
		String payloadstring=mapper.writeValueAsString(payload);
		System.out.println(payloadstring);
		StringEntity entity=new StringEntity(payloadstring);

		//Forming post request
		httppost.setEntity(entity); //passing json body in post call
		httppost.setHeader("Content-Type","application/json"); //setting header for postcall

		//Hitting the post call and getting response in CloseableHttpResponse object
		CloseableHttpResponse response=httpClient.execute(httppost);
		return response;
	}
}

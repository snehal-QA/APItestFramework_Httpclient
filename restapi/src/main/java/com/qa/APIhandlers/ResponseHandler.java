package com.qa.APIhandlers;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class ResponseHandler {

    //Returns statuscode of the response
    public int getstauscode(CloseableHttpResponse response)
    {
        return response.getStatusLine().getStatusCode();
    }

    //This method gets all the response headers for further assertions.
    public  Map<Object, Object> getresponseheaders(CloseableHttpResponse response)
    {

        Header[] allheaders=response.getAllHeaders();
        Map<Object,Object> headermap1=Arrays.asList(allheaders).stream().collect(Collectors.toMap(h -> h.getName(),h -> h.getValue()));
        headermap1.entrySet().stream().forEach(k-> System.out.println(k));
        return headermap1;
    }

    //Converts a CloseableHttpresponse into JSONObject.
    public JSONObject getresponsejson(CloseableHttpResponse response) throws IOException {
        String responsestring=EntityUtils.toString(response.getEntity(), "UTF-8");
        JSONObject responsejson=new JSONObject(responsestring);
        return responsejson;
    }

    //Gives ability to navigate across jsonarray in response
    public JSONArray getjsonarray(JSONObject response,String key) throws IOException {

        JSONArray jarray=response.getJSONArray(key);
        return jarray;
    }
    
   
}

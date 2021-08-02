package com.qa.APIhandlers;

import com.jayway.jsonpath.JsonPathException;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public class ResponseHandler {

    //Returns statuscode of the response
    public int getstauscode(CloseableHttpResponse response)
    {
        return response.getStatusLine().getStatusCode();
    }

    //This method gets all the response headers for further assertions.
    public HashMap<String, String> getresponseheaders(CloseableHttpResponse response)
    {

        HashMap<String,String> headermap=new HashMap<String,String>();
        Header[] allheaders=response.getAllHeaders();
       for(Header header:allheaders)
       {
           headermap.put(header.getName(),header.getValue());
       }
       return headermap;
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

package com.qa.tests;

import com.qa.APIhandlers.APImethods;
import com.qa.APIhandlers.ResponseHandler;
import com.qa.base.Base;
import com.qa.data.Userpayload;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class PUTTest {
    Base base;
    ResponseHandler responseHandler;
    APImethods apimethod;

    @Test
    public void putapimethod() throws IOException {
        base=new Base();
        responseHandler=new ResponseHandler();
        apimethod=new APImethods();
        String url=base.prop.getProperty("url")+base.prop.getProperty("resource")+"/2";
        CloseableHttpResponse response=apimethod.putmethod(url);
        //Validating status code=200
        Assert.assertEquals(response.getStatusLine().getStatusCode(),200);

        //Validating modified data is as expected.
        JSONObject responsejson=responseHandler.getresponsejson(response);
        Assert.assertEquals(responsejson.get("name"),apimethod.userpayload.getName());
        Assert.assertEquals(responsejson.get("job"),apimethod.userpayload.getJob());

    }
}

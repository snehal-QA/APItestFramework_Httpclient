package com.qa.tests;

import com.qa.APIhandlers.APImethods;
import com.qa.APIhandlers.ResponseHandler;
import com.qa.base.Base;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class PostAPItest {
    Base base;
    APImethods apimethods;
    ResponseHandler responsehandler;


    @Test
    public void postapimethod() throws IOException {
        base=new Base();
        apimethods=new APImethods();
        responsehandler=new ResponseHandler();
        String url=base.prop.getProperty("url")+base.prop.getProperty("resource");

        CloseableHttpResponse response=apimethods.postmethod(url);

        Assert.assertEquals(response.getStatusLine().getStatusCode(),201,"Status code 201 is returned");
    }
}

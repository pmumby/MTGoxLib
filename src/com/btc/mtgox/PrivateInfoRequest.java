/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.btc.mtgox;

import java.util.ArrayList;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 * @author Paul Mumby
 */
public class PrivateInfoRequest extends Request
{
    public PrivateInfoRequest(APIConnection apiconn)
    {
        ArrayList<NameValuePair> req = new ArrayList<NameValuePair>();
        Setup(apiconn, "/api/1/generic/private/info?raw", req);       
    }
    
    public PrivateData getResponse() throws Exception
    {
        doRequest();
        return PrivateData.fromJson(jsonResult);
    }    
}

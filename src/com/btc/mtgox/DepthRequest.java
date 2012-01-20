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
public class DepthRequest extends Request {
    public DepthRequest(APIConnection apiconn, CurrencyEnum currency)
    {
        String cString = currency.toString();
        ArrayList<NameValuePair> req = new ArrayList<NameValuePair>();        
        Setup(apiconn, "/api/1/BTC"+cString+"/public/depth?raw", req);       
    }
    
    public DepthData getResponse() throws Exception
    {
        doRequest();
        return DepthData.fromJson(jsonResult);
    }
}

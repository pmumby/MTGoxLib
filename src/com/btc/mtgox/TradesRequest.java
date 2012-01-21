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
public class TradesRequest extends Request {
    public TradesRequest(APIConnection apiconn, CurrencyEnum currency)
    {
        String cString = currency.toString();
        ArrayList<NameValuePair> req = new ArrayList<NameValuePair>();
        Setup(apiconn, "/api/1/BTC"+cString+"/public/trades?raw", req);       
    }

    public TradesRequest(APIConnection apiconn, CurrencyEnum currency, long since)
    {
        String cString = currency.toString();
        ArrayList<NameValuePair> req = new ArrayList<NameValuePair>();
        req.add(new BasicNameValuePair("since", String.valueOf(since)));
        //Setup(apiconn, "/api/1/BTC"+cString+"/public/trades?raw&since="+String.valueOf(since), req);    
        Setup(apiconn, "/api/1/BTC"+cString+"/public/trades?raw", req);       
    }
    
    public TradesData getResponse() throws Exception
    {
        doRequest();
        return TradesData.fromJson(jsonResult);        
    }
}

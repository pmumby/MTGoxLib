/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.btc.mtgox;

import java.util.ArrayList;
import org.apache.http.NameValuePair;

/**
 *
 * @author Paul Mumby
 */
public class TickerRequest extends Request {
    public TickerRequest(APIConnection apiconn, CurrencyEnum currency)
    {
        String cString = currency.toString();
        ArrayList<NameValuePair> req = new ArrayList<NameValuePair>();        
        Setup(apiconn, "/api/1/BTC"+cString+"/public/ticker?raw", req);       
    }
    
    public TickerData getResponse() throws Exception
    {
        doRequest();
        return TickerData.fromJson(jsonResult);
    }    
}

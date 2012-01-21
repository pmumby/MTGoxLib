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
public class OrderRequest extends Request {
    public OrderRequest(APIConnection apiconn, CurrencyEnum currency, TradeTypeEnum type, double amount, double price)
    {
        long amount_int = (long)(amount * 10000000);
        long price_int = (long)(price*100000);        
        String cString = currency.toString();
        ArrayList<NameValuePair> req = new ArrayList<NameValuePair>();
        req.add(new BasicNameValuePair("type", type.toString()));
        req.add(new BasicNameValuePair("amount_int", String.valueOf(amount_int)));
        req.add(new BasicNameValuePair("price_int", String.valueOf(price_int)));        
        Setup(apiconn, "/api/1/BTC"+cString+"/private/order/add?raw", req);       
    }    
    
    public OrderResponse getResponse() throws Exception
    {
        doRequest();
        return OrderResponse.fromJson(jsonResult);        
    }    
}

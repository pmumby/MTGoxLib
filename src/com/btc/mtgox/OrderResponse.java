/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.btc.mtgox;

import com.google.gson.Gson;

/**
 *
 * @author Paul Mumby
 */
public class OrderResponse {
    public String result;
    public String error;
    public String order_id;
    
    public static OrderResponse fromJson(String jsonstring)
    {
        OrderResponse tempdata = new OrderResponse();
        Gson gson = new Gson();
        if(jsonstring.startsWith("{"))
        {
            tempdata = gson.fromJson(jsonstring, OrderResponse.class);
        }else{
            tempdata.result = "success";
            tempdata.error = "";
            tempdata.order_id = gson.fromJson(jsonstring, String.class);                    
        }
        return tempdata;            
    }
}

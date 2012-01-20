/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.btc.mtgox;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Paul Mumby
 */
public class TradesData {
    public ArrayList<TradeRecord> Records;
    
    public static TradesData fromJson(String jsonstring)
    {
        Gson gson = new Gson();
        TradesData tempdata = new TradesData();
        TradeRecord[] temparray;
        temparray = gson.fromJson(jsonstring, TradeRecord[].class);
        tempdata.Records = new ArrayList(Arrays.asList(temparray));        
        return tempdata;
    }
}

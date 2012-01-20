/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.btc.mtgox;

import com.google.gson.Gson;
import java.util.List;
/**
 *
 * @author Paul Mumby
 */
public class DepthData {
    public List<DepthRecord> asks;
    public List<DepthRecord> bids;
    
    public static DepthData fromJson(String jsonstring)
    {
        Gson gson = new Gson();
        DepthData tempdata = gson.fromJson(jsonstring, DepthData.class);
        return tempdata;
    }
}

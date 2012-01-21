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
public class TickerData {
    public CurrencyValue high;
    public CurrencyValue low;
    public CurrencyValue vwap;
    public CurrencyValue vol;
    public CurrencyValue last_local;
    public CurrencyValue last;
    public CurrencyValue last_orig;
    public CurrencyValue last_all;
    public CurrencyValue buy;
    public CurrencyValue sell;
    
    public static TickerData fromJson(String jsonstring)
    {
        Gson gson = new Gson();
        TickerData tempdata = gson.fromJson(jsonstring, TickerData.class);
        return tempdata;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.btc.mtgox;

import com.google.gson.Gson;
import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author Paul Mumby
 */
public class PrivateData {
    public String Login;
    public int Index;
    public ArrayList<String> Rights;
    public String Language;
    public String Created;    
    public String Last_Login;
    public WalletContainer Wallets;
    
    public static PrivateData fromJson(String jsonstring)
    {
        Gson gson = new Gson();
        PrivateData tempdata = gson.fromJson(jsonstring, PrivateData.class);
        return tempdata;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.btc.mtgox;

/**
 *
 * @author Paul Mumby
 */
public class APIConnection {
    public String protocol = "";    
    public String host = "";
    public String key = "";
    public String secret = "";
    
    public APIConnection(String proto, String hostname, String apikey, String apisecret)
    {
        protocol = proto;
        host = hostname;
        key = apikey;
        secret = apisecret;        
    }
}

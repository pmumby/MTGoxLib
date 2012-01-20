/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.btc.mtgox;

/**
 *
 * @author Paul Mumby
 */
public class DepthRecord {
    private long price_int;
    private long amount_int;
    public String stamp;
    
    public float getPrice()
    {
        return (float)(price_int/100000);
    }
    
    public float getAmount()
    {
        return (float)(amount_int/10000000);        
    }
}

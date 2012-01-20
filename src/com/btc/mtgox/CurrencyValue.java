/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.btc.mtgox;

/**
 *
 * @author Paul Mumby
 */
public class CurrencyValue {
    private long value_int;
    public String display;
    public CurrencyEnum currency;
    
    public double getValue()
    {
        switch(currency)
        {
            case BTC:
                return (double)(value_int/10000000);                
            default:
                return (double)(value_int/100000);                
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.btc.mtgox;

/**
 *
 * @author Paul Mumby
 */
public class WalletContainer {
    public Wallet BTC;
    public Wallet USD;
    public Wallet CAD;
    
    public WalletContainer()
    {
        BTC = new Wallet();
        USD = new Wallet();
        CAD = new Wallet();
    }
}

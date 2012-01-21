/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.btc.mtgox;

/**
 *
 * @author Paul Mumby
 */
public class VirtualGox extends MtGoxClient{
    private WalletContainer VirtualWallet = new WalletContainer();
    
    public VirtualGox(String protocol, String host, String key, String secret)
    {
        super(protocol,host,key,secret); 
        VirtualWallet.BTC.Balance.setValue(100);
        VirtualWallet.USD.Balance.setValue(100);
        VirtualWallet.CAD.Balance.setValue(100);        
    }
    
    @Override
    public PrivateData getPrivate() throws Exception
    {
        PrivateData vPrivate = super.getPrivate();
        vPrivate.Wallets = VirtualWallet;        
        return vPrivate;
    }
    
    @Override
    public OrderResponse addOrder(CurrencyEnum currency, TradeTypeEnum type, double amount, double price) throws Exception
    {
        OrderResponse vResponse = new OrderResponse();
        boolean success = VirtualTrade(currency, type, amount, price);
        if(success)
        {
            vResponse.result = "success";
            vResponse.order_id = "VIRTUAL";
        }else{
            vResponse.result = "error";
            vResponse.order_id = "VIRTUAL";
            vResponse.error = "Insufficient Funds!";
        }
        return vResponse;
    }
    
    private boolean VirtualTrade(CurrencyEnum currency, TradeTypeEnum type, double amount, double price)
    {
        //TODO: Fix this so it bases off of actual market depth to fulfill virtual orders
        //For now it just accepts it, and adjusts the balances accordingly, so it's up to the calling app
        //to put real trade requests in that actually could have been fulfilled.
        double btcadjust = 0;
        double curadjust = 0;
        switch(type)
        {
            case ask:
                btcadjust = amount;
                curadjust = amount * price * -1;
                break;
            case bid:
                btcadjust = amount * -1;
                curadjust = amount * price;
                break;
        }
        if((btcadjust * -1)<VirtualWallet.BTC.Balance.getValue())
        {
            VirtualWallet.BTC.Balance.setValue(VirtualWallet.BTC.Balance.getValue()+btcadjust);
        }else{
            return false;
        }
        switch(currency)
        {
            case USD:
                if((curadjust * -1)<VirtualWallet.USD.Balance.getValue())
                {
                    VirtualWallet.USD.Balance.setValue(VirtualWallet.USD.Balance.getValue()+curadjust);
                }else{
                    return false;
                }
                break;
            case CAD:
                if((curadjust * -1)<VirtualWallet.CAD.Balance.getValue())
                {
                    VirtualWallet.CAD.Balance.setValue(VirtualWallet.CAD.Balance.getValue()+curadjust);
                }else{
                    return false;
                }
                break;
            default:
                
                break;               
        }
        return true;
    }
    
}

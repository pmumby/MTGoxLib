/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.btc.mtgox;

/**
 *
 * @author Paul Mumby
 */
public class MtGoxClient {
    private APIConnection Conn;
    
    public MtGoxClient(String protocol, String host, String key, String secret)
    {
        Conn = new APIConnection(protocol, host, key, secret);
    }
    
    public DepthData getDepth(CurrencyEnum currency) throws Exception
    {
        DepthRequest req = new DepthRequest(Conn,currency);
        return req.getResponse();
    }
    
    public PrivateData getPrivate() throws Exception
    {
        PrivateInfoRequest req = new PrivateInfoRequest(Conn);
        return req.getResponse();
    }
    
    public TradesData getTrades(CurrencyEnum currency) throws Exception
    {
        TradesRequest req = new TradesRequest(Conn, currency);
        return req.getResponse();
    }

    public TradesData getTrades(CurrencyEnum currency,long since) throws Exception
    {
        TradesRequest req = new TradesRequest(Conn, currency, since);
        return req.getResponse();
    }
    
    public OrderResponse addOrder(CurrencyEnum currency, TradeTypeEnum type, double amount, double price) throws Exception
    {
        OrderRequest req = new OrderRequest(Conn, currency, type, amount, price);
        return req.getResponse();
    }
}

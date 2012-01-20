/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.btc.mtgox;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.BufferedHeader;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

/**
 *
 * @author Paul Mumby
 */
public class Request {
    public String jsonResult = "";
    public String protocol;
    public String host;
    public String path;
    public String key;
    public String secret;
    public ArrayList<NameValuePair> request;
    private URI uri;
    private HttpClient http;
    private HttpPost httpreq;
    
    public void Request(APIConnection apiconn, String path, ArrayList<NameValuePair> request)
    {
        Setup(apiconn, path, request);
    }
    
    public void Setup(APIConnection apiconn, String urlpath, ArrayList<NameValuePair> req)
    {
        protocol = apiconn.protocol;
        host = apiconn.host;
        path = urlpath;
        key = apiconn.key;
        secret = apiconn.secret;
        request = req;
    }
    
    public void doRequest() throws Exception
    {
        String query = null;
        if(key!=null && secret!=null)
        {
            String nonceval;
            long nanotime = System.nanoTime();
            nonceval = String.valueOf(nanotime);                       
            request.add(new BasicNameValuePair("nonce", nonceval));
        }
        if(request!=null)
        {
            if(request.size()>0)
            {
                query = URLEncodedUtils.format(request,"UTF-8");                
            }
        }
        uri = URIUtils.createURI(protocol,host,-1,path,null,null);
        httpreq = new HttpPost(uri);                 
        http = new DefaultHttpClient();
        
        if(key != null && secret != null)
        {
            String signedsecret = signQuery(query);
            httpreq.addHeader("Rest-Key", key);
            httpreq.addHeader("Rest-Sign", signedsecret);    
        }        
        HttpProtocolParams.setUserAgent(http.getParams(), "Mozilla/4.0 (compatible; MtGox Java Client Library)");
        httpreq.addHeader("Accept", "*/*");
        //httpreq.addHeader("Content-Type","application/x-www-form-urlencoded");
        httpreq.setEntity(new UrlEncodedFormEntity(request,HTTP.UTF_8));         
        HttpResponse resp = http.execute(httpreq);
        
        if (resp.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
            + resp.getStatusLine().getStatusCode());
        }        
        BufferedReader br = new BufferedReader(new InputStreamReader((resp.getEntity().getContent())));       
        jsonResult = "";
        String output;        
        while((output = br.readLine()) != null)
        {
            jsonResult += output;
        }        
        http.getConnectionManager().shutdown();        
    } 
    
    private String signQuery(String query) throws Exception
    {
        Mac mac = Mac.getInstance("HmacSHA512");
        SecretKeySpec encsecret = new SecretKeySpec(Base64.decodeBase64(secret),"HmacSHA512");
        mac.init(encsecret);
        byte[] digest = mac.doFinal(query.getBytes());
        byte[] base64 = Base64.encodeBase64(digest);
        String encstring = new String(base64,Charset.forName("ASCII"));
        //String unsafe = Base64.encodeBase64String(digest);      
        //String safe = Base64.encodeBase64URLSafeString(digest);      
        return encstring;        
    }
}

package main2.model.utils;

import main.java.HillelTracker.persisting.utils.HttpConnector;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUrlConnector {
    final static Logger logger=Logger.getLogger(HttpConnector.class);


    public HttpURLConnection getConnection(String urlOfTransportCompany){
        HttpURLConnection httpURLConnection=null;
        try{URL url=new URL(urlOfTransportCompany);
            httpURLConnection=(HttpURLConnection)url.openConnection();
        }catch (IOException e){
            logger.error("Connection to transport company throws error");
        }


        return httpURLConnection;
    }
}

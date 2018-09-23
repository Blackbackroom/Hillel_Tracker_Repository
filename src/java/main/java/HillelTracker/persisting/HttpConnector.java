package main.java.HillelTracker.persisting;

import main.java.HillelTracker.dataModel.TransportCompany;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnector {
    final static Logger logger=Logger.getLogger(HttpConnector.class);


    public HttpURLConnection getConnection(String urlconn){
        HttpURLConnection httpURLConnection=null;
        try{URL url=new URL("https://api.novaposhta.ua/v2.0/json/");
            httpURLConnection=(HttpURLConnection)url.openConnection();
        }catch (IOException e){
            logger.error("Connection throws error");
        }


        return httpURLConnection;
    }
}

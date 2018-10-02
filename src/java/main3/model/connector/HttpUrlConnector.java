package main3.model.connector;

import org.apache.log4j.Logger;
import java.io.IOException;
import java.net.URL;
import java.net.HttpURLConnection;

public class HttpUrlConnector {
    final static Logger logger = Logger.getLogger(HttpUrlConnector.class);

    public HttpURLConnection getConnection(String urlConnection){
        HttpURLConnection httpURLConnection=null;

        try{
            URL url=new URL(urlConnection);
            httpURLConnection= (HttpURLConnection) url.openConnection();
        }catch (IOException e){
            logger.error("Connection throws error: "+e);
        }

        return httpURLConnection;
    }
}

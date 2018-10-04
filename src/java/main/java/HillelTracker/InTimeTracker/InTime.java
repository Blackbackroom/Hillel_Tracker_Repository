package main.java.HillelTracker.InTimeTracker;

import sun.net.www.protocol.http.HttpURLConnection;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class InTime {
    public static void main(String[] args) throws IOException {


        String api = "27574359530001414107";
        String number = "30001508686";

        String request = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"\n" +
                "xmlns:dat=\"http://ws.wso2.org/dataservice\">\n" +
                " <soapenv:Header/>\n" +
                " <soapenv:Body>\n" +
                " <dat:decl_status_history>\n" +
                " <dat:api_key>" + api + "</dat:api_key>\n" +
                " <dat:decl_num>" + number + "</dat:decl_num>\n" +
                " </dat:decl_status_history>\n" +
                " </soapenv:Body>\n" +
                "</soapenv:Envelope>\n";


        URL url = new URL("http://ws.wso2.org/dataservice");

        HttpURLConnection connection= (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(5000);
        connection.setRequestProperty("Content-Type", "string");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("GET");
        connection.connect();

        OutputStreamWriter wr=new OutputStreamWriter(connection.getOutputStream());
        wr.write(request);
        wr.flush();

        InputStream is = connection.getInputStream();
        BufferedInputStream bis=new BufferedInputStream(is);
        ByteArrayOutputStream buf=new ByteArrayOutputStream();
        int result=bis.read();
        while (result!=-1){
            buf.write((byte)result);
            result=bis.read();
        }

        System.out.println(buf.toString("UTF-8"));


        connection.disconnect();
    }

}

package main.java.HillelTracker.persisting;


import main.java.HillelTracker.dataModel.Invoice;
import main.java.HillelTracker.persisting.utils.HttpConnector;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;

public class HttpMethods {

    HttpConnector httpConnector = new HttpConnector();
    final static Logger logger = Logger.getLogger(HttpMethods.class);
    String url="https://api.novaposhta.ua/v2.0/json/";

    public void getStatus(Invoice invoice) throws IOException {

        //Create string request
        String startRequest = "{\"apiKey\": \"61f13038197a16757f0c082ceac1fdd9\",\n" +
                "    \"modelName\": \"TrackingDocument\",\n" +
                "    \"calledMethod\": \"getStatusDocuments\",\n" +
                "    \"methodProperties\": {\n" +
                "        \"Documents\": [\n" +
                "            {\n\"DocumentNumber\": \"";
        String endRequest = "\",\n" +
                "                \"Phone\":\"\"}]}}";
        StringBuilder request = new StringBuilder();
        request.append(startRequest).append(invoice.getNumber()).append(endRequest);

        String status = invoice.getStatus();

        // Http Connection request
        HttpURLConnection connection = httpConnector.getConnection(url);
        connection.setConnectTimeout(5000);
        connection.setRequestProperty("Content-Type", "string");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("GET");
        connection.connect();
        OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
        wr.write(request.toString());
        wr.flush();

        // Http Connection response
        InputStream is = connection.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        int result = bis.read();
        while (result != -1) {
            buf.write((byte) result);
            result = bis.read();
        }

        //Get status code
        int statusCode = -1;
        String[] arr=(buf.toString()).split("\"");
        for(int i=0; i<arr.length; i++){
            if(arr[i].equalsIgnoreCase("StatusCode")){
              statusCode=Integer.valueOf(arr[i+2]);
            }
        }

        //Get status
        switch (statusCode){
            case 1:
                status="Нова пошта очікує надходження від відправника";
                break;
            case 2:
                status="Видалено";
                break;
            case 3:
                status="Номер не знайдено";
                break;
            case 4:
                status="Відправлення у місті";
                break;
                case 41:
            status="Відправлення у місті";
            break;
            case 5:
            status="Відправлення прямує до міста";
            break;
            case 6:
            status="Відправлення у місті";
            break;
            case 7:
            status="Прибув на відділення";
            break;
            case 8:
            status="Прибув на відділення";
            break;
            case 9:
            status="Відправлення отримано";
            break;
            case 10:
            status="Відправлення отримано";
            break;
            case 11:
            status="Відправлення отримано";
            break;
            case 14:
            status="Відправлення передано до огляду отримувачу";
            break;
            case 101:
            status="На шляху до одержувача";
            break;
            case 102:
            status="Відмова одержувача";
            break;
            case 103:
            status="Відмова одержувача";
            break;
            case 108:
            status="Відмова одержувача";
            break;
            case 104:
            status="Змінено адресу";
            break;
            case 105:
            status="Припинено зберігання";
            break;
            case 106:
            status="Одержано і є ТТН грошовий переказ";
            break;
            case 107:
            status="Нараховується плата за зберігання";
            break;
            default:
            status="Помилка статусу";
            break;
        }

        invoice.setStatus(status);

    }
}
package main3.model;

import main3.model.connector.HttpUrlConnector;
import org.apache.log4j.Logger;
import java.io.*;
import java.net.HttpURLConnection;

import static main3.configuration.Config.novaPoshtaUrl;
import static main3.configuration.Config.novaPoshtaId;

public class NovaPoshta extends TC implements Checkable{

    final static Logger logger=Logger.getLogger(NovaPoshta.class);
    HttpUrlConnector httpUrlConnector=new HttpUrlConnector();

    public NovaPoshta() {
        super.id=novaPoshtaId;
        super.url=novaPoshtaUrl;
        super.name="NovaPoshta";
    }

    private static String createRequest(String number){
        String request=null;

        //Create string request
        String startRequest = "{\"apiKey\": \"61f13038197a16757f0c082ceac1fdd9\",\n" +
                "    \"modelName\": \"TrackingDocument\",\n" +
                "    \"calledMethod\": \"getStatusDocuments\",\n" +
                "    \"methodProperties\": {\n" +
                "        \"Documents\": [\n" +
                "            {\n\"DocumentNumber\": \"";
        String endRequest = "\",\n" +
                "                \"Phone\":\"\"}]}}";
        StringBuilder requestSB = new StringBuilder();
        requestSB.append(startRequest).append(number).append(endRequest);
        request=requestSB.toString();

        return request;
    }

    @Override
    public String checkInvoiceStatus(String number) {
        String status = null;
        int statusCode = -1;

        try {
            HttpURLConnection connection = httpUrlConnector.getConnection(novaPoshtaUrl);
            connection.setConnectTimeout(5000);
            connection.setRequestProperty("Content-Type", "string");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.connect();
            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write(createRequest(number));
            wr.flush();

            InputStream is = connection.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            int result = bis.read();
            while (result != -1) {
                buf.write((byte) result);
                result = bis.read();
            }


            String[] arr = (buf.toString()).split("\"");
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].equalsIgnoreCase("StatusCode")) {
                    statusCode = Integer.valueOf(arr[i + 2]);
                }
            }


        } catch (IOException e) {
            logger.error("NovaPoshta error: " + e);
        }

        // Take status
        switch (statusCode) {
            case 1:
                status = "Нова пошта очікує надходження від відправника";
                break;
            case 2:
                status = "Видалено";
                break;
            case 3:
                status = "Номер не знайдено";
                break;
            case 4:
                status = "Відправлення у місті";
                break;
            case 41:
                status = "Відправлення у місті";
                break;
            case 5:
                status = "Відправлення прямує до міста";
                break;
            case 6:
                status = "Відправлення у місті";
                break;
            case 7:
                status = "Прибув на відділення";
                break;
            case 8:
                status = "Прибув на відділення";
                break;
            case 9:
                status = "Відправлення отримано";
                break;
            case 10:
                status = "Відправлення отримано";
                break;
            case 11:
                status = "Відправлення отримано";
                break;
            case 14:
                status = "Відправлення передано до огляду отримувачу";
                break;
            case 101:
                status = "На шляху до одержувача";
                break;
            case 102:
                status = "Відмова одержувача";
                break;
            case 103:
                status = "Відмова одержувача";
                break;
            case 108:
                status = "Відмова одержувача";
                break;
            case 104:
                status = "Змінено адресу";
                break;
            case 105:
                status = "Припинено зберігання";
                break;
            case 106:
                status = "Одержано і є ТТН грошовий переказ";
                break;
            case 107:
                status = "Нараховується плата за зберігання";
                break;
            default:
                status = null;
                break;
        }


        return status;
    }
}

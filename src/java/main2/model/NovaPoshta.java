package main2.model;

import main2.model.utils.HttpUrlConnector;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import static main2.configuration.Config.novaPoshtaURL;
import java.lang.StringBuilder;

public class NovaPoshta implements TransportCompany{

    private final int id=1;

    public int getId() {
        return id;
    }

    final static Logger logger=Logger.getLogger(NovaPoshta.class);
    HttpUrlConnector httpConnector=new HttpUrlConnector();

    private List<Invoice> allInvoices=new ArrayList();
    private StringBuilder request=new StringBuilder();

    String startRequest = "{\"apiKey\": \"61f13038197a16757f0c082ceac1fdd9\",\n" +
            "    \"modelName\": \"TrackingDocument\",\n" +
            "    \"calledMethod\": \"getStatusDocuments\",\n" +
            "    \"methodProperties\": {\n" +
            "        \"Documents\": [\n" +
            "            {\n\"DocumentNumber\": \"";

    String endRequest = "\",\n" +
            "                \"Phone\":\"\"}]}}";



    public List<Invoice> getAllInvoices() {
        return allInvoices;
    }


    @Override
    public String checkInvoiceStatus(Invoice invoice) throws IOException {
String status=null;

// Create request
            request.append(startRequest).append(invoice.getId()).append(endRequest);

// Send request
            HttpURLConnection connection = httpConnector.getConnection(novaPoshtaURL);
            connection.setConnectTimeout(5000);
            connection.setRequestProperty("Content-Type", "string");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            connection.connect();
            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write(request.toString());
            wr.flush();

// Take response
            InputStream is = connection.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            int result = bis.read();
            while (result != -1) {
                buf.write((byte) result);
                result = bis.read();
            }

// Take status code
        int statusCode = -1;
            String b=" ";
        String[] arr=(buf.toString()).split("\"");
        for(int i=0; i<arr.length; i++){
            if(arr[i].equalsIgnoreCase("StatusCode")){
                statusCode=Integer.valueOf(arr[i+2]);
                b=arr[i+2];
            }
        }

        connection.disconnect();


// Take status
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
        return String.valueOf(buf);

        }




}

package main.java.HillelTracker.configuration;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Properties;


public class Config {
final static Logger logger=Logger.getLogger(Config.class);

    static {
        try{
            Properties properties=new Properties();
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
            user=properties.getProperty("user");
            dbUrl=properties.getProperty("db_url");
            dbUser=properties.getProperty("db_user");
            dbPassword=properties.getProperty("db_password");
            novaPoshtaURL=properties.getProperty("nova_poshta_url");
        }catch (IOException e){
            logger.error("Error in configurations: "+e);
        }
    }

    public static String user;
    public static String dbUrl;
    public static String dbUser;
    public static String dbPassword;
    public static String novaPoshtaURL;

}
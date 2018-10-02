package main3.configuration;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Properties;


public class Config {

final static Logger logger = Logger.getLogger( Config.class );

static {
    try{
        Properties properties=new Properties();
        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
        user=properties.getProperty("user");
        dbUrl=properties.getProperty("db_url");
        dbUser=properties.getProperty("db_user");
        dbPassword=properties.getProperty("db_password");
        novaPoshtaUrl=properties.getProperty("nova_poshta_url");
        novaPoshtaId=Integer.valueOf(properties.getProperty("nova_poshta_id"));
    }catch (IOException e){
        logger.error("Error in configuration: "+e);
    }
}

public static String user;
public static String dbUrl;
public static String dbUser;
public static String dbPassword;
public static String novaPoshtaUrl;
public static int novaPoshtaId;

}

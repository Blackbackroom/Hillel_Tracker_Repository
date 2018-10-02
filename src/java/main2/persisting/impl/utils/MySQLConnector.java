package main2.persisting.impl.utils;

import org.apache.log4j.Logger;
import java.sql.*;

import static main2.configuration.Config.dbPassword;
import static main2.configuration.Config.dbUrl;
import static main2.configuration.Config.dbUser;

public class MySQLConnector {
    final static Logger logger=Logger.getLogger(MySQLConnector.class);

    public  MySQLConnector(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            logger.error("Wrong driver: "+e);
        }
    }

    public Connection getConnection(){
        Connection connection=null;
        try{
            connection=DriverManager.getConnection(dbUrl,dbUser,dbPassword);
        }catch (SQLException e){
            logger.error("Error in connection: "+e);
        }return connection;
    }
}

package atmBank.persisting.impl.utils;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static atmBank.configuration.Config.dbPassword;
import static atmBank.configuration.Config.dbUrl;
import static atmBank.configuration.Config.dbUser;

public class MySQLConnector {
    final static Logger logger=Logger.getLogger(MySQLConnector.class);

    public MySQLConnector(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            logger.error("Wrong driver");
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

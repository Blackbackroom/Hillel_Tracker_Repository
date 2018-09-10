package HillelTracker.persisting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static HillelTracker.configuration.Config.dbUrl;
import static HillelTracker.configuration.Config.dbUser;
import static HillelTracker.configuration.Config.dbPassword;
import static HillelTracker.configuration.Config.user;

public class MySQLConnector {
    public  MySQLConnector(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        Connection connection=null;
        try{
            connection=DriverManager.getConnection(dbUrl,dbUser,dbPassword);
        }catch (SQLException e){
            e.printStackTrace();
        }return connection;
    }
}

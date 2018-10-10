package tracker.persisting;

import tracker.model.Invoice;
import tracker.model.TC;
import tracker.persisting.util.MySQLConnector;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepositoryInvoiceImpl implements RepositoryInvoice {
MySQLConnector mySQLConnector=new MySQLConnector();
final static Logger logger=Logger.getLogger(RepositoryInvoiceImpl.class);


    @Override
    public void add(TC tc, String number) {
        Invoice invoice = new Invoice(number);
        invoice.setTcId(tc.getId());
        try (Connection connection=mySQLConnector.getConnection();
             PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO `invoices` (tc_id, number, available, status) VALUES(?,?,?,?)")){
                preparedStatement.setInt(1, invoice.getTcId());
                preparedStatement.setString(2, invoice.getNumber());
                preparedStatement.setBoolean(3, invoice.getAvailable());
                preparedStatement.setString(4, invoice.getStatus());
                preparedStatement.executeUpdate();
        }catch (SQLException e){
            logger.error("Add invoice throws exception: "+e);
        }

    }

    @Override
    public Invoice get(int tcId, String number) {
        Invoice invoice = null;

        try (Connection connection=mySQLConnector.getConnection();
             PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM `invoices` WHERE tc_id=? AND number=?")){
                preparedStatement.setInt(1, tcId);
                preparedStatement.setString(2, number);
            try(ResultSet resultSet=preparedStatement.executeQuery()){
                while(resultSet.next()){
                    if(resultSet.getString("number")!=null) {
                        invoice = new Invoice(resultSet.getString("number"));
                        invoice.setTcId(resultSet.getInt("tc_id"));
                        invoice.setAvailable(resultSet.getBoolean("available"));
                        invoice.setStatus(resultSet.getString("status"));
                    }
                    else {
                        invoice=null;
                    }
                }
            }
        }catch (SQLException e){
            logger.error("Get invoice throws exception: "+e);
        }

        return invoice;
    }

    @Override
    public void delete(int tcId, String number) {
        try (Connection connection=mySQLConnector.getConnection();
             PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM `invoices` WHERE tc_id=? AND number=?")){
                preparedStatement.setInt(1, tcId);
                preparedStatement.setString(2, number);
                preparedStatement.executeUpdate();
        }catch (SQLException e){
            logger.error("Delete invoice throws exception: "+e);
        }

    }

    @Override
    public void update(Invoice invoice) {
        try (Connection connection=mySQLConnector.getConnection();
             PreparedStatement preparedStatement=connection.prepareStatement("UPDATE `invoices` SET available=?, status=? WHERE tc_id=? AND number=?")){
                preparedStatement.setBoolean(1, invoice.getAvailable());
                preparedStatement.setString(2, invoice.getStatus());
                preparedStatement.setInt(3, invoice.getTcId());
                preparedStatement.setString(4, invoice.getNumber());
                preparedStatement.executeUpdate();
        }catch (SQLException e){
            logger.error("Update invoice throws exception: "+e);
        }
    }

    @Override
    public List<Invoice> getAvailable() {
        List<Invoice> invoices=new ArrayList<>();

        try(Connection connection=mySQLConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM `invoices` WHERE available=?")){
            preparedStatement.setBoolean(1, true);
                try(ResultSet resultSet=preparedStatement.executeQuery()){
                    while (resultSet.next()){
                        Invoice invoice=new Invoice(resultSet.getString("number"));
                        invoice.setTcId(resultSet.getInt("tc_id"));
                        invoice.setAvailable(resultSet.getBoolean("available"));
                        invoice.setStatus(resultSet.getString("status"));
                        invoices.add(invoice);
                    }
            }
        }catch (SQLException e){
            logger.error("Get available invoices throws exception "+e);
        }

        return invoices;
    }


}

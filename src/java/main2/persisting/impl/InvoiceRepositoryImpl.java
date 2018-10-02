package main2.persisting.impl;

import main2.model.Invoice;
import main2.persisting.InvoiceRepository;
import main2.persisting.impl.utils.MySQLConnector;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InvoiceRepositoryImpl implements InvoiceRepository {

    final static Logger logger=Logger.getLogger(InvoiceRepositoryImpl.class);
    MySQLConnector mySQLConnector=new MySQLConnector();


    @Override
    public void addInvoiceToRepository(Invoice invoice) {
        try(Connection connection=mySQLConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO `invoices` (tc_id, number, available, status) VALUES(?,?,?,?)")){
            preparedStatement.setInt(1, invoice.getTransportCompanyId());
            preparedStatement.setString(2, invoice.getId());
            preparedStatement.setBoolean(3, invoice.isAvailable());
            preparedStatement.setString(4, invoice.getStatus());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            logger.error("Add invoice to repository throws exception");
        }
    }

    @Override
    public void removeInvoiceFromRepository(Invoice invoice) {
        try(Connection connection=mySQLConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM `invoices` WHERE tc_id=?, number=?")){
            preparedStatement.setInt(1, invoice.getTransportCompanyId());
            preparedStatement.setString(2, invoice.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            logger.error("Remove invoice from repository throws exception");
        }
    }

    @Override
    public void updateInvoiceInRepository(Invoice invoice) {
        try(Connection connection=mySQLConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("UPDATE `invoices` SET available=?, status=? WHERE tc_id=?, number=?")){
            preparedStatement.setBoolean(1, invoice.isAvailable());
            preparedStatement.setString(2, invoice.getStatus());
            preparedStatement.setInt(3, invoice.getTransportCompanyId());
            preparedStatement.setString(4, invoice.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            logger.error("Update invoice in repository throws exception");
        }
    }

    @Override
    public Invoice getInvoiceFromRepository(int transportCompanyId, String number) {
        Invoice invoice=new Invoice(number);
        invoice.setTransportCompanyId(transportCompanyId);

        try(Connection connection=mySQLConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM `invoices` WHERE tc_id=?, number=?")){
            preparedStatement.setInt(1, transportCompanyId);
            preparedStatement.setString(2, number);
            try(ResultSet resultSet=preparedStatement.executeQuery()){
                while(resultSet.next()){
                invoice.setAvailable(resultSet.getBoolean("available"));
                invoice.setStatus(resultSet.getString("status"));
                }
            }
        }catch (SQLException e){
            logger.error("Get invoice from repository throws exception");
        }
        return invoice;
    }
}

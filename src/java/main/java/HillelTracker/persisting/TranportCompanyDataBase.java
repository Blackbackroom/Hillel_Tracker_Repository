package main.java.HillelTracker.persisting;

import main.java.HillelTracker.dataModel.Invoice;
import main.java.HillelTracker.dataModel.TransportCompany;
import main.java.HillelTracker.persisting.MySQLConnector;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TranportCompanyDataBase implements TransportCompanyRepository {
    MySQLConnector mySQLConnector=new MySQLConnector();
    InvoiceDataBase invoiceDataBase=new InvoiceDataBase();
    final static Logger logger=Logger.getLogger(TranportCompanyDataBase.class);

    // ======================================================================
    public void addTransportCompany(TransportCompany transportCompany) {

        try(Connection connection=mySQLConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO `transport_company` (tc_id, tc_name, tc_url) VALUES(?,?,?)")) {
            preparedStatement.setInt(1,transportCompany.getId());
            preparedStatement.setString(2, transportCompany.getName());
            preparedStatement.setString(3,transportCompany.getURL());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Add transport company throws exception: "+e);
        }

            for (Invoice invoice:transportCompany.getInvoices()
                 ) {
                invoiceDataBase.addInvoice(invoice);
            }

    }

    //===============================================================================
    public void updateTransportCompany(TransportCompany transportCompany) {
        try(Connection connection=mySQLConnector.getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("UPDATE `transport_company` SET tc_name=?, tc_url=? WHERE tc_id=?")){
            preparedStatement.setString(1, transportCompany.getName());
            preparedStatement.setString(2, transportCompany.getURL());
            preparedStatement.setInt(3, transportCompany.getId());
        }catch (SQLException e){
            logger.error("Update transport company throws exception "+e);
        }

    }

    //==================================================================================
    public void deleteTransportCompany(int id) {

        try(Connection connection=mySQLConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM `transport_company` WHERE tc_id=?")){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            logger.error("Delete transport company throws exception: "+e);
        }

        try(Connection connection=mySQLConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM `invoice` WHERE tc_id=?")){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            logger.error("Delete transport company throws exception: "+e);
        }

    }

    //===============================================================================
    public TransportCompany getTransportCompany(int id) {
        TransportCompany transportCompany=new TransportCompany();
        List<Invoice> invoices=new ArrayList<Invoice>();

        try(Connection connection=mySQLConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM `transport_company` WHERE tc_id=?")){
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet=preparedStatement.executeQuery()){
                while (resultSet.next()){
                    transportCompany.setId(resultSet.getInt("tc_id"));
                    transportCompany.setName(resultSet.getString("tc_name"));
                    transportCompany.setURL(resultSet.getString("tc_url"));
                }
            }catch (SQLException e){
                logger.error("Get transport company throws exception: "+e);
            }

        }catch (SQLException e){
            logger.error("Get transport company throws exception: "+e);
        }

        try (Connection connection = mySQLConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `invoice` WHERE tc_id=?")){
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet=preparedStatement.executeQuery()){
                while (resultSet.next()){
                    Invoice invoice=new Invoice();
                    invoice.setTransportCompanyId(resultSet.getInt("tc_id"));
                    invoice.setNumber(resultSet.getString("invoice_number"));
                    invoice.setActivity(resultSet.getBoolean("invoice_activity"));
                    invoice.setStatus(resultSet.getString("invoice_status"));
                    invoices.add(invoice);
                }

            }catch (SQLException e){
                logger.error("Get transport company throws exception: "+e);
            }

        }catch (SQLException e){
            logger.error("Get transport company throws exception: "+e);
        }
        transportCompany.setInvoices(invoices);

        return transportCompany;
    }


}

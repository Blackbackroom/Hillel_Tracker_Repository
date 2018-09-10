package HillelTracker.persisting;

import HillelTracker.dataModel.Invoice;
import HillelTracker.dataModel.TransportCompany;
import HillelTracker.persisting.MySQLConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TranportCompanyDataBase implements TransportCompanyRepository {
    MySQLConnector mySQLConnector=new MySQLConnector();
    InvoiceDataBase invoiceDataBase=new InvoiceDataBase();

    // ======================================================================
    public void addTransportCompany(TransportCompany transportCompany) {

        try {Connection connection=mySQLConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO `transport_company` (tc_id, tc_name, tc_url) VALUES(?,?,?)");
            preparedStatement.setInt(1,transportCompany.getId());
            preparedStatement.setString(2, transportCompany.getName());
            preparedStatement.setString(3,transportCompany.getURL());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

            for (Invoice invoice:transportCompany.getInvoices()
                 ) {
                invoiceDataBase.addInvoice(invoice);
            }

    }

    //===============================================================================
    public void updateTransportCompany(TransportCompany transportCompany) {
        //Simple way to update Transport company in DataBase
        deleteTransportCompany(transportCompany.getId());
        addTransportCompany(transportCompany);

    }

    //==================================================================================
    public void deleteTransportCompany(int id) {

        try{Connection connection=mySQLConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM `transport_company` WHERE tc_id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

        try{Connection connection=mySQLConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM `invoice` WHERE tc_id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    //===============================================================================
    public TransportCompany getTransportCompany(int id) {
        TransportCompany transportCompany=new TransportCompany();
        List<Invoice> invoices=new ArrayList<Invoice>();

        try{Connection connection=mySQLConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM `transport_company` WHERE tc_id=?");
            preparedStatement.setInt(1, id);

            try {ResultSet resultSet=preparedStatement.executeQuery();
                while (resultSet.next()){
                    transportCompany.setId(resultSet.getInt("tc_id"));
                    transportCompany.setName(resultSet.getString("tc_name"));
                    transportCompany.setURL(resultSet.getString("tc_url"));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        try {
            Connection connection = mySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `invoice` WHERE tc_id=?");
            preparedStatement.setInt(1, id);

            try {ResultSet resultSet=preparedStatement.executeQuery();
                while (resultSet.next()){
                    Invoice invoice=new Invoice();
                    invoice.setTransportCompanyId(resultSet.getInt("tc_id"));
                    invoice.setNumber(resultSet.getLong("invoice_number"));
                    invoice.setActivity(resultSet.getBoolean("invoice_activity"));
                    invoice.setStatus(resultSet.getString("invoice_status"));
                    invoices.add(invoice);
                }

            }catch (SQLException e){
                e.printStackTrace();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        transportCompany.setInvoices(invoices);

        return transportCompany;
    }


}

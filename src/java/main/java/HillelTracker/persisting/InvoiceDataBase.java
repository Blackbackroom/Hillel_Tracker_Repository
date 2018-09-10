package HillelTracker.persisting;

import HillelTracker.dataModel.Invoice;
import HillelTracker.dataModel.TransportCompany;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InvoiceDataBase implements InvoiceRepository {
MySQLConnector mySQLConnector=new MySQLConnector();

    //====================================================
    public void addInvoice(Invoice invoice) {

        try{Connection connection=mySQLConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO `invoice` (tc_id, invoice_number, invoice_activity, invoice_status) VALUES(?,?,?,?)");
            preparedStatement.setInt(1, invoice.getTransportCompanyId());
            preparedStatement.setLong(2,invoice.getNumber());
            preparedStatement.setBoolean(3, invoice.getActivity());
            preparedStatement.setString(4, invoice.getStatus());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    //========================================================
    public void updateInvoice(Invoice invoice) {
       deleteInvoice(invoice);
       addInvoice(invoice);

    }

    //========================================================
    public void deleteInvoice(Invoice invoice) {
        try{Connection connection=mySQLConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM `invoice` WHERE tc_id=?, invoice_number=?");
            preparedStatement.setInt(1, invoice.getTransportCompanyId());
            preparedStatement.setLong(2, invoice.getNumber());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    //=======================================================
    public Invoice getInvoice(int transportCompanyId, int invoiceNumber) {
        Invoice invoice=new Invoice();

        try{Connection connection=mySQLConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM `invoice` WHERE tc_id=?, invoice_number=?");
            preparedStatement.setInt(1, transportCompanyId);
            preparedStatement.setLong(2,invoiceNumber);
            try{ResultSet resultSet=preparedStatement.executeQuery();
                while (resultSet.next()){
                    invoice.setTransportCompanyId(transportCompanyId);
                    invoice.setNumber(invoiceNumber);
                    invoice.setActivity(resultSet.getBoolean("invoice_activity"));
                    invoice.setStatus(resultSet.getString("invoice_status"));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return invoice;
    }

    //====================================================================
    public void deleteAllInvoicesOfTransportCompany(TransportCompany transportCompany) {

        try{Connection connection=mySQLConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM `invoice` WHERE tc_id=?");
            preparedStatement.setInt(1, transportCompany.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}

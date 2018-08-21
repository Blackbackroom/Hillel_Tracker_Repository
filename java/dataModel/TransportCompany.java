package dataModel;

import java.util.ArrayList;
import java.util.List;

public class TransportCompany {
    private int transportCompanyId;
    private String name;
    private String transportCompanyURL;
    private List<Invoice> invoices=new ArrayList<>();



    public int getTransportCompanyId() {
        return transportCompanyId;
    }

    public void setTransportCompanyId(int transportCompanyId) {
        this.transportCompanyId = transportCompanyId;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getTransportCompanyURL() {
        return transportCompanyURL;
    }

    public void setTransportCompanyURL(String transportCompanyURL) {
        this.transportCompanyURL = transportCompanyURL;
    }



    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }
}

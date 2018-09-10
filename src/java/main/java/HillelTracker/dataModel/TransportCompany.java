package HillelTracker.dataModel;

import java.util.ArrayList;
import java.util.List;

public class TransportCompany {

    // Data
    private int Id;
    private String Name;
    private String URL;
    private List<Invoice> invoices=new ArrayList<Invoice>();

    // Setters and Getters

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }
}

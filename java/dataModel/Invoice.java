package dataModel;
import java.util.LinkedList;
import java.util.List;

public class Invoice {
    private int invoiceId;
    private int transportCompany;
    private long invoiceNumber; //Обычно инвойс от 6 до 12 цифр
    private boolean activity; //Проверка на актиность. Если false, не отслеживать.
    private List<Status> statuses = new LinkedList<>();



    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }


    public int getTransportCompany() {
        return transportCompany;
    }

    public void setTransportCompany(int transportCompany) {
        this.transportCompany = transportCompany;
    }


    public long getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(long invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }


    public boolean isActivity() {
        return activity;
    }

    public void setActivity(boolean activity) {
        this.activity = activity;
    }


    public List<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Status> statuses) {
        this.statuses = statuses;
    }
}

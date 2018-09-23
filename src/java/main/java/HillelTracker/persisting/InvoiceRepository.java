package main.java.HillelTracker.persisting;

import main.java.HillelTracker.dataModel.Invoice;
import main.java.HillelTracker.dataModel.TransportCompany;

public interface InvoiceRepository {
    void addInvoice(Invoice invoice);
    void updateInvoice(Invoice invoice);
    void deleteInvoice(Invoice invoice);
    Invoice getInvoice(int transportCompanyId, String invoiceNumber);
    void deleteAllInvoicesOfTransportCompany(TransportCompany transportCompany);
}

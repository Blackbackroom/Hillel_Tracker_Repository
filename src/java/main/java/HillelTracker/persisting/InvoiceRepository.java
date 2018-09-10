package HillelTracker.persisting;

import HillelTracker.dataModel.Invoice;
import HillelTracker.dataModel.TransportCompany;

public interface InvoiceRepository {
    void addInvoice(Invoice invoice);
    void updateInvoice(Invoice invoice);
    void deleteInvoice(Invoice invoice);
    Invoice getInvoice(int transportCompanyId, int invoiceNumber);
    void deleteAllInvoicesOfTransportCompany(TransportCompany transportCompany);
}

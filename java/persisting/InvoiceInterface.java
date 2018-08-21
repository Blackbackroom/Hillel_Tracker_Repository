package persisting;

import dataModel.Invoice;
import dataModel.Status;

import java.util.List;

public interface InvoiceInterface {

    void addInvoice(Invoice invoice);

    Invoice getInvoice(int id);

    void removeInvoice(int id);

    Invoice updateInvoice(Invoice invoice);

    void addStatusToInvoice(Invoice invoice, Status status);

    List<Status> getAllStatusesOfInvoice(Invoice invoice);
}

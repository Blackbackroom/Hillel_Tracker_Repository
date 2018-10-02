package main2.persisting;

import main2.model.Invoice;

public interface InvoiceRepository {
    void addInvoiceToRepository(Invoice invoice);
    void removeInvoiceFromRepository(Invoice invoice);
    void updateInvoiceInRepository(Invoice invoice);
    Invoice getInvoiceFromRepository(int transportCompanyId, String number);


}

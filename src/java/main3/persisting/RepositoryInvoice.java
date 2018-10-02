package main3.persisting;

import main3.model.Invoice;

public interface RepositoryInvoice {
    void add (Invoice invoice);
    Invoice get (int tcId, String number);
    void delete (int tcId, String number);
    void update (Invoice invoice);
    void changeTc (Invoice invoice, int tcId);
}

package main3.persisting;

import main3.model.Invoice;
import main3.model.TC;
import java.util.List;

public interface RepositoryInvoice {
    void add (TC tc, String number);
    Invoice get (int tcId, String number);
    void delete (int tcId, String number);
    void update (Invoice invoice);
    List<Invoice> getAvailable();
}

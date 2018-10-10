package tracker.persisting;

import tracker.model.Invoice;
import tracker.model.TC;
import java.util.List;

public interface RepositoryInvoice {
    void add (TC tc, String number);
    Invoice get (int tcId, String number);
    void delete (int tcId, String number);
    void update (Invoice invoice);
    List<Invoice> getAvailable();
}

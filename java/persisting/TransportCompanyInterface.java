package persisting;

import dataModel.Invoice;
import dataModel.TransportCompany;

import java.util.List;

public interface TransportCompanyInterface {

    void addTransportCompany(TransportCompany transportCompany);

    TransportCompany getTransportCompany(int id);

    void removeTransportCompany(int id);

    TransportCompany updateTransportCompany(TransportCompany transportCompany);

    void addInvoiceToTransportCompany(TransportCompany transportCompany, Invoice invoice);

    void removeInvoiceFromTransportCompany(TransportCompany transportCompany, Invoice invoice);

    List<Invoice> getAllInvoicesOfTransportCompany(TransportCompany transportCompany);
}

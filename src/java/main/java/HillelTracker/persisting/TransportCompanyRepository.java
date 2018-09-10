package HillelTracker.persisting;

import HillelTracker.dataModel.Invoice;
import HillelTracker.dataModel.TransportCompany;

public interface TransportCompanyRepository {
    void addTransportCompany(TransportCompany transportCompany);
    void updateTransportCompany(TransportCompany transportCompany);
    void deleteTransportCompany(int id);
    TransportCompany getTransportCompany(int id);


}

package main.java.HillelTracker.persisting;

import main.java.HillelTracker.dataModel.Invoice;
import main.java.HillelTracker.dataModel.TransportCompany;

public interface TransportCompanyRepository {
    void addTransportCompany(TransportCompany transportCompany);
    void updateTransportCompany(TransportCompany transportCompany);
    void deleteTransportCompany(int id);
    TransportCompany getTransportCompany(int id);


}

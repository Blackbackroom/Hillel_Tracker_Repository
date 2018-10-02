package main2.model;

import java.io.IOException;
import java.net.ProtocolException;
import java.util.List;

public interface TransportCompany {


    String checkInvoiceStatus(Invoice invoice) throws IOException;
}

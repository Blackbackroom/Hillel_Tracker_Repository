package main.java.HillelTracker.starter;

import main.java.HillelTracker.dataModel.Invoice;
import main.java.HillelTracker.dataModel.TransportCompany;
import main.java.HillelTracker.persisting.InvoiceDataBase;
import main.java.HillelTracker.persisting.InvoiceRepository;
import main.java.HillelTracker.persisting.TranportCompanyDataBase;
import main.java.HillelTracker.service.InvoiceService;

import java.io.IOException;

public class Starter {
    public static void main(String[] args) throws IOException {
    Invoice invoice=new Invoice();
    invoice.setNumber("59998068606256");

    InvoiceService invoiceService=new InvoiceService();
    invoiceService.getStatusFromServer(invoice);

        System.out.println(invoice.getStatus());

    }
}

package main.java.HillelTracker.service;

import main.java.HillelTracker.dataModel.Invoice;
import main.java.HillelTracker.persisting.InvoiceDataBase;

public class InvoiceService {
    private InvoiceDataBase invoiceDataBase=new InvoiceDataBase();

    public void addInvoice(Invoice invoice){
        invoice.setActivity(true);
        invoiceDataBase.addInvoice(invoice);
    }

    public void changeActivityToFalse(Invoice invoice){
        invoice.setActivity(false);
        invoiceDataBase.updateInvoice(invoice);
    }

    public void changeActivityToTrue(Invoice invoice){
        invoice.setActivity(true);
        invoiceDataBase.updateInvoice(invoice);
    }

    public void changeStatusOfinvoice(Invoice invoice, String status){
        invoice.setStatus(status);
        invoiceDataBase.updateInvoice(invoice);
    }

    public void deleteInvoice(Invoice invoice){
        invoiceDataBase.deleteInvoice(invoice);
    }

    public void changeIdOfTransportCompany(Invoice invoice, int transportCompanyId){
        invoice.setTransportCompanyId(transportCompanyId);
        invoiceDataBase.updateInvoice(invoice);
    }
}

package main3.service;

import main3.model.Invoice;
import main3.model.NovaPoshta;
import main3.model.TC;
import main3.persisting.RepositoryInvoiceImpl;

public class ServiceInvoice {
    NovaPoshta novaPoshta=new NovaPoshta();
    RepositoryInvoiceImpl repositoryInvoice=new RepositoryInvoiceImpl();

    public void addInvoice(Invoice invoice, TC tc){
        invoice.setTcId(tc.getId());
        repositoryInvoice.add(invoice);
    }

    public void removeInvoice(TC tc, String number){
        repositoryInvoice.delete(tc.getId(), number);
    }

    public Invoice getInvoice(TC tc, String number){
        Invoice invoice = null;
        invoice=repositoryInvoice.get(tc.getId(), number);
        return invoice;
    }

    public void update(Invoice invoice){
        repositoryInvoice.update(invoice);
    }

    public void changeTransportCompany(Invoice invoice, TC tc){
        repositoryInvoice.changeTc(invoice,tc.getId());
    }

    public String checkStatus(Invoice invoice){
        String status=null;

        return status;
    }

    public NovaPoshta getNovaPoshta() {
        return novaPoshta;
    }
}

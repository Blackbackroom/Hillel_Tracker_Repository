package main3.service;

import main3.model.Invoice;
import main3.model.NovaPoshta;
import main3.model.TC;
import main3.persisting.RepositoryInvoiceImpl;
import org.apache.log4j.Logger;

public class ServiceInvoice {
    NovaPoshta novaPoshta=new NovaPoshta();
    RepositoryInvoiceImpl repositoryInvoice=new RepositoryInvoiceImpl();
    final static Logger logger=Logger.getLogger(ServiceInvoice.class);

    public void addInvoice(TC tc, String number){
        if(getInvoice(tc,number)==null){
        repositoryInvoice.add(tc,number);
        logger.info("Invoice number "+number+" in Transport company "+tc.getName()+" added");
        }else{
            logger.info("Invoice number "+number+" in Transport company "+tc.getName()+" already exists");
        }

    }

    public void removeInvoice(TC tc, String number){
        if(getInvoice(tc,number)==null){
            logger.info("Invoice number "+number+" in Transport company "+tc.getName()+" doesn't exist");
        }else{
        repositoryInvoice.delete(tc.getId(), number);
        logger.info("Invoice number "+number+" in Transport company "+tc.getName()+" removed");
        }
    }

    public Invoice getInvoice(TC tc, String number){
        Invoice invoice = null;
            invoice = repositoryInvoice.get(tc.getId(), number);
        return invoice;
    }

    private void update(TC tc, String number){
        Invoice invoice=getInvoice(tc, number);
        if(invoice==null){
            logger.info("Invoice number "+number+" in Transport company "+tc.getName()+" doesn't exist");
        }else {
            repositoryInvoice.update(invoice);
            logger.info("Invoice number "+number+" in Transport company "+tc.getName()+ " updated");
        }
    }


    private String checkStatus(Invoice invoice){
        String status=null;
        switch(invoice.getTcId()){
            case 1: status=novaPoshta.checkInvoiceStatus(invoice.getNumber());
            break;
        }
        return status;
    }

    public void checkAvailableInvoices(){
        for (Invoice invoice:repositoryInvoice.getAvailable()
             ) {
            invoice.setStatus(checkStatus(invoice));
            repositoryInvoice.update(invoice);
        }

    }


    public NovaPoshta getNovaPoshta() {
        return novaPoshta;
    }
}

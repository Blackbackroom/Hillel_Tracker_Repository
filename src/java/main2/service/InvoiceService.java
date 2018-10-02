package main2.service;


import main2.model.Invoice;
import main2.model.NovaPoshta;
import main2.persisting.impl.InvoiceRepositoryImpl;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class InvoiceService {

    final static Logger logger=Logger.getLogger(InvoiceService.class);
    private NovaPoshta novaPoshta=new NovaPoshta();
    private InvoiceRepositoryImpl invoiceRepository;

    public void setInvoiceRepository(InvoiceRepositoryImpl invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public NovaPoshta getNovaPoshta() {
        return novaPoshta;
    }


    public void addInvoice(Invoice invoice){
        if(invoice.getTransportCompanyId()==novaPoshta.getId()){
            novaPoshta.getAllInvoices().add(invoice);
            invoiceRepository.addInvoiceToRepository(invoice);
        }
    }

    public void removeInvoice(Invoice invoice){
        if(invoice.getTransportCompanyId()==novaPoshta.getId()){
            novaPoshta.getAllInvoices().remove(invoice);
            invoice.setTransportCompanyId(0);
            invoiceRepository.updateInvoiceInRepository(invoice);
        }
    }

    public void deleteInvoice(Invoice invoice){
        if(invoice.getTransportCompanyId()==novaPoshta.getId()){
            novaPoshta.getAllInvoices().remove(invoice);
        }
        invoiceRepository.removeInvoiceFromRepository(invoice);
    }

    public String checkInvoicesStatus(Invoice invoice){
        String s=null;
        try {

            invoice.setStatus(novaPoshta.checkInvoiceStatus(invoice));
            s=novaPoshta.checkInvoiceStatus(invoice);
//                invoiceRepository.updateInvoiceInRepository(invoice);

        }catch (IOException e){
            logger.error("Check available invoices in NovaPoshta throws exception");
        }return s;
    }

    public Invoice getInvoice(String number, int transportCompanyId){

        Invoice invoice=null;
        invoice=invoiceRepository.getInvoiceFromRepository(transportCompanyId,number);
        return invoice;
    }


}

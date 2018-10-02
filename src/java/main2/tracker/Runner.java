package main2.tracker;


import main2.model.Invoice;
import main2.persisting.impl.InvoiceRepositoryImpl;
import main2.service.InvoiceService;

import java.io.IOException;

public class Runner {
    public static void main(String[] args) {

        InvoiceService invoiceService=new InvoiceService();
        InvoiceRepositoryImpl invoiceRepository=new InvoiceRepositoryImpl();
        invoiceService.setInvoiceRepository(invoiceRepository);

        Invoice invoice1=new Invoice("59000367273697");
        Invoice invoice2=new Invoice("59000353003366");

        invoice1.setTransportCompanyId(invoiceService.getNovaPoshta().getId());
        invoice2.setTransportCompanyId(invoiceService.getNovaPoshta().getId());
        invoice1.setAvailable(true);
        invoice2.setAvailable(true);

        invoiceService.addInvoice(invoice1);
        invoiceService.addInvoice(invoice2);

        System.out.println(invoiceService.checkInvoicesStatus(invoice1));



        System.out.println("59000353003366"+" "+invoiceService.getInvoice("59000353003366", invoiceService.getNovaPoshta().getId()).getStatus());


    }
}

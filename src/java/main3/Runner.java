package main3;

import main3.service.ServiceInvoice;

public class Runner {
    public static void main(String[] args) {
        ServiceInvoice serviceInvoice=new ServiceInvoice();


        serviceInvoice.addInvoice(serviceInvoice.getNovaPoshta(),"59998069085966");
        serviceInvoice.addInvoice(serviceInvoice.getNovaPoshta(),"59998069159924");

        serviceInvoice.checkAvailableInvoices();

        System.out.println(serviceInvoice.getInvoice(serviceInvoice.getNovaPoshta(),"59998069085966").getStatus());
        System.out.println(serviceInvoice.getInvoice(serviceInvoice.getNovaPoshta(),"59998069159924").getStatus());


//        serviceInvoice.removeInvoice(serviceInvoice.getNovaPoshta(),"59998069085966");
//        serviceInvoice.removeInvoice(serviceInvoice.getNovaPoshta(),"59998069159924");


    }
}

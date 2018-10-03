package main3;

import main3.service.ServiceInvoice;
import org.apache.log4j.Logger;

public class Runner {
    final static Logger logger=Logger.getLogger(Runner.class);
    public static void main(String[] args) {

        ServiceInvoice serviceInvoice=new ServiceInvoice();


        serviceInvoice.addInvoice(serviceInvoice.getNovaPoshta(),"59998069085966");
        serviceInvoice.addInvoice(serviceInvoice.getNovaPoshta(),"59998069159924");

        serviceInvoice.checkAvailableInvoices();

        logger.info(serviceInvoice.getInvoice(serviceInvoice.getNovaPoshta(),"59998069085966").getStatus());
        logger.info(serviceInvoice.getInvoice(serviceInvoice.getNovaPoshta(),"59998069159924").getStatus());

//        serviceInvoice.removeInvoice(serviceInvoice.getNovaPoshta(),"59998069085966");
//        serviceInvoice.removeInvoice(serviceInvoice.getNovaPoshta(),"59998069159924");


    }
}

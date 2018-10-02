package main3;
import main3.model.NovaPoshta;
import main3.model.Invoice;
import main3.model.TC;
import main3.service.ServiceInvoice;

public class Runner {
    public static void main(String[] args) {
        ServiceInvoice serviceInvoice=new ServiceInvoice();


        Invoice invoice=new Invoice("59000367273697");
        Invoice invoice1=new Invoice("59998014551455");

//        serviceInvoice.addInvoice(invoice, serviceInvoice.getNovaPoshta());
//        serviceInvoice.removeInvoice(serviceInvoice.getNovaPoshta(), invoice.getNumber());


    }
}

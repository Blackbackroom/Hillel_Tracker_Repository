package dataModel;

import java.util.ArrayList;
import java.util.List;

public class Invoice {
    private int invoiceId;
    private int transportCompany;
    private long invoiceNumber; //Обычно инвойс от 6 до 12 цифр
    private boolean activity; //Проверка на актиность. Если false, не отслеживать.
    List<Status> statuses=new ArrayList<Status>();
    // Статусы нужно сделать в виде Deque, чтобы соблюдать LIFO



}

package atmBank.model;

import org.apache.log4j.Logger;


import java.util.Date;

public class Card {
    final static Logger logger=Logger.getLogger(Card.class);

    private int id;
    private int account;
    private int pin;
    private TypeCard type;
    private Date endDate;
    private boolean block;
    private String reason;
    private String nameCardHolder;

    public String getNameCardHolder() {
        return nameCardHolder;
    }

    public void setNameCardHolder(String nameCardHolder) {
        this.nameCardHolder = nameCardHolder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public TypeCard getType() {
        return type;
    }

    public void setType(TypeCard type) {
        this.type = type;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

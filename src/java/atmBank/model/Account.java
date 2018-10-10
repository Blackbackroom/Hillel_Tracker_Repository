package atmBank.model;

public class Account {
    private int id;
    private Money money;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }
}

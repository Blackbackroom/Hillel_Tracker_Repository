package atmBank.model;

public class Atm {
    private int id;
    private Money money;
    private Card card;

    public Atm(int id, int money){
        this.id=id;
        this.money.setUah(money);
    }

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

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}

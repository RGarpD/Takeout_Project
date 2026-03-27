public class Customer {
    private String name;
    private int money;

    // Constructor
    public Customer(String name, int money) {
        this.name = name;
        this.money = money;
    }
    // Variable name getter setter
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Variable money getter setter
    public int getMoney() {
        return this.money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}

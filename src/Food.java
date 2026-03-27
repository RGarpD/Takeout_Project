public class Food implements PricedItem<Integer>{
    private String name;
    private String description;
    private int price;

    // Constructor
    public Food(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
    // Variable name getter setter
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Variable description getter setter
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Variable price getter setter
    @Override
    public Integer getPrice() {
        return this.price;
    }
    @Override
    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString(){
        return String.format("Enjoy %s: %s    Cost: $%d", name, description, price);
    }
}
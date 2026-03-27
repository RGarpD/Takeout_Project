import java.util.Scanner;

public class TakeOutSimulator{
    private Customer customer;
    private FoodMenu menu;
    private Scanner input;

    public TakeOutSimulator(Customer customer, FoodMenu menu, Scanner input){
        this.customer = customer;
        this.menu = menu;
        this.input = input;
    }

    private <T> T getResponse(String userInputPrompt, UserInputRetriever<T> userInputRetriever){
        while(true){
            System.out.println(userInputPrompt);

            if(this.input.hasNextInt()){
                int retrieved = this.input.nextInt();
                try{
                    return userInputRetriever.produceOutput(retrieved);
                }catch(IllegalArgumentException e){
                    System.out.println(retrieved+ " is not a valid input. Try Again!");
                }catch(Exception e){
                    System.out.println("An unexpected error occurred. "+e.getMessage());
                }
            }else{
                System.out.println("Input needs to be an `int` type. Try Again!");
                this.input.next();
            }

        }
    }

    boolean shouldSimulate(){
        String userPrompt = "Enter 1 to CONTINUE simulation or 0 to EXIT program: ";

        UserInputRetriever<Boolean> userInputRetriever = (selection) -> {
                if(selection != 0 && selection != 1){
                    throw new IllegalArgumentException();
                }
                if (selection == 1 && customer.getMoney() >= menu.getLowestCostFood().getPrice()) {
                    System.out.println("You have decided to continue simulation!\n");
                    return true;
                } else if (selection == 1 && customer.getMoney() < menu.getLowestCostFood().getPrice()) {
                    System.out.println("You don't have enough money to continue shopping :( - ending simulation...");
                    return false;
                } else {
                    System.out.println("Ending simulation...");
                    return false;
                }
        };
        return getResponse(userPrompt, userInputRetriever);
    }

    Food getMenuSelection(){
        String userPrompt = "Today's Menu Options!\n";

        UserInputRetriever<Food> userInputRetriever = (selection) -> {
            Food selectedFood = this.menu.getFood(selection);
            if(selectedFood == null){
                throw new IllegalArgumentException();
            }
                return selectedFood;
        };
        return getResponse(userPrompt, userInputRetriever);
    }

    boolean isStillOrderingFood(){
        String userPrompt = "Enter 1 to CONTINUE shopping or 0 to CHECKOUT: ";

        UserInputRetriever<Boolean> userInputRetriever = (selection) -> {
            if (selection != 0 && selection != 1) {
                throw new IllegalArgumentException();
            }
            if(selection == 1)
                return true;
            else
                return false;
        };
        return getResponse(userPrompt, userInputRetriever);
    }

    void checkoutCustomer(ShoppingBag<Food> shoppingBag){
        System.out.println("Processing payment ...");

        int remainingCustomerMoney = this.customer.getMoney() - shoppingBag.getTotalPrice();
        this.customer.setMoney(remainingCustomerMoney);

        System.out.println("Your remaining money: $" + remainingCustomerMoney);

        System.out.println("Thank you and enjoy your food!");
    }

    void takeOutPrompt(){
        ShoppingBag<Food> shoppingBag = new ShoppingBag<>();
        int customerMoneyLeft =  this.customer.getMoney();

        while(true){
            System.out.printf("You have $%d left to spend\n\n", customerMoneyLeft);
            Food chosenFood = getMenuSelection();
            if(chosenFood.getPrice() <= customerMoneyLeft){
                customerMoneyLeft -= chosenFood.getPrice();
                shoppingBag.addItem(chosenFood);
            }else {
                System.out.println("Oops, Looks like you don't have enough money, please choose another food or checkout");
                if(!isStillOrderingFood()) {
                    checkoutCustomer(shoppingBag);
                    break;
                }
            }
        }
    }

    public void startTakeOutSimulator(){
        System.out.println("Hello, welcome to my restaurant!");
        while(shouldSimulate()){
            System.out.println("Welcome "+customer.getName());
            takeOutPrompt();
        }
    }
}
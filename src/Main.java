import java.util.InputMismatchException;
import java.util.Scanner;

public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    System.out.println("Please enter your name: ");
    String customerName = input.nextLine();

    int money = 0;
    System.out.println("Please enter your budget: ");
    while(true){
        try {
            money = input.nextInt();
            if(money <= 0) {
                System.out.println("Get some money...\nPlease enter your budget: ");
            }else if(money > 0) {
                break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter a number! error: "+e.getMessage());
            input.next();
        }
    }
    Customer customer = new Customer(customerName,money);
    FoodMenu foodMenu = new FoodMenu();
    TakeOutSimulator takeOutSimulator = new TakeOutSimulator(customer, foodMenu, input);

    takeOutSimulator.startTakeOutSimulator();
}

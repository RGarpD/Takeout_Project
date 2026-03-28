import java.util.ArrayList;
import java.util.List;

public class FoodMenu{
    private List<Food> menu;
    public FoodMenu(){

        this.menu = new ArrayList<>();

        this.menu.add(new Food("Taco", "Yummy tacos", 5));
        this.menu.add(new Food("Burger", "Juicy beef burger", 12));
        this.menu.add(new Food("Pizza", "Margherita pizza", 10));
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0;i<this.menu.size();i++){
            stringBuilder.append(i+1)
                    .append(". Enjoy ")
                    .append(this.menu.get(i))
                    .append("\n");
        }
        return stringBuilder.toString();
    }

    public Food getFood(int index){
        if(index > 0 && index <= this.menu.size()){
        return this.menu.get(index-1);
        }else{
            System.out.println("Food Not Found");
            return null;
        }
    }

    public Food getLowestCostFood(){
        if(this.menu.isEmpty()){
            return null;
        }
        int lowestCostIndex = 0;
        for(int i = 1;i<this.menu.size();i++){
            if(this.menu.get(i).getPrice() < this.menu.get(lowestCostIndex).getPrice()){
                lowestCostIndex = i;
            }
        }
        return this.menu.get(lowestCostIndex);
    }

    public int getSize(){
        return this.menu.size();
    }
}

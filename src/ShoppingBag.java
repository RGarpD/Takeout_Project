import java.util.HashMap;
import java.util.Map;

public class ShoppingBag<T extends PricedItem<Integer>>{
    private Map<T, Integer> shoppingBag;
    public ShoppingBag(){
        shoppingBag = new HashMap<>();
    }
    public void addItem(T item){
        if(shoppingBag.containsKey(item)){
            shoppingBag.put(item, shoppingBag.get(item)+1);
        }else{
            shoppingBag.put(item, 1);
        }
    }
    public Integer getTotalPrice(){
        Integer totalPrice = 0;
        for(Map.Entry<T, Integer> item : shoppingBag.entrySet()){
            totalPrice += item.getValue() * item.getKey().getPrice();
        }
        return totalPrice;
    }

    public String getTotalItem(){
        StringBuilder sb = new StringBuilder();
        sb.append("Your ordered: \n");
        for(Map.Entry<T, Integer> item : shoppingBag.entrySet()){
            Food foodItem = (Food) item.getKey();
            int quantity = item.getValue();
            String foodName =  foodItem.getName();

              sb.append(quantity)
                .append("x ")
                .append(foodName)
                .append("\n");
        }
        return sb.toString();
    }
}

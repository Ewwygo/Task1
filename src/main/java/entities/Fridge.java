package entities;

import customExceptions.NoSuchProductException;
import customExceptions.ProductAmountException;
import manufacturers.Manufacturers;
import services.FoodManagerService;

import java.util.HashMap;

public class Fridge extends Appliances implements FoodManagerService {

    private int lowestT;

    private HashMap<String, Integer> products;

    public Fridge(int power, Manufacturers manufacturer, int lowestT){
        super(power, manufacturer);
        this.lowestT = lowestT;
        this.products = new HashMap<>();
        creationMessage();
    }

    @Override
    public void creationMessage() {
        super.creationMessage();
        System.out.println("fridge");
    }
    @Override
    public void putProduct(String product, Integer amount){
        if (products.containsKey(product)){
            products.replace(product,products.get(product)+amount);
            System.out.println("You ve add product - " + product + " amount - " + amount);
        } else {
            products.put(product,amount);
            System.out.println("You ve put product - " + product + " amount - " + amount);
        }
    }
    @Override
    public void getProduct(String product, Integer amount){
        try{
            System.out.println("Open fridge");
            if (!products.containsKey(product)){
                throw new NoSuchProductException(product);
            } else {
                if (products.get(product)< amount){
                    throw new ProductAmountException(product,amount);
                }
                else {
                    products.replace(product,products.get(product)-amount);
                    System.out.println("You ve got product - " + product + " amount - " + amount);
                }
            }
        }
        catch (NoSuchProductException | ProductAmountException e){
            System.err.println(e.getMessage());
        }
        finally {
            System.out.println("Close fridge");
        }
    }
}

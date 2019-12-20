package Entities;

import CustomExceptions.NoSuchProductException;
import CustomExceptions.ProductAmountException;
import Manufacturers.Manufacturers;
import lombok.Data;

import java.util.HashMap;
import java.util.HashSet;

public class Fridge extends Appliances {

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

    public void putProduct(String product, int amount){
        if (products.containsKey(product)){
            products.replace(product,products.get(product)+amount);
            System.out.println("You ve add product - " + product + " amount - " + amount);
        } else {
            products.put(product,amount);
            System.out.println("You ve put product - " + product + " amount - " + amount);
        }
    }

    public void getProduct(String product, int amount){
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

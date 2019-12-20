import Entities.Appliances;
import Entities.Fridge;
import Entities.Microwave;
import Manufacturers.Manufacturers;

import java.util.ArrayList;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {

        Appliances.appMeaning();

        ArrayList<Appliances> appliances = new ArrayList<>();

        appliances.add(new Fridge(1000, Manufacturers.LG,-20));
        appliances.add(new Fridge(1200,Manufacturers.LG,-25));
        appliances.add(new Fridge(1200,Manufacturers.SAMSUNG,-30));
        appliances.add(new Microwave(600,Manufacturers.XIAOMI));
        appliances.add(new Microwave(800,Manufacturers.SAMSUNG));
        appliances.add(new Microwave(1000,Manufacturers.LG));

        appliances.get(1).turnOn();
        appliances.get(2).turnOn();
        appliances.get(5).turnOn();

        int powerConsump = 0;
        Comparator<Appliances> comparator = Comparator.comparingInt(Appliances::getPower);
        appliances.sort(comparator);

        for (Appliances i: appliances
             ) {
            System.out.println(i.toString());
            if (i.isPowered()){
                powerConsump += i.getPower();
            }
        }

        System.out.println("Power consupmtion is " + powerConsump);


        Fridge fridge = (Fridge) appliances.get(5);
        fridge.putProduct("milk",1);
        fridge.getProduct("mil", 2);
    }
}

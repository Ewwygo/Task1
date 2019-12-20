package Entities;

import Manufacturers.Manufacturers;
import lombok.Data;

public class Microwave extends Appliances {

    public Microwave(int power, Manufacturers manufacturer) {
        super(power, manufacturer);
        creationMessage();
    }

    @Override
    public void creationMessage() {
        super.creationMessage();
        System.out.println("microwave");
    }
}

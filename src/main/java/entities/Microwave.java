package entities;

import annotations.ThisCodeSmells;
import manufacturers.Manufacturers;
import services.Working;


@SuppressWarnings("deprecation") // shutdown deprecation annotation
public class Microwave extends Appliances implements Working {


    public Microwave(int power, Manufacturers manufacturer) {
        super(power, manufacturer);
        creationMessage();
    }

    @ThisCodeSmells(reviewer = "user1")
    @ThisCodeSmells(reviewer = "user2")
    @ThisCodeSmells(reviewer = "3")
    @ThisCodeSmells(reviewer = "4")
    @Deprecated  // Not needed method
    @Override
    public void doWork() {
        System.out.println("Working");
    }

    @ThisCodeSmells(reviewer = "1")
    @ThisCodeSmells(reviewer = "2")
    @ThisCodeSmells(reviewer = "3")
    @Override
    public void creationMessage() {
        super.creationMessage();
        System.out.println("microwave");
    }

    @ThisCodeSmells(reviewer = "asd")
    public void meth(){

    }
}

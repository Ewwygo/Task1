package Entities;

import Interfaces.Electrical;
import Manufacturers.Manufacturers;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public abstract class Appliances implements Electrical,Comparable<Appliances> {

    private boolean powered;
    private int power;
    private Manufacturers manufacturer;

    public Appliances(int power, Manufacturers manufacturer){
        this.powered = false;
        this.power = power;
        this.manufacturer = manufacturer;
    }

    @Override
    public int compareTo(Appliances appliances) {
        return (Integer.compare(this.getPower(), appliances.getPower()));
    }

    @Override
    public final void turnOn() {
        powered = true;
    }

    @Override
    public final void turnOff() {
        powered = false;
    }

    @Override
    public void creationMessage(){
        System.out.print("New appliance created - ");
    }

    public boolean isPowered(){
        return powered;
    }

    public static void appMeaning(){
        System.out.println("Appliance is a device or piece of equipment designed to perform a specific task");
    }

    public void setPowered(boolean powered) {
        this.powered = powered;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

}

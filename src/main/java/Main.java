import annotationHandler.ThisCodeSmellsHandler;
import annotations.ProdCode;
import entities.Appliances;
import entities.Fridge;
import entities.Microwave;
import manufacturers.Manufacturers;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

    static private Class arrayList = ArrayList.class;
    static private ArrayList<Appliances> appliances;

    static {
        try {
            appliances = (ArrayList) arrayList.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)  {

        Appliances.appMeaning();
        Class fridge = Fridge.class;
        Class microwave = Microwave.class;
        try {
            Constructor fridgeCon = fridge.getDeclaredConstructor(int.class, Manufacturers.class, int.class);
            appliances.add((Fridge) fridgeCon.newInstance(1000, Manufacturers.LG,-20));
            appliances.add((Fridge) fridgeCon.newInstance(1200,Manufacturers.LG,-25));
            appliances.add((Fridge) fridgeCon.newInstance(1200,Manufacturers.SAMSUNG,-30));
            Field fridgeField = fridge.getDeclaredField("lowestT");
            fridgeField.setAccessible(true);
            fridgeField.setInt(appliances.get(1),-35);
            Constructor microwaveCon = microwave.getDeclaredConstructor(int.class, Manufacturers.class);
            appliances.add((Microwave) microwaveCon.newInstance(600,Manufacturers.XIAOMI));
            appliances.add((Microwave) microwaveCon.newInstance(800,Manufacturers.SAMSUNG));
            appliances.add((Microwave) microwaveCon.newInstance(1000,Manufacturers.LG));
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchFieldException e) {
            e.printStackTrace();
        }

        Class appClass = Appliances.class;
        try {
            Method appMethod = appClass.getMethod("turnOn");
            appMethod.invoke(appliances.get(1));
            appMethod.invoke(appliances.get(2));
            appMethod.invoke(appliances.get(5));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }


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


        Fridge fridgeObj = (Fridge) appliances.get(5);
        fridgeObj.putProduct("milk",1);
        fridgeObj.getProduct("milk", 1);

        System.out.println("\nApps finded by energy consumption");
        ArrayList<Appliances> finded = findByEnergyConsumption(800,1000); // Find all appliances with energy consumption between 1st and 2nd arguments
        for (Appliances app:finded
             ) {
            System.out.println(app.toString());
        }

        System.out.println("\nMetadata for Appliances");
        System.out.println(Arrays.toString(Appliances.class.getDeclaredFields()));
        System.out.println(Arrays.toString(Appliances.class.getDeclaredConstructors()));
        System.out.println(Arrays.toString(Appliances.class.getDeclaredMethods()));

        System.out.println("\nMetadata for fridge");
        System.out.println(Arrays.toString(fridge.getDeclaredFields()));
        System.out.println(Arrays.toString(fridge.getDeclaredConstructors()));
        System.out.println(Arrays.toString(fridge.getDeclaredMethods()));

        System.out.println("\nMetadata for microwave");
        System.out.println(Arrays.toString(microwave.getDeclaredFields()));
        System.out.println(Arrays.toString(microwave.getDeclaredConstructors()));
        System.out.println(Arrays.toString(microwave.getDeclaredMethods()));


        Class clazz = ThisCodeSmellsHandler.class;
        for (Method method: clazz.getDeclaredMethods()
             ) {
            if (method.isAnnotationPresent(ProdCode.class)){
                try {
                    method.invoke(clazz.newInstance(),Microwave.class);
                } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    static private ArrayList<Appliances> findByEnergyConsumption(int i1, int i2){
        ArrayList<Appliances> temp = new ArrayList<>();
        for (Appliances app: appliances
             ) {
            if (app.getPower() >= i1 && app.getPower() <= i2){
                temp.add(app);
            }
        }
        return temp;
    }
}

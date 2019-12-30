package services;


public interface FoodManagerService {
    void putProduct(String product, Integer amount);
    void getProduct(String product, Integer amount);
}

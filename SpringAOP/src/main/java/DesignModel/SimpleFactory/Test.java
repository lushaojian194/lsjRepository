package DesignModel.SimpleFactory;

public class Test {
    public static void main(String[] args){
        Car bmw = CarFactory.createCar("宝马");
        Car aodi = CarFactory.createCar("奥迪");
        bmw.run();
        aodi.run();
    }
}

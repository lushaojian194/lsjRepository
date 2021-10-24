package DesignModel.SimpleFactory;

public class CarFactory {
    //简单工厂
    //优点：简单工厂模式能够根据外界给定的信息，决定究竟应该创建哪个具体类的对象。明确区分了各自的职责和权力，有利于整个软件体系结构的优化。
    //缺点：很明显工厂类集中了所有实例的创建逻辑，容易违反GRASPR的高内聚的责任分配原则
    public static Car createCar(String name){
        if("".equals(name)){
            return null;
        }
        if(name.equals("宝马")){
            return new Bmw();
        }else if(name.equals("奥迪")){
            return new AoDi();
        }
        return null;
    }
}

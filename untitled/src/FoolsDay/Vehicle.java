package FoolsDay;

import Fifth.IMove;

public abstract class Vehicle implements IMove {//抽象类不能生成其对应的实例，但可以被子类继承，生成子类的实例
    protected int v;
    public Vehicle(int av){
        v=av;
    }
    public abstract void move(int hour);//abstract修饰的抽象方法不能有函数体，但要求该类的子类必须重写该方法
}
package step33$Spring_Ioc.exam02;

public class Car {
  
  String model;
  String maker;
  int cc;
  public String getModel() {
    return model;
  }
  public Car(String model, int cc) {
    System.out.println("Car(String model, int cc)");
    this.model = model;
    this.cc = cc;
  }
  public Car(String model, String maker, int cc) {
    System.out.println("Car(String model, String maker, int cc)");
    this.model = model;
    this.maker = maker;
    this.cc = cc;
  }
  public Car(String model, String maker) {
    System.out.println("Car(String model, String maker)");
    this.model = model;
    this.maker = maker;
  }
  
  public void setModel(String model) {
    this.model = model;
  }
  public String getMaker() {
    return maker;
  }
  public void setMaker(String maker) {
    this.maker = maker;
  }
  public int getCc() {
    return cc;
  }
  public void setCc(int cc) {
    this.cc = cc;
  }

  
  public Car() {
    System.out.println("car()");
  }
  @Override
  public String toString() {
    return "Car [model=" + model + ", maker=" + maker + ", cc=" + cc + "]";
  }
}

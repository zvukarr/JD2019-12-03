package by.it.popkov.jd01_08.oop;

public class Runner {
    public static void main(String[] args) {
        Ship powerFull = new Tanker();
        powerFull.sail();
        System.out.println(powerFull);
    }
}

package hw6;
import java.util.Random;

public class hw6  {
    public static Random rand = new Random();

    public static void main(String[] args){
        int swim= 0;
        int run= rand.nextInt(201)-1;
        Cat cat = new Cat(swim,run);
        System.out.println("кошка не умеет плавать поэтому дистанция " + cat.swimdist() + "м");
        System.out.println("кошка пробежала " + cat.rundist()+ "м");
        swim= rand.nextInt(11)-1;
        run= rand.nextInt(501)-1;
        Dog dog = new Dog(swim,run);
        System.out.println("собака проплыла " + dog.swimdist()+ "м");
        System.out.println("собака пробежала " + dog.rundist()+ "м");

    }

}

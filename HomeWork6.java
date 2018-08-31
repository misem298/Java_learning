/**
 * Java 1. Home Work Lesson 6.
 * Classes Cog, Cat, Dog1 created from abstract class Animal
 * Methods run, swim, jump with
 * *@author Michail
 * @version dated Aug 23, 2018
 */
import java.util.Scanner;
public class HomeWork6 {
    static Scanner sc = new Scanner(System.in);
    static float run_dist = 1;
    static float swim_dist = 1;
    static float jump_dist = 1;
    public static void main(String[] args) {
        //Cat cat = new Cat("Murzik", "white", 3);
        //Dog dog = new Dog("Sharic", "black", 5);
        Animal[] animals = {new Cat("Murzik", "white", 3,
                200f,0f,2f),
                new Dog ("Sharik", "black", 5,
                        500f, 5.5f, 12f),
                new Dog1 ("Tuzik", "brown", 4,
                        400f, 15f, 8f)};
        for (Animal animal : animals) {
            System.out.println(animal + " run: " + animal.run_limit +
                    " swim: " + animal.swim_limit + " jump: " + animal.jump_limit);
           //System.out.println(animal + " run: " + animal.run(300f) +
           //        " swim: " + animal.swim(5f) + " jump: " + animal.jump(15f));
        }
        while (run_dist + swim_dist + jump_dist != 0) {
            System.out.println("Enter run distance, swim distance and jump height OR 0,0,0 to exit");
            run_dist = sc.nextFloat();
            swim_dist = sc.nextFloat();
            jump_dist = sc.nextFloat();
            for (Animal animal : animals) {
                System.out.println(animal + "*** run: " + animal.run(run_dist) +
                        " swim: " + animal.swim(swim_dist) + " jump: " + animal.jump(jump_dist));
            }
        }
    }
}

class Cat extends Animal {
    Cat(String name, String color, int age,
        float run_limit, float swim_limit, float jump_limit) {
        super(name, color, age, run_limit, swim_limit, jump_limit);
    }
    public String voice() {
            return "miau";
        }
}
 class Dog1 extends Dog {
    Dog1(String name, String color, int age,
        float run_limit, float swim_limit, float jump_limit) {
        super(name, color, age, run_limit, swim_limit, jump_limit);
    }
    public String voice() {return "gaf-gaf"; }
}
class Dog extends Animal {
    Dog(String name, String color, int age,
        float run_limit, float swim_limit, float jump_limit) {
        super(name, color, age, run_limit, swim_limit, jump_limit);
    }
   public String voice() {return "gaf"; }

}

  //  interface IAnimal{
  //      abstract String swim(int dist);
  //      abstract boolean run(int dist);
  //  }

interface IAnimal {

}
abstract class Animal implements IAnimal {
        private String name;
        private String color;
        private int age;
        protected float run_limit;
        protected float swim_limit;
        protected float jump_limit;

        Animal(String name, String color, int age,
               float run_limit, float swim_limit, float jump_limit ){
            this.name = name;
            this.color = color;
            this.run_limit = run_limit;
            this.swim_limit = swim_limit;
            this.jump_limit = setJump_limit(jump_limit);// jump_limit;
            this.age = setAge(age);
        }
    abstract String voice();

    public int setAge(int age) {
        if (age>0)
            this.age = age;
        return age;
    }
        float setJump_limit(float jump_limit) {
           if (jump_limit > 0 && jump_limit < 10)
               this.jump_limit = jump_limit;
           else
               jump_limit = 10;
           return jump_limit;
        }

        public int getAge() {
                return age;
        }

        @Override
        public String toString() {
        return ("name: " + name + " color: " + color + " age: " + age);
        }

    public boolean run(float dist) {
        if (run_limit >= dist) {return true;}
        return false;
    }
    public boolean swim(float dist) {
        if (swim_limit >= dist) {return true;}
        return false;
    }
    public boolean jump(float dist) {
        if (jump_limit >= dist) {return true;}
        return false;
    }
}

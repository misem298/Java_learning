/**
 * Java 1. Home Work Lesson 7.
 * Array of Cat created
 * void decreaseFood allows eat if food into plate is more than cat's appetite
 * If q-ty of food less than cat's appetite we add 10 (void addFood) and cat eats again
 * *@author Michail
 * @version dated Sept 2, 2018
 */
public class HomeWork7 {
    public static void main(String[] args) {
        Cat[] cat = {new Cat ("Barsik", 5, false),
                new Cat ("Murzik", 7, false),
                new Cat ("Tmka", 4, false),
                new Cat ("Murka", 8, false)};
        Plate plate = new Plate(1);
        plate.setFood(10);
        for (Cat  c: cat) {
            plate.info();
            if ((plate.getFood() - c.appetite) >= 0) {
                c.eat(plate);
            }
            else {
                plate.addFood(c.appetite);
                plate.info();
                c.eat(plate);
            }
            c.satiety = true;
            System.out.println(" name: " + c.name + "  сытость: " + c.satiety);
        }
    }
}

class Cat {
    protected String name;
    protected int appetite;
    boolean satiety;
    Cat (String name, int appetite, boolean satiety) {
        this.name = name;
        this.appetite = appetite;
        this.satiety = satiety;
    }
    void eat(Plate p) {p.decreaseFood(appetite);}
}

class Plate {
    private int food;
    Plate(int food) {
        this.food = getFood();
    }

    void decreaseFood (int n) {if (food >= n ) food -= n;}

    void info() {System.out.println(" plate: " + food);}

    int getFood() {return food;}

    void setFood(int f){
        if(f > 0) this.food = food + f;
    }

    void addFood(int af) {food += af ;}
}



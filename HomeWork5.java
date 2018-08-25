class HomeWork5 {
    public static void main(String[] args) {

        Coworker[] coworkers = new Coworker[5];
        coworkers[0] = new Coworker("Ivanov Ivan", "Engineer", "ivivan@gmail.com", "892312312", 1000, 30);
        coworkers[1] = new Coworker("Petrov Petr", "Engineer", "ppetrov@gmail.com", "892312312", 2000, 35);
        coworkers[2] = new Coworker("John Smith", "spy", "jsmith@gmail.com", "892312312", 1300, 44);
        coworkers[3] = new Coworker("Kurt Muller", "driver", "kmuller@gmail.com", "892312312", 1400, 48);
        coworkers[4] = new Coworker("Pierre Cardin", "couturier", "pierrecardin@gmail.com", "892312312", 1700, 54);
        for (int i=0; i < coworkers.length; i++) {
            coworkers[i].selectUpwards(40);
        }
    }
}

class Coworker {
    String name;
    String position;
    String email;
    String phone;
    int salary;
    int age;

    Coworker(String name, String position, String email, String phone, int salary, int age) {
        this.name = name;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }
    void selectUpwards(int over) {
        if (this.age > over)
            System.out.println(this.name + ", " + this.position + ", " + this.email +
                    ", " + this.phone + ", " + this.salary + ", " + this.age);
    }
}



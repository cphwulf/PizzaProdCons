import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class PizzaBaker implements Runnable{
    private ArrayList<Pizza> pizzas;
    private ArrayList<Pizza> menu;

    public PizzaBaker(ArrayList<Pizza> pizzas, ArrayList<Pizza> menu) {
        this.pizzas = pizzas;
        this.menu = menu;
    }

    public void run() {
        System.out.println("running bakery ..");
        while (true) {
            System.out.println("looping bakery ..");

            Random random = new Random();
            int choice = random.nextInt(menu.size());
            System.out.println("Adding " + menu.get(choice).getNo() + ", " + menu.get(choice).getName());
            Pizza pizza = new Pizza(menu.get(choice));
            pizzas.add(pizza);
            try {
                //Thread.sleep(random.nextInt(random.nextInt(5000)+1));
                Thread.sleep(2500);
            } catch(InterruptedException e) {

            }
        }
    }
}


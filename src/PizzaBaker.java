import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class PizzaBaker implements Runnable{
    private ArrayList<Pizza> pizzas;
    private OrderHandler orderHandler;
    private ArrayList<Pizza> menu;

    public PizzaBaker(ArrayList<Pizza> pizzas, ArrayList<Pizza> menu) {
        this.pizzas = pizzas;
        this.menu = menu;
    }
    public PizzaBaker(OrderHandler orderHandler, ArrayList<Pizza> menu) {
        this.orderHandler = orderHandler;
        this.menu = menu;
    }

    public void run() {
        System.out.println(TCol.ANSI_GREEN+"running bakery ..");

        while (true) {


            Random random = new Random();
            int choice = random.nextInt(menu.size());
            Pizza pizza = new Pizza(menu.get(choice));
            //pizzas.add(pizza);
            if (orderHandler.getSize() < 12) {

                System.out.println(TCol.ANSI_GREEN+"Adding " + menu.get(choice).getNo() + ", " + menu.get(choice).getName());
                orderHandler.addPizzaToOrders(pizza);
                try {
                    //Thread.sleep(random.nextInt(random.nextInt(5000)+1));
                    Thread.sleep(1000);
                } catch(InterruptedException e) {
                    System.out.println(TCol.ANSI_GREEN+"was interrupted ..");
                    e.printStackTrace();
                    break;
                }
            } else {
                try {
                    Thread.sleep(random.nextInt(random.nextInt(5000)+1));
                    System.out.println(TCol.ANSI_GREEN+"sleeping bakery ..");
                } catch(InterruptedException e) {
                    System.out.println(TCol.ANSI_GREEN+"was interrupted ..");
                    e.printStackTrace();
                    break;
                }

            }
        }
    }
}


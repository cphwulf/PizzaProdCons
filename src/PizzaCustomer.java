import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class PizzaCustomer implements Runnable{
    private Random rd;
    private String name;
    private String message;
    private ArrayList<Pizza> pizzas;
    private ArrayList<Pizza> menu;
    private boolean empty = true;

    public PizzaCustomer(ArrayList<Pizza> pizzas, ArrayList<Pizza> menu, String name)  {
        this.name = name;
        this.pizzas = pizzas;
        this.menu = menu;
        rd = new Random();
    }

    @Override
    public void run() {
        System.out.println("running customer ..");
        while(true) {
            System.out.print("hungry looping customer ..");

            if (pizzas.size()>0) {
                int choice = rd.nextInt(pizzas.size());
                System.out.println("looping customer .." + this.name + " for no " + choice + " for noof pizzas " + pizzas.size());
                Iterator<Pizza> i = pizzas.iterator();
                while(i.hasNext()) {
                    Pizza p = i.next();
                    System.out.print(p.getNo() + ",");
                    if (p.getNo()==choice) {
                        System.out.println(this.name + " took " + p.getName());
                        i.remove();
                        }
                    }
                System.out.println(" ");
                try {
                    Thread.sleep(rd.nextInt(5000));
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
    }

    public String getName() {
        return name;
    }
}
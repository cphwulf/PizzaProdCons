import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class PizzaCustomer implements Runnable{
    private Random rd;
    private String name;
    private String message;
    private ArrayList<Pizza> pizzas;
    private OrderHandler orderHandler;
    private ArrayList<Pizza> menu;
    private boolean empty = true;

    public PizzaCustomer(ArrayList<Pizza> pizzas, ArrayList<Pizza> menu, String name)  {
        this.name = name;
        this.pizzas = pizzas;
        this.menu = menu;
        rd = new Random();
    }

    public PizzaCustomer(OrderHandler orderHandler, ArrayList<Pizza> menu, String name)  {
        this.name = name;
        this.orderHandler = orderHandler;
        this.menu = menu;
        rd = new Random();
    }

    @Override
    public void run() {
        while(true) {
            System.out.println(TCol.ANSI_BLUE+"Size " + orderHandler.getSize());
            if (orderHandler.getSize()>0) {
                int choice = rd.nextInt(orderHandler.getSize());
                System.out.println(TCol.ANSI_BLUE+"looping customer .." + this.name + " for no " + choice + " for noof pizzas " + orderHandler.getSize());
                try {
                    orderHandler.removeFromList(choice);
                } catch (NoSuchElementException e) {
                    System.out.println(TCol.ANSI_BLUE+e.getMessage());
                }

                /*
                Iterator<Pizza> i = orderHandler.getPizzas();
                while(i.hasNext()) {
                    Pizza p = i.next();
                    System.out.print(p.getNo() + ",");
                    if (p.getNo()==choice) {
                        System.out.println(this.name + " took " + p.getName());
                        i.remove();
                        }
                    }
                System.out.println(" ");

                 */
                try {
                    Thread.sleep(rd.nextInt(2000));
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
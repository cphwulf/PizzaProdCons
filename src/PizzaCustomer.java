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
            //kjSystem.out.println(TCol.ANSI_BLUE+"looping customer .." + this.name);
            //System.out.println(TCol.ANSI_BLUE+"looping customer .." + this.name);
            if (orderHandler.getSize()>0) {
                //int choice = rd.nextInt(orderHandler.getSize());
                int choice = 1+rd.nextInt((menu.size()-2));
                //System.out.println(TCol.ANSI_BLUE+"looping customer .." + this.name + " for no " + choice + " for noof pizzas " + orderHandler.getSize());
                try {
                    //System.out.println(TCol.ANSI_BLUE+"looping customer .." + this.name + " for no " + choice + " for noof pizzas " + orderHandler.getSize());
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
                    System.out.println(TCol.ANSI_BLUE+"sleeping customer " + Thread.currentThread().getName() );
                    Thread.sleep(rd.nextInt(2300));
                    //Thread.sleep(7000);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                    break;
                }
            } else {
                try {
                    System.out.println(TCol.ANSI_BLUE+"sleeping customer " + Thread.currentThread().getName() );
                    Thread.sleep(rd.nextInt(2300));
                    //Thread.sleep(7000);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                    break;
                }
            }
        }
    }

    public String getName() {
        return name;
    }
}
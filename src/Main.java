import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {
    ArrayList<Pizza> menu;
    ArrayList<Pizza> pizzas;


    public static void main(String[] args) {
        Main  main = new Main();
        main.runProgram();
        System.out.println(TCol.ANSI_RED+"MAIN " + Thread.currentThread().getName());
    }

    private void runProgram() {
        menu = new ArrayList<>();
        //pizzas = new ArrayList<>();
        OrderHandler orderHandler = new OrderHandler();

        initMenu();
        Thread t = new Thread(new PizzaBaker(orderHandler,menu));
        t.setName("Baker");
        Thread c1 = new Thread(new PizzaCustomer(orderHandler,menu, "Kurt"));
        Thread c2 = new Thread(new PizzaCustomer(orderHandler,menu,"Berit"));
        Thread c3 = new Thread(new PizzaCustomer(orderHandler,menu,"Lone"));
        Thread c4 = new Thread(new PizzaCustomer(orderHandler,menu,"Vivi"));
        Thread c5 = new Thread(new PizzaCustomer(orderHandler,menu,"Snurre Snup"));
        Thread c6 = new Thread(new PizzaCustomer(orderHandler,menu,"Tintin"));
        c1.setName("kurt");
        c1.start();
        c2.setName("Berit");
        c2.start();
        c3.setName("Lone");
        c3.start();

        final int count=10;
        Thread myT = new Thread(()-> {
            while(true) {
                System.out.println(TCol.ANSI_YELLOW+"Try this");
                if (orderHandler.getSize()>0){
                    Iterator<Pizza> pizzaIterator = orderHandler.getPizzas();
                    while(pizzaIterator.hasNext()) {
                        Pizza p = pizzaIterator.next();
                        System.out.println(TCol.ANSI_YELLOW+"HIDDEN GOT: "+ p.getName());
                        if (p.getNo()==3) {
                            System.out.println(TCol.ANSI_YELLOW+"HIDDEN REMOVED: "+ p.getName());
                            pizzaIterator.remove();
                        }
                    }
                }
                try {

                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(TCol.ANSI_YELLOW+"interrupted .");
                    break;
                }
            }
        });
        myT.start();

        Thread bu = new Thread(new Runnable() {
            boolean done=false;

            @Override
            public void run() {
                while(true) {
                    done = orderHandler.backupPizzas("resources/bu.txt");
                    System.out.println(TCol.ANSI_CYAN + " backup ...");
                    Long start = System.currentTimeMillis();
                    Long fin = System.currentTimeMillis();
                    System.out.println(TCol.ANSI_CYAN + " backup done " + done + " "+ (fin-start));
                    try {
                        Thread.sleep(9000);
                    } catch (InterruptedException ie) {
                        System.out.println(TCol.ANSI_CYAN+ie.getMessage());
                        System.out.println(TCol.ANSI_CYAN + "is interrupted .. will close" + done);
                        while(!done) {
                            done = orderHandler.backupPizzas("resources/bu.txt");
                            System.out.println(TCol.ANSI_CYAN + "is interrupted .. will close");
                        }
                        break;
                    }
                }
            }
        });
        bu.setName("Backup");
        System.out.println(TCol.ANSI_RED+"Currten " + Thread.currentThread().getName());
        bu.start();
        System.out.println(TCol.ANSI_RED+"Currten " + Thread.currentThread().getName());
        t.start();
        System.out.println(TCol.ANSI_RED+"Currten " + Thread.currentThread().getName());
        System.out.println(TCol.ANSI_RED+"Currten " + Thread.currentThread().getName());
        System.out.println(TCol.ANSI_RED+"DONE ...");
        long startT = System.currentTimeMillis();
        long stopT = 30*1000;
        while(System.currentTimeMillis() - startT < stopT) {
            System.out.println("STILL " + (System.currentTimeMillis() - startT));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        myT.interrupt();
        bu.interrupt();

        t.interrupt();
        c1.interrupt();
        c2.interrupt();
        c3.interrupt();
//        c3.start();
        //       c4.start();
        //      c5.start();
        //     c6.start();
    }

    private void initMenu() {
        File file = new File("resources/menu");
        //11,Hawai,Tomatsauce|ost|skinke|ananas|oregano,61
        String line = "";
        Pizza tmpPizza = null;

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            while((line=br.readLine())!=null) {
                String[] lineArr = line.split(",");
                String[] ingredients = lineArr[2].split("|");
                String ingr = String.join("",ingredients);
                tmpPizza = new Pizza(Integer.valueOf(lineArr[0]),lineArr[1],Double.valueOf(lineArr[3]),ingr);
                menu.add(tmpPizza);
            }
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }
}


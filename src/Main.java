import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    ArrayList<Pizza> menu;
    ArrayList<Pizza> pizzas;


    public static void main(String[] args) {
        Main  main = new Main();
        main.runProgram();
    }

    private void runProgram() {
        menu = new ArrayList<>();
        //pizzas = new ArrayList<>();
        OrderHandler orderHandler = new OrderHandler();

        initMenu();
        Thread t = new Thread(new PizzaBaker(orderHandler,menu));
        Thread c1 = new Thread(new PizzaCustomer(orderHandler,menu, "Kurt"));
        Thread c2 = new Thread(new PizzaCustomer(orderHandler,menu,"Berit"));
        Thread c3 = new Thread(new PizzaCustomer(orderHandler,menu,"Lone"));
        Thread c4 = new Thread(new PizzaCustomer(orderHandler,menu,"Vivi"));
        Thread c5 = new Thread(new PizzaCustomer(orderHandler,menu,"Snurre Snup"));
        Thread c6 = new Thread(new PizzaCustomer(orderHandler,menu,"Tintin"));
        Thread bu = new Thread(new Runnable() {

            @Override
            public void run() {
                while(true) {
                    try {
                        System.out.println(TCol.ANSI_CYAN + " backup ...");
                        orderHandler.backupPizzas("resources/bu.txt");
                    } catch (IOException ie) {
                        ie.printStackTrace();
                    }
                    try {
                        Thread.sleep(8000);
                    } catch (InterruptedException ie) {
                        System.out.println(TCol.ANSI_CYAN+ie.getMessage());
                    }
                }
            }
        });
        bu.setName("Backup");
        bu.start();
        t.start();
        c1.start();
        c2.start();
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


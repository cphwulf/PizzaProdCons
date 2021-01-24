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
        pizzas = new ArrayList<>();

        initMenu();
        Thread t = new Thread(new PizzaBaker(pizzas,menu));
        Thread c1 = new Thread(new PizzaCustomer(pizzas,menu, "Kurt"));
        Thread c2 = new Thread(new PizzaCustomer(pizzas,menu,"Berit"));
        Thread c3 = new Thread(new PizzaCustomer(pizzas,menu,"Lone"));
        Thread c4 = new Thread(new PizzaCustomer(pizzas,menu,"Vivi"));
        Thread c5 = new Thread(new PizzaCustomer(pizzas,menu,"Snurre Snup"));
        Thread c6 = new Thread(new PizzaCustomer(pizzas,menu,"Tintin"));
        Thread bu = new Thread(new Runnable() {

            @Override
            public void run() {


            }
        });
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
                tmpPizza = new Pizza(Integer.valueOf(lineArr[0]),lineArr[1],Double.valueOf(lineArr[3]),ingredients);
                menu.add(tmpPizza);
            }
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }
}


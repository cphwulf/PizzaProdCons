import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class OrderHandler {
    private static int MYC=0;
    List<Pizza> pizzas;
    private final BlockingQueue messages;

    public OrderHandler() {
        this.messages = new ArrayBlockingQueue(1024);
        //this.pizzas = new ArrayBlockingQueue<Pizza>(0)
        pizzas = new ArrayList<>();
    }


    public void addPizzaToOrders(Pizza p) {
        pizzas.add(p);
    }
    public Pizza removeFromList(int pizzaNo) throws NoSuchElementException{
        Pizza tmpPizza = null;
        for (Pizza p : pizzas ) {
            if (p.getNo() == pizzaNo) {
                tmpPizza = p;
                pizzas.remove(p);
                System.out.println(TCol.ANSI_BLACK+"ate "+ p.getName()+ ","+p.getNo());
                return tmpPizza;
            }
        }
        if (tmpPizza == null) {
            throw new NoSuchElementException("no such pizza in list " + pizzaNo);
        }
        return tmpPizza;
    }
    public int getSize() {
        return pizzas.size();
    }

    public Iterator<Pizza> getPizzas() {
        return pizzas.iterator();
    }

    public boolean backupPizzas(String fileDest) {
        MYC++;
        boolean done = false;
        File file = new File(fileDest+MYC);
        System.out.println(TCol.ANSI_BLACK+"bu " + fileDest+MYC);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter( new FileWriter(file));
            for (Pizza p : pizzas ) {
                System.out.println(TCol.ANSI_BLACK+"bu " + p.getNo());
                doFact();
                bw.write(p.toString());
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(TCol.ANSI_BLACK+"bu done ");
        return true;
    }

    public void doFact() {
        long f = 1L;
        int prod = 0;
        for (long i = 1L; i < 199990L; i++) {
            f=f*i;
        }
    }
}

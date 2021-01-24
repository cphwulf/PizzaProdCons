import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class OrderHandler {
    List<Pizza> pizzas;

    public OrderHandler() {
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

    public void backupPizzas(String fileDest) throws IOException {
        File file = new File(fileDest);
        BufferedWriter bw = new BufferedWriter( new FileWriter(file));
        for (Pizza p : pizzas ) {
            bw.write(p.toString());
            bw.newLine();
        }
        bw.close();
    }
}

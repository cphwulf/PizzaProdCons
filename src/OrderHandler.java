import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderHandler {
    List<Pizza> pizzas;

    public OrderHandler() {
        pizzas = new ArrayList<>();
    }

    public void addPizzaToOrders(Pizza p) {
        pizzas.add(p);
    }
    public Pizza removeFromList(int pizzaNo){
        Pizza tmpPizza = null;

        for (Pizza p : pizzas ) {
            if (p.getNo() == pizzaNo) {
                tmpPizza = p;
                pizzas.remove(p);
                return tmpPizza;
            }
        }
        return tmpPizza;
    }

    public void backupPizzas(String fileDest) throws IOException {
        File file = new File(fileDest);
        BufferedWriter bw = new BufferedWriter( new FileWriter(file));
        for (Pizza p : pizzas ) {

        }
    }
}

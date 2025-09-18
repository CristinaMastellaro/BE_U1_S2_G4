package cristinamastellaro;

import com.github.javafaker.Faker;
import cristinamastellaro.entities.Customer;
import cristinamastellaro.entities.Order;
import cristinamastellaro.entities.Product;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        //List<Order> list = createOrders(20);
        //System.out.println("La lista presa in considerazione è: " + list);
        //es1(list);
        //es2(list);
        //es4(list);

        List<Product> listProducts = createListProduct(50);
        System.out.println("La lista di prodotti considerata è: " + listProducts);
        //es3(listProducts);
        es5(listProducts);

    }

    public static List<Product> createListProduct(int numProducts) {
        ArrayList<String> categories = new ArrayList<>();
        categories.add("books");
        categories.add("boys");
        categories.add("babies");
        categories.add("beauty");
        Faker randomNames = new Faker();
        Random r = new Random();

        List<Product> products = new ArrayList<>();
        for (int i = 0; i < numProducts; i++) {
                products.add(new Product(randomNames.commerce().productName(), categories.get(r.nextInt(4)), Math.floor(r.nextDouble(1, 4000) * 100) / 100));
           }
        return products;
    }

    public static List<Order> createOrders(int numOrders) {
        Faker randomNames = new Faker();
        Random r = new Random();
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < numOrders; i++) {
            LocalDate date = LocalDate.now().minusDays(r.nextInt(1400, 2000));
            orders.add(
                    new Order(date, createListProduct(10), new Customer(randomNames.artist().name(), r.nextInt(1, 5))));
        }
        return orders;
    }

    public static void es1(List<Order> list){
        Map<Customer, List<Order>> clientMap = list.stream().collect(Collectors.groupingBy(Order::getCustomer));
        clientMap.forEach((client, ordersList) -> System.out.println("\nIl cliente " + client.getName() + " (id " + client.getId() + ") ha la seguente lista di ordini: " + ordersList + "\n"));
    }

    public static void es2(List<Order> list) {
        Map<Customer, Double> totalSpentByClient = list.stream().collect(Collectors.groupingBy(Order::getCustomer,
                // Adesso cerchiamo di fare la somma dei prezzi dei prodotti
                // Il problema è che summingDouble ha bisogno di un numero; in questo caso il numero deve corrispondere alla somma totale del prezzo dei prodotti dell'ordine
                // Quindi prima prendiamo i prodotti
                Collectors.summingDouble(order -> {
            List<Product> products = order.getProducts();
            // Ora dobbiamo fare la somma dei prezzi di questi prodotti
            List<Double> allPrices = products.stream().map(Product::getPrice).toList();
            double totalSpentForOrder = 0;
            for (double price: allPrices) {
                totalSpentForOrder += price;
            }
            // Finalmente abbiamo trovato quale numero dare a summingDouble: la somma dei prezzi dei prodotti dell'ordine
            return totalSpentForOrder;
        })));
        System.out.println("\nEcco la lista di quanto ha speso ciascun cliente: ");
        totalSpentByClient.forEach((client, totalSpent) -> System.out.println(client.getName() + " ha speso in totale " + Math.floor(totalSpent * 100) / 100 + "€"));
    }

    public static void es3(List<Product> list) {
        List<Product> moreExpensiveProducts = list.stream().sorted(Comparator.comparingDouble(Product::getPrice).reversed()).limit(5).toList();
        System.out.println("\nI cinque prodotti più costosi della lista sono:");
        moreExpensiveProducts.forEach(System.out::println);
    }

    public static void es4(List<Order> list) {
        OptionalDouble averagePriceInOrders = list.stream()
                // mapToDouble funziona con un numero, e questo numero è la somma totale dei prodotti dell'ordine. Troviamo sta somma come abbiamo fatto prima
                .mapToDouble(order -> {
            List<Product> allProducts = order.getProducts();
            double sumPricesOrder = 0;
            for (Product product: allProducts) {
                sumPricesOrder += product.getPrice();
            }
            return sumPricesOrder;
        }).average();
         if (averagePriceInOrders.isPresent()) System.out.println("\nLa media della spesa totale dei prodotti è " + Math.floor(averagePriceInOrders.getAsDouble() * 100) /100 + "€" );
         else System.out.println("\nNon ci sono ordini di cui fare la media");
    }

    public static void es5(List<Product> list) {
        Map<String, Double> totalByCategory = list.stream().collect(Collectors.groupingBy(Product::getCategory, Collectors.summingDouble(Product::getPrice)));
        System.out.println("\nEcco la somma totale degli importi per categoria");
        totalByCategory.forEach((category, sum) -> System.out.println("Dalla categoria " + category + " si sono guadagnati " + sum + "€" ));
    }
}

import java.util.*;
import java.io.*;

// Stock Class
class Stock {
    String symbol;
    double price;

    Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }
}

// User Portfolio Class
class Portfolio {
    HashMap<String, Integer> holdings = new HashMap<>();
    double balance = 10000; // Starting money

    void buyStock(String symbol, int quantity, double price) {
        double cost = price * quantity;
        if (balance >= cost) {
            balance -= cost;
            holdings.put(symbol, holdings.getOrDefault(symbol, 0) + quantity);
            System.out.println("✅ Bought " + quantity + " shares of " + symbol);
        } else {
            System.out.println("❌ Not enough balance!");
        }
    }

    void sellStock(String symbol, int quantity, double price) {
        if (holdings.containsKey(symbol) && holdings.get(symbol) >= quantity) {
            holdings.put(symbol, holdings.get(symbol) - quantity);
            balance += price * quantity;
            System.out.println("✅ Sold " + quantity + " shares of " + symbol);
        } else {
            System.out.println("❌ You don't own that many shares!");
        }
    }

    void viewPortfolio() {
        System.out.println("\n----- Portfolio Summary -----");
        System.out.println("Balance: ₹" + balance);
        System.out.println("Stocks Owned:");
        for (String symbol : holdings.keySet()) {
            System.out.println(symbol + ": " + holdings.get(symbol));
        }
        System.out.println("-----------------------------\n");
    }
}

// Main System
public class Main {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        HashMap<String, Stock> market = new HashMap<>();
        market.put("TCS", new Stock("TCS", 3400));
        market.put("INFY", new Stock("INFY", 1560));
        market.put("HDFC", new Stock("HDFC", 1650));
        market.put("RELI", new Stock("RELI", 2450));

        Portfolio portfolio = new Portfolio();

        while (true) {
            System.out.println("\n----- STOCK TRADING MENU -----");
            System.out.println("1. View Market Prices");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n--- Market Prices ---");
                    for (Stock s : market.values()) {
                        System.out.println(s.symbol + " : ₹" + s.price);
                    }
                    break;

                case 2:
                    System.out.print("Enter Stock Symbol: ");
                    String buySymbol = sc.next().toUpperCase();
                    if (!market.containsKey(buySymbol)) {
                        System.out.println("❌ Invalid stock!");
                        break;
                    }
                    System.out.print("Enter Quantity: ");
                    int buyQty = sc.nextInt();
                    portfolio.buyStock(buySymbol, buyQty, market.get(buySymbol).price);
                    break;

                case 3:
                    System.out.print("Enter Stock Symbol: ");
                    String sellSymbol = sc.next().toUpperCase();
                    if (!market.containsKey(sellSymbol)) {
                        System.out.println("❌ Invalid stock!");
                        break;
                    }
                    System.out.print("Enter Quantity: ");
                    int sellQty = sc.nextInt();
                    portfolio.sellStock(sellSymbol, sellQty, market.get(sellSymbol).price);
                    break;

                case 4:
                    portfolio.viewPortfolio();
                    break;

                case 5:
                    System.out.println("Thank you for trading!");
                    return;

                default:
                    System.out.println("❌ Invalid choice!");
            }
        }
    }
}

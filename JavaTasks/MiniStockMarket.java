// Task 2: Stock Trading Platform - CodeAlpha Internship
// Description: Basic buy/sell stocks with portfolio tracking
import java.io.*;
import java.util.Scanner;
class Stock {
    String name;
    double price;
    int shares;
    Stock(String name, double price) {
        this.name = name;
        this.price = price;
        this.shares = 0;
    }
}
public class MiniStockMarket {
        public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        File file = new File("portfolio.txt");
        Stock[] stocks = {
            new Stock("AAPL", 150.0),
            new Stock("GOOG", 200.0),
            new Stock("TSLA", 100.0)
        };
        double cash = 5000.0;
        if (file.exists()) {
            BufferedReader br = new BufferedReader(new FileReader(file));
            cash = Double.parseDouble(br.readLine());
            for (int i = 0; i < stocks.length; i++) {
                stocks[i].shares = Integer.parseInt(br.readLine());
            }
            br.close();
            System.out.println("Previous data loaded!");
        }
        while (true) {
            System.out.println("\n[1] Market [2] Portfolio [3] Buy [4] Sell [5] Next Day [6] Exit");
            System.out.print("Choice: ");
            int choice = in.nextInt();

            if (choice == 6) break;

            if (choice == 1) { 
                for (Stock s : stocks)
                    System.out.println(s.name + ": $" + s.price);
            }
            else if (choice == 2) { // Portfolio Tracking
                double totalValue = cash;
                System.out.println("Cash: $" + cash);
                for (Stock s : stocks) {
                    if (s.shares > 0) {
                        System.out.println(s.name + ": " + s.shares + " shares");
                        totalValue += s.shares * s.price;
                    }
                }
                System.out.println("Total Net Worth: $" + totalValue);
            }
            else if (choice == 3 || choice == 4) { // Buy & Sell
                System.out.print("Stock index (0-AAPL, 1-GOOG, 2-TSLA): ");
                int idx = in.nextInt();
                System.out.print("Quantity: ");
                int qty = in.nextInt();
                if (idx >= 0 && idx < stocks.length) {
                    double cost = qty * stocks[idx].price;
                    if (choice == 3 && cash >= cost) { // Buy
                        cash -= cost;
                        stocks[idx].shares += qty;
                        System.out.println("Bought!");
                    } else if (choice == 4 && stocks[idx].shares >= qty) { // Sell
                        cash += cost;
                        stocks[idx].shares -= qty;
                        System.out.println("Sold!");
                    } else {
                        System.out.println("Transaction failed (Insufficient funds/shares).");
                    }
                }
            }
            else if (choice == 5) { 
                for (Stock s : stocks) {
                    s.price = Math.round(s.price * (0.9 + Math.random() * 0.2) * 100.0) / 100.0;
                }
                System.out.println("Market updated for the new day!");
            }
            PrintWriter pw = new PrintWriter(new FileWriter(file));
            pw.println(cash);
            for (Stock s : stocks) {
                pw.println(s.shares);
            }
            pw.close();
        }
        in.close();
        System.out.println("Data saved to portfolio.txt. Bye!");
    }
}

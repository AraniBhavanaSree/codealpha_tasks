import java.io.*;
import java.util.Scanner;
public class Hotel {
    public static void main(String[] args) throws Exception {
        String[] type = {"Standard", "Deluxe", "Suite"};
        double[] price = {2000, 4000, 7000};
        boolean[] available = {true, true, true};
        String[] bookings = new String[3]; 
        Scanner sc = new Scanner(System.in);
        File file = new File("bookings.txt");
        if (file.exists()) {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                for (int i = 0; i < 3; i++) {
                    if (line.contains(type[i])) {
                        bookings[i] = line;
                        available[i] = false;
                    }
                }
            }
            br.close();
        }
        while (true) {
            System.out.println("\n1.View 2.Book 3.Cancel 4.Details 5.Exit");
            System.out.print("Choice: ");
            int ch = sc.nextInt();
            sc.nextLine(); 
            if (ch == 1) { 
                for (int i = 0; i < 3; i++) {
                    String s = available[i] ? "Available" : "Booked";
                    System.out.println(type[i] + " - Rs." + price[i] + " [" + s + "]");
                }
            } 
            else if (ch == 2) { 
                System.out.print("Name: ");
                String name = sc.nextLine();
                System.out.print("Room (Standard/Deluxe/Suite): ");
                String t = sc.nextLine();
                for (int i = 0; i < 3; i++) {
                    if (type[i].equalsIgnoreCase(t) && available[i]) {
                        System.out.print("Pay Rs." + price[i] + ": ");
                        double pay = sc.nextDouble();
                        if (pay >= price[i]) {
                            available[i] = false;
                            bookings[i] = name + " booked " + type[i];
                            System.out.println("Success!");
                        } else {
                            System.out.println("Insufficient payment.");
                        }
                    }
                }
            } 
            else if (ch == 3) { 
                System.out.print("Enter Name: ");
                String name = sc.nextLine();
                for (int i = 0; i < 3; i++) {
                    if (bookings[i] != null && bookings[i].startsWith(name)) {
                        available[i] = true;
                        bookings[i] = null; 
                        System.out.println("Cancelled.");
                    }
                }
            } 
            else if (ch == 4) { 
                boolean empty = true;
                for (int i = 0; i < 3; i++) {
                    if (bookings[i] != null) {
                        System.out.println("- " + bookings[i]);
                        empty = false;
                    }
                }
                if (empty) System.out.println("No bookings found.");
            } 
            else {
                break;
            }
            PrintWriter pw = new PrintWriter(new FileWriter(file));
            for (int i = 0; i < 3; i++) {
                if (bookings[i] != null) pw.println(bookings[i]);
            }
            pw.close();
        }
        sc.close();
    }
}
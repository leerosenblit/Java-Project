//Lee Rosenblit 322357880 & Tomer Feldon 206558470
// Both in Keren's class

import java.util.Scanner;

public class Project {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;  // The choice from the menu

        Manager m = new Manager();

        m.addSeller("Lee", "lee");
        m.addSeller("Mary", "mary");
        m.addSeller("John", "john");

        Address address = m.createAddress("yahalom", "30a", "Pardes hanna", "Israel");

        m.addCustomer("Roni", "roni", address);
        m.addCustomer("Mary", "mary", address);
        m.addCustomer("John", "john", address);

        m.addProductToSeller("Lee", "Iphone", 1000, 3);
        m.addProductToSeller("Lee", "Bin", 100, 1);
        m.addProductToSeller("Lee", "PS4", 350, 2);

        m.addProductToSeller("John", "Glass", 10, 2);
        m.addProductToSeller("John", "Sink", 100, 1);
        m.addProductToSeller("John", "PC", 800, 1);

        m.addProductToSeller("John", "Snack", 3, 2);
        m.addProductToSeller("John", "Burger", 10, 2);
        m.addProductToSeller("John", "Silver", 100, 1);



        while (true) {
            menu();

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 0:
                    System.exit(0);
                case 1:
                    case1(m);
                    break;
                case 2:
                    case2(m);
                    break;
                case 3:
                    case3(m);
                    break;
                case 4:
                    case4(m);
                    break;
                case 5:
                    case5(m);
                    break;
                case 6:
                    case6(m);
                    break;
                case 7:
                    case7(m);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void menu(){
        System.out.println("Menu:");
        System.out.println("0 - Exit");
        System.out.println("1 - Add Seller");
        System.out.println("2 - Add customer");
        System.out.println("3 - Add Product to Seller");
        System.out.println("4 - Add Product to customer");
        System.out.println("5 - Payment of Order for customer");
        System.out.println("6 - Display All customers");
        System.out.println("7 - Display All Sellers");
    }

    private static void case1(Manager m){ // Add seller
        System.out.print("Enter seller name: ");
        String seller = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        if(m.addSeller(seller, password)){
        System.out.println("Seller added successfully.");
        } else {
        System.out.println("Seller username already exists.");
        }
    }

    private static void case2(Manager m) { // Add customer
        System.out.print("Enter customer name: ");
        String customer = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        System.out.print("Enter street name: ");
        String street = sc.nextLine();
        System.out.print("Enter block number: ");
        String blockNumber = sc.nextLine(); //string because it could be 23a etc...
        System.out.print("Enter city: ");
        String city = sc.nextLine();
        System.out.print("Enter state: ");
        String state = sc.nextLine();
        Address address = m.createAddress(street, blockNumber, city, state);

        if(m.addCustomer(customer, password, address)){
            System.out.println("Customer added successfully.");
        } else {
            System.out.println("Customer username already exists.");
        }
    }

    private static void case3(Manager m) { // Add product to seller
        System.out.print("Enter Seller name: ");
        String seller = sc.nextLine();
        System.out.print("Enter product name: ");
        String productName = sc.nextLine();
        System.out.print("Enter the price: ");
        double price = Double.parseDouble(sc.nextLine());
        System.out.print(m.getCategories());
        System.out.print("Enter the category number: ");
        int category = sc.nextInt();

        if (m.addProductToSeller(seller,productName, price, category)) {
            System.out.println("Product added successfully.");
        }
        else {
            System.out.println("Invalid operation");
        }
    }

    private static void case4(Manager m) { // Add product to customer
        System.out.print("Enter customer name: ");
        String customer = sc.nextLine();
        System.out.print("Enter seller name: ");
        String seller = sc.nextLine();
        if (m.addProductToCustomer(customer, seller)) {
            System.out.println("Choose your desired product(enter the name of the product): ");
            String productList = m.getSellerProducts(seller);
            System.out.println(productList);
            String product = sc.nextLine();
            if (m.addProductToCustomer(customer, seller, product)) {
                System.out.println("Product added successfully.");
            } else {
                System.out.println("Invalid operation");
            }
        } else {
            System.out.println("Invalid operation");
        }
    }

    private static void case5(Manager m){ // Pay for customer
        System.out.print("Enter customer name: ");
        String customer = sc.nextLine();
        if(m.getNumOfProducts(customer) > 0){
            double totalPayment = m.customerPay(customer);
            if(totalPayment != -1)
                System.out.println("You paid: " + totalPayment);
            else
                System.out.println("Invalid");
        }
        else if(m.getNumOfProducts(customer) == 0){
            System.out.println("No products in cart.");
        }
        else{
            System.out.println("No customer with the name " + customer + ".");
        }
    }

    private static void case6(Manager m){ // Display all customers
        String customerDetails = m.getAllCustomers();
        if(customerDetails != null) {
            System.out.println("The list of all the customers:");
            System.out.println(customerDetails);
        }
        else{
            System.out.println("No customers at the moment.");
        }
    }

    private static void case7(Manager m){ // Display all sellers
        String sellerDetails = m.getAllSellers();
        if(sellerDetails != null) {
            System.out.println("The list of all the sellers:");
            System.out.println(sellerDetails);
        }
        else{
            System.out.println("No sellers at the moment.");
        }
    }
}


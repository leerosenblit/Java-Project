import java.util.Arrays;
import java.util.Comparator;

public class Manager {

    private Seller[] sellers;
    private int numOfSellers;

    private Customer[] customers;
    private int numOfCustomers;

    public Manager(){
        this.numOfSellers = 0;
        this.numOfCustomers = 0;
        sellers = new Seller[2];
        customers = new Customer[2];
    }

    public boolean addSeller(String username, String password){
        if (checkIfExistSellers(username)){
            return false;
        }
        if (numOfSellers >= sellers.length) {
            sellers = increaseArray(sellers);
        }
        sellers[numOfSellers++] = new Seller(username, password);
        return true;
    }

    public boolean addCustomer(String username, String password, Address address){
        if (checkIfExistCustomers(username)){
            return false;
        }
        if (numOfCustomers >= customers.length) {
            customers = increaseArray(customers);
        }
        customers[numOfCustomers++] = new Customer(username, password, address);
        return true;
    }

    public double customerPay(String customerName){
        int customerIndex = returnIndexCustomers(customerName);
        if (customerIndex != -1){
            return customers[returnIndexCustomers(customerName)].pay();
        }
        return -1;
    }

    public boolean addProductToSeller(String sellerName, String productName, double price, int category, double packingPrice){
        if(returnIndexSellers(sellerName) == -1){
            return false;
        }
        else{
            if(packingPrice!=0){
                sellers[returnIndexSellers(sellerName)].addProduct(productName, price, category,packingPrice);
            }
            else{
                sellers[returnIndexSellers(sellerName)].addProduct(productName, price, category);
            }
            return true;
        }
    }


    public boolean IsCustomerExists(String customerName, String sellerName){
        return checkIfExistCustomers(customerName) && checkIfExistSellers(sellerName);
    }


    public boolean addProductToCustomer(String customerName, String sellerName, String productName){
        int customerIndex = returnIndexCustomers(customerName);
        int sellerIndex = returnIndexSellers(sellerName);
        if (sellers[sellerIndex].getProductByName(productName) instanceof PackagedProduct){
            PackagedProduct p = new PackagedProduct((PackagedProduct) sellers[sellerIndex].getProductByName(productName));
            customers[customerIndex].addProduct(p);
            return true;
        }
        Product p = new Product(sellers[sellerIndex].getProductByName(productName));
        customers[customerIndex].addProduct(p);
        return true;
    }

    public String getSpecificCustomer(String customerName){
        int customerIndex = returnIndexCustomers(customerName);
        return customers[customerIndex].getOrderHistory();
    }

    public int getNumOfTransactions(String customerName){
        int customerIndex = returnIndexCustomers(customerName);
        return customers[returnIndexCustomers(customerName)].getNumOfTransactions();
    }

    public boolean checkIfNeedPack(String customerName){
        int customerIndex = returnIndexCustomers(customerName);
        if(customers[customerIndex].checkIfNeedPack()){
            return true;
        }
        return false;
    }

    public String getAllSellers(){
        StringBuilder sb = new StringBuilder();
        Seller[] sortedSellers = Arrays.copyOf(sellers, numOfSellers);

        Arrays.sort(sortedSellers, Comparator.comparingInt(Seller::getNumOfProducts).reversed());

        if(numOfSellers > 0) {
            for(int i = 0; i < numOfSellers; i++) {
                sb.append(i + 1).append(": ");
                sb.append(sortedSellers[i].toString());
                sb.append("\n");
            }
            return sb.toString();
        }
        return null;
    }

    public String getAllCustomers(){
        StringBuilder sb = new StringBuilder();
        if(numOfCustomers > 0) {
            for(int i = 0; i < numOfCustomers; i++) {
                sb.append(i + 1).append(": ");
                sb.append(customers[i].toString());
                sb.append("\n");
            }
            return sb.toString();
        }
        return null;
    }

    public Address createAddress(String street, String blockNumber, String city, String state){
        return new Address(street, blockNumber, city, state);
    }

    public String getSellerProducts(String sellerName){
        int sellerIndex = returnIndexSellers(sellerName);
        return sellers[sellerIndex].getProducts();
    }

    public String getCategories(){
        return Product.categoryToString();
    }

    public int getNumOfProducts(String customer){
        int customerIndex = returnIndexCustomers(customer);
        if (customerIndex != -1){
            return customers[customerIndex].getCartSize();
        }
        return -1;
    }
    private Seller[] increaseArray(Seller[] originalArray) {  // increase an array before adding into it
        int newLength = originalArray.length * 2;//multiply the size in two and return the new one.
        Seller[] newArray = new Seller[newLength];
        System.arraycopy(originalArray, 0, newArray, 0, originalArray.length);
        return newArray;
    }

    private Customer[] increaseArray(Customer[] originalArray) {  // increase an array before adding into it
        int newLength = originalArray.length * 2;//multiply the size in two and return the new one.
        Customer[] newArray = new Customer[newLength];
        System.arraycopy(originalArray, 0, newArray, 0, originalArray.length);
        return newArray;
    }


    public boolean checkIfExistSellers(String choice) { // To check if a seller already exists
        if(numOfSellers == 0) {
            return false;
        }
        for (int i = 0; i < numOfSellers; i++) {
            if (choice.equals(sellers[i].getUsername())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfExistCustomers(String choice) { // To check if a customer already exists
        if(numOfCustomers == 0) {
            return false;
        }
        for (int i = 0; i < numOfCustomers; i++) {
            if (choice.equals(customers[i].getUsername())) {
                return true;
            }
        }
        return false;
    }

    private int returnIndexSellers(String username){ // input: name. output: index
        for(int i=0; i<numOfSellers;i++){
            if(username.equals(sellers[i].getUsername()))
                return i;
        }
        return -1;
    }

    private int returnIndexCustomers(String username){ // input: name. output: index
        for(int i=0; i<numOfCustomers;i++){
            if(username.equals(customers[i].getUsername()))
                return i;
        }
        return -1;
    }
    public void displayProductsByCategory(int category) {
        boolean found = false;
        for (int i = 0; i < numOfSellers; i++) {
            String products = sellers[i].getProductsByCategory(category);
            if (products != null && !products.isEmpty()) {
                found = true;
                System.out.println("seller: " + sellers[i].getUsername());
                System.out.println(products);
            }
        }
        if (!found) {
            System.out.println("No products found in this category. ");

        }
    }

    public void setCartFromHistory(String customerName, int i) {
        int customerIndex = returnIndexCustomers(customerName);
        customers[customerIndex].setCartFromHistory(i);
    }
}
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

    public boolean addProductToSeller(String sellerName, String productName, double price, int category){
        if(returnIndexSellers(sellerName) == -1){
            return false;
        }
        else{
            sellers[returnIndexSellers(sellerName)].addProduct(productName, price, category);
            return true;
        }
    }


    public boolean addProductToCustomer(String customerName, String sellerName){
        return checkIfExistCustomers(customerName) && checkIfExistSellers(sellerName);
    }


    public boolean addProductToCustomer(String customerName, String sellerName, String productName){
        int customerIndex = returnIndexCustomers(customerName);
        int sellerIndex = returnIndexSellers(sellerName);
        Product p = new Product(sellers[sellerIndex].getProductByName(productName));
        customers[customerIndex].addProduct(p);
        return true;
    }

    public String getAllSellers(){
        StringBuilder sb = new StringBuilder();
        if(numOfSellers > 0) {
            for(int i = 0; i < numOfSellers; i++) {
                sb.append(i + 1).append(": ");
                sb.append(sellers[i].toString());
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


    private boolean checkIfExistSellers(String choice) { // To check if a seller already exists
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

    private boolean checkIfExistCustomers(String choice) { // To check if a customer already exists
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
}
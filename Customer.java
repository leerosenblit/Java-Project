import java.time.LocalDate;
import java.util.Arrays;

public class Customer {
    private String username;
    private String password;
    private Address address;

    private Product[] cart;
    private int cartSize;

    private Product[][] history; //history of carts
    private String[] historyDates;
    private int numOfTransactions;


    public Customer(String username, String password, Address address) {
        this.username = username;
        this.password = password;
        this.address = new Address(address);
        cart = new Product[2];
        cartSize = 0;
        history = new Product[1][1];
        historyDates = new String[1];
        numOfTransactions = 0;
    }

    public void addProduct(Product p){
        if(cartSize >= cart.length) {
            cart = increaseArray(cart);
        }
        cart[cartSize++] = p;
    }

    private double sumCart(){ // sums the cart's price
        double sumCart = 0;
        for (int i=0;i<cartSize;i++){
            sumCart += cart[i].getPrice();
        }
        return sumCart;
    }


    public int getCartSize(){
        return cartSize;
    }

    public double pay(){
        // Check if the history array needs to be increased
        if (numOfTransactions >= history.length) {
            history = increaseArray(history);
        }
        if (numOfTransactions >= historyDates.length) {
            historyDates = increaseArray(historyDates);
        }
        // Sum the cart
        double sumCart =sumCart();

        // Record the transaction date
        historyDates[numOfTransactions] = LocalDate.now().toString();
        // Initialize the history entry for this transaction
        history[numOfTransactions] = new Product[cart.length];
        // Copy the current cart to the history
        System.arraycopy(cart, 0, history[numOfTransactions], 0, cart.length);
        numOfTransactions++;
        // Reset the cart
        cart = new Product[1];
        cartSize = 0;

        return sumCart;
    }


    public String getOrderHistory() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numOfTransactions; i++) {
            sb.append("\n");
            sb.append((i + 1)).append(". Date: ").append(historyDates[i]).append(", Products: ").append(history[i][0]);
        }
        return sb.toString();
    }

    public String getCart(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cartSize; i++) {
            sb.append((i + 1)).append(": ").append(cart[i]);
        }
        return sb.toString();
    }

    public int getNumOfTransactions(){
        return numOfTransactions;
    }

    public boolean checkIfNeedPack(){
        for(int i = 0; i < cartSize; i++){
            if(cart[i] instanceof PackagedProduct){
                return true;
            }
        }
        return false;
    }

    private String[] increaseArray(String[] historyDates) {
        String[] newArray = new String[historyDates.length * 2];
        System.arraycopy(historyDates, 0, newArray, 0, historyDates.length);
        return newArray;
    }

    private Product[] increaseArray(Product[] originalArray) {  // increase an array before adding into it
        int newLength = originalArray.length * 2;//multiply the size in two and return the new one.
        Product[] newArray = new Product[newLength];
        System.arraycopy(originalArray, 0, newArray, 0, originalArray.length);
        return newArray;
    }

     private Product[][] increaseArray(Product[][] original) {
        Product[][] newArray = new Product[original.length * 2][];
        System.arraycopy(original, 0, newArray, 0, original.length);
        return newArray;
    }

    public String getUsername() {
        return username;
    }

    public String getAddress(){
        return address.toString();
    }

    @Override
    public String toString() {
        String cartString = cartSize > 0 ? getCart() : "No items in cart.";
        String historyString = numOfTransactions > 0 ? getOrderHistory() : "No history of transactions.";
        return "username=" + username + '\n' +
                "address=" + address.toString() + '\n' +
                "cart=" + cartString + '\n' +
                "history=" + historyString;
    }

    public void setCartFromHistory(int i) {
        int newLength = history[i].length;
        cart = new Product[newLength];
        System.arraycopy(history[i], 0, cart, 0, newLength);
        cartSize = 0;
        for (int j = 0; j < cart.length; j++) {
            if(cart[j] == null) {
                break;
            }
            cartSize++;
        }
    }
}

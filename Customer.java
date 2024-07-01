import java.time.LocalDate;
import java.util.Arrays;

public class Customer {
    private String username;
    private String password;
    private Address address;

    private Product[] cart;
    private int cartSize;

    private String[][] history; //history of transactions, the first column for date and the second column is for the cart's toString
    private int numOfTransactions;


    public Customer(String username, String password, Address address) {
        this.username = username;
        this.password = password;
        this.address = new Address(address);
        cart = new Product[2];
        cartSize = 0;
        history = new String[1][2];
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
        if (numOfTransactions >= history.length) {
            history = increaseArray(history);
        }
        double sumCart =sumCart();

        history[numOfTransactions][0] = LocalDate.now().toString();
        history[numOfTransactions][1] = Arrays.toString(cart);
        numOfTransactions++;
        cart = new Product[1];
        cartSize = 0;

        return sumCart;
    }


    public String getOrderHistory() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numOfTransactions; i++) {
            sb.append("\n");
            sb.append((i + 1)).append(". Date: ").append(history[i][0]).append(", Products: ").append(history[i][1]);
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

    private Product[] increaseArray(Product[] originalArray) {  // increase an array before adding into it
        int newLength = originalArray.length * 2;//multiply the size in two and return the new one.
        Product[] newArray = new Product[newLength];
        System.arraycopy(originalArray, 0, newArray, 0, originalArray.length);
        return newArray;
    }

    private String[][] increaseArray(String[][] originalArray) {  // increase an array before adding into it
        int newLength = originalArray.length * 2;//multiply the size in two and return the new one.
        String[][] newArray = new String[newLength][2];
        System.arraycopy(originalArray, 0, newArray, 0, originalArray.length);
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
        String cartString = cartSize > 0 ? Arrays.toString(cart) : "No items in cart.";
        String historyString = numOfTransactions > 0 ? getOrderHistory() : "No history of transactions.";
        return "username=" + username + '\n' +
                "address=" + address.toString() + '\n' +
                "cart=" + cartString + '\n' +
                "history=" + historyString;
    }
}

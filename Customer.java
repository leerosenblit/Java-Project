import java.time.LocalDate;

public class Customer implements Comparable<Customer> {
    private String username; // Customer's username
    private String password; // Customer's password
    private Address address; // Customer's address

    private Product[] cart; // Current cart of products
    private int cartSize; // Number of items in the cart

    private Product[][] history; // History of carts (transactions)
    private String[] historyDates; // Dates of transactions
    private int numOfTransactions; // Number of transactions

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

    // Adds a product to the cart
    public void addProduct(Product p) {
        if (cartSize >= cart.length) {
            cart = increaseArray(cart);
        }
        cart[cartSize++] = p;
    }

    // Sums the total price of the products in the cart
    private double sumCart() {
        double sumCart = 0;
        for (int i = 0; i < cartSize; i++) {
            sumCart += cart[i].getPrice();
        }
        return sumCart;
    }

    // Returns the number of items in the cart
    public int getCartSize() {
        return cartSize;
    }

    // Processes the payment for the current cart and records the transaction
    public double pay() {
        // Check if the history array needs to be increased
        if (numOfTransactions >= history.length) {
            history = increaseArray(history);
        }
        if (numOfTransactions >= historyDates.length) {
            historyDates = increaseArray(historyDates);
        }

        // Sum the cart
        double sumCart = sumCart();

        // Record the transaction date
        historyDates[numOfTransactions] = LocalDate.now().toString();

        // Initialize the history entry for this transaction
        history[numOfTransactions] = new Product[cart.length];

        // Copy the current cart to the history
        System.arraycopy(cart, 0, history[numOfTransactions], 0, cart.length);
        numOfTransactions++;

        // Reset the cart
        cart = new Product[2];
        cartSize = 0;

        return sumCart;
    }

    // Returns the order history as a formatted string
    public String getOrderHistory() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numOfTransactions; i++) {
            sb.append("\n");
            sb.append((i + 1)).append(". Date: ").append(historyDates[i]).append(", Products: ").append(history[i][0]);
        }
        return sb.toString();
    }

    // Returns the current cart as a formatted string
    public String getCart() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cartSize; i++) {
            sb.append((i + 1)).append(": ").append(cart[i]);
        }
        return sb.toString();
    }

    // Returns the number of transactions
    public int getNumOfTransactions() {
        return numOfTransactions;
    }

    // Checks if the cart contains any packaged products
    public boolean checkIfNeedPack() {
        for (int i = 0; i < cartSize; i++) {
            if (cart[i] instanceof PackagedProduct) {
                return true;
            }
        }
        return false;
    }

    // Increases the size of a String array
    private String[] increaseArray(String[] historyDates) {
        String[] newArray = new String[historyDates.length * 2];
        System.arraycopy(historyDates, 0, newArray, 0, historyDates.length);
        return newArray;
    }

    // Increases the size of a Product array
    private Product[] increaseArray(Product[] originalArray) {
        int newLength = originalArray.length * 2;
        Product[] newArray = new Product[newLength];
        System.arraycopy(originalArray, 0, newArray, 0, originalArray.length);
        return newArray;
    }

    // Increases the size of a Product 2D array
    private Product[][] increaseArray(Product[][] original) {
        Product[][] newArray = new Product[original.length * 2][];
        System.arraycopy(original, 0, newArray, 0, original.length);
        return newArray;
    }

    // Returns the customer's username
    public String getUsername() {
        return username;
    }

    // Returns the customer's address as a string
    public String getAddress() {
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

    // Sets the current cart to a specific transaction from history
    public void setCartFromHistory(int i) {
        int newLength = history[i].length;
        cart = new Product[newLength];
        System.arraycopy(history[i], 0, cart, 0, newLength);
        cartSize = 0;
        for (int j = 0; j < cart.length; j++) {
            if (cart[j] == null) {
                break;
            }
            cartSize++;
        }
    }

    @Override
    public int compareTo(Customer c) {
        return this.username.compareTo(c.username);
    }
}

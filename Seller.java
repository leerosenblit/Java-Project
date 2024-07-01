import java.util.Arrays;

public class Seller {
    private String username;
    private String password;
    private Product[] products;
    private int numOfProducts;

    public Seller(String username, String password) {
        this.username = username;
        this.password = password;
        products = new Product[2];
        numOfProducts = 0;
    }

    public boolean addProduct(String name, double price, int category)
    {
        if (checkIfExist(name, products)){
            return false;
        }
        if (category > 3){
            return false;
        }
        if (numOfProducts >= products.length) {
            products = increaseArray(products);
        }

        products[numOfProducts++] = new Product(name, price, category);
        return true;
    }

    public String getUsername() {
        return username;
    }

    public int getNumOfProducts() {
        return numOfProducts;
    }

    public String getProducts() { // return the products of the seller
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numOfProducts; i++) {
            sb.append((i + 1)).append(". ").append(products[i].getName()).append("(").append(products[i].getPrice()).append("$)");
            sb.append("\n");
        }
        return sb.toString();
    }

    public Product getProductByName(String productName){ // input: name. output: the index of it
        for(int i=0; i<products.length; i++){
            if(productName.equals(products[i].getName())){
                return products[i];
            }
        }
        return null;
    }

    private Product[] increaseArray(Product[] originalArray) {  // increase an array before adding into it
        int newLength = originalArray.length * 2;//multiply the size in two and return the new one.
        Product[] newArray = new Product[newLength];
        System.arraycopy(originalArray, 0, newArray, 0, originalArray.length);
        return newArray;
    }

    private boolean checkIfExist(String choice, Product[] arr) { // To check if a product already exists
        if(numOfProducts == 0){
            return false;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null)
            {
                return false;
            }
            if (choice.equals(arr[i].getName())) {
                return true;
            }
        }
        return false;
    }

    public String productsToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Product product : products) {
            if (product != null) {
                if (sb.length() > 1) {
                    sb.append(", ");
                }
                sb.append(product.toString());
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public String toString() {
        String productsString = numOfProducts>0 ? productsToString() : "No products at the moment";
        return "username=" + username + '\n' +
                "products=" + productsString +
                '\n' +
                "numOfProducts=" + numOfProducts;
    }
}


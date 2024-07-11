public class Seller implements Comparable<Seller>{
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

    public boolean addProduct(String name, double price, int category, double packingPrice)
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

        products[numOfProducts++] = new PackagedProduct(name, price, category, packingPrice);
        return true;
    }

    public String getProductsByCategory(int category){ //Returns all the products of the chosen category
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < numOfProducts; i++){
            if(products[i].getCategoryIndex() == (category - 1)){
                sb.append(products[i].toString()).append("  ");
            }
        }
        return sb.toString();
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
            sb.append((i + 1)).append(". ").append(products[i].getName()).append(", Category:").append(products[i].getCategory());
            sb.append("  (").append(products[i].getPrice()).append("$)");
            if(products[i] instanceof PackagedProduct){
                sb.append(", Including additional packing fee of ").append(((PackagedProduct) products[i]).getPackPrice());
                sb.append("$");
            }
            sb.append("\n");
        }
        return sb.toString();
    }


    public Product getProductByName(String productName){ // input: name. output: the object
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
            if (arr[i] == null)
            {
                return false;
            }
            if (choice.equals(arr[i].getName())) {
                return true;
            }
        }
        return false;
    }

    // Method to sort sellers alphabetically based on username
    @Override
    public int compareTo(Seller s){
        return this.username.compareTo(s.username);
    }

    @Override
    public String toString() {
        String productsString = numOfProducts>0 ? getProducts() : "No products at the moment.\n";
        return "username=" + username + '\n' +
                "products=\n" + productsString +
                "numOfProducts=" + numOfProducts +
                "\n";
    }
}




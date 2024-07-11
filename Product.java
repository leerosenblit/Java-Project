// Represents a product with a name, price, category, and serial number.
public class Product {
    // Enum representing different categories of products.
    public enum eCategory {
        Kids, Electricity, Office, Clothing
    };

    private String name;
    private double price;
    private static int serialNumber = 1;
    private eCategory category;

    // Constructor to initialize product fields.
    public Product(String name, double price, int category){
        this.name = name;
        this.price = price;
        this.serialNumber = serialNumber++;
        this.category = getCategoryByIndex(category);
    }

    // Copy constructor for Product class.
    public Product(Product other){
        this.name = other.name;
        this.price = other.price;
        this.category = other.category;
    }

    // Getter for product name.
    public String getName(){
        return name;
    }

    // Getter for product serial number.
    public static int getSerialNumber() {
        return serialNumber;
    }

    // Getter for product price.
    public double getPrice(){
        return price;
    }

    // Getter for category index.
    public int getCategoryIndex(){
        return category.ordinal();
    }

    // Private method to retrieve category enum based on index.
    private eCategory getCategoryByIndex(int category){
        eCategory[] categories = eCategory.values();
        return categories[category - 1];
    }

    // Static method to convert category enums to string.
    public static String categoryToString(){
        StringBuilder sb = new StringBuilder();
        for(eCategory c : eCategory.values()){
            sb.append(c.ordinal() + 1).append(". ").append(c.name()).append('\n');
        }
        return sb.toString();
    }

    // Override toString to provide a string representation of the product.
    @Override
    public String toString() {
        return "Product{" +
                "name='" + this.name + '\'' +
                ", price=" + this.price +
                ", category=" + this.category.toString() +
                '}';
    }
}

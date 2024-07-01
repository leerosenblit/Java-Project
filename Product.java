public class Product {

    public enum Category{
        Kids, Electricity, Office, Clothing
    };

    private String name;
    private double price;
    private static int serialNumber = 1;
    private Category category;

    public Product(String name, double price, int category){
        this.name = name;
        this.price = price;
        this.serialNumber = serialNumber++;
        this.category = getCategoryByIndex(category);
    }
    wassup
    tomer
    public Product(Product other){
        this.name = other.name;
        this.price = other.price;
    }
    public String getName(){
        return name;
    }

    public static int getSerialNumber() {
        return serialNumber;
    }

    public double getPrice(){
        return price;
    }

    private Category getCategoryByIndex(int category){
        Category[] categories = Category.values();
        return categories[category - 1];
    }

    public static String categoryToString(){
        StringBuilder sb = new StringBuilder();
        for(Category c : Category.values()){
            sb.append(c.ordinal() + 1).append(". ").append(c.name()).append('\n');
        }
        return sb.toString();
    }


    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                '}';
    }
}

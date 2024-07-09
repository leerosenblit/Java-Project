public class Product {

    public enum eCategory {
        Kids, Electricity, Office, Clothing
    };

    private String name;
    private double price;
    private static int serialNumber = 1;
    private eCategory category;

    public Product(String name, double price, int category){
        this.name = name;
        this.price = price;
        this.serialNumber = serialNumber++;
        this.category = getCategoryByIndex(category);
    }

    public Product(Product other){
        this.name = other.name;
        this.price = other.price;
        this.category = other.category;
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

    public int getCategoryIndex(){
        return category.ordinal();
    }

    private eCategory getCategoryByIndex(int category){
        eCategory[] categories = eCategory.values();
        return categories[category - 1];
    }

    public static String categoryToString(){
        StringBuilder sb = new StringBuilder();
        for(eCategory c : eCategory.values()){
            sb.append(c.ordinal() + 1).append(". ").append(c.name()).append('\n');
        }
        return sb.toString();
    }


    @Override
    public String toString() {
        return "Product{" +
                "name='" + this.name + '\'' +
                ", price=" + this.price +
                ", category=" + this.category.toString() +
                '}';
    }
}

// Represents a packaged product, extending the Product class and adding a packaging price.
public class PackagedProduct extends Product{
    private double packPrice;

    public PackagedProduct(String name, double price, int category, double packPrice) {
        super(name, price, category);
        this.packPrice = packPrice;
    }

    public PackagedProduct(PackagedProduct other){
        super(other);
        this.packPrice = other.getPackPrice();
    }

    public double getPackPrice() {
        return packPrice;
    }
    @Override
    public double getPrice(){
        return packPrice+super.getPrice();
    }
}

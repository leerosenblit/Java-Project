// Custom exception for indicating that no products are in the cart.
public class EmptyCartException extends Exception{
    public EmptyCartException(){
        super("No product in cart. ");
    }
}

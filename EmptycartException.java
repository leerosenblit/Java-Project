public class EmptycartException extends Exception{
    public EmptycartException(){
        super("No product in cart. ");
    }
}

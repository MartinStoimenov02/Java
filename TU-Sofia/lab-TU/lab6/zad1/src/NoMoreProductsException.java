public class NoMoreProductsException extends Exception{
    @Override
    public String getMessage() {
        return "No More Products!";
    }
}

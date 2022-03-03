package main;

public class DeliveryCalculationException extends RuntimeException{
    
    public DeliveryCalculationException(String message) {
        super("Delivery calculation exception occurred. Information: " + message);
    }
}
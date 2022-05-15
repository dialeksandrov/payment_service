package kg.aleksandrov.paymentservice.exceptions;

/**
 * @author dialeksandrov
 * @created 14/05/2022
 **/

public class InvalidRequisiteException extends RuntimeException{
    public InvalidRequisiteException(String message){
        super(message);
    }
}

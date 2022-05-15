package kg.aleksandrov.paymentservice.utils;

import java.util.regex.Pattern;

/**
 * @author dialeksandrov
 * @created 14/05/2022
 **/

public class RequisiteFormatter {
    private static final Pattern PATTERN = Pattern.compile("^[a-zA-Z0-9]{10}$");
    private final static RequisiteFormatter instance = new RequisiteFormatter();

    public static RequisiteFormatter getInstance(){
        return instance;
    }

    private RequisiteFormatter(){}

    public boolean isValid(String text){
        return PATTERN.matcher(text).matches();
    }
}

package kg.aleksandrov.paymentservice.models;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author dialeksandrov
 * @created 12/05/2022
 **/

public class CreateOrderRequest {
    @NotNull(message = "Сумма не может равняться нулю")
    private BigDecimal amount;
    private String requisite;
    private String source;
}

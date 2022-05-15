package kg.aleksandrov.paymentservice.models;

import kg.aleksandrov.paymentservice.common.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dialeksandrov
 * @created 14/05/2022
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Long id;
    private String message;
    private PaymentStatus status;
}

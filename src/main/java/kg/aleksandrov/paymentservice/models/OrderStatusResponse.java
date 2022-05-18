package kg.aleksandrov.paymentservice.models;

import kg.aleksandrov.paymentservice.common.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dialeksandrov
 * @created 18/05/2022
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusResponse {
    private Long paymentId;
    private PaymentStatus status;
}

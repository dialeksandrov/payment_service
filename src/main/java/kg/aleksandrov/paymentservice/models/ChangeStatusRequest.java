package kg.aleksandrov.paymentservice.models;

import lombok.Data;

/**
 * @author dialeksandrov
 * @created 14/05/2022
 **/
@Data
public class ChangeStatusRequest {

    private Long orderId;
    private OrderState state;

    public enum OrderState{
        CONFIRM, CANCEL
    }
}

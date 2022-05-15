package kg.aleksandrov.paymentservice.services;

import kg.aleksandrov.paymentservice.models.ChangeStatusRequest;
import kg.aleksandrov.paymentservice.models.CreateOrderRequest;
import kg.aleksandrov.paymentservice.models.OrderResponse;

/**
 * @author dialeksandrov
 * @created 12/05/2022
 **/

public interface PaymentService {

    OrderResponse createOrder(CreateOrderRequest orderRequest);

    OrderResponse confirmOrder(ChangeStatusRequest changeRequest);

    OrderResponse cancelOrder(ChangeStatusRequest changeRequest);
}

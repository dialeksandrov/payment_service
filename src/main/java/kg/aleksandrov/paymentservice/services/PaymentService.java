package kg.aleksandrov.paymentservice.services;

import kg.aleksandrov.paymentservice.models.ChangeStatusRequest;
import kg.aleksandrov.paymentservice.models.CreateOrderRequest;
import kg.aleksandrov.paymentservice.models.OrderResponse;
import kg.aleksandrov.paymentservice.models.OrderStatusResponse;

/**
 * @author dialeksandrov
 * @created 12/05/2022
 **/

public interface PaymentService {

    OrderResponse createOrder(CreateOrderRequest orderRequest, String username);

    OrderResponse confirmOrder(ChangeStatusRequest changeRequest);

    OrderResponse cancelOrder(ChangeStatusRequest changeRequest);

    OrderStatusResponse checkOrderStatus(Long paymentId);
}

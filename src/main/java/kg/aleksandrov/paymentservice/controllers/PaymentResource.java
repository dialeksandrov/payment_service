package kg.aleksandrov.paymentservice.controllers;

import kg.aleksandrov.paymentservice.common.RestResponse;
import kg.aleksandrov.paymentservice.models.ChangeStatusRequest;
import kg.aleksandrov.paymentservice.models.CreateOrderRequest;
import kg.aleksandrov.paymentservice.models.OrderResponse;
import kg.aleksandrov.paymentservice.services.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

/**
 * @author dialeksandrov
 * @created 12/05/2022
 **/
@Slf4j
@RestController
@RequestMapping(value = "/orders")
public class PaymentResource {

    private final PaymentService paymentService;

    public PaymentResource(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse createOrder(@RequestBody CreateOrderRequest request,
                                    Authentication authentication){
        try {
            User user = (User) authentication.getPrincipal();
            OrderResponse response = paymentService.createOrder(request, user.getUsername());
            return RestResponse.success(response, "Успешно");
        } catch (Exception e){
            log.error(e.getMessage());
            return RestResponse.error(e.getMessage());
        }
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse confirmOrder(@RequestBody ChangeStatusRequest request){
        try {
            OrderResponse response = paymentService.confirmOrder(request);
            return RestResponse.success(response, "Успешно");
        } catch (Exception e){
            log.error(e.getMessage());
            return RestResponse.error("Произошла ошибка");
        }
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse cancelOrder(@RequestBody ChangeStatusRequest request){
        try {
            OrderResponse response = paymentService.cancelOrder(request);
            return RestResponse.success(response, "Успешно");
        } catch (Exception e){
            log.error(e.getMessage());
            return RestResponse.error("Произошла ошибка");
        }
    }
}

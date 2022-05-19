package kg.aleksandrov.paymentservice.services.impl;

import kg.aleksandrov.paymentservice.common.enums.PaymentStatus;
import kg.aleksandrov.paymentservice.dao.PaymentRepository;
import kg.aleksandrov.paymentservice.dao.UserRepository;
import kg.aleksandrov.paymentservice.dao.entity.PaymentEntity;
import kg.aleksandrov.paymentservice.exceptions.InvalidRequisiteException;
import kg.aleksandrov.paymentservice.models.ChangeStatusRequest;
import kg.aleksandrov.paymentservice.models.CreateOrderRequest;
import kg.aleksandrov.paymentservice.models.OrderResponse;
import kg.aleksandrov.paymentservice.models.OrderStatusResponse;
import kg.aleksandrov.paymentservice.services.PaymentService;
import kg.aleksandrov.paymentservice.utils.RequisiteFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author dialeksandrov
 * @created 12/05/2022
 **/
@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, UserRepository userRepository) {
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public OrderResponse createOrder(CreateOrderRequest orderRequest, String username) {
        if (!RequisiteFormatter.getInstance().isValid(orderRequest.getRequisite())){
            throw new InvalidRequisiteException("Неверный формат реквизита");
        }

        try {
           PaymentEntity payment = paymentRepository.save(new PaymentEntity(orderRequest, username, PaymentStatus.CREATED));
           return new OrderResponse(payment.getId(), "Платежное поручение успешно создано", PaymentStatus.CREATED);
        } catch (Exception e) {
            log.error("Unable create order cause {}", e.getMessage());
            return new OrderResponse(null, "Cannot create order with requisite " + orderRequest.getRequisite(),
                    PaymentStatus.ERROR);
        }
    }

    @Override
    public OrderResponse confirmOrder(ChangeStatusRequest changeRequest) {
        return changeOrderStatus(changeRequest, PaymentStatus.SUCCESS);
    }

    @Override
    public OrderResponse cancelOrder(ChangeStatusRequest changeRequest) {
        return changeOrderStatus(changeRequest, PaymentStatus.CANCELED);
    }

    @Override
    public OrderStatusResponse checkOrderStatus(Long paymentId) {
        if (paymentId == null)
            throw new RuntimeException("Платеж с id: " + paymentId + " не найден");
        List<PaymentEntity> entities = paymentRepository.findAllByIdOrCanceledPaymentId(paymentId);
        Optional<PaymentEntity> payment;
        if (entities.size() == 1){
            payment = Optional.ofNullable(entities.get(0));
        } else {
            payment = entities.stream().filter(x -> x.getStatus().equals(PaymentStatus.CANCELED))
                    .findFirst();
        }
        return new OrderStatusResponse(paymentId, payment.get().getStatus());
    }

    private OrderResponse changeOrderStatus(ChangeStatusRequest request, PaymentStatus status){
        PaymentEntity fromDb = paymentRepository.getById(request.getOrderId());
        try {
            switch (request.getState()){
                case CONFIRM:
                    if (fromDb.getStatus().equals(PaymentStatus.SUCCESS)){
                        return new OrderResponse(fromDb.getId(), "Платеж уже был подтвержден ранее", PaymentStatus.SUCCESS);
                    }
                    fromDb.setStatus(status);
                    paymentRepository.saveAndFlush(fromDb);
                    return new OrderResponse(fromDb.getId(), "Платеж успешно подтвержден", PaymentStatus.SUCCESS);
                case CANCEL:
                    if (!checkIfCanceled(request.getOrderId())){
                        return new OrderResponse(fromDb.getId(), "Платеж уже был отменен ранее", null);
                    }
                    PaymentEntity forCancel = new PaymentEntity();
                    forCancel.setStatus(PaymentStatus.CANCELED);
                    forCancel.setCanceledPaymentId(fromDb.getId());
                    forCancel.setAmount(fromDb.getAmount().negate());
                    forCancel.setRequisite(fromDb.getRequisite());
                    forCancel.setSource(fromDb.getSource());
                    forCancel.setCreatedDt(LocalDateTime.now());
                    forCancel.setCanceledPaymentId(request.getOrderId());
                    PaymentEntity canceledPayment = paymentRepository.save(forCancel);
                    return new OrderResponse(canceledPayment.getId(), "Платеж успешно отменен", PaymentStatus.CANCELED);
            }
        } catch (Exception e) {
            log.error("Unable change status for payment {} cause {}", fromDb.getId(), e.getMessage());
            return new OrderResponse(fromDb.getId(), "Не удалось сменить статус", PaymentStatus.ERROR);
        }
        return null;
    }

    private boolean checkIfCanceled(Long id){
        PaymentEntity payment = paymentRepository.findByCanceledPaymentId(id);
        return payment == null;
    }
}

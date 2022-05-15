package kg.aleksandrov.paymentservice.dao.entity;

import kg.aleksandrov.paymentservice.common.enums.PaymentStatus;
import kg.aleksandrov.paymentservice.models.CreateOrderRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author dialeksandrov
 * @created 12/05/2022
 **/
@Data
@NoArgsConstructor
@Table(name = "payments")
@Entity
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_dt")
    private LocalDateTime createdDt;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "requisite")
    private String requisite;

    @Column(name = "source")
    private String source;

    @Column(name = "payment_status")
    @Enumerated(value = EnumType.STRING)
    private PaymentStatus status;

    @Column(name = "canceled_payment_id")
    private Long canceledPaymentId;

    public PaymentEntity(CreateOrderRequest request, PaymentStatus status){
        this.createdDt = LocalDateTime.now();
        this.amount = request.getAmount();
        this.requisite = request.getRequisite();
        this.status = status;
        this.source = request.getSource();
    }
}

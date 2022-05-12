package kg.aleksandrov.paymentservice.dao.entity;

import kg.aleksandrov.paymentservice.common.enums.PaymentStatus;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author dialeksandrov
 * @created 12/05/2022
 **/
@Data
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

    @Column(name = "payment_status")
    private PaymentStatus status;
}

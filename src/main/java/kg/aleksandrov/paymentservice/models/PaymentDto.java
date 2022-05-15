package kg.aleksandrov.paymentservice.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import kg.aleksandrov.paymentservice.common.enums.PaymentStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static kg.aleksandrov.paymentservice.common.Common.DATE_TIME_FORMAT;

/**
 * @author dialeksandrov
 * @created 12/05/2022
 **/
@Data
public class PaymentDto {
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_FORMAT)
    private LocalDateTime createdDt;
    private BigDecimal amount;
    private PaymentStatus status;
    private String requisite;
    private String source;
}

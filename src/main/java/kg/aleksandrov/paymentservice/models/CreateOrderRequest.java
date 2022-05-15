package kg.aleksandrov.paymentservice.models;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author dialeksandrov
 * @created 12/05/2022
 **/
public class CreateOrderRequest {
    @NotNull(message = "Сумма не может равняться нулю")
    private BigDecimal amount;
    private String requisite;
    private String source;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getRequisite() {
        return requisite;
    }

    public void setRequisite(String requisite) {
        this.requisite = requisite;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}

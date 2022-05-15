package kg.aleksandrov.paymentservice.dao;

import kg.aleksandrov.paymentservice.dao.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author dialeksandrov
 * @created 12/05/2022
 **/
@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
    PaymentEntity findByCanceledPaymentId(Long id);
}

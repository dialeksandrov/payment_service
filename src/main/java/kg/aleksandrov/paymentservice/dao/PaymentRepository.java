package kg.aleksandrov.paymentservice.dao;

import kg.aleksandrov.paymentservice.dao.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dialeksandrov
 * @created 12/05/2022
 **/
@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
    PaymentEntity findByCanceledPaymentId(Long id);

    @Query(value = "select p from PaymentEntity p where (p.id = :id or p.canceledPaymentId = :id)")
    List<PaymentEntity> findAllByIdOrCanceledPaymentId(@Param("id") Long paymentId);
}

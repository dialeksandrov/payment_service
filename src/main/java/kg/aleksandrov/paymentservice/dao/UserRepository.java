package kg.aleksandrov.paymentservice.dao;

import kg.aleksandrov.paymentservice.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author dialeksandrov
 * @created 12/05/2022
 **/
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByLogin(String login);
}

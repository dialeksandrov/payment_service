package kg.aleksandrov.paymentservice.dao.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author dialeksandrov
 * @created 12/05/2022
 **/
@Data
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;
}

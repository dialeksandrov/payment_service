package kg.aleksandrov.paymentservice.services.security;

import kg.aleksandrov.paymentservice.dao.UserRepository;
import kg.aleksandrov.paymentservice.dao.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.List;

/**
 * @author dialeksandrov
 * @created 15/05/2022
 **/
@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init(){
        userRepository.deleteAll();
        List<UserEntity> users = Arrays.asList(
                new UserEntity(1L,"umai", "$2a$10$66/gp3RRxyj/o1.KmzcM7.kvnKuYMVvaeCnJpIMI4QS9EmZnvnsUO", "user"),
                new UserEntity(2L,"nur", "$2a$10$66/gp3RRxyj/o1.KmzcM7.kvnKuYMVvaeCnJpIMI4QS9EmZnvnsUO", "user")
        );
        userRepository.saveAll(users);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByLogin(username);
        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("user"));
        return new User(user.getLogin(), user.getPassword(), authorities);
    }
}

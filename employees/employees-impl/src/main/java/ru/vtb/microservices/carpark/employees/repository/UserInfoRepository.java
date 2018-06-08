package ru.vtb.microservices.carpark.employees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vtb.microservices.carpark.auth.model.UserInfo;

/**
 * @author mirzoevnik
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    UserInfo getByLogin(String login);
}

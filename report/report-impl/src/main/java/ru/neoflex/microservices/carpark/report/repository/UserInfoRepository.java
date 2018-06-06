package ru.neoflex.microservices.carpark.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.neoflex.microservices.carpark.auth.model.UserInfo;

/**
 * Created by rmorenko on 06.06.2018.
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
}

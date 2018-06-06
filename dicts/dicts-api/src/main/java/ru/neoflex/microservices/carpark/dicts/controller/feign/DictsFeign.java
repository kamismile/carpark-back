package ru.neoflex.microservices.carpark.dicts.controller.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import ru.neoflex.microservices.carpark.dicts.controller.api.DictsApi;

/**
 * @author rmorenko
 */
@FeignClient(name="dicts")
public interface DictsFeign extends DictsApi {

}

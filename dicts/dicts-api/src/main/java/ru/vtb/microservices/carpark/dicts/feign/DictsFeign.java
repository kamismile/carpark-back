package ru.vtb.microservices.carpark.dicts.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import ru.vtb.microservices.carpark.dicts.api.DictsApi;

/**
 * @author rmorenko
 */
@FeignClient(name="dicts")
public interface DictsFeign extends DictsApi {

}
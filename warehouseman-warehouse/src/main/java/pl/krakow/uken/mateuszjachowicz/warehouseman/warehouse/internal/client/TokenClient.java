package pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.client;

import org.springframework.cloud.openfeign.FeignClient;
import pl.krakow.uken.mateuszjachowicz.warehouseman.auth.application.controllers.TokenController;

@FeignClient(url = "${apis.warehouseman-auth.url}", name = "warehouseman-auth")
public interface TokenClient extends TokenController {
}

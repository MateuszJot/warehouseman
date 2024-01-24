package pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse.internal.client;

import org.springframework.cloud.openfeign.FeignClient;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.controllers.ItemController;

@FeignClient(url = "${apis.warehouseman-product.url}", name = "warehouseman-product")
public interface ItemClient extends ItemController {
}

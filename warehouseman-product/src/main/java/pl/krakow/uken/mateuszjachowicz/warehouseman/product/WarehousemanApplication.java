package pl.krakow.uken.mateuszjachowicz.warehouseman.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@EnableFeignClients
public class WarehousemanApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarehousemanApplication.class, args);
	}

}

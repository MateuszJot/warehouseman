package pl.krakow.uken.mateuszjachowicz.warehouseman.warehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WarehousemanWarehouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarehousemanWarehouseApplication.class, args);
	}

}

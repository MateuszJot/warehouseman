package pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.suppliers.SupplierCreateRequestDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.suppliers.SupplierResponseDTO;

public interface SupplierController {
    @PostMapping("/suppliers")
    ResponseEntity<SupplierResponseDTO> createSupplier(@Valid @RequestBody SupplierCreateRequestDTO body);
}

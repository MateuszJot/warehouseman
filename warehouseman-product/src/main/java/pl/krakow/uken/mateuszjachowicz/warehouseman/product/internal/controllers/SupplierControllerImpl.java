package pl.krakow.uken.mateuszjachowicz.warehouseman.product.internal.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.controllers.SupplierController;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.suppliers.SupplierCreateRequestDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.suppliers.SupplierListResponseDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.suppliers.SupplierResponseDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.internal.services.SupplierService;

@RestController
@AllArgsConstructor
public class SupplierControllerImpl implements SupplierController {
    private final SupplierService service;
    @Override
    public ResponseEntity<SupplierResponseDTO> createSupplier(SupplierCreateRequestDTO body) {
        return ResponseEntity.ok(service.createSupplier(body));
    }

    @Override
    public ResponseEntity<SupplierListResponseDTO> getSuppliers() {
        return ResponseEntity.ok(service.getSuppliers());
    }
}

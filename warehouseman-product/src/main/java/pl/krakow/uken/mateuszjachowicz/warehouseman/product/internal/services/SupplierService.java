package pl.krakow.uken.mateuszjachowicz.warehouseman.product.internal.services;

import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.suppliers.SupplierCreateRequestDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.suppliers.SupplierListResponseDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.suppliers.SupplierResponseDTO;

public interface SupplierService {
    SupplierResponseDTO createSupplier(SupplierCreateRequestDTO body);

    SupplierListResponseDTO getSuppliers();
}

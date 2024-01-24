package pl.krakow.uken.mateuszjachowicz.warehouseman.product.internal.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.suppliers.SupplierCreateRequestDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.suppliers.SupplierResponseDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.internal.entites.SupplierEntity;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.internal.mappers.SupplierMapper;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.internal.repositories.SupplierRepository;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository repository;
    private final SupplierMapper mapper;
    @Override
    public SupplierResponseDTO createSupplier(SupplierCreateRequestDTO body) {
        SupplierEntity entity = mapper.toEntity(body);
        SupplierEntity persistEntity = repository.save(entity);
        return mapper.toResponse(persistEntity);
    }
}

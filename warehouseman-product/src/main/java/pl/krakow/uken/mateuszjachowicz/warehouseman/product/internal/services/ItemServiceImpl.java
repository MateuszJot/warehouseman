package pl.krakow.uken.mateuszjachowicz.warehouseman.product.internal.services;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pl.krakow.uken.mateuszjachowicz.warehouseman.lib.exceptions.EntityNotFoundException;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.items.ItemCreateRequestDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.items.ItemListResponseDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.items.ItemResponseDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.application.dtos.items.ItemSearchParamsDTO;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.internal.entites.ItemEntity;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.internal.entites.ItemEntity_;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.internal.entites.SupplierEntity;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.internal.mappers.ItemMapper;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.internal.repositories.ItemRepository;
import pl.krakow.uken.mateuszjachowicz.warehouseman.product.internal.repositories.SupplierRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository repository;
    private final ItemMapper mapper;
    private final SupplierRepository supplierRepository;
    @Override
    public ItemResponseDTO createItem(ItemCreateRequestDTO body) {
        ItemEntity entity = mapper.toEntity(body);
        SupplierEntity supplierEntity = supplierRepository
                .findById(body.getSupplier())
                .orElseThrow(() -> new EntityNotFoundException(SupplierEntity.class, body.getSupplier()));
        entity.setSupplier(supplierEntity);
        ItemEntity persistEntity = repository.save(entity);
        return mapper.toResponse(persistEntity);
    }

    @Override
    public ItemResponseDTO getItemByID(UUID id) {
        return repository
                .findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException(ItemEntity.class, id));
    }

    @Override
    public ItemListResponseDTO getItemsByParams(ItemSearchParamsDTO params) {
        Page<ItemEntity> items = repository
                .findAll((Specification<ItemEntity>) (root, query, builder) -> {
                    List<Predicate> predicates = new ArrayList<>();
                    if (params.getSearchString() != null) {
                        predicates.add(builder.like(root.get(ItemEntity_.name), "%" + params.getSearchString() + "%"));
                    }
                    if (params.getPriceMax() != null) {
                        predicates.add(builder.greaterThan(root.get(ItemEntity_.price), params.getPriceMax()));
                    }
                    if (params.getPriceMin() != null) {
                        predicates.add(builder.greaterThan(root.get(ItemEntity_.price), params.getPriceMin()));
                    }

                    return builder.and(predicates.toArray(new Predicate[0]));
                }, PageRequest.of(params.getPageNumber(), params.getPageSize()));
        return mapper.toResponse(items);
    }
}

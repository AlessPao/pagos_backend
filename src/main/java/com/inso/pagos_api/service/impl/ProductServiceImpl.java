package com.inso.pagos_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.inso.pagos_api.model.Product;
import com.inso.pagos_api.repo.IGenericRepo;
import com.inso.pagos_api.repo.IProductRepo;
import com.inso.pagos_api.service.IProductService;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends CRUDImpl<Product, Integer> implements IProductService {

    private final IProductRepo repo;

    @Override
    protected IGenericRepo<Product, Integer> getRepo() {
        return repo;
    }
}

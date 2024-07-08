package com.inso.pagos_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.inso.pagos_api.model.Category;
import com.inso.pagos_api.repo.ICategoryRepo;
import com.inso.pagos_api.repo.IGenericRepo;
import com.inso.pagos_api.service.ICategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl extends CRUDImpl<Category, Integer> implements ICategoryService
{
    private final ICategoryRepo repo;

    @Override
    protected IGenericRepo<Category, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<Category> saveAll(List<Category> categories) throws Exception {
        return repo.saveAll(categories);
    }
}

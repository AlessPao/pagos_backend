package com.inso.pagos_api.service;

import com.inso.pagos_api.model.Category;

import java.util.List;

public interface ICategoryService extends ICRUD<Category, Integer>{
    List<Category> saveAll(List<Category> categories) throws Exception;
}

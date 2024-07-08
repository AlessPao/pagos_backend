package com.inso.pagos_api.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inso.pagos_api.dto.CategoryDTO;
import com.inso.pagos_api.model.Category;
import com.inso.pagos_api.service.ICategoryService;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController
{
    private final ModelMapper mapper;
    private final ICategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> readAll() throws Exception {
        List<CategoryDTO> list = service.readAll().stream().map(this::convertToDto).toList();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    private CategoryDTO convertToDto(Category obj){
        return mapper.map(obj, CategoryDTO.class);
    }

    private Category convertToEntity(CategoryDTO dto){
        return mapper.map(dto, Category.class);
    }
}

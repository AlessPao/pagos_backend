package com.inso.pagos_api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.inso.pagos_api.dto.ProductDTO;
import com.inso.pagos_api.model.Product;
import com.inso.pagos_api.service.IProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService service;
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> readAll() throws Exception {
        List<ProductDTO> list = service.readAll().stream()
                .map(this::convertToDto).collect(Collectors.toList());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> readById(@PathVariable("id") Integer id) throws Exception {
        ProductDTO dto = this.convertToDto(service.readById(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO dto) throws Exception{
        Product obj = service.save(this.convertToEntity(dto));
        return new ResponseEntity<>(this.convertToDto(obj), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable("id") Integer id, @Valid @RequestBody ProductDTO dto) throws Exception{
        dto.setIdProduct(id);
        Product obj = service.update(this.convertToEntity(dto), id);
        return new ResponseEntity<>(this.convertToDto(obj), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /////////////////////////////
    private ProductDTO convertToDto(Product obj){
        return mapper.map(obj, ProductDTO.class);
    }

    private Product convertToEntity(ProductDTO dto){
        return mapper.map(dto, Product.class);
    }
}

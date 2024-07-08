package com.inso.pagos_api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.inso.pagos_api.dto.CompanyDTO;
import com.inso.pagos_api.model.Company;
import com.inso.pagos_api.service.ICompanyService;

import java.util.List;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController
{
    private final ICompanyService service;
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<CompanyDTO>> readAll() throws Exception {
        List<CompanyDTO> list = service.readAll().stream().map(this::convertToDto).toList();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> readById(@PathVariable("id") Integer id) throws Exception {
        CompanyDTO dto = this.convertToDto(service.readById(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<List<CompanyDTO>> readByCode(@PathVariable("code") Integer code) throws Exception {
        List<CompanyDTO> dto = service.findByCode(code).stream().map(this::convertToDto).toList();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CompanyDTO> create(@Valid @RequestBody CompanyDTO dto) throws Exception{
        Company obj = service.save(this.convertToEntity(dto));
        return new ResponseEntity<>(this.convertToDto(obj), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDTO> update(@PathVariable("id") Integer id, @Valid @RequestBody CompanyDTO dto) throws Exception{
        Company obj = service.update(this.convertToEntity(dto), id);
        return new ResponseEntity<>(this.convertToDto(obj), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /////////////////////////////
    private CompanyDTO convertToDto(Company obj){
        return mapper.map(obj, CompanyDTO.class);
    }

    private Company convertToEntity(CompanyDTO dto){
        return mapper.map(dto, Company.class);
    }
}

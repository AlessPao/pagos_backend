package com.inso.pagos_api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.inso.pagos_api.dto.InvoiceDTO;
import com.inso.pagos_api.model.Invoice;
import com.inso.pagos_api.service.IInvoiceService;

import java.util.List;

@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
public class InvoiceController
{
    private final IInvoiceService service;
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<InvoiceDTO>> readAll() throws Exception {
        List<InvoiceDTO> list = service.readAll().stream().map(this::convertToDto).toList();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDTO> readById(@PathVariable("id") Integer id) throws Exception {
        InvoiceDTO dto = this.convertToDto(service.readById(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/email")
    public ResponseEntity<List<InvoiceDTO>> readById(@RequestParam("email") String email) throws Exception {
        List<InvoiceDTO> invoices = service.findByClientEmail(email).stream().map(this::convertToDto).toList();
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Invoice> create(@Valid @RequestBody Invoice entity) throws Exception{
        Invoice obj = service.save(entity);
        return new ResponseEntity<>(obj, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<InvoiceDTO> update(@Valid @RequestBody InvoiceDTO dto) throws Exception{
        Invoice obj = service.update(this.convertToEntity(dto), dto.getIdInvoice());
        return new ResponseEntity<>(this.convertToDto(obj), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /////////////////////////////
    private InvoiceDTO convertToDto(Invoice obj){
        return mapper.map(obj, InvoiceDTO.class);
    }

    private Invoice convertToEntity(InvoiceDTO dto){
        return mapper.map(dto, Invoice.class);
    }
}

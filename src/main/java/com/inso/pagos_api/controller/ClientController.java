package com.inso.pagos_api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.inso.pagos_api.dto.ClientDTO;
import com.inso.pagos_api.model.Client;
import com.inso.pagos_api.service.IClientService;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController
{
    private final IClientService service;
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> readAll() throws Exception {
        List<ClientDTO> list = service.readAll().stream().map(this::convertToDto).toList();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> readById(@PathVariable("id") Integer id) throws Exception {
        ClientDTO dto = this.convertToDto(service.readById(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> create(@Valid @RequestBody ClientDTO dto) throws Exception{
        Client obj = service.save(this.convertToEntity(dto));
        return new ResponseEntity<>(this.convertToDto(obj), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable("id") Integer id, @Valid @RequestBody ClientDTO dto) throws Exception{
        dto.setIdClient(id);
        Client obj = service.update(this.convertToEntity(dto), id);
        return new ResponseEntity<>(this.convertToDto(obj), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /////////////////////////////
    private ClientDTO convertToDto(Client obj){
        return mapper.map(obj, ClientDTO.class);
    }

    private Client convertToEntity(ClientDTO dto){
        return mapper.map(dto, Client.class);
    }
}

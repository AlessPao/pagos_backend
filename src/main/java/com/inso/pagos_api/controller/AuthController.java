package com.inso.pagos_api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inso.pagos_api.dto.AuthDTO;
import com.inso.pagos_api.dto.ClientDTO;
import com.inso.pagos_api.model.Client;
import com.inso.pagos_api.service.IAuthService;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController
{
    private final IAuthService service;
    private final ModelMapper mapper;

    @PostMapping
    public ResponseEntity<ClientDTO> create(@Valid @RequestBody AuthDTO dto) throws Exception{
        ClientDTO obj = convertToDto(service.login(dto));
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    private ClientDTO convertToDto(Client obj){
        return mapper.map(obj, ClientDTO.class);
    }

}

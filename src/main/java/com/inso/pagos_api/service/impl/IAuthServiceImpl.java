package com.inso.pagos_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.inso.pagos_api.dto.AuthDTO;
import com.inso.pagos_api.exception.ModelNotFoundException;
import com.inso.pagos_api.model.Client;
import com.inso.pagos_api.repo.IClientRepo;
import com.inso.pagos_api.service.IAuthService;

@Service
@RequiredArgsConstructor
public class IAuthServiceImpl implements IAuthService
{

    private final IClientRepo repo;

    @Override
    public Client login(AuthDTO dto) {
        Client client = repo.findByEmail(dto.getEmail());
        if (client == null) {
            throw new ModelNotFoundException("Cliente no encontrado");
        }else if (!client.getPassword().equals(dto.getPassword())) {
            throw new ModelNotFoundException("Contraseña incorrecta");
        }

        return client;
    }
}

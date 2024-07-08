package com.inso.pagos_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.inso.pagos_api.model.Client;
import com.inso.pagos_api.repo.IClientRepo;
import com.inso.pagos_api.repo.IGenericRepo;
import com.inso.pagos_api.service.IClientService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl extends CRUDImpl<Client, Integer> implements IClientService {

    private final IClientRepo repo;

    @Override
    protected IGenericRepo<Client, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<Client> saveAll(List<Client> clients) throws Exception {
        return repo.saveAll(clients);
    }
}

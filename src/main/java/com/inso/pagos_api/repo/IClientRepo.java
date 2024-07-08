package com.inso.pagos_api.repo;

import com.inso.pagos_api.model.Client;

public interface IClientRepo extends IGenericRepo<Client, Integer> {
    Client findByEmail(String email);
    Client findByPassword(String password);
}

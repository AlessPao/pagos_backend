package com.inso.pagos_api.service;


import com.inso.pagos_api.model.Client;

import java.util.List;

public interface IClientService extends ICRUD<Client, Integer>{
    List<Client> saveAll(List<Client> clients) throws Exception;
}

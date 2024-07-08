package com.inso.pagos_api.service;

import com.inso.pagos_api.dto.AuthDTO;
import com.inso.pagos_api.model.Client;

public interface IAuthService
{
    Client login(AuthDTO dto);
}

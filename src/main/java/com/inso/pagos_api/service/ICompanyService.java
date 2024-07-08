package com.inso.pagos_api.service;

import com.inso.pagos_api.model.Company;

import java.util.List;

public interface ICompanyService extends ICRUD<Company, Integer>
{
    List<Company> saveAll(List<Company> companies) throws Exception;

    List<Company> findByCode(int code) throws Exception;
}

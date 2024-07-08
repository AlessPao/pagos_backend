package com.inso.pagos_api.repo;

import com.inso.pagos_api.model.Company;

import java.util.List;

public interface ICompanyRepo extends IGenericRepo<Company, Integer>
{
    List<Company> findByCode(int code);
}

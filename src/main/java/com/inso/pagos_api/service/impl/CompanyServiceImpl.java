package com.inso.pagos_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.inso.pagos_api.model.Company;
import com.inso.pagos_api.repo.ICompanyRepo;
import com.inso.pagos_api.repo.IGenericRepo;
import com.inso.pagos_api.service.ICompanyService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl extends CRUDImpl<Company, Integer> implements ICompanyService
{
    private final ICompanyRepo repo;

    @Override
    protected IGenericRepo<Company, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<Company> saveAll(List<Company> companies) throws Exception {
        return repo.saveAll(companies);
    }

    @Override
    public List<Company> findByCode(int code) throws Exception {
        return repo.findByCode(code);
    }
}

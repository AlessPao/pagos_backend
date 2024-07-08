package com.inso.pagos_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.inso.pagos_api.model.InvoiceDetail;
import com.inso.pagos_api.repo.IGenericRepo;
import com.inso.pagos_api.repo.IInvoiceDetailRepo;
import com.inso.pagos_api.service.InvoiceDetailService;

@Service
@RequiredArgsConstructor
public class InvoiceDetailServiceImpl extends CRUDImpl<InvoiceDetail, Integer> implements InvoiceDetailService
{

    private final IInvoiceDetailRepo repo;

    @Override
    protected IGenericRepo<InvoiceDetail, Integer> getRepo() {
        return repo;
    }
}

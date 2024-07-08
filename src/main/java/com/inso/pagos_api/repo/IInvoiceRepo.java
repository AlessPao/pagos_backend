package com.inso.pagos_api.repo;


import com.inso.pagos_api.model.Invoice;

import java.util.List;

public interface IInvoiceRepo extends IGenericRepo<Invoice, Integer>
{
    List<Invoice> findByClientEmail(String email);
    //exists codeEmitted
    boolean existsByCodeEmitted(String codeEmitted);
}

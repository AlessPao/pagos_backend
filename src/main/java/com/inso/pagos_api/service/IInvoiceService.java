package com.inso.pagos_api.service;

import com.inso.pagos_api.model.Invoice;

import java.util.List;

public interface IInvoiceService extends ICRUD<Invoice, Integer>{
    List<Invoice> findByClientEmail(String email);

}

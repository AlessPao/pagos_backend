package com.inso.pagos_api.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.inso.pagos_api.exception.ModelNotFoundException;
import com.inso.pagos_api.model.Category;
import com.inso.pagos_api.model.Invoice;
import com.inso.pagos_api.model.InvoiceDetail;
import com.inso.pagos_api.repo.ICategoryRepo;
import com.inso.pagos_api.repo.IGenericRepo;
import com.inso.pagos_api.repo.IInvoiceDetailRepo;
import com.inso.pagos_api.repo.IInvoiceRepo;
import com.inso.pagos_api.service.IInvoiceService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl extends CRUDImpl<Invoice, Integer> implements IInvoiceService
{

    private final IInvoiceRepo repo;
    private final ICategoryRepo categoryRepository;
    private final IInvoiceDetailRepo invoiceDetailRepository;

    @Override
    protected IGenericRepo<Invoice, Integer> getRepo() {
        return repo;
    }

    @Transactional
    @Override
    public Invoice save(Invoice invoice) throws Exception {

        Category categoryFound = categoryRepository.findById(invoice.getCategory().getIdCategory()).orElse(null);
        if(this.existsCodeEmitted(invoice.getCodeEmitted()))
        {
            throw new ModelNotFoundException("Número de documento ya existe");
        }
        if(categoryFound == null){
            throw new ModelNotFoundException("Category not found");
        }
        else{
            if(categoryFound.getCode() == 2){
                return this.saveInvoiceCode2(invoice);
            }else {
                return this.saveInvoiceDefault(invoice);
            }
        }
    }

    private Invoice saveInvoiceCode2(Invoice invoice) {
        double tasaInteresMensual = invoice.getTax() / 100 / 12;

        double cuotaFija = Math.round((invoice.getTotal() * tasaInteresMensual * Math.pow(1 + tasaInteresMensual, invoice.getTime())) /
                (Math.pow(1 + tasaInteresMensual, invoice.getTime()) - 1) * 100.0) / 100.0;

        double saldo = invoice.getTotal();

        List<InvoiceDetail> detalles = new ArrayList<>();

        for (int i = 0; i < invoice.getTime(); i++) {
            InvoiceDetail detail = new InvoiceDetail();
            detail.setInvoice(invoice);

            double interest = Math.round(saldo * tasaInteresMensual * 100.0) / 100.0;
            detail.setInterest(interest);

            double capital = cuotaFija - interest;
            detail.setPrice(capital);

            detail.setQuantity(saldo - capital);
            saldo -= capital;

            detail.setQuota(cuotaFija);
            detail.setDescription("Cuota " + (i + 1));

            detail.setDateDue(this.calculateDueDate(invoice.getDateDue(), i));

            detalles.add(detail);
        }

        //invoice.setCodeEmitted("F-" + LocalDate.now().format(this.formatDate()) + "-" + (System.nanoTime()/ 1000000));

        invoice.setDetails(detalles);
        invoice.setDateEmitted(LocalDate.now());
        //invoice.setDateDue(LocalDate.now().plusDays(7));

        return repo.save(invoice);
    }

    private Invoice saveInvoiceDefault(Invoice invoice) {
        double total=0;
        for (InvoiceDetail item:invoice.getDetails()){
            total+=item.getPrice()*item.getQuantity();
        }
        //invoice.setCodeEmitted("F-" + LocalDate.now().format(this.formatDate()) + "-" + (System.nanoTime()/ 1000000));
        invoice.setTotal(total);
        invoice.getDetails().forEach(line->line.setInvoice(invoice));
        invoice.setDateEmitted(LocalDate.now());

        return repo.save(invoice);
    }

    private LocalDate calculateDueDate(LocalDate initialDate, int monthsToAdd) {
        LocalDate dueDate = initialDate.plusMonths(monthsToAdd);

        if (initialDate.getDayOfMonth() > dueDate.lengthOfMonth()) {
            dueDate = dueDate.withDayOfMonth(dueDate.lengthOfMonth());
        }

        if (dueDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
            dueDate = dueDate.plusDays(2);
        } else if (dueDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            dueDate = dueDate.plusDays(1);
        }

        return dueDate;
    }

    private DateTimeFormatter formatDate() {
        return DateTimeFormatter.ofPattern("MMddyyyy");
    }

    @Override
    public List<Invoice> findByClientEmail(String email) {
        return repo.findByClientEmail(email);
    }

    private boolean existsCodeEmitted(String code) {
        return repo.existsByCodeEmitted(code);
    }
}

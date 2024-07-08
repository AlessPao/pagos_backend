package com.inso.pagos_api.dto;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvoiceDTO
{
    @Min(value = 1)
    private Integer idInvoice;

    private String codeEmitted;

    // @NotNull
    private LocalDate dateEmitted;

    // @NotNull
    private LocalDate dateDue;

    @NotNull
    private ClientDTO client;

    //private CompanyDTO company;
    private String nameCompany;

    private String rucCompany;

    private String addressCompany;

    private int codigo;

    private CategoryDTO category;

    //@NotNull
    //@Min(value = 1)
    private double total;

    @NotNull
    private boolean isPaid;

    private int time;

    @NotNull
    @JsonManagedReference
    private List<InvoiceDetailDTO> details;

    @NotNull
    private double tax;
}

package com.inso.pagos_api.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvoiceDetailDTO
{
    private Integer idInvoiceDetail;

    @NotNull
    @JsonBackReference
    private InvoiceDTO invoice;

    @Length(max = 80)
    private String description;
    /*@NotNull
    private ProductDTO product;*/

    @NotNull
    @Min(value = 1)
    private double quantity;

    @NotNull
    @Min(value = 1)
    private double price;

    @Min(value = 0)
    private double interest;

    @Min(value = 1)
    private double quota;

    private LocalDate dateDue;
}

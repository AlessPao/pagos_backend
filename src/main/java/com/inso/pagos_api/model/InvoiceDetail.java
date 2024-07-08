package com.inso.pagos_api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class InvoiceDetail
{
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idInvoiceDetail;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_sale", nullable = false, foreignKey = @ForeignKey(name = "FK_DETAIL_INVOICE"))
    private Invoice invoice;

    @Column(length = 80)
    private String description;

    @Column(nullable = false)
    private double quantity;

    @Column(columnDefinition = "decimal(8,2)", nullable = false)
    private double price;

    @Column(columnDefinition = "decimal(8,2)")
    private double interest;

    @Column(columnDefinition = "decimal(8,2)")
    private double quota;

    @Column(nullable = false)
    private boolean isPaid;

    private LocalDate dateDue;
}

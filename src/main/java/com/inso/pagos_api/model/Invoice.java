package com.inso.pagos_api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Invoice
{
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idInvoice;

    @Column(length = 60, nullable = false, unique = true)
    private String codeEmitted;

    @Column(nullable = false)
    private LocalDate dateEmitted;

    @Column(nullable = false)
    private LocalDate dateDue;

    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false, foreignKey = @ForeignKey(name = "FK_INVOICE_CLIENT"))
    private Client client;

    /*@ManyToOne@JoinColumn(name = "id_company", nullable = false, foreignKey = @ForeignKey(name = "FK_INVOICE_COMPANY"))
    private Company company;*/

    @Column(length = 100, nullable = false)
    private String nameCompany;

    @Column(length = 11, nullable = false)
    private String rucCompany;

    @Column(length = 100, nullable = false)
    private String addressCompany;

    private int codigo;

    @ManyToOne //(FK)
    @JoinColumn(name = "id_category", nullable = false, foreignKey = @ForeignKey(name = "FK_INVOICE_CATEGORY"))
    private Category category;

    @Column(columnDefinition = "decimal(8,2)", nullable = false)
    private double total;

    @Column(nullable = false)
    private boolean isPaid;

    @JsonManagedReference
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<InvoiceDetail> details;

    private int time;

    private double tax;
}

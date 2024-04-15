package cz.itnetwork.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity(name = "invoice")
@Getter
@Setter
public class InvoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int invoiceNumber;

    @Column(nullable = false)
    private Date issued;

    @Column(nullable = false)
    private Date dueDate;

    @Column(nullable = false)
    private String product;

    private long price;

    private int vat;

    @Column(columnDefinition = "TEXT",nullable = true)
    private String note;

    @ManyToOne
    @JoinColumn(nullable = false)
    private PersonEntity buyer;

    @ManyToOne
    @JoinColumn(nullable = false)
    private PersonEntity seller;

}

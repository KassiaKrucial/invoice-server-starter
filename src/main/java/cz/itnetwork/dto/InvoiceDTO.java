package cz.itnetwork.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

/**
 * This class represents a DTO of an invoice
 * getters, setters, constructors generated with lombok
 *
 * @author Kat
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO {

    /**
     * Represents the id of an invoice
     * Generated by the database {@link cz.itnetwork.entity.InvoiceEntity}
     * Connects to json property sent from client via {@code @JsonProperty("_id)}
     */
    @JsonProperty("_id")
    private Long id;

    /**
     * Represents the invoice number
     * Entered by user
     * Cannot be null
     */
    @NotNull
    private int invoiceNumber;

    /**
     * Represents the date an invoice is issued
     * Entered by user
     * Json property shaped by {@code @JsonFormat as pattern `yyyy-MM-dd`}
     * Cannot be null
     */
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date issued;

    /**
     * Represents the date an invoice is due
     * Entered by user
     * Json property shaped by {@code @JsonFormat as pattern `yyyy-MM-dd`}
     * Cannot be null
     */
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dueDate;

    /**
     * Represents the name of an invoice
     * Entered by user
     * Cannot be null
     */
    @NotNull
    private String product;

    /**
     * Represents the price of a product
     * Entered by user
     * Cannot be null, must be positive or 0
     */
    @NotNull
    @PositiveOrZero
    private long price;

    /**
     * Represents the Value Added Tax of a product
     * Entered by user
     * Cannot be null, must be positive or 0
     */
    @NotNull
    @PositiveOrZero
    private int vat;

    /**
     * Represents a note to an invoice
     * Entered by user
     * Can be null
     */
    private String note;

    /**
     * Represents the PersonDTO of a buyer {@link PersonDTO}
     * User sends the id of a person already existing in the database in json object buyer
     * Cannot be null
     */
    @NotNull
    private PersonDTO buyer;

    /**
     * Represents the PersonDTO of a seller {@link PersonDTO}
     * User sends the id of a person already existing in the database in json object seller
     * Cannot be null
     */
    @NotNull
    private PersonDTO seller;
}

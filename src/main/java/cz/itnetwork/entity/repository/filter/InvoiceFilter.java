package cz.itnetwork.entity.repository.filter;

import lombok.Data;

/**
 * <p>This class defines filtering parameters of invoices</p>
 * <p>Getters, setters, constructor generated with lombok</p>
 *
 * @author Kat
 */
@Data
public class InvoiceFilter {

    /**
     * <p>Represents the id of a buyer</p>
     * <p>Selected by user</p>
     */
    private Long buyerID;

    /**
     * <p>Represents the id of a seller</p>
     * <p>Selected by user</p>
     */
    private Long sellerID;

    /**
     * <p>Represents name of a product</p>
     * <p>Written by user</p>
     */
    private String product;

    /**
     * <p>Represents the minimal price of a product</p>
     * <p>Written by user</p>
     */
    private Long minPrice;

    /**
     * <p>Represents the maximal price of a product</p>
     * <p>Written by user</p>
     */
    private Long maxPrice;

    /**
     * <p>Limits the total of results</p>
     * <p>Written by user</p>
     * <p>Default value is {@code 10}</p>
     */
    private Integer limit = 10;

}

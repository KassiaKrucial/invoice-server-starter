package cz.itnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p>This class represents the DTO of statistics for invoices</p>
 * <p>Getters, setters, constructors generated with lombok</p>
 *
 * @author Kat
 */
@Data
@AllArgsConstructor
public class InvoicesStatisticsDTO {

    /**
     * Represents the sum of revenue of all invoices issued during the current year
     */
    private Long currentYearSum;

    /**
     * Represents the sum of revenue of all invoices in the database
     */
    private Long allTimeSum;

    /**
     * Represents the total number of invoices in the database
     */
    private Long invoicesCount;
}

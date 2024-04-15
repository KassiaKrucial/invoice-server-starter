package cz.itnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This class represents a DTO of statistics for invoices
 * Getters, setters, constructors generated with lombok
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

package cz.itnetwork.service;

import cz.itnetwork.dto.InvoicesStatisticsDTO;
import cz.itnetwork.dto.PersonsStatisticsDTO;

import java.util.List;

/**
 * The interface provides methods for reading statistics
 *
 * @author Kat
 */
public interface StatisticsService {

    /**
     * Gets general statistics of invoices
     * @return The required data as {@link InvoicesStatisticsDTO}
     */
    InvoicesStatisticsDTO getInvoicesStatistics();

    /**
     * Gets statistics grouped by specific people/companies
     * @return List of people/companies (id, name) and the statistic
     */
    List<PersonsStatisticsDTO> getPersonsStatistics();
}

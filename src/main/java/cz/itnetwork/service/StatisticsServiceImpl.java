package cz.itnetwork.service;

import cz.itnetwork.dto.InvoicesStatisticsDTO;
import cz.itnetwork.dto.PersonsStatisticsDTO;
import cz.itnetwork.entity.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This is an implementation of {@link StatisticsService}, provides READ operations for statistics
 *
 * @author Kat
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    /**
     * Autowires the necessary {@link InvoiceRepository}
     */
    @Autowired
    private InvoiceRepository invoiceRepository;

    /**
     * Gets general statistics of invoices i.e. sum of revenue for every/current year and total count of invoices saved in the database
     * @return The required data as {@link InvoicesStatisticsDTO}
     */
    @Override
    public InvoicesStatisticsDTO getInvoicesStatistics() {
        InvoicesStatisticsDTO invoicesStatisticsDTO = invoiceRepository.getInvoicesStatistics();
        if (invoicesStatisticsDTO.getCurrentYearSum() == null) {
            invoicesStatisticsDTO.setCurrentYearSum(0L);
        }
        return invoicesStatisticsDTO;
    }

    /**
     * Gets statistics grouped by specific people/companies
     * @return List of people/companies (id, name) and the statistic for their revenue
     */
    @Override
    public List<PersonsStatisticsDTO> getPersonsStatistics() {
        return invoiceRepository.getPersonsStatistics();
    }
}

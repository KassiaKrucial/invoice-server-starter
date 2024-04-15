package cz.itnetwork.controller;

import cz.itnetwork.dto.InvoicesStatisticsDTO;
import cz.itnetwork.dto.PersonsStatisticsDTO;
import cz.itnetwork.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * This rest controller catches http requests to url addresses starting with `api`
 *
 * @author Kat
 */
@RestController
@RequestMapping("/api")
public class StatisticsController {

    /**
     * Represents an autowired bean of {@link StatisticsService} needed
     */
    @Autowired
    private StatisticsService statisticsService;

    /**
     * Shows statistics for invoices
     * Catches http GET method to url ending with `/invoices/statistics`
     * @return data filtered at database level as {@link InvoicesStatisticsDTO}
     */
    @GetMapping("/invoices/statistics")
    public InvoicesStatisticsDTO getInvoicesStatistics() {
        return statisticsService.getInvoicesStatistics();
    }

    /**
     * Shows statistics of individual people/companies
     * Catches http GET method to url ending with `/persons/statistics`
     * @return data filtered at the database level as list of {@link PersonsStatisticsDTO}
     */
    @GetMapping("/persons/statistics")
    public List<PersonsStatisticsDTO> getPersonsStatistics() {
        return statisticsService.getPersonsStatistics();
    }
}

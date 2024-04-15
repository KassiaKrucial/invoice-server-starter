package cz.itnetwork.controller;

import cz.itnetwork.dto.InvoicesStatisticsDTO;
import cz.itnetwork.dto.PersonsStatisticsDTO;
import cz.itnetwork.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/invoices/statistics")
    public InvoicesStatisticsDTO getInvoicesStatistics() {
        return statisticsService.getInvoicesStatistics();
    }

    @GetMapping("/persons/statistics")
    public List<PersonsStatisticsDTO> getPersonsStatistics() {
        return statisticsService.getPersonsStatistics();
    }
}

package cz.itnetwork.service;

import cz.itnetwork.dto.InvoicesStatisticsDTO;
import cz.itnetwork.dto.PersonsStatisticsDTO;

import java.util.List;

public interface StatisticsService {

    InvoicesStatisticsDTO getInvoicesStatistics();

    List<PersonsStatisticsDTO> getPersonsStatistics();
}

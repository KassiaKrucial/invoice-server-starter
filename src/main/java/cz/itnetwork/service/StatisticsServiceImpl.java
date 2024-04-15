package cz.itnetwork.service;

import cz.itnetwork.dto.InvoicesStatisticsDTO;
import cz.itnetwork.dto.PersonsStatisticsDTO;
import cz.itnetwork.entity.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public InvoicesStatisticsDTO getInvoicesStatistics() {
        InvoicesStatisticsDTO invoicesStatisticsDTO = invoiceRepository.getInvoicesStatistics();
        if (invoicesStatisticsDTO.getCurrentYearSum() == null) {
            invoicesStatisticsDTO.setCurrentYearSum(0L);
        }
        return invoicesStatisticsDTO;
    }

    @Override
    public List<PersonsStatisticsDTO> getPersonsStatistics() {
        return invoiceRepository.getPersonsStatistics();
    }
}

package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.entity.repository.filter.InvoiceFilter;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InvoiceService {

    InvoiceDTO addInvoice(InvoiceDTO dto);

    List<InvoiceDTO> getAllInvoices(InvoiceFilter invoiceFilter);

    InvoiceDTO getInvoiceById(long id);

    InvoiceDTO updateInvoice(long id, InvoiceDTO invoiceDTO);

    ResponseEntity removeInvoice(long id);

    List<InvoiceDTO> getSales(String identificationNumber);

    List<InvoiceDTO> getPurchases(String identificationNumber);


}

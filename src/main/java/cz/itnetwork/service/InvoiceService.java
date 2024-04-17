package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.entity.repository.filter.InvoiceFilter;
import org.springframework.http.ResponseEntity;
import java.util.List;

/**
 * This interface provides CRUD operations for working with invoices
 *
 * @author Kat
 */
public interface InvoiceService {

    /**
     * Creates an invoice
     * @param dto data as {@link InvoiceDTO} sent by user
     * @return created invoice data an {@link InvoiceDTO}
     */
    InvoiceDTO addInvoice(InvoiceDTO dto);

    /**
     * Finds all invoices
     * @param invoiceFilter Can be filtered by data sent by user as {@link InvoiceFilter}
     * @return List of all/filtered invoices
     */
    List<InvoiceDTO> getAllInvoices(InvoiceFilter invoiceFilter);

    /**
     * Finds invoice by id
     * @param id Sent by user in url parameter
     * @return Found invoice
     */
    InvoiceDTO getInvoiceById(long id);

    /**
     * Updates an existing invoice
     * @param id Sent by user in url parameter
     * @param invoiceDTO Data sent by user for changing the invoice
     * @return The data of the newly updated invoice
     */
    InvoiceDTO updateInvoice(long id, InvoiceDTO invoiceDTO);

    /**
     * Deletes an invoice
     * @param id Sent by user in url parameter
     * @return http response status
     */
    ResponseEntity removeInvoice(long id);

    /**
     * Finds all invoices for products sold by a specific person/company
     * @param identificationNumber A http parameter by which to filter invoices
     * @return List of filtered invoices
     */
    List<InvoiceDTO> getSales(String identificationNumber);

    /**
     * Finds all invoices for products purchased by a specific person/company
     * @param identificationNumber A http parameter by which to filter invoices
     * @return List of filtered invoices
     */
    List<InvoiceDTO> getPurchases(String identificationNumber);
}

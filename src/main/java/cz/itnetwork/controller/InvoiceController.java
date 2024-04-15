package cz.itnetwork.controller;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.entity.repository.filter.InvoiceFilter;
import cz.itnetwork.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * This rest controller catches http requests to url addresses starting on `/api/invoices`
 *
 * @author Kat
 */
@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    /**
     * Represents an autowired bean of {@link InvoiceService} needed
     */
    @Autowired
    private InvoiceService invoiceService;

    /**
     * Sends data for creating an invoice
     * Catches http POST method to url ending with `/`
     * @param invoiceDTO the DTO that the transferred data is mapped to
     * @return the saved data (with id generated by database) as a {@link InvoiceDTO}
     */
    @PostMapping("/")
    public InvoiceDTO addInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.addInvoice(invoiceDTO);
    }

    /**
     * Shows a list of filtered or all invoices saved in the database
     * Catches http method GET to url ending with `/`
     * @param invoiceFilter contains selected parameters that the list can be filtered by {@link InvoiceFilter}
     * @return un/filtered list of {@link InvoiceDTO}
     */
    @GetMapping("/")
    public List<InvoiceDTO> getAllInvoices(InvoiceFilter invoiceFilter) {
        return invoiceService.getAllInvoices(invoiceFilter);
    }

    /**
     * Shows the detail of an invoice
     * Catches http GET method to url ending with `/{id}`
     * @param id the id of the invoice user wants to see
     * @return chosen invoice as {@link InvoiceDTO}
     */
    @GetMapping("/{id}")
    public InvoiceDTO getInvoiceById(@PathVariable Long id) {
        return invoiceService.getInvoiceById(id);
    }

    /**
     * Sends data to update an existing invoice
     * Catches http PUT method to url ending with `/{id}`
     * @param id the id of an invoice user wants to change
     * @param invoiceDTO contains new data for the invoice user wants to update
     * @return the updated invoice data as {@link InvoiceDTO}
     */
    @PutMapping("/{id}")
    public InvoiceDTO updateInvoice(@PathVariable Long id,
                                    @RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.updateInvoice(id, invoiceDTO);
    }

    /**
     * Sends the id of an invoice to be deleted
     * Catches http DELETE method to url ending with `/{id}`
     * @param id the id of an invoice to be deleted
     * @return http status 204 - no content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity removeInvoice(@PathVariable Long id) {
        return invoiceService.removeInvoice(id);
    }

}

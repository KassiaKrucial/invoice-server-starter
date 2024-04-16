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
     * <p>Sends data for creating an invoice</p>
     * <p>Catches http POST method to url ending with `/`</p>
     * @param invoiceDTO the DTO that the transferred data is mapped to
     * @return the saved data (with id generated by database) as a {@link InvoiceDTO}
     */
    @PostMapping("/")
    public InvoiceDTO addInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.addInvoice(invoiceDTO);
    }

    /**
     * <p>Shows a list of filtered or all invoices saved in the database</p>
     * <p>Catches http method GET to url ending with `/`</p>
     * @param invoiceFilter contains selected parameters that the list can be filtered by {@link InvoiceFilter}
     * @return un/filtered list of invoices as {@link InvoiceDTO}
     */
    @GetMapping("/")
    public List<InvoiceDTO> getAllInvoices(InvoiceFilter invoiceFilter) {
        return invoiceService.getAllInvoices(invoiceFilter);
    }

    /**
     * <p>Shows the detail of an invoice</p>
     * <p>Catches http GET method to url ending with `/{id}`</p>
     * @param id the id of the invoice user wants to see
     * @return chosen invoice as {@link InvoiceDTO}
     */
    @GetMapping("/{id}")
    public InvoiceDTO getInvoiceById(@PathVariable Long id) {
        return invoiceService.getInvoiceById(id);
    }

    /**
     * <p>Sends data to update an existing invoice</p>
     * <p>Catches http PUT method to url ending with `/{id}`</p>
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
     * <p>Sends the id of an invoice to be deleted</p>
     * <p>Catches http DELETE method to url ending with `/{id}`</p>
     * @param id the id of an invoice to be deleted
     * @return http status 204 - no content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity removeInvoice(@PathVariable Long id) {
        return invoiceService.removeInvoice(id);
    }

}

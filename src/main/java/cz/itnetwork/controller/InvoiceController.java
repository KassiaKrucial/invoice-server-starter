package cz.itnetwork.controller;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.entity.repository.filter.InvoiceFilter;
import cz.itnetwork.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/invoices")
    public InvoiceDTO addInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.addInvoice(invoiceDTO);
    }

    @GetMapping("/invoices")
    public List<InvoiceDTO> getAllInvoices(InvoiceFilter invoiceFilter) {
        return invoiceService.getAllInvoices(invoiceFilter);
    }

    @GetMapping("/invoices/{id}")
    public InvoiceDTO getInvoiceById(@PathVariable Long id) {
        return invoiceService.getInvoiceById(id);
    }

    @PutMapping("/invoices/{id}")
    public InvoiceDTO updateInvoice(@PathVariable Long id,
                                    @RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.updateInvoice(id, invoiceDTO);
    }

    @DeleteMapping("/invoices/{id}")
    public ResponseEntity removeInvoice(@PathVariable Long id) {
        return invoiceService.removeInvoice(id);
    }

    @GetMapping("/identification/{identificationNumber}/sales")
    public List<InvoiceDTO> getSales(
            @PathVariable String identificationNumber) {
        return invoiceService.getSales(identificationNumber);
    }

    @GetMapping("/identification/{identificationNumber}/purchases")
    public List<InvoiceDTO> getPurchases(
            @PathVariable String identificationNumber) {
        return invoiceService.getPurchases(identificationNumber);
    }
    
}

package cz.itnetwork.controller;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * This rest controller catches http requests to url addresses starting on `/api/identification`
 *
 * @author Kat
 */
@RestController
@RequestMapping("/api/identification")
public class IdentificationNumberController {

    /**
     * Represents an autowired bean of {@link InvoiceService} needed
     */
    @Autowired
    private InvoiceService invoiceService;

    /**
     * <p>Shows a person's/company's sales</p>
     * <p>Catches http GET method to url ending with `/{identificationNumber}/sales`</p>
     * @param identificationNumber the identification number of a person/company saved in the database
     * @return list of {@link InvoiceDTO} corresponding to the sales of a person/company
     */
    @GetMapping("/{identificationNumber}/sales")
    public List<InvoiceDTO> getSales(
            @PathVariable String identificationNumber) {
        return invoiceService.getSales(identificationNumber);
    }

    /**
     * <p>Shows a person's/company's purchases</p>
     * <p>Catches http GET method to url ending with `/{identificationNumber}/purchases`</p>
     * @param identificationNumber the identification number of a person/company saved in the database
     * @return list of {@link InvoiceDTO} corresponding to the purchases of a person/company
     */
    @GetMapping("/{identificationNumber}/purchases")
    public List<InvoiceDTO> getPurchases(
            @PathVariable String identificationNumber) {
        return invoiceService.getPurchases(identificationNumber);
    }
}

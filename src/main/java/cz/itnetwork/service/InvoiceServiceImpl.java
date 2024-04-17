package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.PersonEntity;
import cz.itnetwork.entity.repository.filter.InvoiceFilter;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.PersonRepository;
import cz.itnetwork.entity.repository.specification.InvoiceSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * This is an implementation of {@link InvoiceService}, provides CRUD operations for working with invoices
 *
 * @author Kat
 */
@Service
public class InvoiceServiceImpl implements InvoiceService {

    /**
     * Autowires the necessary bean of {@link InvoiceRepository}
     */
    @Autowired
    private InvoiceRepository invoiceRepository;

    /**
     * Autowires the necessary bean of {@link InvoiceMapper}
     */
    @Autowired
    private InvoiceMapper invoiceMapper;

    /**
     * Autowires the necessary bean of {@link PersonRepository}
     */
    @Autowired
    private PersonRepository personRepository;

    /**
     * Autowires the necessary bean oof {@link PersonService}
     */
    @Autowired
    private PersonService personService;

    /**
     * Creates an invoice and saves it into the database
     * @param invoiceDTO Data of the new invoice sent by user
     * @return The newly created invoice with database generated id
     */
    @Override
    public InvoiceDTO addInvoice(InvoiceDTO invoiceDTO) {
        PersonEntity seller = personService.fetchPersonById(invoiceDTO.getSeller().getId());
        PersonEntity buyer = personService.fetchPersonById(invoiceDTO.getBuyer().getId());

        InvoiceEntity invoiceEntity = invoiceMapper.toEntity(invoiceDTO);
        invoiceEntity.setSeller(seller);
        invoiceEntity.setBuyer(buyer);

        InvoiceEntity savedInvoice = invoiceRepository.save(invoiceEntity);
        return invoiceMapper.toDTO(savedInvoice);
    }

    /**
     * Finds all/filtered invoices in the database
     * @param invoiceFilter Can be filtered by data sent by user as {@link InvoiceFilter}
     * @return List of all/filtered invoices
     */
    @Override
    public List<InvoiceDTO> getAllInvoices(InvoiceFilter invoiceFilter) {
        InvoiceSpecification invoiceSpecification = new InvoiceSpecification(invoiceFilter);
        Page<InvoiceEntity> allInvoiceEntities =
                invoiceRepository.findAll(invoiceSpecification,
                        PageRequest.of(0, invoiceFilter.getLimit()));

        List<InvoiceDTO> allInvoiceDTOs = new ArrayList<>();
        for (InvoiceEntity entity: allInvoiceEntities) {
            allInvoiceDTOs.add(invoiceMapper.toDTO(entity));
        }
        return allInvoiceDTOs;
    }

    /**
     * Finds an invoice in the database by its id
     * @param id Sent by user in url parameter
     * @return Found invoice or EntityNotFoundException
     */
    @Override
    public InvoiceDTO getInvoiceById(long id) {
        return invoiceMapper.toDTO(fetchInvoiceById(id));
    }

    /**
     * Updates an invoice existing in the database
     * @param id Sent by user in url parameter
     * @param invoiceDTO Data sent by user for changing the invoice
     * @return The newly updated invoice or EntityNotFoundException
     */
    @Override
    public InvoiceDTO updateInvoice(long id, InvoiceDTO invoiceDTO) {
        InvoiceEntity enteredEntity = invoiceMapper.toEntity(invoiceDTO);
        InvoiceEntity fetchedEntity = fetchInvoiceById(id);

        fetchedEntity.setInvoiceNumber(enteredEntity.getInvoiceNumber());
        fetchedEntity.setSeller(personService.fetchPersonById(enteredEntity.getSeller().getId()));
        fetchedEntity.setBuyer(personService.fetchPersonById(enteredEntity.getBuyer().getId()));
        fetchedEntity.setIssued(enteredEntity.getIssued());
        fetchedEntity.setDueDate(enteredEntity.getDueDate());
        fetchedEntity.setProduct(enteredEntity.getProduct());
        fetchedEntity.setPrice(enteredEntity.getPrice());
        fetchedEntity.setVat(enteredEntity.getVat());
        fetchedEntity.setNote(enteredEntity.getNote());

        InvoiceEntity savedEntity = invoiceRepository.save(fetchedEntity);
        return invoiceMapper.toDTO(savedEntity);
    }

    /**
     * Deletes an invoice by id from the database
     * @param id Sent by user in url parameter
     * @return http status 204 - no content or EntityNotFoundException
     */
    @Override
    public ResponseEntity removeInvoice(long id) {
        invoiceRepository.delete(fetchInvoiceById(id));
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    /**
     * Finds invoices in the database for products sold by specific person/company
     * @param identificationNumber A http parameter by which to filter invoices
     * Second method parameter must be set as TRUE to get sales
     * @return List of found invoices or EntityNotFoundException
     */
    @Override
    public List<InvoiceDTO> getSales(String identificationNumber) {
        return getSalesOrPurchases(identificationNumber, true);
    }

    /**
     * Finds invoices in the database for products bought by specific person/company
     * @param identificationNumber A http parameter by which to filter invoices
     * Second method parameter must be set as FALSE to get purchases
     * @return List of found invoices or EntityNotFoundException
     */
    @Override
    public List<InvoiceDTO> getPurchases(String identificationNumber) {
        return getSalesOrPurchases(identificationNumber, false);
    }

    /**
     * Finds invoices in the database for products sold or bought by specific person/company
     * @param personIdentificationNumber Identification number of person/company
     * @param isSeller set as true to get sales, set as false to get purchases
     * @return List of found invoices or EntityNotFoundException
     */
    private List<InvoiceDTO> getSalesOrPurchases(String personIdentificationNumber, boolean isSeller) {

        if (!personRepository.existsByIdentificationNumber(personIdentificationNumber)) {
            throw new EntityNotFoundException();
        } else {
            List<InvoiceDTO> invoiceDTOs = new ArrayList<>();
            List<PersonEntity> personEntity = personRepository
                    .findByIdentificationNumber(personIdentificationNumber);
            List<InvoiceEntity> foundInvoices = new ArrayList<>();

            for (PersonEntity foundEntity : personEntity) {
                if (isSeller) {
                    foundInvoices.addAll(invoiceRepository.findBySeller(foundEntity.getId()));
                } else {
                    foundInvoices.addAll(invoiceRepository.findByBuyer(foundEntity.getId()));
                }
            }
            if (foundInvoices.isEmpty()) {
                throw new EntityNotFoundException();
            }

            for (InvoiceEntity invoice : foundInvoices) {
                invoiceDTOs.add(invoiceMapper.toDTO(invoice));
            }

            return invoiceDTOs;
        }
    }

    /**
     * Finds invoice by id in the database or throws EntityNotFoundException
     * @param id Id of an invoice to find
     * @return Found invoice or EntityNotFoundException
     */
    private InvoiceEntity fetchInvoiceById(long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

}


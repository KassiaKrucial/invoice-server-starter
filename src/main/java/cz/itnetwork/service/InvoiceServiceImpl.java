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

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

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

    @Override
    public List<InvoiceDTO> getAllInvoices(InvoiceFilter invoiceFilter) {
        InvoiceSpecification invoiceSpecification = new InvoiceSpecification(invoiceFilter);
        Page<InvoiceEntity> allInvoiceEntities =
                invoiceRepository.findAll(invoiceSpecification, PageRequest.of(0, invoiceFilter.getLimit()));

        List<InvoiceDTO> allInvoiceDTOs = new ArrayList<>();
        for (InvoiceEntity entity: allInvoiceEntities) {
            allInvoiceDTOs.add(invoiceMapper.toDTO(entity));
        }
        return allInvoiceDTOs;
    }

    @Override
    public InvoiceDTO getInvoiceById(long id) {

        return invoiceMapper.toDTO(fetchInvoiceById(id));

    }

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

    @Override
    public ResponseEntity removeInvoice(long id) {
        invoiceRepository.delete(fetchInvoiceById(id));
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Override
    public List<InvoiceDTO> getSales(String identificationNumber) {
        return getSalesOrPurchases(identificationNumber, true);
    }

    @Override
    public List<InvoiceDTO> getPurchases(String identificationNumber) {
        return getSalesOrPurchases(identificationNumber, false);
    }

    private List<InvoiceDTO> getSalesOrPurchases(String personIdentificationNumber, boolean isSeller) {

        if (!personRepository.existsByIdentificationNumber(personIdentificationNumber)) {
            throw new EntityNotFoundException();
        } else {
            List<InvoiceDTO> invoiceDTOs = new ArrayList<>();
            List<PersonEntity> personEntity;
            List<InvoiceEntity> foundInvoices = new ArrayList<>();

            personEntity = personRepository.findByIdentificationNumber(personIdentificationNumber);

            for (int i = 0; i < personEntity.size(); i++) {
                PersonEntity foundEntity = personEntity.get(i);
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

    private InvoiceEntity fetchInvoiceById(long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
    }

}


package cz.itnetwork.dto.mapper;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.PersonEntity;
import java.sql.Date;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Amazon.com Inc.)"
)
@Component
public class InvoiceMapperImpl implements InvoiceMapper {

    @Override
    public InvoiceDTO toDTO(InvoiceEntity entity) {
        if ( entity == null ) {
            return null;
        }

        InvoiceDTO invoiceDTO = new InvoiceDTO();

        if ( entity.getId() != null ) {
            invoiceDTO.setId( entity.getId() );
        }
        invoiceDTO.setInvoiceNumber( entity.getInvoiceNumber() );
        invoiceDTO.setIssued( entity.getIssued() );
        invoiceDTO.setDueDate( entity.getDueDate() );
        invoiceDTO.setProduct( entity.getProduct() );
        invoiceDTO.setPrice( entity.getPrice() );
        invoiceDTO.setVat( entity.getVat() );
        invoiceDTO.setNote( entity.getNote() );
        invoiceDTO.setBuyer( personEntityToPersonDTO( entity.getBuyer() ) );
        invoiceDTO.setSeller( personEntityToPersonDTO( entity.getSeller() ) );

        return invoiceDTO;
    }

    @Override
    public InvoiceEntity toEntity(InvoiceDTO dto) {
        if ( dto == null ) {
            return null;
        }

        InvoiceEntity invoiceEntity = new InvoiceEntity();

        invoiceEntity.setId( dto.getId() );
        invoiceEntity.setInvoiceNumber( dto.getInvoiceNumber() );
        if ( dto.getIssued() != null ) {
            invoiceEntity.setIssued( new Date( dto.getIssued().getTime() ) );
        }
        if ( dto.getDueDate() != null ) {
            invoiceEntity.setDueDate( new Date( dto.getDueDate().getTime() ) );
        }
        invoiceEntity.setProduct( dto.getProduct() );
        invoiceEntity.setPrice( dto.getPrice() );
        invoiceEntity.setVat( dto.getVat() );
        invoiceEntity.setNote( dto.getNote() );
        invoiceEntity.setBuyer( personDTOToPersonEntity( dto.getBuyer() ) );
        invoiceEntity.setSeller( personDTOToPersonEntity( dto.getSeller() ) );

        return invoiceEntity;
    }

    protected PersonDTO personEntityToPersonDTO(PersonEntity personEntity) {
        if ( personEntity == null ) {
            return null;
        }

        PersonDTO personDTO = new PersonDTO();

        personDTO.setId( personEntity.getId() );
        personDTO.setName( personEntity.getName() );
        personDTO.setIdentificationNumber( personEntity.getIdentificationNumber() );
        personDTO.setTaxNumber( personEntity.getTaxNumber() );
        personDTO.setAccountNumber( personEntity.getAccountNumber() );
        personDTO.setBankCode( personEntity.getBankCode() );
        personDTO.setIban( personEntity.getIban() );
        personDTO.setTelephone( personEntity.getTelephone() );
        personDTO.setMail( personEntity.getMail() );
        personDTO.setStreet( personEntity.getStreet() );
        personDTO.setZip( personEntity.getZip() );
        personDTO.setCity( personEntity.getCity() );
        personDTO.setCountry( personEntity.getCountry() );
        personDTO.setNote( personEntity.getNote() );

        return personDTO;
    }

    protected PersonEntity personDTOToPersonEntity(PersonDTO personDTO) {
        if ( personDTO == null ) {
            return null;
        }

        PersonEntity personEntity = new PersonEntity();

        if ( personDTO.getId() != null ) {
            personEntity.setId( personDTO.getId() );
        }
        personEntity.setName( personDTO.getName() );
        personEntity.setIdentificationNumber( personDTO.getIdentificationNumber() );
        personEntity.setTaxNumber( personDTO.getTaxNumber() );
        personEntity.setAccountNumber( personDTO.getAccountNumber() );
        personEntity.setBankCode( personDTO.getBankCode() );
        personEntity.setIban( personDTO.getIban() );
        personEntity.setTelephone( personDTO.getTelephone() );
        personEntity.setMail( personDTO.getMail() );
        personEntity.setStreet( personDTO.getStreet() );
        personEntity.setZip( personDTO.getZip() );
        personEntity.setCity( personDTO.getCity() );
        personEntity.setCountry( personDTO.getCountry() );
        personEntity.setNote( personDTO.getNote() );

        return personEntity;
    }
}

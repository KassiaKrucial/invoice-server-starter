package cz.itnetwork.dto.mapper;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.entity.InvoiceEntity;
import org.mapstruct.Mapper;

/**
 * <p>This class represents a mapper between {@link InvoiceDTO} and {@link InvoiceEntity}</p>
 * <p>Component model is `spring`</p>
 *
 * @author Kat
 */
@Mapper(componentModel = "spring")
public interface InvoiceMapper {

    /**
     * Converts {@link InvoiceEntity} to {@link InvoiceDTO}
     * @param entity the entity representing an invoice
     * @return the DTO representing an invoice
     */
    InvoiceDTO toDTO(InvoiceEntity entity);

    /**
     * Converts {@link InvoiceDTO} to {@link InvoiceEntity}
     * @param dto The DTO representing an invoice
     * @return the entity representing an invoice
     */
    InvoiceEntity toEntity(InvoiceDTO dto);
}

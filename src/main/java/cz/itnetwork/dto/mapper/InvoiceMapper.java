package cz.itnetwork.dto.mapper;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.entity.InvoiceEntity;
import org.mapstruct.Mapper;

/**
 * This class represents a mapper between InvoiceDTO and InvoiceEntity
 * Component model is `spring`
 */
@Mapper(componentModel = "spring")
public interface InvoiceMapper {

    /**
     * Converts InvoiceEntity to InvoiceDTO
     * @param entity the entity representing invoice
     * @return the DTO representing invoice
     */
    InvoiceDTO toDTO(InvoiceEntity entity);

    /**
     * Converts InvoiceDTO to InvoiceEntity
     * @param dto The DTO representing an invoice
     * @return the entity representing an invoice
     */
    InvoiceEntity toEntity(InvoiceDTO dto);
}

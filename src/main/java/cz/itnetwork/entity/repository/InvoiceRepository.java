package cz.itnetwork.entity.repository;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoicesStatisticsDTO;
import cz.itnetwork.dto.PersonsStatisticsDTO;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.PersonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvoiceRepository extends
        JpaRepository<InvoiceEntity, Long>,
        JpaSpecificationExecutor<InvoiceEntity> {

    @Query(value = "SELECT invoice.* FROM invoice invoice INNER JOIN person person ON invoice.seller_id = person.id WHERE invoice.seller_id = :sellerId", nativeQuery = true)
    List<InvoiceEntity> findBySeller(@Param("sellerId") long sellerId);

    @Query(value = "SELECT invoice.* FROM invoice invoice INNER JOIN person person ON invoice.buyer_id = person.id WHERE invoice.buyer_id = :buyerId", nativeQuery = true)
    List<InvoiceEntity> findByBuyer(@Param("buyerId") long buyerId);

    @Query(value = "SELECT NEW cz.itnetwork.dto.InvoicesStatisticsDTO(SUM(letosek.price), SUM(vsechno.price), COUNT(vsechno.id)) FROM invoice vsechno LEFT JOIN invoice letosek ON vsechno.id = letosek.id AND YEAR(letosek.issued) = YEAR(CURRENT_DATE)")
    InvoicesStatisticsDTO getInvoicesStatistics();

    @Query(value = """
        SELECT NEW cz.itnetwork.dto.PersonsStatisticsDTO(
        person.id, person.name, SUM(invoice.price)) 
        FROM person person
        INNER JOIN invoice invoice ON person.id = invoice.seller.id
        GROUP BY person.id
        """)
    List<PersonsStatisticsDTO> getPersonsStatistics();


}
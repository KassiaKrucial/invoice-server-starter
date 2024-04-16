package cz.itnetwork.entity.repository;

import cz.itnetwork.dto.InvoicesStatisticsDTO;
import cz.itnetwork.dto.PersonsStatisticsDTO;
import cz.itnetwork.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * This interface CRUD operations and written sql queries for invoices
 *
 * @author Kat
 */
public interface InvoiceRepository extends
        JpaRepository<InvoiceEntity, Long>,
        JpaSpecificationExecutor<InvoiceEntity> {

    /**
     * <p>Finds invoices by seller</p>
     * <p>Selected by user</p>
     * @param sellerId The id of an invoice
     * @return List of filtered invoices
     */
    @Query(value = "SELECT invoice.* FROM invoice invoice INNER JOIN person person ON invoice.seller_id = person.id WHERE invoice.seller_id = :sellerId", nativeQuery = true)
    List<InvoiceEntity> findBySeller(@Param("sellerId") long sellerId);

    /**
     * <p>Finds invoices by buyer</p>
     * <p>Selected by user</p>
     * @param buyerId
     * @return
     */
    @Query(value = "SELECT invoice.* FROM invoice invoice INNER JOIN person person ON invoice.buyer_id = person.id WHERE invoice.buyer_id = :buyerId", nativeQuery = true)
    List<InvoiceEntity> findByBuyer(@Param("buyerId") long buyerId);

    /**
     * Sums revenues for current year, every year and counts total number of invoices in the database
     * @return According sums of revenues and count of invoices as {@link InvoicesStatisticsDTO}
     */
    @Query(value = """
        SELECT NEW cz.itnetwork.dto.InvoicesStatisticsDTO(
            SUM(currentYearInvoices.price), 
            SUM(allYearsInvoices.price), 
            COUNT(allYearsInvoices.id)) 
        FROM invoice allYearsInvoices 
        LEFT JOIN invoice currentYearInvoices 
        ON allYearsInvoices.id = currentYearInvoices.id 
        AND YEAR(currentYearInvoices.issued) = YEAR(CURRENT_DATE)
        """)
    InvoicesStatisticsDTO getInvoicesStatistics();

    /**
     * Sums revenues for each person/company
     * @return List of according sums of revenues, person/company id and name as {@link PersonsStatisticsDTO}
     */
    @Query(value = """
        SELECT NEW cz.itnetwork.dto.PersonsStatisticsDTO(
            person.id, person.name, SUM(invoice.price)) 
        FROM person person
        INNER JOIN invoice invoice ON person.id = invoice.seller.id
        GROUP BY person.id
        """)
    List<PersonsStatisticsDTO> getPersonsStatistics();
}
/*  _____ _______         _                      _
 * |_   _|__   __|       | |                    | |
 *   | |    | |_ __   ___| |___      _____  _ __| | __  ___ ____
 *   | |    | | '_ \ / _ \ __\ \ /\ / / _ \| '__| |/ / / __|_  /
 *  _| |_   | | | | |  __/ |_ \ V  V / (_) | |  |   < | (__ / /
 * |_____|  |_|_| |_|\___|\__| \_/\_/ \___/|_|  |_|\_(_)___/___|
 *                                _
 *              ___ ___ ___ _____|_|_ _ _____
 *             | . |  _| -_|     | | | |     |  LICENCE
 *             |  _|_| |___|_|_|_|_|___|_|_|_|
 *             |_|
 *
 *   PROGRAMOVÁNÍ  <>  DESIGN  <>  PRÁCE/PODNIKÁNÍ  <>  HW A SW
 *
 * Tento zdrojový kód je součástí výukových seriálů na
 * IT sociální síti WWW.ITNETWORK.CZ
 *
 * Kód spadá pod licenci prémiového obsahu a vznikl díky podpoře
 * našich členů. Je určen pouze pro osobní užití a nesmí být šířen.
 * Více informací na http://www.itnetwork.cz/licence
 */
package cz.itnetwork.entity.repository;

import cz.itnetwork.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * This interface contains CRUD operations for invoices
 */
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    /**
     * Finds people/companies by parameter hidden
     * @param hidden If true, a person/company is viewed as deleted
     * @return List of filtered people/companies
     */
    List<PersonEntity> findByHidden(boolean hidden);

    /**
     * Finds person/company by identification number {@link PersonEntity}
     * @param identificationNumber Identification number of person/company
     * @return List of filtered people/companies
     */
    List<PersonEntity> findByIdentificationNumber(String identificationNumber);

    /**
     * Finds out, if a person/company exists in the database
     * @param IdentificationNumber Indentification number of person/company
     * @return The existence of person/company
     */
    Boolean existsByIdentificationNumber(String IdentificationNumber);

}
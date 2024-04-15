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
package cz.itnetwork.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.itnetwork.constant.Countries;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents the DTO of a person (or company), that can be later designated as buyer or seller for an invoice
 * Getters, setters, constructors generated with lombok
 *
 * @author Itnetwork.cz
 * @author The author of javadoc for this class is Kat
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    /**
     * Represents the id of a person/company
     * Generated by the database in {@link cz.itnetwork.entity.PersonEntity}
     * Connects to json property sent from client via {@code @JsonProperty("_id)}
     */
    @JsonProperty("_id")
    private Long id;

    /**
     * Represents the name of the person/company
     * Entered by user
     */
    private String name;

    /**
     * Represents the identification number of a person/company (IČO in Czechia)
     * Entered by user
     */
    private String identificationNumber;

    /**
     * Represents the tax number of a person/company (DIČ in Czechia)
     * Entered by user
     */
    private String taxNumber;

    /**
     * Represents the account number of a person/company
     * Entered by user
     */
    private String accountNumber;

    /**
     * Represents the code of a bank
     * Entered by user
     */
    private String bankCode;

    /**
     * Represents the International Bank Account Number of a person/company
     * Entered by user
     */
    private String iban;

    /**
     * Represents the telephone number of a person/company
     * Entered by user
     */
    private String telephone;

    /**
     * Represents the e-mail of a person/company
     * Entered by user
     */
    private String mail;

    /**
     * Represents the street of the headquarters of a person/company
     * Entered by user
     */
    private String street;

    /**
     * Represents the zip code of the headquarters of a person/company
     * Entered by user
     */
    private String zip;

    /**
     * Represents the city, where the headquarters of a person/company is located
     * Entered by user
     */
    private String city;

    /**
     * Represents the country, where the headquarters of a person/company is located
     * Must be chosen from enum {@link Countries}
     * Entered by user
     */
    private Countries country;

    /**
     * Represents a note to a person/company
     * It is mandatory
     */
    private String note;
}

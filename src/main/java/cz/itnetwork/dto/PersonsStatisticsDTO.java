package cz.itnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p>This class represents the DTO for statistics of people/companies saved in the database (includes hidden people/companies)</p>
 * <p>Getters, setters, constructors generated with lombok</p>
 *
 * @author Kat
 */
@Data
@AllArgsConstructor
public class PersonsStatisticsDTO {

    /**
     * Represents a person's/company's database id
     */
    private Long personId;

    /**
     * Represents a person's/company's name
     */
    private String personName;

    /**
     * Represents the sum of revenue of a person/company
     */
    private Long revenue;
}

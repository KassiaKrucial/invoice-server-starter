package cz.itnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonsStatisticsDTO {
    private Long personId;
    private String personName;
    private Long revenue;
}

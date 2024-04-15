package cz.itnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvoicesStatisticsDTO {
    private Long currentYearSum;
    private Long allTimeSum;
    private Long invoicesCount;
}

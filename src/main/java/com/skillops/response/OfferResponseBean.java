package com.skillops.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfferResponseBean {
    private Long id;
    private Long applicationId;
    private String designation;
    private BigDecimal offeredCtc;
    private LocalDate joiningDate;
    private LocalDate offerExpiry;
    private String offerStatus;
}

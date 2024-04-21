package com.liebsterkamerad.coding_assignment;

import com.opencsv.bean.CsvBindByPosition;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Report {

    @CsvBindByPosition(position = 0)
    private String source;

    @CsvBindByPosition(position = 1)
    private String codeListCode;

    @Id
    @CsvBindByPosition(position = 2)
    private String code;

    @CsvBindByPosition(position = 3)
    private String displayValue;

    @CsvBindByPosition(position = 4)
    private String longDescription;

    @CsvBindByPosition(position = 5)
    private String fromDate;

    @CsvBindByPosition(position = 6)
    private String toDate;

    @CsvBindByPosition(position = 7)
    private String sortingPriority;
}

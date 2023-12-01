package ru.nabokovsg.templates.dto.subsection.subsectionData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class SubsectionDataDto {

    private String subsectionDataType;
    private Boolean methodologicalDocument;
    private Boolean regulatoryDocument;
    private Long objectTypeId;
    private Long divisionId;
    private String divisionType;
    private String divisionName;
}
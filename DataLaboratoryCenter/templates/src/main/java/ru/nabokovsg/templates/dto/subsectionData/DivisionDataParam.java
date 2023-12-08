package ru.nabokovsg.templates.dto.subsectionData;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Указать контакты структурного подразделения")
public class DivisionDataParam {

    private String divisionName;
    private Boolean address;
    private Boolean license;
}
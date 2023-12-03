package ru.nabokovsg.templates.dto.subsectionData;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные нового подраздела")
public class NewDivisionDataDto {

    @Schema(description = "Индентификатор структурного подразделения организации")
    private Long divisionId;
    @Schema(description = "Пользовательское название структурного подразделения организации")
    private String divisionName;
    @Schema(description = "Указать адресс структурного подразделения")
    private Boolean divisionAddress;
    @Schema(description = "Указать контакты структурного подразделения")
    private Boolean divisionContact;
    @Schema(description = "Указать лицензию/аттестацию структурного подразделения")
    private Boolean divisionLicense;
}
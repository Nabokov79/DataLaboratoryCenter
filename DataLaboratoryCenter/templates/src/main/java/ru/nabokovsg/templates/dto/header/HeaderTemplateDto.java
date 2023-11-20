package ru.nabokovsg.templates.dto.header;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные заголовка титульной страницы")
public class HeaderTemplateDto {

    @Schema(description = "Индентификатор")
    private long id;
    @Schema(description = "Организация")
    private String organization;
    @Schema(description = "Лицензия организации")
    private String organizationLicense;
    @Schema(description = "Лицензия организации")
    private String organizationContacts;
    @Schema(description = "Филиал организации")
    private String branch;
    @Schema(description = "Реквизиты филиала")
    private String branchContacts;
    @Schema(description = "Лицензия/аттестация филиала")
    private String licenseBranch;
    @Schema(description = "Подразделение филиала организации")
    private String department;
    @Schema(description = "Реквизиты подразделения")
    private String departmentContacts;
    @Schema(description = "Лицензия/аттестация подразделения")
    private String departmentLicense;
}
package ru.nabokovsg.data.dto.organization;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.data.dto.contact.NewContactDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Данные новой организации")
public class NewOrganizationDto {

    @Schema(description = "Полное наименование организации")
    @NotBlank(message = "organization should not be blank")
    private String organization;
    @Schema(description = "Краткое наименование организации")
    @NotBlank(message = "short name organization should not be blank")
    private String shortNameOrganization;
    @Schema(description = "Индентификатор адреса")
    @NotNull(message = "address id should not be blank")
    @Positive(message = "address id can only be positive")
    private Long addressId;
    @Schema(description = "Реквизиты организации")
    private NewContactDto contact;
}
package ru.nabokovsg.templates.client.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Данные реквизитов")
public class ContactDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Адрес организации")
    private AddressDto address;
    @Schema(description = "Почтовый индекс")
    private Integer index;
    @Schema(description = "Номер телефона")
    private String phone;
    @Schema(description = "Факс")
    private String fax;
    @Schema(description = "электронная почта")
    private String email;
}
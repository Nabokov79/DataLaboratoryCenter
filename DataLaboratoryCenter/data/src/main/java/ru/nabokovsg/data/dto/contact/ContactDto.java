package ru.nabokovsg.data.dto.contact;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.data.dto.address.AddressDto;

@Setter
@Getter
@AllArgsConstructor
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
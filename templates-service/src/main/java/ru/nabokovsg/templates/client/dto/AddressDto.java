package ru.nabokovsg.templates.client.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Данные адреса")
public class AddressDto {

    @Schema(description = "Индентификатор города")
    private Long id;
    @Schema(description = "Почтовый индекс")
    private Integer index;
    @Schema(description = "Населенный пункт")
    private String city;
    @Schema(description = "Название улицы")
    private String street;
    @Schema(description = "Номер дома")
    private Integer houseNumber;
    @Schema(description = "Номер корпуса дома")
    private Integer buildingNumber;
    @Schema(description = "Литера дома")
    private String letter;
}
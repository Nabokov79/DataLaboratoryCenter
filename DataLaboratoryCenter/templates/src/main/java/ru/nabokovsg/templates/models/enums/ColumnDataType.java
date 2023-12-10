package ru.nabokovsg.templates.models.enums;

import java.util.Optional;

public enum ColumnDataType {

    DATE,
    SURVEYS_TYPE,
    DESCRIPTION,
    DOCUMENT_NUMBER,
    ORGANIZATION,
    STRING_NUMBER,
    ELEMENT,
    SUB_ELEMENT,
    DEFECT,
    REPAIR_PLACE,
    DESIGN_THICKNESS,
    MEASURING_THICKNESS,
    MAX_CORROSION,
    RESIDUAL_THICKNESS,
    ACCEPTABLE_THICKNESS,
    HEIGHT,
    PRECIPITATION,
    AMOUNT_DEVIATION,
    DIFFERENCE_NEIGHBORING_POINT,
    DIFFERENCE_OPPOSITE_POINT;

    public static Optional<ColumnDataType> from(String columnDataType) {
        for (ColumnDataType type : values()) {
            if (type.name().equalsIgnoreCase(columnDataType)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}
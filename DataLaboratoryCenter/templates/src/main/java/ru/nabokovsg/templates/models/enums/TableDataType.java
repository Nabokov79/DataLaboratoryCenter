package ru.nabokovsg.templates.models.enums;

import java.util.Optional;

public enum TableDataType {

    VISUAL_MEASURING_CONTROL,
    ULTRASONIC_CONTROL,
    ULTRASONIC_MEASURING_THICKNESS,
    HARDNESS_MEASURING,
    MEASUREMENT_POINTS,
    MEASUREMENT_REFERENCE_POINTS,
    REPAIRS,
    SURVEYS,
    SUMMARY_DATA;

    public static Optional<TableDataType> from(String tableDataType) {
        for (TableDataType type : values()) {
            if (type.name().equalsIgnoreCase(tableDataType)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}
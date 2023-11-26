package ru.nabokovsg.templates.models.enums;

import java.util.Optional;

public enum SubsectionDataType {

    NULL,
    ALL_DOCUMENT,
    REGULATORY_DOCUMENT,
    METHODOLOGICAL_DOCUMENT,
    LABORATORY_DATA;

    public static Optional<SubsectionDataType> from(String subsectionDataType) {
        for (SubsectionDataType type : values()) {
            if (type.name().equalsIgnoreCase(subsectionDataType)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}

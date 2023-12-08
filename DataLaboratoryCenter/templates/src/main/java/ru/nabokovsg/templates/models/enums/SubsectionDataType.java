package ru.nabokovsg.templates.models.enums;

import java.util.Optional;

public enum SubsectionDataType {

    USER_TEXT,
    ALL_DOCUMENT,
    REGULATORY_DOCUMENT,
    METHODOLOGICAL_DOCUMENT,
    ORGANIZATION,
    BRANCH,
    DEPARTMENT,
    DOCUMENTATION,
    LABORATORY_DATA,
    VISUAL_MEASURING_CONTROL,
    ULTRASONIC_MEASURING_CONTROL,
    GEODESIC_MEASURING,
    ULTRASONIC_CONTROL,
    HARDNESS_MEASURING,
    REPAIRS,
    SURVEYS;

    public static Optional<SubsectionDataType> from(String subsectionDataType) {
        for (SubsectionDataType type : values()) {
            if (type.name().equalsIgnoreCase(subsectionDataType)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}

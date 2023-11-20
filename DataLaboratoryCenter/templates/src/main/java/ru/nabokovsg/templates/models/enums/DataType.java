package ru.nabokovsg.templates.models.enums;

import java.util.Optional;

public enum DataType {

    SIGNATURE,
    LICENSE,
    CONTACT,
    ORGANIZATION,
    BRANCH,
    DEPARTMENT,
    DOCUMENTATION;

    public static Optional<DataType> from(String dataType) {
        for (DataType type : values()) {
            if (type.name().equalsIgnoreCase(dataType)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}
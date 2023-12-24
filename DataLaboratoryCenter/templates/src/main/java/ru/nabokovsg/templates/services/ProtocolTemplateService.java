package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.models.TableTemplate;

public interface ProtocolTemplateService {

    boolean existsById(Long id);

    void saveWithTable(Long protocolId, TableTemplate table);
}
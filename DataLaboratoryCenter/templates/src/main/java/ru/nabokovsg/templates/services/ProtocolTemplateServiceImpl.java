package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.exceptions.NotFoundException;
import ru.nabokovsg.templates.models.ProtocolTemplate;
import ru.nabokovsg.templates.models.TableTemplate;
import ru.nabokovsg.templates.repository.ProtocolTemplateRepository;

@Service
@RequiredArgsConstructor
public class ProtocolTemplateServiceImpl implements ProtocolTemplateService {

    private final ProtocolTemplateRepository repository;

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public void saveWithTable(Long protocolId, TableTemplate table) {
        ProtocolTemplate protocol = getById(protocolId);
        protocol.getTables().add(table);
        repository.save(protocol);
    }

    public ProtocolTemplate getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("SubsectionTemplate with id=%s not found", id)));
    }
}
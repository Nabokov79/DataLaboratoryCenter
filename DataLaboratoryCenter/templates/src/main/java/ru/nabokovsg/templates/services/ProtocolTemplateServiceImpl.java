package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.client.TemplateClient;
import ru.nabokovsg.templates.dto.protocol.NewProtocolTemplateDto;
import ru.nabokovsg.templates.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.templates.dto.protocol.ShortProtocolTemplateDto;
import ru.nabokovsg.templates.dto.protocol.UpdateProtocolTemplateDto;
import ru.nabokovsg.templates.exceptions.NotFoundException;
import ru.nabokovsg.templates.mappers.ProtocolTemplateMapper;
import ru.nabokovsg.templates.models.*;
import ru.nabokovsg.templates.repository.ProtocolTemplateRepository;
import ru.nabokovsg.templates.services.builders.StringBuilderService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProtocolTemplateServiceImpl implements ProtocolTemplateService {

    private final ProtocolTemplateRepository repository;
    private final ProtocolTemplateMapper mapper;
    private final TemplateClient client;
    private final HeaderTemplateService headerService;
    private final StringBuilderService builderService;
    private final ConclusionTemplateService conclusionService;
    private final AppendicesTemplateService appendicesService;
    private final RecommendationTemplateService recommendationService;

    @Override
    public ShortProtocolTemplateDto save(NewProtocolTemplateDto protocolDto) {
        ProtocolTemplate template = repository.findByReportingDocumentIdAndObjectTypeId(protocolDto.getReportingDocumentId()
                , protocolDto.getObjectTypeId());
        if (template == null) {
            template = mapper.mapToNewProtocolTemplate(protocolDto
                    , headerService.save(protocolDto.getHeader()));
            template = mapper.mapToProtocolTemplate(template
                                , builderService.convertObjectsType(client.getObjectsType(protocolDto.getObjectTypeId()))
                                , client.getReportingDocument(protocolDto.getReportingDocumentId()));
        }
        return mapper.mapToShortProtocolTemplateDto(repository.save(template));
    }

    @Override
    public ShortProtocolTemplateDto update(UpdateProtocolTemplateDto protocolDto) {
        if (repository.existsById(protocolDto.getId())) {
            ProtocolTemplate template = mapper.mapToUpdateProtocolTemplate(protocolDto
                                                                , headerService.save(protocolDto.getHeader()));
            template = mapper.mapToProtocolTemplate(template
                    , builderService.convertObjectsType(client.getObjectsType(protocolDto.getObjectTypeId()))
                    , client.getReportingDocument(protocolDto.getReportingDocumentId()));
            return mapper.mapToShortProtocolTemplateDto(repository.save(template));
        }
        throw new NotFoundException(
                String.format("Protocol template by id=%s not found for update", protocolDto.getId())
        );
    }

    @Override
    public List<ShortProtocolTemplateDto> getAll() {
        List<ProtocolTemplate> templates = repository.findAll();
        if (templates.isEmpty()) {
            throw new NotFoundException(String.format("<Protocols templates %s not found", templates));
        }
        return templates.stream().map(mapper::mapToShortProtocolTemplateDto).toList();
    }

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

    @Override
    public void saveWithSubsection(Long protocolId, SubsectionTemplate subsection) {
        ProtocolTemplate protocol = getById(protocolId);
        protocol.getSubsections().add(subsection);
        repository.save(protocol);
    }

    @Override
    public ProtocolTemplateDto addConclusion(Long id) {
        ProtocolTemplate protocol = getById(id);
        protocol.setConclusions(conclusionService.getByReportingDocumentId(protocol.getReportingDocumentId()));
        return mapper.mapToProtocolTemplateDto(repository.save(protocol));
    }

    @Override
    public ProtocolTemplateDto addAppendices(Long id) {
        ProtocolTemplate protocol = getById(id);
        protocol.setAppendices(appendicesService.getByObjectTypeId(protocol.getObjectTypeId()));
        return mapper.mapToProtocolTemplateDto(repository.save(protocol));
    }

    @Override
    public ProtocolTemplateDto addRecommendation(Long id) {
        ProtocolTemplate protocol = getById(id);
        protocol.setRecommendations(recommendationService.getByObjectTypeId(protocol.getObjectTypeId()));
        return mapper.mapToProtocolTemplateDto(repository.save(protocol));
    }

    public ProtocolTemplate getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("SubsectionTemplate with id=%s not found", id)));
    }
}
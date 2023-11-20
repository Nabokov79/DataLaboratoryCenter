package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.nabokovsg.templates.client.dto.ReportingDocumentDto;
import ru.nabokovsg.templates.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.templates.dto.title.TitleDto;
import ru.nabokovsg.templates.mappers.ProtocolTemplateMapper;
import ru.nabokovsg.templates.models.HeaderTemplate;
import ru.nabokovsg.templates.models.ProtocolTemplate;
import ru.nabokovsg.templates.models.Title;
import ru.nabokovsg.templates.repository.ProtocolTemplateRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProtocolTemplateServiceImpl implements ProtocolTemplateService {

    private final ProtocolTemplateRepository repository;
    private final ProtocolTemplateMapper mapper;

    @Override
    public void create(HeaderTemplate header, ReportingDocumentDto reportingDocument, Long objectTypeId) {
        repository.save(mapper.mapToNewProtocolTemplate(header, reportingDocument, objectTypeId));
    }

    @Override
    public ProtocolTemplateDto get(Long objectTypeId, Long reportingDocumentId) {
        ProtocolTemplate protocol = repository.findByObjectTypeIdAndReportingDocumentId(objectTypeId, reportingDocumentId);
        if (protocol == null) {
            throw new NotFoundException(
                    String.format("Protocol template by objectsTypeId=%s, reportingDocumentId=%s not found"
                            , objectTypeId
                            , reportingDocumentId));
        }
        return mapper.mapToProtocolTemplateDto(protocol);
    }

    @Override
    public List<TitleDto> getAll() {
        return mapper.mapToTitleDto(repository.findAllTitle());
    }
}
package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.dto.conclusion.ConclusionTemplateDto;
import ru.nabokovsg.templates.dto.conclusion.NewConclusionTemplateDto;
import ru.nabokovsg.templates.dto.conclusion.UpdateConclusionTemplateDto;
import ru.nabokovsg.templates.exceptions.NotFoundException;
import ru.nabokovsg.templates.mappers.ConclusionTemplateMapper;
import ru.nabokovsg.templates.models.ConclusionTemplate;
import ru.nabokovsg.templates.repository.ConclusionTemplateRepository;

@Service
@RequiredArgsConstructor
public class ConclusionTemplateServiceImpl implements ConclusionTemplateService {

    private final ConclusionTemplateRepository repository;
    private final ConclusionTemplateMapper mapper;

    @Override
    public ConclusionTemplateDto save(NewConclusionTemplateDto conclusionDto) {
        ConclusionTemplate conclusion = repository.findByObjectTypeIdAndReportingDocumentId(
                                                                                conclusionDto.getObjectTypeId()
                                                                              , conclusionDto.getReportingDocumentId());
        if (conclusion == null) {
            conclusion = repository.save(mapper.mapToNewConclusionTemplate(conclusionDto));
        }
        return mapper.mapToConclusionTemplateDto(conclusion);
    }

    @Override
    public ConclusionTemplateDto update(UpdateConclusionTemplateDto conclusionDto) {
        return mapper.mapToConclusionTemplateDto(
                repository.save(mapper.mapToUpdateConclusionTemplate(get(conclusionDto.getId()), conclusionDto))
        );
    }

    @Override
    public ConclusionTemplateDto getBy(Long objectTypeId, Long reportingDocumentId) {
        ConclusionTemplate conclusion = repository.findByObjectTypeIdAndReportingDocumentId(objectTypeId
                                                                                          , reportingDocumentId);
        if (conclusion == null) {
            throw new NotFoundException(
                    String.format("Conclusion template by objectTypeId=%s, reportingDocumentId=%s not found",
                                                                                      objectTypeId, reportingDocumentId)
            );
        }
        return mapper.mapToConclusionTemplateDto(conclusion);
    }

    private ConclusionTemplate get(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Conclusion template with id=%s not found", id))
        );
    }
}
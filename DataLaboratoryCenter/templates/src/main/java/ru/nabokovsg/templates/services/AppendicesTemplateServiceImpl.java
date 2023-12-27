package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.dto.appendices.AppendicesTemplateDto;
import ru.nabokovsg.templates.dto.appendices.NewAppendicesTemplateDto;
import ru.nabokovsg.templates.dto.appendices.UpdateAppendicesTemplateDto;
import ru.nabokovsg.templates.exceptions.NotFoundException;
import ru.nabokovsg.templates.mappers.AppendicesTemplateMapper;
import ru.nabokovsg.templates.models.AppendicesTemplate;
import ru.nabokovsg.templates.repository.AppendicesTemplateRepository;

import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AppendicesTemplateServiceImpl implements AppendicesTemplateService {

    private final AppendicesTemplateRepository repository;
    private final AppendicesTemplateMapper mapper;

    @Override
    public AppendicesTemplateDto save(NewAppendicesTemplateDto appendicesDto) {
        AppendicesTemplate appendices = repository.findByAppendicesName(appendicesDto.getAppendicesName());
        if (appendices == null || !Objects.equals(appendices.getObjectTypeId(), appendicesDto.getObjectTypeId())) {
            appendices = repository.save(mapper.mapToNewAppendicesTemplate(appendicesDto));
        }
        return mapper.mapToAppendicesTemplateDto(appendices);
    }

    @Override
    public AppendicesTemplateDto update(UpdateAppendicesTemplateDto appendicesDto) {
        if (repository.existsById(appendicesDto.getId())) {
            return mapper.mapToAppendicesTemplateDto(
                    repository.save(mapper.mapToUpdateAppendicesTemplate(appendicesDto))
            );
        }
        throw new NotFoundException(
                String.format("Appendices template with id=%s not found for update", appendicesDto.getId())
        );
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw  new NotFoundException(String.format("Appendices template with id=%s not found for delete", id));
    }

    @Override
    public Set<AppendicesTemplate> getByObjectTypeId(Long objectTypeId) {
        return repository.findByObjectTypeId(objectTypeId);
    }
}
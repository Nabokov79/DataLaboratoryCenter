package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.dto.passportData.NewPassportDataTemplateDto;
import ru.nabokovsg.templates.dto.passportData.PassportDataTemplateDto;
import ru.nabokovsg.templates.dto.passportData.UpdatePassportDataTemplateDto;
import ru.nabokovsg.templates.exceptions.NotFoundException;
import ru.nabokovsg.templates.mappers.PassportDataTemplateMapper;
import ru.nabokovsg.templates.models.PassportDataTemplate;
import ru.nabokovsg.templates.repository.PassportDataTemplateRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class PassportDataTemplateServiceImpl implements PassportDataTemplateService {

    private final PassportDataTemplateRepository repository;
    private final PassportDataTemplateMapper mapper;

    @Override
    public List<PassportDataTemplateDto> save(List<NewPassportDataTemplateDto> passportDataDto) {
        Map<String, PassportDataTemplate> passportDataTemplates = repository.findAllByDataName(
                                                    passportDataDto.stream()
                                                                   .map(NewPassportDataTemplateDto::getSequentialNumber)
                                                                   .toList()
                                                 ,  passportDataDto.stream()
                                                                   .map(NewPassportDataTemplateDto::getDataName)
                                                                   .toList())
                                                  .stream()
                                                  .collect(Collectors.toMap(PassportDataTemplate::getDataName, p -> p));
        passportDataDto = passportDataDto.stream()
                                         .filter(d -> !passportDataTemplates.containsKey(d.getDataName()))
                                         .toList();
        if (passportDataDto.isEmpty()) {
            return passportDataTemplates.values().stream().map(mapper::mapToPassportDataTemplateDto).toList();
        }
        return Stream.of(passportDataTemplates.values()
                       , repository.saveAll(passportDataDto.stream()
                                                           .map(mapper::mapToNewPassportDataTemplate)
                                                           .toList()))
                .flatMap(Collection::stream)
                .map(mapper::mapToPassportDataTemplateDto)
                .toList();
    }

    @Override
    public List<PassportDataTemplateDto> update(List<UpdatePassportDataTemplateDto> passportDataDto) {
        validateIds(passportDataDto.stream().map(UpdatePassportDataTemplateDto::getId).toList());
        return repository.saveAll(mapper.mapToUpdatePassportDataTemplates(passportDataDto))
                                                    .stream()
                                                    .map(mapper::mapToPassportDataTemplateDto)
                                                    .toList();
    }

    @Override
    public List<PassportDataTemplateDto> getAll() {
        List<PassportDataTemplate> passportDataTemplates = repository.findAll();
        if (passportDataTemplates.isEmpty()) {
            return new ArrayList<>();
        }
        return passportDataTemplates.stream().map(mapper::mapToPassportDataTemplateDto).toList();
    }

    private void validateIds(List<Long> ids) {
        Map<Long, PassportDataTemplate> passportDataTemplates = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(PassportDataTemplate::getId, m -> m));
        if (passportDataTemplates.size() != ids.size() || passportDataTemplates.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(passportDataTemplates.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("Passport data templates with ids= %s not found", ids));
        }
    }
}

package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.dto.subsectionDada.NewSubsectionDataTemplateDto;
import ru.nabokovsg.templates.dto.subsectionDada.UpdateSubsectionDataTemplateDto;
import ru.nabokovsg.templates.models.SubsectionDataTemplate;
import ru.nabokovsg.templates.repository.SubsectionDataTemplateRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubsectionDataTemplateServiceImpl implements SubsectionDataTemplateService {

    private final SubsectionDataTemplateRepository repository;

    @Override
    public List<SubsectionDataTemplate> save(List<NewSubsectionDataTemplateDto> subsectionDataDto) {
        return null;
    }

    @Override
    public List<SubsectionDataTemplate> update(List<UpdateSubsectionDataTemplateDto> subsectionDataDto) {
        return null;
    }
}
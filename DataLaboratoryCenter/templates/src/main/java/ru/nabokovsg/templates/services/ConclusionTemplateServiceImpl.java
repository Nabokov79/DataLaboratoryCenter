package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.mappers.ConclusionTemplateMapper;
import ru.nabokovsg.templates.repository.ConclusionTemplateRepository;

@Service
@RequiredArgsConstructor
public class ConclusionTemplateServiceImpl implements ConclusionTemplateService {

    private final ConclusionTemplateRepository repository;
    private final ConclusionTemplateMapper mapper;
}
package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.protocol.NewProtocolTemplateDto;
import ru.nabokovsg.templates.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.templates.dto.protocol.ShortProtocolTemplateDto;
import ru.nabokovsg.templates.dto.protocol.UpdateProtocolTemplateDto;
import ru.nabokovsg.templates.models.*;

import java.util.List;

public interface ProtocolTemplateService {

    ShortProtocolTemplateDto save(NewProtocolTemplateDto protocolDto);

    ShortProtocolTemplateDto update(UpdateProtocolTemplateDto protocolDto);

    List<ShortProtocolTemplateDto> getAll();

    boolean existsById(Long id);

    void saveWithTable(Long protocolId, TableTemplate table);

    void saveWithSubsection(Long protocolId,SubsectionTemplate subsection);

    ProtocolTemplateDto addConclusion(Long id);

    ProtocolTemplateDto addAppendices(Long id);

    ProtocolTemplateDto addRecommendation(Long id);
}
package ru.nabokovsg.data.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.data.dto.ObjectsIds;
import ru.nabokovsg.data.dto.certificate.CertificateDto;
import ru.nabokovsg.data.dto.certificate.NewCertificateDto;
import ru.nabokovsg.data.dto.certificate.UpdateCertificateDto;
import ru.nabokovsg.data.models.Certificate;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CertificateMapper {

    List<CertificateDto> mapToCertificatesDto(List<Certificate> certificates);

    Certificate mapToNewCertificate(NewCertificateDto newCertificateDto);

    Certificate mapToUpdateCertificate(UpdateCertificateDto updateCertificateDto);

    ObjectsIds mapFromNewCertificateDto(NewCertificateDto certificateDto);

    ObjectsIds mapFromUpdateCertificateDto(UpdateCertificateDto certificateDto);
}
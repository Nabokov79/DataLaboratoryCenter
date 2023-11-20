package ru.nabokovsg.data.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.data.models.enums.DocumentType;
import ru.nabokovsg.data.models.enums.ProtocolType;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reporting_documents")
public class ReportingDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "document")
    private String document;
    @Column(name = "document_title")
    private String documentTitle;
    @Column(name = "document_type")
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;
    @Column(name = "protocol_type")
    @Enumerated(EnumType.STRING)
    private ProtocolType protocolType;
}
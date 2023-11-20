package ru.nabokovsg.templates.models;

import lombok.*;
import ru.nabokovsg.templates.client.enums.ProtocolType;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "protocol_templates")
public class ProtocolTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "object_type_id")
    private Long objectTypeId;
    @Column(name = "reporting_document_id")
    private Long reportingDocumentId;
    @Column(name = "document_type")
    @Enumerated(EnumType.STRING)
    private ProtocolType protocolType;
    @Column(name = "document_name")
    private String documentName;
    @Column(name = "document_title")
    private String documentTitle;
    @OneToOne
    @JoinColumn(name = "header_id", referencedColumnName = "id")
    private HeaderTemplate header;
}
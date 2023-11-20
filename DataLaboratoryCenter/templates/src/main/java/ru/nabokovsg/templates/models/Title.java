package ru.nabokovsg.templates.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "protocol_templates")
public class Title {

    @Id
    private long id;
    @Column(name = "document_name")
    private String documentName;
    @Column(name = "document_title")
    private String documentTitle;
}
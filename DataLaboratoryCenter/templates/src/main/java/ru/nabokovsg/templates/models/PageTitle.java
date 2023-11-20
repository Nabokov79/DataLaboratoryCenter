package ru.nabokovsg.templates.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "page_title_templates")
public class PageTitle {

    @Id
    private long id;
    @Column(name = "document_name")
    private String documentName;
    @Column(name = "document_title")
    private String documentTitle;
}

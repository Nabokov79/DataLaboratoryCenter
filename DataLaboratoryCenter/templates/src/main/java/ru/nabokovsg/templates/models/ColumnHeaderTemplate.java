package ru.nabokovsg.templates.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.templates.models.enums.ColumnDataType;

import javax.persistence.*;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "columns_headers_templates")
public class ColumnHeaderTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "column_data_type")
    @Enumerated(EnumType.STRING)
    private ColumnDataType columnDataType;
    @Column(name = "sequential_number")
    private Integer sequentialNumber;
    @Column(name = "heading")
    private String heading;
}

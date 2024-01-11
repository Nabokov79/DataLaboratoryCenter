package ru.nabokovsg.templates.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

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
    @Column(name = "sequential_number")
    private Integer sequentialNumber;
    @Column(name = "heading")
    private String heading;

    @Override
    public String toString() {
        return "ColumnHeaderTemplate{" +
                "id=" + id +
                ", sequentialNumber=" + sequentialNumber +
                ", heading='" + heading + '\'' +
                '}';
    }
}

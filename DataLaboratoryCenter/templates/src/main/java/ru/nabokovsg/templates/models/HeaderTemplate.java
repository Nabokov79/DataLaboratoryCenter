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
@Table(name = "header_templates")
public class HeaderTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "organization")
    private String organization;
    @Column(name = "organization_license")
    private String organizationLicense;
    @Column(name = "organization_contacts")
    private String organizationContacts;
    @Column(name = "branch")
    private String branch;
    @Column(name = "branch_contacts")
    private String branchContacts;
    @Column(name = "branch_license")
    private String branchLicense;
    @Column(name = "department")
    private String department;
    @Column(name = "department_contacts")
    private String departmentContacts;
    @Column(name = "department_license")
    private String departmentLicense;
}
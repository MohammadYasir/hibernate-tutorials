package com.example;

import javax.persistence.*;

@Entity
public class WorksForEntity {

    @EmbeddedId
    private WorksForEntityId primaryKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("employeeId")
    @JoinColumn(name = "employeeId")
    private EnployeeEntity employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("projectId")
    @JoinColumn(name = "projectId")
    private ProjectEntity project;

    private int hours;

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public WorksForEntityId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(WorksForEntityId primaryKey) {
        this.primaryKey = primaryKey;
    }

    public EnployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EnployeeEntity employee) {
        this.employee = employee;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }
}

package com.bd.logger.domain;
import com.bd.logger.enumeration.GeneralAppLogSeverity;
import com.bd.logger.enumeration.GeneralAppLogType;
import com.bd.logger.enumeration.SearchByClass;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A GeneralAppLog.
 */
@Entity
@Table(name = "general_app_log")
public class GeneralAppLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private GeneralAppLogType type;

    @NotNull
    @Size(max = 512)
    @Column(name = "description", length = 512, nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "severity")
    private GeneralAppLogSeverity severity;

    @Column(name = "time_stamp")
    @Type(type = "org.hibernate.type.TimestampType")
    private Timestamp timeStamp;

    private String location;

    @Size(max = 512)
    @Column(name = "action", length = 512)
    private String action;

    @NotNull
    @Size(max = 512)
    @Column(name = "created_by", length = 512, nullable = false)
    private String createdBy;

    @Size(max = 512)
    @Column(name = "source", length = 512)
    private String source;

    @Column(name = "items_id")
    private Long items_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "search_by_class")
    private SearchByClass searchByClass;

    @Column(name = "lre_id")
    private Long lre_id;

    public GeneralAppLog() {
    }

    public GeneralAppLog(GeneralAppLogType type, String description, GeneralAppLogSeverity severity, Timestamp timeStamp, String location, String action, String createdBy, String source, Long items_id, SearchByClass searchByClass, Long lre_id) {
        this.type = type;
        this.description = description;
        this.severity = severity;
        this.timeStamp = timeStamp;
        this.location = location;
        this.action = action;
        this.createdBy = createdBy;
        this.source = source;
        this.items_id = items_id;
        this.searchByClass = searchByClass;
        this.lre_id = lre_id;
    }

    @Override
    public String toString() {
        return "GeneralAppLog{" +
                "id=" + id +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", severity=" + severity +
                ", timeStamp=" + timeStamp +
                ", location='" + location + '\'' +
                ", action='" + action + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", source='" + source + '\'' +
                ", items_id=" + items_id +
                ", searchByClass=" + searchByClass +
                ", lre_id=" + lre_id +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GeneralAppLogType getType() {
        return type;
    }

    public void setType(GeneralAppLogType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GeneralAppLogSeverity getSeverity() {
        return severity;
    }

    public void setSeverity(GeneralAppLogSeverity severity) {
        this.severity = severity;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Long getItems_id() {
        return items_id;
    }

    public void setItems_id(Long items_id) {
        this.items_id = items_id;
    }

    public SearchByClass getSearchByClass() {
        return searchByClass;
    }

    public void setSearchByClass(SearchByClass searchByClass) {
        this.searchByClass = searchByClass;
    }

    public Long getLre_id() {
        return lre_id;
    }

    public void setLre_id(Long lre_id) {
        this.lre_id = lre_id;
    }
}

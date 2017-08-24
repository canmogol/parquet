package com.comodo.parquet.model;

import javax.persistence.*;

/**
 * can | 8/23/17.
 */
@Entity
@Table(name = "connection_summary")
public class ConnectionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long hourRange;
    private Long sourceIp;
    private Long destinationIp;
    private ProtocolEnum protocol;
    private Long numberOfEvents;
    private String sourceFile;
    private String uploadCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHourRange() {
        return hourRange;
    }

    public void setHourRange(Long hourRange) {
        this.hourRange = hourRange;
    }

    public Long getSourceIp() {
        return sourceIp;
    }

    public void setSourceIp(Long sourceIp) {
        this.sourceIp = sourceIp;
    }

    public Long getDestinationIp() {
        return destinationIp;
    }

    public void setDestinationIp(Long destinationIp) {
        this.destinationIp = destinationIp;
    }

    public ProtocolEnum getProtocol() {
        return protocol;
    }

    public void setProtocol(ProtocolEnum protocol) {
        this.protocol = protocol;
    }

    public Long getNumberOfEvents() {
        return numberOfEvents;
    }

    public void setNumberOfEvents(Long numberOfEvents) {
        this.numberOfEvents = numberOfEvents;
    }

    public String getSourceFile() {
        return sourceFile;
    }

    public void setSourceFile(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    public String getUploadCode() {
        return uploadCode;
    }

    public void setUploadCode(String uploadCode) {
        this.uploadCode = uploadCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConnectionModel that = (ConnectionModel) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (hourRange != null ? !hourRange.equals(that.hourRange) : that.hourRange != null) return false;
        if (sourceIp != null ? !sourceIp.equals(that.sourceIp) : that.sourceIp != null) return false;
        if (destinationIp != null ? !destinationIp.equals(that.destinationIp) : that.destinationIp != null)
            return false;
        if (protocol != that.protocol) return false;
        if (numberOfEvents != null ? !numberOfEvents.equals(that.numberOfEvents) : that.numberOfEvents != null)
            return false;
        if (sourceFile != null ? !sourceFile.equals(that.sourceFile) : that.sourceFile != null) return false;
        return uploadCode != null ? uploadCode.equals(that.uploadCode) : that.uploadCode == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (hourRange != null ? hourRange.hashCode() : 0);
        result = 31 * result + (sourceIp != null ? sourceIp.hashCode() : 0);
        result = 31 * result + (destinationIp != null ? destinationIp.hashCode() : 0);
        result = 31 * result + (protocol != null ? protocol.hashCode() : 0);
        result = 31 * result + (numberOfEvents != null ? numberOfEvents.hashCode() : 0);
        result = 31 * result + (sourceFile != null ? sourceFile.hashCode() : 0);
        result = 31 * result + (uploadCode != null ? uploadCode.hashCode() : 0);
        return result;
    }

}

package com.kunlinr.lifealien.enclosure.po;

public class Enclosure {
    private String id;

    private String refid;

    private String enclosureName;

    private Double enclosureSize;

    private String enclosureType;

    private byte[] enclosureData;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRefid() {
        return refid;
    }

    public void setRefid(String refid) {
        this.refid = refid == null ? null : refid.trim();
    }

    public String getEnclosureName() {
        return enclosureName;
    }

    public void setEnclosureName(String enclosureName) {
        this.enclosureName = enclosureName == null ? null : enclosureName.trim();
    }

    public Double getEnclosureSize() {
        return enclosureSize;
    }

    public void setEnclosureSize(Double enclosureSize) {
        this.enclosureSize = enclosureSize;
    }

    public String getEnclosureType() {
        return enclosureType;
    }

    public void setEnclosureType(String enclosureType) {
        this.enclosureType = enclosureType == null ? null : enclosureType.trim();
    }

    public byte[] getEnclosureData() {
        return enclosureData;
    }

    public void setEnclosureData(byte[] enclosureData) {
        this.enclosureData = enclosureData;
    }
}
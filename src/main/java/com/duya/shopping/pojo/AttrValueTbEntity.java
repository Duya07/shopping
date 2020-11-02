package com.duya.shopping.pojo;

import javax.persistence.*;

@Entity
@Table(name = "attr_value_tb", schema = "shopping", catalog = "")
public class AttrValueTbEntity {
    private int attrNameId;
    private String attrName;
    private int attrValueId;
    private String attrValue;
    private int classId;

    @Basic
    @Column(name = "attr_name_id")
    public int getAttrNameId() {
        return attrNameId;
    }

    public void setAttrNameId(int attrNameId) {
        this.attrNameId = attrNameId;
    }

    @Basic
    @Column(name = "attr_name")
    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    @Id
    @Column(name = "attr_value_id")
    public int getAttrValueId() {
        return attrValueId;
    }

    public void setAttrValueId(int attrValueId) {
        this.attrValueId = attrValueId;
    }

    @Basic
    @Column(name = "attr_value")
    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    @Basic
    @Column(name = "class_id")
    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttrValueTbEntity that = (AttrValueTbEntity) o;

        if (attrNameId != that.attrNameId) return false;
        if (attrValueId != that.attrValueId) return false;
        if (classId != that.classId) return false;
        if (attrName != null ? !attrName.equals(that.attrName) : that.attrName != null) return false;
        if (attrValue != null ? !attrValue.equals(that.attrValue) : that.attrValue != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = attrNameId;
        result = 31 * result + (attrName != null ? attrName.hashCode() : 0);
        result = 31 * result + attrValueId;
        result = 31 * result + (attrValue != null ? attrValue.hashCode() : 0);
        result = 31 * result + classId;
        return result;
    }
}

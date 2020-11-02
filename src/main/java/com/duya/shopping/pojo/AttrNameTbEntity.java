package com.duya.shopping.pojo;

import javax.persistence.*;

@Entity
@Table(name = "attr_name_tb", schema = "shopping", catalog = "")
public class AttrNameTbEntity {
    private int attrNameId;
    private String attrName;
    private int classId;

    @Id
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

        AttrNameTbEntity that = (AttrNameTbEntity) o;

        if (attrNameId != that.attrNameId) return false;
        if (classId != that.classId) return false;
        if (attrName != null ? !attrName.equals(that.attrName) : that.attrName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = attrNameId;
        result = 31 * result + (attrName != null ? attrName.hashCode() : 0);
        result = 31 * result + classId;
        return result;
    }
}

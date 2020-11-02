package com.duya.shopping.pojo;

import javax.persistence.*;

@Entity
@Table(name = "bs_relation", schema = "shopping", catalog = "")
public class BsRelationEntity {
    private int id;
    private int bId;
    private int sId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "b_id")
    public int getbId() {
        return bId;
    }

    public void setbId(int bId) {
        this.bId = bId;
    }

    @Basic
    @Column(name = "s_id")
    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BsRelationEntity that = (BsRelationEntity) o;

        if (id != that.id) return false;
        if (bId != that.bId) return false;
        if (sId != that.sId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + bId;
        result = 31 * result + sId;
        return result;
    }
}

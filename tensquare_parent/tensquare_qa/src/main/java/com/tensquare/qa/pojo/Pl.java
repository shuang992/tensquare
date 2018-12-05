package com.tensquare.qa.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;
@Entity
@Table(name="tb_pl")
@IdClass(Pl.class) //复合联合主键
public class Pl implements Serializable {

    @Id
    private String problemid;
    @Id
    private String lableid;

    public Pl() {
    }

    public Pl(String problemid, String lableid) {
        this.problemid = problemid;
        this.lableid = lableid;
    }

    public String getProblemid() {
        return problemid;
    }

    public void setProblemid(String problemid) {
        this.problemid = problemid;
    }

    public String getLableid() {
        return lableid;
    }

    public void setLableid(String lableid) {
        this.lableid = lableid;
    }
}

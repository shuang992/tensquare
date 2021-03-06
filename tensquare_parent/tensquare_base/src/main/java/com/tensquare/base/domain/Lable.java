package com.tensquare.base.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
@Entity
@Table(name = "tb_label")
public class Lable implements Serializable {
    @Id
 private String id;// '标签ID',
 private String labelname;// '标签名称',
 private String  state ;//'状态',
 private Long count;// '使用数量',
 private String recommend;//'是否推荐',
 private Long fans;// '粉丝数',

    public Lable() {
    }

    public String getId() {
        return id;
    }

    public Lable(String id, String labelname, String state, Long count, String recommend, Long fans) {
        this.id = id;
        this.labelname = labelname;
        this.state = state;
        this.count = count;
        this.recommend = recommend;
        this.fans = fans;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabelname() {
        return labelname;
    }

    public void setLabelname(String labelname) {
        this.labelname = labelname;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public Long getFans() {
        return fans;
    }

    public void setFans(Long fans) {
        this.fans = fans;
    }
}

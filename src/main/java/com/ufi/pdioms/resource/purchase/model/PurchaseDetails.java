package com.ufi.pdioms.resource.purchase.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 采购信息带《详情》实体类
 */
@Data
@Table(name = "tb_purchase_details")
public class PurchaseDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "parentId")
    private Long parentId;
    private String sn;
    private String number;
    private String category;
    private String model;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "PurchaseDetails{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", sn='" + sn + '\'' +
                ", number='" + number + '\'' +
                ", category='" + category + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
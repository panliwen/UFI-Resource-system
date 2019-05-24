package com.ufi.pdioms.resource.purchase.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 采购信息实体类
 */
@Data
@Table(name = "tb_purchase")
public class Purchase implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @Column(name = "purchaseTime")
    private Date purchaseTime;
    private String purchase;
    private String supplier;
    private String remarks;

    @Column(name = "isDelete")
    private Integer isDelete;

    //实体对象引用
    private List<PurchaseDetails> detailsList;


    public List<PurchaseDetails> getDetailsList() {
        return detailsList;
    }

    public void setDetailsList(List<PurchaseDetails> detailsList) {
        this.detailsList = detailsList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public String getPurchase() {
        return purchase;
    }

    public void setPurchase(String purchase) {
        this.purchase = purchase;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", purchaseTime=" + purchaseTime +
                ", detailsList=" + detailsList +
                ", purchase='" + purchase + '\'' +
                ", supplier='" + supplier + '\'' +
                ", remarks='" + remarks + '\'' +
                ", isDelete=" + isDelete +
                '}';
    }
}

package com.ufi.pdioms.resource.parts.model;

import com.ufi.pdioms.resource.purchase.model.Purchase;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 厂家实体类
 */
@Data
@Table(name = "resource_parts")
public class Parts implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private String model;
    private String sn;
    private String rfid;
    private String department;
    private String supplier;
    private String manufacturer;
    @Column(name = "status")
    private Integer status;

    @Column(name = "is_delete")
    private Integer isDelete;

    private Purchase purchase;

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }


    @Override
    public String toString() {
        return "Battery{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", model='" + model + '\'' +
                ", sn='" + sn + '\'' +
                ", rfid='" + rfid + '\'' +
                ", department='" + department + '\'' +
                ", supplier='" + supplier + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", status=" + status +
                ", isDelete=" + isDelete +
                ", purchase=" + purchase +
                '}';
    }
}

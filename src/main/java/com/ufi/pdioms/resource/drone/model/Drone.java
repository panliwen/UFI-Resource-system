package com.ufi.pdioms.resource.drone.model;

import com.ufi.pdioms.resource.purchase.model.Purchase;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 厂家实体类
 */
@Data
@Table(name = "tb_device_drone")
public class Drone implements Serializable
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
    @Column(name = "isSuit")
    private Integer isSuit; //是否是套装系列

    @Column(name = "isDelete")
    private long isDelete;
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

    public long getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(long isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getIsSuit() {
        return isSuit;
    }

    public void setIsSuit(Integer isSuit) {
        this.isSuit = isSuit;
    }

    @Override
    public String toString() {
        return "Drone{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", model='" + model + '\'' +
                ", sn='" + sn + '\'' +
                ", rfid='" + rfid + '\'' +
                ", department='" + department + '\'' +
                ", supplier='" + supplier + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", status=" + status +
                ", isSuit=" + isSuit +
                ", isDelete=" + isDelete +
                ", purchase=" + purchase +
                '}';
    }
}

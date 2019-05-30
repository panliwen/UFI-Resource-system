package com.ufi.pdioms.resource.manufacturer.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 厂家实体类
 */
@Data
@Table(name = "resource_manufacturer")
public class Manufacturer implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String manufacturer;
    private String remarks;
    @Column(name = "isDelete")
    private Integer isDelete;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
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
        return "Manufacturer{" +
                "id=" + id +
                ", manufacturer='" + manufacturer + '\'' +
                ", remarks='" + remarks + '\'' +
                ", isDelete=" + isDelete +
                '}';
    }
}

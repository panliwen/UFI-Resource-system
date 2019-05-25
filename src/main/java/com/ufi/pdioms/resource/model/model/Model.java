package com.ufi.pdioms.resource.model.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 设备型号实体类
 */
@Data
@Table(name = "tb_device_model")
public class Model implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "typeImage")
    private String typeImage;
    private String model;
    private String category;
    private String manufacturer;

    @Column(name = "isDelete")
    private long isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeImage() {
        return typeImage;
    }

    public void setTypeImage(String typeImage) {
        this.typeImage = typeImage;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public long getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(long isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", typeImage='" + typeImage + '\'' +
                ", model='" + model + '\'' +
                ", category='" + category + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", isDelete=" + isDelete +
                '}';
    }
}

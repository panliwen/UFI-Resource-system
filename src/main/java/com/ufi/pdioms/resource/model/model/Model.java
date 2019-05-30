package com.ufi.pdioms.resource.model.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 设备型号实体类
 */
@Data
@Table(name = "resource_device_model")
public class Model implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "type_image")
    private String typeImage;
    private String model;
//    @Column(name = "type_name")
    private String typeName;
    private String manufacturer;

    @Column(name = "is_delete")
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
                ", category='" + typeName + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", isDelete=" + isDelete +
                '}';
    }
}

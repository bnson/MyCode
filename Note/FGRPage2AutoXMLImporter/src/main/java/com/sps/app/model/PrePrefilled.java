/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sps.app.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * This is a data structure, so fields can be public. (Clean-Code)
 */
@Entity
@Table(name = "pre_prefilled")
public class PrePrefilled implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(columnDefinition = "serial")  
  private long id;
  
  @Column(columnDefinition = "file_name")  
  private String fileName;
  
  private String field_name;
  private String ocr_value;
  private String captured_value;
  private int x_pos;
  private int y_pos;
  private int width;
  private int height;
  private String image_base64;
  private Timestamp create_time;

    public PrePrefilled() {
    }
    
    public PrePrefilled(String fileName, String field_name, String ocr_value, String captureldvalue, int x_pos, int y_pos, int width, int height, String image_base64, Timestamp create_time) {
        this.fileName = fileName;
        this.field_name = field_name;
        this.ocr_value = ocr_value;
        this.captured_value = captureldvalue;
        this.x_pos = x_pos;
        this.y_pos = y_pos;
        this.width = width;
        this.height = height;
        this.image_base64 = image_base64;
        this.create_time = create_time;
    }    

    public PrePrefilled(long id, String fileName, String field_name, String ocr_value, String captureldvalue, int x_pos, int y_pos, int width, int height, String image_base64, Timestamp create_time) {
        this.id = id;
        this.fileName = fileName;
        this.field_name = field_name;
        this.ocr_value = ocr_value;
        this.captured_value = captureldvalue;
        this.x_pos = x_pos;
        this.y_pos = y_pos;
        this.width = width;
        this.height = height;
        this.image_base64 = image_base64;
        this.create_time = create_time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getField_name() {
        return field_name;
    }

    public void setField_name(String field_name) {
        this.field_name = field_name;
    }

    public String getOcr_value() {
        return ocr_value;
    }

    public void setOcr_value(String ocr_value) {
        this.ocr_value = ocr_value;
    }

    public String getCaptureldvalue() {
        return captured_value;
    }

    public void setCaptureldvalue(String captureldvalue) {
        this.captured_value = captureldvalue;
    }

    public int getX_pos() {
        return x_pos;
    }

    public void setX_pos(int x_pos) {
        this.x_pos = x_pos;
    }

    public int getY_pos() {
        return y_pos;
    }

    public void setY_pos(int y_pos) {
        this.y_pos = y_pos;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getImage_base64() {
        return image_base64;
    }

    public void setImage_base64(String image_base64) {
        this.image_base64 = image_base64;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}

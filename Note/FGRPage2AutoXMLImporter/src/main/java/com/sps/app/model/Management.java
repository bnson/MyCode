/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sps.app.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * This is a data structure, so fields can be public. (Clean-Code)
 */
@Entity
@Table(name = "management")
public class Management implements Serializable {

    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "full_file_name")
    private String fullFileName;
    @Column(name = "comment")
    private String comment;
    @Column(name = "data_recognition_status")
    private int dataRecognitionStatus;

    private int record_status;
    private String created_time;
    private int form_id;
    private int bad_image;
    private String reason_bad;
    private String step1_status;
    private String step2_status;
    private String step3_status;
    private int qc_status;
    
    @Column(name = "section")
    private int section;
    
    private String bad_image_note;
    private String image_angle;
    private String due_date_time;

    public Management() {
    }

    public Management(long id, String fileName, String fullFileName, String comment, int dataRecognitionStatus, int record_status, String created_time, int form_id, int bad_image, String reason_bad, String step1_status, String step2_status, String step3_status, int qc_status, int section, String bad_image_note, String image_angle, String due_date_time) {
        this.id = id;
        this.fileName = fileName;
        this.fullFileName = fullFileName;
        this.comment = comment;
        this.dataRecognitionStatus = dataRecognitionStatus;
        this.record_status = record_status;
        this.created_time = created_time;
        this.form_id = form_id;
        this.bad_image = bad_image;
        this.reason_bad = reason_bad;
        this.step1_status = step1_status;
        this.step2_status = step2_status;
        this.step3_status = step3_status;
        this.qc_status = qc_status;
        this.section = section;
        this.bad_image_note = bad_image_note;
        this.image_angle = image_angle;
        this.due_date_time = due_date_time;
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

    public String getFullFileName() {
        return fullFileName;
    }

    public void setFullFileName(String fullFileName) {
        this.fullFileName = fullFileName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getDataRecognitionStatus() {
        return dataRecognitionStatus;
    }

    public void setDataRecognitionStatus(int dataRecognitionStatus) {
        this.dataRecognitionStatus = dataRecognitionStatus;
    }

    public int getRecord_status() {
        return record_status;
    }

    public void setRecord_status(int record_status) {
        this.record_status = record_status;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public int getForm_id() {
        return form_id;
    }

    public void setForm_id(int form_id) {
        this.form_id = form_id;
    }

    public int getBad_image() {
        return bad_image;
    }

    public void setBad_image(int bad_image) {
        this.bad_image = bad_image;
    }

    public String getReason_bad() {
        return reason_bad;
    }

    public void setReason_bad(String reason_bad) {
        this.reason_bad = reason_bad;
    }

    public String getStep1_status() {
        return step1_status;
    }

    public void setStep1_status(String step1_status) {
        this.step1_status = step1_status;
    }

    public String getStep2_status() {
        return step2_status;
    }

    public void setStep2_status(String step2_status) {
        this.step2_status = step2_status;
    }

    public String getStep3_status() {
        return step3_status;
    }

    public void setStep3_status(String step3_status) {
        this.step3_status = step3_status;
    }

    public int getQc_status() {
        return qc_status;
    }

    public void setQc_status(int qc_status) {
        this.qc_status = qc_status;
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public String getBad_image_note() {
        return bad_image_note;
    }

    public void setBad_image_note(String bad_image_note) {
        this.bad_image_note = bad_image_note;
    }

    public String getImage_angle() {
        return image_angle;
    }

    public void setImage_angle(String image_angle) {
        this.image_angle = image_angle;
    }

    public String getDue_date_time() {
        return due_date_time;
    }

    public void setDue_date_time(String due_date_time) {
        this.due_date_time = due_date_time;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    
}

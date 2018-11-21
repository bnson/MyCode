/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sps.app.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * @author bnson
 */
@Entity
@Table(name = "field")
public class Field implements Serializable {

    @EmbeddedId 
    private FieldId fieldId;

    private Integer page_number;
    
    @Column(name = "field_name")
    private String fieldName;
    
    private String tooltip;
    
    @Column(name = "tab_order")
    private Integer tabOrder;
    
    @Column(name = "input_Section")
    private Integer inputSection;
    
    private String default_value;
    private String caption;

    public Field() {
    }

    public Field(FieldId fieldId, Integer page_number, String fieldName, String tooltip, Integer tabOrder, Integer inputSection, String default_value, String caption) {
        this.fieldId = fieldId;
        this.page_number = page_number;
        this.fieldName = fieldName;
        this.tooltip = tooltip;
        this.tabOrder = tabOrder;
        this.inputSection = inputSection;
        this.default_value = default_value;
        this.caption = caption;
    }

    public FieldId getFieldId() {
        return fieldId;
    }

    public void setFieldId(FieldId fieldId) {
        this.fieldId = fieldId;
    }

    public Integer getPage_number() {
        return page_number;
    }

    public void setPage_number(Integer page_number) {
        this.page_number = page_number;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getTooltip() {
        return tooltip;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    public Integer getTabOrder() {
        return tabOrder;
    }

    public void setTabOrder(Integer tabOrder) {
        this.tabOrder = tabOrder;
    }

    public Integer getInputSection() {
        return inputSection;
    }

    public void setInputSection(Integer inputSection) {
        this.inputSection = inputSection;
    }

    public String getDefault_value() {
        return default_value;
    }

    public void setDefault_value(String default_value) {
        this.default_value = default_value;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}


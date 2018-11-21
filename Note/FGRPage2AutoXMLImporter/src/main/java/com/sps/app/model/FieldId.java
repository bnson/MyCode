/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sps.app.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class FieldId implements Serializable {

    @NotNull         
    int form_id;
    
    @NotNull
    int field_id;

    public FieldId() {
    }

    public FieldId(int form_id, int field_id) {
        this.form_id = form_id;
        this.field_id = field_id;
    }

    public int getForm_id() {
        return form_id;
    }

    public void setForm_id(int form_id) {
        this.form_id = form_id;
    }

    public int getField_id() {
        return field_id;
    }

    public void setField_id(int field_id) {
        this.field_id = field_id;
    }
    
    

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sps.app.component;

import com.sps.app.model.Field;
import com.sps.app.repository.FieldRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class FieldComponent {
    @Autowired
    private FieldRepository fieldRepository;
    
    @Transactional
    public void save(Field fieldTmp) {
        fieldRepository.save(fieldTmp);
    }

    @Transactional(readOnly=true)
    public List<Field> findAll() {
        return fieldRepository.findAll();
    }       
    
    @Transactional
    public void deleteAllInBatch() {
        fieldRepository.deleteAllInBatch();
    }

    @Transactional(readOnly=true)
    public List<String> findAllFieldName() {
        return fieldRepository.findAllFieldName();
    }       

}

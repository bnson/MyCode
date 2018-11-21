/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sps.app.repository;

import com.sps.app.model.Field;
import com.sps.app.model.FieldId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldRepository extends JpaRepository<Field, FieldId> {

    @Query("select DISTINCT f.fieldName from Field f")
    public List<String> findAllFieldName();
    
}

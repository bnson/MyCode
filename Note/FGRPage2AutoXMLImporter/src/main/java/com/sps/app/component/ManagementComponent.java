/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sps.app.component;

import com.sps.app.model.Management;
import com.sps.app.repository.ManagementRepository;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ManagementComponent {
    
    @Autowired
    private ManagementRepository managementRepository;
    
    @Transactional
    public void save(Management fieldTmp) {
        managementRepository.save(fieldTmp);
    }

    @Transactional(readOnly=true)
    public List<Management> findAll() {
        return managementRepository.findAll();
    }       
    
    @Transactional
    public void deleteAllInBatch() {
        managementRepository.deleteAllInBatch();
    }

    @Transactional
    public List<Management> findByFileNameIn(Set<String> listFileName) {
        return managementRepository.findByFileNameIn(listFileName);
    }
    
    @Transactional
    public List<Management> getManagementProcess(Set<String> listFileName) {
        return managementRepository.getManagementProcess(listFileName);
    }    

}

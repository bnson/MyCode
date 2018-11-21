/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sps.app.component;

import com.sps.app.model.PrePrefilled;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.sps.app.repository.PrePrefilledRepository;

/**
 * Service layer. Specify transactional behavior and mainly delegate calls to
 * Repository.
 */
@Component
public class TablePrePrefilledComponent {

    @Autowired
    private PrePrefilledRepository tablePrePrefilledRepository;

    @Transactional
    public void save(PrePrefilled tablePrePrefilled) {
        tablePrePrefilledRepository.save(tablePrePrefilled);
    }

    @Transactional(readOnly = true)
    public List<PrePrefilled> findAll() {
        return tablePrePrefilledRepository.findAll();
    }
    
    @Transactional
    public void deleteByFileName(String fileName) {
        tablePrePrefilledRepository.deleteByFileName(fileName);
    }

}

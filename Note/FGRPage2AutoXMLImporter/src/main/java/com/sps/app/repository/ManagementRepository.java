/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sps.app.repository;

import com.sps.app.model.Management;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface ManagementRepository extends JpaRepository<Management, Long>  {
    //Object findFullFileNameTop1ByFileNameOrderByIdDesc(String fileName);    
    //@Query("select m.full_file_name from Management m where m.file_name = ?1")
    //public String getFullFileName(String fileName);    
    //List<Management> findTop1ByFileNameOrderByFileNameAsc(String fileName);
    
//    @Query("select m.fullFileName from Management m where m.fileName = ?1")
//    String findFullFileNameByFileName(String fileName);    
    
//    @Query("select m.fullFileName from Management m where m.fileName in ?1")
//    List<String> findFullFileNameByListFileName(Set<String> listFileName);    
    
    
    List<Management> findByFileNameIn(Set<String> fileName);
    
    @Transactional(timeout = 10)
    @Query("select m from Management m where m.dataRecognitionStatus = 0 AND m.fileName in ?1")
    List<Management> getManagementProcess(Set<String> fileName);
    
}

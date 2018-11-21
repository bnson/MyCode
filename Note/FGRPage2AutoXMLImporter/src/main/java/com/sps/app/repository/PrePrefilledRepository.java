/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sps.app.repository;

import com.sps.app.model.PrePrefilled;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PrePrefilledRepository extends JpaRepository<PrePrefilled, Long> {

//    /**
//     * No need to define findAll() here, because inherited from JpaRepository
//     * with many other basic JPA operations.*
//     */
//    //public List<Product> findAll();
//    /**
//     * spring-jpa-data understands this method name, because it supports the
//     * resolution of specific keywords inside method names. *
//     * @param searchString
//     * @return 
//     */
//    public List<TablePrePrefilled> findByNameContainingIgnoreCase(String searchString);
//
//    /**
//     * You can define a JPA query.*
//     * @param name
//     * @return 
//     */
//    @Query("select p from Product p where p.name = :name")
//    public List<TablePrePrefilled> findByNameIs(@Param("name") String name);

    @Modifying
    @Transactional(timeout = 10)
    void deleteByFileName(String fileName);

}

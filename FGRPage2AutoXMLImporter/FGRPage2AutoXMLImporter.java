package com.sps.app;

import javax.xml.bind.JAXBException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

@SpringBootApplication
@EnableScheduling
public class FGRPage2AutoXMLImporter {

    public static void main(String[] args) throws JAXBException {
        SpringApplication.run(FGRPage2AutoXMLImporter.class, args);   
    }
    
}

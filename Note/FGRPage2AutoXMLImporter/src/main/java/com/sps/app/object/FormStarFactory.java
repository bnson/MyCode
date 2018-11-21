/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sps.app.object;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the viet.java package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class FormStarFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: viet.java
     * 
     */
    public FormStarFactory() {
    }

    /**
     * Create an instance of {@link FormStar.Applikation }
     * 
     * @return 
     */
    public FormStar.Applikation createFormStarApplikation() {
        return new FormStar.Applikation();
    }

    /**
     * Create an instance of {@link FormStar.Applikation.Batch }
     * @return 
     */
    public FormStar.Applikation.Batch createFormStarApplikationBatch() {
        return new FormStar.Applikation.Batch();
    }

    /**
     * Create an instance of {@link FormStar.Applikation.Batch.Dokument }
     * @return 
     */
    public FormStar.Applikation.Batch.Dokument createFormStarApplikationBatchDokument() {
        return new FormStar.Applikation.Batch.Dokument();
    }

    /**
     * Create an instance of {@link FormStar }
     * @return 
     */
    public FormStar createFormStar() {
        return new FormStar();
    }

    /**
     * Create an instance of {@link FormStar.Applikation.Batch.Dokument.Field.Char }
     * @return 
     */
    public FormStar.Applikation.Batch.Dokument.Field.Char createFormStarApplikationBatchDokumentFieldChar() {
        return new FormStar.Applikation.Batch.Dokument.Field.Char();
    }

    /**
     * Create an instance of {@link FormStar.Applikation.Batch.Dokument.Field }
     * @return 
     */
    public FormStar.Applikation.Batch.Dokument.Field createFormStarApplikationBatchDokumentField() {
        return new FormStar.Applikation.Batch.Dokument.Field();
    }

}

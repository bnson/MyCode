/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sps.app.object;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Applikation">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Batch">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Dokument" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="Field" maxOccurs="unbounded" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="zone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="char" maxOccurs="unbounded" minOccurs="0">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="box" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                           &lt;element name="choices" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                         &lt;/sequence>
 *                                                       &lt;/restriction>
 *                                                     &lt;/complexContent>
 *                                                   &lt;/complexType>
 *                                                 &lt;/element>
 *                                               &lt;/sequence>
 *                                               &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                               &lt;attribute name="state" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                               &lt;attribute name="confidence" type="{http://www.w3.org/2001/XMLSchema}short" />
 *                                               &lt;attribute name="groupcheck" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                               &lt;attribute name="groupname" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                               &lt;attribute name="grouptype" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                     &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="state" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                           &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}int" />
 *                           &lt;attribute name="Date" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="path" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="state" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="producer" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}float" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "applikation"
})
@XmlRootElement(name = "FormStar")
public class FormStar {

    @XmlElement(name = "Applikation", required = true)
    protected FormStar.Applikation applikation;
    @XmlAttribute
    protected Float version;

    /**
     * Gets the value of the applikation property.
     *
     * @return possible object is {@link FormStar.Applikation }
     *
     */
    public FormStar.Applikation getApplikation() {
        return applikation;
    }

    /**
     * Sets the value of the applikation property.
     *
     * @param value allowed object is {@link FormStar.Applikation }
     *
     */
    public void setApplikation(FormStar.Applikation value) {
        this.applikation = value;
    }

    /**
     * Gets the value of the version property.
     *
     * @return possible object is {@link Float }
     *
     */
    public Float getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     *
     * @param value allowed object is {@link Float }
     *
     */
    public void setVersion(Float value) {
        this.version = value;
    }

    /**
     * <p>
     * Java class for anonymous complex type.
     *
     * <p>
     * The following schema fragment specifies the expected content contained
     * within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Batch">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="Dokument" maxOccurs="unbounded" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="Field" maxOccurs="unbounded" minOccurs="0">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                       &lt;element name="zone" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                       &lt;element name="char" maxOccurs="unbounded" minOccurs="0">
     *                                         &lt;complexType>
     *                                           &lt;complexContent>
     *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                               &lt;sequence>
     *                                                 &lt;element name="box" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                                 &lt;element name="choices" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                               &lt;/sequence>
     *                                             &lt;/restriction>
     *                                           &lt;/complexContent>
     *                                         &lt;/complexType>
     *                                       &lt;/element>
     *                                     &lt;/sequence>
     *                                     &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                                     &lt;attribute name="state" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                                     &lt;attribute name="confidence" type="{http://www.w3.org/2001/XMLSchema}short" />
     *                                     &lt;attribute name="groupcheck" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                                     &lt;attribute name="groupname" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                                     &lt;attribute name="grouptype" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                           &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           &lt;attribute name="state" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *                 &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}int" />
     *                 &lt;attribute name="Date" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="path" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="state" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="producer" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "batch"
    })
    public static class Applikation {

        @XmlElement(name = "Batch", required = true)
        protected FormStar.Applikation.Batch batch;
        @XmlAttribute
        protected String name;
        @XmlAttribute
        protected String state;
        @XmlAttribute
        protected String producer;

        /**
         * Gets the value of the batch property.
         *
         * @return possible object is {@link FormStar.Applikation.Batch }
         *
         */
        public FormStar.Applikation.Batch getBatch() {
            return batch;
        }

        /**
         * Sets the value of the batch property.
         *
         * @param value allowed object is {@link FormStar.Applikation.Batch }
         *
         */
        public void setBatch(FormStar.Applikation.Batch value) {
            this.batch = value;
        }

        /**
         * Gets the value of the name property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the value of the name property.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setName(String value) {
            this.name = value;
        }

        /**
         * Gets the value of the state property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getState() {
            return state;
        }

        /**
         * Sets the value of the state property.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setState(String value) {
            this.state = value;
        }

        /**
         * Gets the value of the producer property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getProducer() {
            return producer;
        }

        /**
         * Sets the value of the producer property.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setProducer(String value) {
            this.producer = value;
        }

        /**
         * <p>
         * Java class for anonymous complex type.
         *
         * <p>
         * The following schema fragment specifies the expected content
         * contained within this class.
         *
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="Dokument" maxOccurs="unbounded" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="Field" maxOccurs="unbounded" minOccurs="0">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                             &lt;element name="zone" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                             &lt;element name="char" maxOccurs="unbounded" minOccurs="0">
         *                               &lt;complexType>
         *                                 &lt;complexContent>
         *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                                     &lt;sequence>
         *                                       &lt;element name="box" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                                       &lt;element name="choices" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                                     &lt;/sequence>
         *                                   &lt;/restriction>
         *                                 &lt;/complexContent>
         *                               &lt;/complexType>
         *                             &lt;/element>
         *                           &lt;/sequence>
         *                           &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                           &lt;attribute name="state" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                           &lt;attribute name="confidence" type="{http://www.w3.org/2001/XMLSchema}short" />
         *                           &lt;attribute name="groupcheck" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                           &lt;attribute name="groupname" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                           &lt;attribute name="grouptype" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *                 &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="state" type="{http://www.w3.org/2001/XMLSchema}string" />
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}int" />
         *       &lt;attribute name="Date" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="path" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "dokument"
        })
        public static class Batch {

            @XmlElement(name = "Dokument")
            public List<FormStar.Applikation.Batch.Dokument> dokument;
            @XmlAttribute
            protected Integer name;
            @XmlAttribute(name = "Date")
            protected String date;
            @XmlAttribute
            protected String path;

            /**
             * Gets the value of the dokument property.
             *
             * <p>
             * This accessor method returns a reference to the live list, not a
             * snapshot. Therefore any modification you make to the returned
             * list will be present inside the JAXB object. This is why there is
             * not a <CODE>set</CODE> method for the dokument property.
             *
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getDokument().add(newItem);
             * </pre>
             *
             *
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link FormStar.Applikation.Batch.Dokument }
             *
             *
             * @return
             */
            public List<FormStar.Applikation.Batch.Dokument> getDokument() {
                if (dokument == null) {
                    dokument = new ArrayList<FormStar.Applikation.Batch.Dokument>();
                }
                return this.dokument;
            }

            /**
             * Gets the value of the name property.
             *
             * @return possible object is {@link Integer }
             *
             */
            public Integer getName() {
                return name;
            }

            /**
             * Sets the value of the name property.
             *
             * @param value allowed object is {@link Integer }
             *
             */
            public void setName(Integer value) {
                this.name = value;
            }

            /**
             * Gets the value of the date property.
             *
             * @return possible object is {@link String }
             *
             */
            public String getDate() {
                return date;
            }

            /**
             * Sets the value of the date property.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setDate(String value) {
                this.date = value;
            }

            /**
             * Gets the value of the path property.
             *
             * @return possible object is {@link String }
             *
             */
            public String getPath() {
                return path;
            }

            /**
             * Sets the value of the path property.
             *
             * @param value allowed object is {@link String }
             *
             */
            public void setPath(String value) {
                this.path = value;
            }

            /**
             * <p>
             * Java class for anonymous complex type.
             *
             * <p>
             * The following schema fragment specifies the expected content
             * contained within this class.
             *
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;sequence>
             *         &lt;element name="Field" maxOccurs="unbounded" minOccurs="0">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                   &lt;element name="zone" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                   &lt;element name="char" maxOccurs="unbounded" minOccurs="0">
             *                     &lt;complexType>
             *                       &lt;complexContent>
             *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                           &lt;sequence>
             *                             &lt;element name="box" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                             &lt;element name="choices" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                           &lt;/sequence>
             *                         &lt;/restriction>
             *                       &lt;/complexContent>
             *                     &lt;/complexType>
             *                   &lt;/element>
             *                 &lt;/sequence>
             *                 &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
             *                 &lt;attribute name="state" type="{http://www.w3.org/2001/XMLSchema}string" />
             *                 &lt;attribute name="confidence" type="{http://www.w3.org/2001/XMLSchema}short" />
             *                 &lt;attribute name="groupcheck" type="{http://www.w3.org/2001/XMLSchema}string" />
             *                 &lt;attribute name="groupname" type="{http://www.w3.org/2001/XMLSchema}string" />
             *                 &lt;attribute name="grouptype" type="{http://www.w3.org/2001/XMLSchema}string" />
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *       &lt;/sequence>
             *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="state" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             *
             *
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "field"
            })
            public static class Dokument {

                @XmlElement(name = "Field")
                protected List<FormStar.Applikation.Batch.Dokument.Field> field;
                @XmlAttribute
                protected String name;
                @XmlAttribute
                protected String state;

                /**
                 * Gets the value of the field property.
                 *
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object. This is
                 * why there is not a <CODE>set</CODE> method for the field
                 * property.
                 *
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getField().add(newItem);
                 * </pre>
                 *
                 *
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link FormStar.Applikation.Batch.Dokument.Field }
                 *
                 * @return
                 */
                public List<FormStar.Applikation.Batch.Dokument.Field> getField() {
                    if (field == null) {
                        field = new ArrayList<FormStar.Applikation.Batch.Dokument.Field>();
                    }
                    return this.field;
                }

                /**
                 * Gets the value of the name property.
                 *
                 * @return possible object is {@link String }
                 *
                 */
                public String getName() {
                    return name;
                }

                /**
                 * Sets the value of the name property.
                 *
                 * @param value allowed object is {@link String }
                 *
                 */
                public void setName(String value) {
                    this.name = value;
                }

                /**
                 * Gets the value of the state property.
                 *
                 * @return possible object is {@link String }
                 *
                 */
                public String getState() {
                    return state;
                }

                /**
                 * Sets the value of the state property.
                 *
                 * @param value allowed object is {@link String }
                 *
                 */
                public void setState(String value) {
                    this.state = value;
                }

                /**
                 * <p>
                 * Java class for anonymous complex type.
                 *
                 * <p>
                 * The following schema fragment specifies the expected content
                 * contained within this class.
                 *
                 * <pre>
                 * &lt;complexType>
                 *   &lt;complexContent>
                 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *       &lt;sequence>
                 *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *         &lt;element name="zone" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *         &lt;element name="char" maxOccurs="unbounded" minOccurs="0">
                 *           &lt;complexType>
                 *             &lt;complexContent>
                 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                 &lt;sequence>
                 *                   &lt;element name="box" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *                   &lt;element name="choices" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *                 &lt;/sequence>
                 *               &lt;/restriction>
                 *             &lt;/complexContent>
                 *           &lt;/complexType>
                 *         &lt;/element>
                 *       &lt;/sequence>
                 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
                 *       &lt;attribute name="state" type="{http://www.w3.org/2001/XMLSchema}string" />
                 *       &lt;attribute name="confidence" type="{http://www.w3.org/2001/XMLSchema}short" />
                 *       &lt;attribute name="groupcheck" type="{http://www.w3.org/2001/XMLSchema}string" />
                 *       &lt;attribute name="groupname" type="{http://www.w3.org/2001/XMLSchema}string" />
                 *       &lt;attribute name="grouptype" type="{http://www.w3.org/2001/XMLSchema}string" />
                 *     &lt;/restriction>
                 *   &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 *
                 *
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                    "value",
                    "zone",
                    "_char"
                })
                public static class Field {

                    @XmlElement(required = true)
                    protected String value;
                    @XmlElement(required = true)
                    protected String zone;
                    @XmlElement(name = "char")
                    protected List<FormStar.Applikation.Batch.Dokument.Field.Char> _char;
                    @XmlAttribute
                    protected String name;
                    @XmlAttribute
                    protected String state;
                    @XmlAttribute
                    protected Short confidence;
                    @XmlAttribute
                    protected String groupcheck;
                    @XmlAttribute
                    protected String groupname;
                    @XmlAttribute
                    protected String grouptype;

                    /**
                     * Gets the value of the value property.
                     *
                     * @return possible object is {@link String }
                     *
                     */
                    public String getValue() {
                        return value;
                    }

                    /**
                     * Sets the value of the value property.
                     *
                     * @param value allowed object is {@link String }
                     *
                     */
                    public void setValue(String value) {
                        this.value = value;
                    }

                    /**
                     * Gets the value of the zone property.
                     *
                     * @return possible object is {@link String }
                     *
                     */
                    public String getZone() {
                        return zone;
                    }

                    /**
                     * Sets the value of the zone property.
                     *
                     * @param value allowed object is {@link String }
                     *
                     */
                    public void setZone(String value) {
                        this.zone = value;
                    }

                    /**
                     * Gets the value of the char property.
                     *
                     * <p>
                     * This accessor method returns a reference to the live
                     * list, not a snapshot. Therefore any modification you make
                     * to the returned list will be present inside the JAXB
                     * object. This is why there is not a <CODE>set</CODE>
                     * method for the char property.
                     *
                     * <p>
                     * For example, to add a new item, do as follows:
                     * <pre>
                     *    getChar().add(newItem);
                     * </pre>
                     *
                     *
                     * <p>
                     * Objects of the following type(s) are allowed in the list
                     * {@link FormStar.Applikation.Batch.Dokument.Field.Char }
                     *
                     * @return
                     */
                    public List<FormStar.Applikation.Batch.Dokument.Field.Char> getChar() {
                        if (_char == null) {
                            _char = new ArrayList<FormStar.Applikation.Batch.Dokument.Field.Char>();
                        }
                        return this._char;
                    }

                    /**
                     * Gets the value of the name property.
                     *
                     * @return possible object is {@link String }
                     *
                     */
                    public String getName() {
                        return name;
                    }

                    /**
                     * Sets the value of the name property.
                     *
                     * @param value allowed object is {@link String }
                     *
                     */
                    public void setName(String value) {
                        this.name = value;
                    }

                    /**
                     * Gets the value of the state property.
                     *
                     * @return possible object is {@link String }
                     *
                     */
                    public String getState() {
                        return state;
                    }

                    /**
                     * Sets the value of the state property.
                     *
                     * @param value allowed object is {@link String }
                     *
                     */
                    public void setState(String value) {
                        this.state = value;
                    }

                    /**
                     * Gets the value of the confidence property.
                     *
                     * @return possible object is {@link Short }
                     *
                     */
                    public Short getConfidence() {
                        return confidence;
                    }

                    /**
                     * Sets the value of the confidence property.
                     *
                     * @param value allowed object is {@link Short }
                     *
                     */
                    public void setConfidence(Short value) {
                        this.confidence = value;
                    }

                    /**
                     * Gets the value of the groupcheck property.
                     *
                     * @return possible object is {@link String }
                     *
                     */
                    public String getGroupcheck() {
                        return groupcheck;
                    }

                    /**
                     * Sets the value of the groupcheck property.
                     *
                     * @param value allowed object is {@link String }
                     *
                     */
                    public void setGroupcheck(String value) {
                        this.groupcheck = value;
                    }

                    /**
                     * Gets the value of the groupname property.
                     *
                     * @return possible object is {@link String }
                     *
                     */
                    public String getGroupname() {
                        return groupname;
                    }

                    /**
                     * Sets the value of the groupname property.
                     *
                     * @param value allowed object is {@link String }
                     *
                     */
                    public void setGroupname(String value) {
                        this.groupname = value;
                    }

                    /**
                     * Gets the value of the grouptype property.
                     *
                     * @return possible object is {@link String }
                     *
                     */
                    public String getGrouptype() {
                        return grouptype;
                    }

                    /**
                     * Sets the value of the grouptype property.
                     *
                     * @param value allowed object is {@link String }
                     *
                     */
                    public void setGrouptype(String value) {
                        this.grouptype = value;
                    }

                    /**
                     * <p>
                     * Java class for anonymous complex type.
                     *
                     * <p>
                     * The following schema fragment specifies the expected
                     * content contained within this class.
                     *
                     * <pre>
                     * &lt;complexType>
                     *   &lt;complexContent>
                     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                     *       &lt;sequence>
                     *         &lt;element name="box" type="{http://www.w3.org/2001/XMLSchema}string"/>
                     *         &lt;element name="choices" type="{http://www.w3.org/2001/XMLSchema}string"/>
                     *       &lt;/sequence>
                     *     &lt;/restriction>
                     *   &lt;/complexContent>
                     * &lt;/complexType>
                     * </pre>
                     *
                     *
                     */
                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = {
                        "box",
                        "choices"
                    })
                    public static class Char {

                        @XmlElement(required = true)
                        protected String box;
                        @XmlElement(required = true)
                        protected String choices;

                        /**
                         * Gets the value of the box property.
                         *
                         * @return possible object is {@link String }
                         *
                         */
                        public String getBox() {
                            return box;
                        }

                        /**
                         * Sets the value of the box property.
                         *
                         * @param value allowed object is {@link String }
                         *
                         */
                        public void setBox(String value) {
                            this.box = value;
                        }

                        /**
                         * Gets the value of the choices property.
                         *
                         * @return possible object is {@link String }
                         *
                         */
                        public String getChoices() {
                            return choices;
                        }

                        /**
                         * Sets the value of the choices property.
                         *
                         * @param value allowed object is {@link String }
                         *
                         */
                        public void setChoices(String value) {
                            this.choices = value;
                        }

                    }

                }

            }

        }

    }

}

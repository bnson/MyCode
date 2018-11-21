/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sps.app.service;

import com.sps.app.component.FieldComponent;
import com.sps.app.component.ManagementComponent;
import com.sps.app.component.TablePrePrefilledComponent;
import com.sps.app.model.PrePrefilled;
import com.sps.app.object.FormStar;
import com.sps.app.utilities.utils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MainService {

    @Autowired
    FieldComponent fieldComponent;
    @Autowired
    TablePrePrefilledComponent tablePrePrefilledComponent;
    @Autowired
    ManagementComponent managementComponent;

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(MainService.class);

    @Value("${app.scheduled.fixed-delay}")
    private String scheduledFixedDelay;
    @Value("${app.xml.folder}")
    private String xmlFolder;
    @Value("${app.xml.folder.done}")
    private String xmlFolderDone;

    private String statusMessages = "";

    @PostConstruct
    @Scheduled(fixedDelayString = "${app.scheduled.fixed-delay}")
    public void init() {
        try {
            Path sourceOcrFolder = Paths.get(xmlFolder);
            Files.walk(sourceOcrFolder, 1)
                    .filter(p -> p.toString().endsWith(".xml"))
                    .map(p -> p.toString())
                    .forEach((String pathXmlFile) -> {
                        try {
                            statusMessages = "Process: " + pathXmlFile;
                            LOGGER.info(statusMessages);
                            System.out.print(statusMessages);
                            processOcrXmlFile(pathXmlFile);
                        } catch (JAXBException | IOException ex) {
                            LOGGER.error(ex);
                        }
                    });

            if (!statusMessages.contains("Sleeping")) {
                statusMessages = "Process: Sleeping(" + (Long.parseLong(scheduledFixedDelay) / 1000) + "s)...";
                System.out.println(statusMessages);
            }
        } catch (IOException ex) {
            LOGGER.error(ex);
        }
    }

    private void processOcrXmlFile(String pathXmlFile) throws JAXBException, IOException {

        //-- GET LIST FIELD FROM DATABASE.
        List<String> fieldNameList = fieldComponent.findAllFieldName();
        //fieldNames.forEach(System.out::println);

        JAXBContext jaxbContext = JAXBContext.newInstance(FormStar.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        File xmlFile = new File(pathXmlFile);
        FormStar formStar = (FormStar) jaxbUnmarshaller.unmarshal(xmlFile);

        formStar.getApplikation().getBatch().dokument.forEach((dok) -> {
            String xmlFileName = dok.getName(); //Image name
            tablePrePrefilledComponent.deleteByFileName(xmlFileName);

            dok.getField().forEach((filed) -> {
                String xmlFieldName = filed.getName();

                if (xmlFieldName.equalsIgnoreCase("Frau_L1")) {
                    xmlFieldName = "Anreder";
                }

                if (fieldNameList.contains(xmlFieldName)) {
                    String xmlOcrValue = filed.getValue();
                    String xmlCaptureValue = filed.getValue();
                    String[] xmlPosition = filed.getZone().split(" ", -1);
                    int xmlPosX = Integer.parseInt(xmlPosition[0]);
                    int xmlPosY = Integer.parseInt(xmlPosition[1]);
                    int xmlPosW = Integer.parseInt(xmlPosition[2]);
                    int xmlPosH = Integer.parseInt(xmlPosition[3]);

                    tablePrePrefilledComponent.save(new PrePrefilled(xmlFileName, xmlFieldName, xmlOcrValue, xmlCaptureValue, xmlPosX, xmlPosY, xmlPosW, xmlPosH, "", utils.getTimestampCurrent()));
                }

            });
        });

        File xmlFileDone = new File(xmlFolderDone + File.separator + xmlFile.getName());
        if (xmlFileDone.exists()) {
            FileUtils.deleteQuietly(xmlFileDone);
        }
        FileUtils.moveFile(xmlFile, xmlFileDone);

    }

}

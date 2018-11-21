/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sps.app.utilities;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author bnson
 */
public final class utils {

    public static BufferedImage cropImage(String imageFile, int x, int y, int width, int height) {
        BufferedImage croppedImage = null;
        System.out.println("-- Crop Image: " + imageFile);
        try {
            BufferedImage original = ImageIO.read(new File(imageFile));

            //System.out.println("Image Original size: " + original.getWidth() + "x " + original.getHeight());
            //System.out.println("Crop size: " + x + " " + y + " " + width + " " + height);
            if (x + width > original.getWidth()) {
                return null;
            }

            croppedImage = original.getSubimage(x, y, width, height);
        } catch (IOException ex) {
            Logger.getLogger(utils.class.getName()).log(Level.SEVERE, null, ex);
        }

        return croppedImage;
    }

    public static BufferedImage cropImage(BufferedImage bufferedImage, int x, int y, int width, int height) {
        BufferedImage croppedImage = bufferedImage.getSubimage(x, y, width, height);
        return croppedImage;
    }

    public static String encodeToString(BufferedImage image, String type) {
        String base64 = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        if (image != null) {
            try {
                ImageIO.write(image, type, baos);
                baos.flush();
                byte[] imageInByte = baos.toByteArray();
                baos.close();
                base64 = Base64.encodeBase64String(imageInByte);
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }
        }

        return base64;
    }

    public static Timestamp getTimestampCurrent() {
        Date date = new Date();
        long time = date.getTime();
        return new Timestamp(time);
    }

    public static String getOperatingSystemName() {
        String operatingSystemName = System.getProperty("os.name").toLowerCase();

        if (operatingSystemName.contains("win")) {
            operatingSystemName = "windows";
        }
        if (operatingSystemName.contains("nix") || operatingSystemName.contains("nux") || operatingSystemName.contains("aix")) {
            operatingSystemName = "linux";
        }
        if (operatingSystemName.contains("mac")) {
            operatingSystemName = "mac";
        }
        if (operatingSystemName.contains("sunos")) {
            operatingSystemName = "solaris";
        }

        return operatingSystemName;
    }

}

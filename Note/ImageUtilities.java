/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.st.image;

import com.sun.media.jai.codec.ByteArraySeekableStream;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageDecoder;
import com.sun.media.jai.codec.SeekableStream;
import java.awt.image.RenderedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.jai.PlanarImage;
import java.awt.Image;
import java.io.File;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.swing.JOptionPane;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author bnson
 */
public class ImageUtilities {
 
    public static RenderedImage readImageToRenderedImage(String pathImage) {
        RenderedImage renderedImage = null;
        try {
            FileInputStream in = new FileInputStream(pathImage);
            FileChannel channel = in.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate((int)channel.size());
            channel.read(buffer);
            SeekableStream stream = new ByteArraySeekableStream(buffer.array());
            String[] names = ImageCodec.getDecoderNames(stream);
            ImageDecoder dec = ImageCodec.createImageDecoder(names[0], stream, null);            
            renderedImage = dec.decodeAsRenderedImage();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
        }
        return renderedImage;
    }

    public static PlanarImage readImageToPlanarImage(String fileName, String imgType, int page) throws IOException, Exception {
        imgType = imgType.toUpperCase();
        switch (imgType) {
            case "JPEG":
                return PlanarImage.wrapRenderedImage(ImageIO.read(new File(fileName)));
            case "PDF":
                throw new Exception("Not support this image type");
            case "TIF":
            case "TIFF":
                if (page > 1) {
                    
                } else {
                    while (true) {
                        try {
                            return JAI.create("fileload", fileName);
                        } catch (Throwable e) {
                            try {
                                TimeUnit.SECONDS.sleep(1);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace(System.out);
                            }
                            if (e.getMessage().equalsIgnoreCase("Java Heap Space")) {
                                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                                System.exit(0);
                            }
                            e.printStackTrace(System.out);
                        }
                    }
                }
            default:
                throw new Exception("Not support this image type");
        }
    }
    
    public static int getTotalPageOfImage(String pathImage) {
        int totalPage = 1;
        try {
            FileInputStream in = new FileInputStream(pathImage);
            FileChannel channel = in.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate((int)channel.size());
            channel.read(buffer);
            SeekableStream stream = new ByteArraySeekableStream(buffer.array());
            String[] names = ImageCodec.getDecoderNames(stream);
            ImageDecoder dec = ImageCodec.createImageDecoder(names[0], stream, null);            
            totalPage = dec.getNumPages();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalPage;
    }    
    
    
    public static Image convertRenderedImageToImage(RenderedImage renderedImage) {
        Image image = PlanarImage.wrapRenderedImage(renderedImage).getAsBufferedImage();
        return image;
    }    
    
    public static String getExtension(String pathImage) {
        String extension = FilenameUtils.getExtension(pathImage);
        return extension;
    }
}

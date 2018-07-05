    public Run() {
        initComponents();
        String imageTifHeader = "O:\\bnson\\project\\puc_925xxx\\form\\SPS_Wallpaper_1920x1080.jpg";
        Image image = new Image();
        image.load(imageTifHeader);
        scrImage.setViewportView(image);
    }
    
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SourceCode.Application;

import com.sun.media.jai.widget.DisplayJAI;
import com.twelvemonkeys.imageio.metadata.CompoundDirectory;
import com.twelvemonkeys.imageio.metadata.Directory;
import com.twelvemonkeys.imageio.metadata.Entry;
import com.twelvemonkeys.imageio.metadata.exif.EXIFReader;
import com.twelvemonkeys.imageio.metadata.jpeg.JPEG;
import com.twelvemonkeys.imageio.metadata.jpeg.JPEGSegment;
import com.twelvemonkeys.imageio.metadata.jpeg.JPEGSegmentUtil;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author bnson
 */
public class Image extends DisplayJAI {
    private String pathImage;
    private String extension;
    private int totalPage;   
    private int pageCurrent;
    private int width;
    private int height;
    private CompoundDirectory exif;

    private BufferedImage bufferedImageRoot;
    private BufferedImage bufferedImageProcess;
    

    public Image() {
    }

    public void load(String pathImage) {

        this.pathImage = pathImage;
        this.extension = FilenameUtils.getExtension(pathImage).toLowerCase();
        this.pageCurrent = 0;

        try (ImageInputStream input = ImageIO.createImageInputStream(new File(this.pathImage))) {
            Iterator<ImageReader> readers = ImageIO.getImageReaders(input);
            if (!readers.hasNext()) {
                throw new IllegalArgumentException("No reader for: " + this.pathImage);
            }
            ImageReader reader = readers.next();
            try {
                // Optionally, listen for read warnings, progress, etc.
                reader.setInput(input);
                // Optionally, control read settings like sub sampling, source region or destination etc.
                ImageReadParam param = reader.getDefaultReadParam();
                // Finally read the image, using settings from param
                bufferedImageRoot = reader.read(0, param);
                bufferedImageProcess = bufferedImageRoot;
                // Optionally, read thumbnails, meta data, etc...
                totalPage = reader.getNumImages(true);
                width = reader.getWidth(this.pageCurrent);
                height = reader.getHeight(this.pageCurrent);

                readEXIF();
                printInfor();

                set(bufferedImageProcess);

            } finally {
                // Dispose reader in finally block to avoid memory leaks
                reader.dispose();
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }

    }

    private void readEXIF() {
        try (ImageInputStream input = ImageIO.createImageInputStream(new File(this.pathImage))) {
            switch (extension) {
                case "jpeg":
                case "jpg":
                    List<JPEGSegment> exifSegment = JPEGSegmentUtil.readSegments(input, JPEG.APP1, "Exif");
                    InputStream exifData = exifSegment.get(0).data();
                    exifData.read(); // Skip 0-pad for Exif in JFIF
                    exif = (CompoundDirectory) new EXIFReader().read(ImageIO.createImageInputStream(exifData));
                    break;
                case "tif":
                case "tiff":
                    exif = (CompoundDirectory) new EXIFReader().read(ImageIO.createImageInputStream(new File(this.pathImage)));
                    break;
                default:
                    System.out.println("Not support read EXIF.");
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    private void printInfor() {
        System.out.println("===========================");
        System.out.println("Path image: " + pathImage);
        System.out.println("-- Extension: " + extension);
        System.out.println("-- Total page: " + totalPage);
        System.out.println("-- Page current: " + pageCurrent);
        System.out.println("-- Width: " + width);
        System.out.println("-- Height: " + height);        
        
        System.out.println("===========================\nEXIF");        
        for (int i = 0; i < exif.directoryCount(); i++) {
            Directory directory = exif.getDirectory(i);
            for (Entry entry : directory) {
                System.out.println(entry);
                //Object value = entry.getValue();
                //System.out.println(value.toString());
            }
        }
    }
    
}

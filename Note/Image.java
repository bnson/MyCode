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
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
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
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author bnson
 */
public class Image extends DisplayJAI implements ImageInterface, MouseListener, MouseWheelListener, MouseMotionListener {

    private String pathImage;
    private String extension;
    private int totalPage;
    private int pageCurrent;
    private int width;
    private int height;
    private CompoundDirectory exif;

    private BufferedImage bufImageRoot;
    private BufferedImage bufImageProcess;

    private int zoomImage = 100;
    private final int zoomUnit = 3;

    private Point startPoint;
    private Point endPoint;

    private String KeyPressed;

    private ItemRectangle itemRectangle;
    
    private ImageInterfaceEvent iiEvent;
    
    public Image() {
        super.setAutoscrolls(true);
        super.addMouseListener(Image.this);
        super.addMouseMotionListener(Image.this);
        super.addMouseWheelListener(Image.this);
    }

    public void load(String pathImage) {

        this.pathImage = pathImage;
        this.extension = FilenameUtils.getExtension(pathImage).toLowerCase();
        this.pageCurrent = 0;
        this.KeyPressed = "";
        this.itemRectangle = new ItemRectangle();

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
                bufImageRoot = reader.read(0, param);
                bufImageProcess = bufImageRoot;
                // Optionally, read thumbnails, meta data, etc...
                totalPage = reader.getNumImages(true);
                width = reader.getWidth(this.pageCurrent);
                height = reader.getHeight(this.pageCurrent);

                readEXIF();
                printImageInfo();

                set(bufImageProcess);

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

    @Override
    public void printImageInfo() {
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

    @Override
    public void setZoomIn() {
        System.out.println("-- Zoom In");
        if (bufImageRoot != null) {
            if (bufImageProcess != null) {
                bufImageProcess.getGraphics().dispose();
                bufImageProcess = null;
                System.gc();
            }

            bufImageProcess = ImageUtil.zoomBufferedImage(bufImageRoot, zoomImage += zoomUnit);
            set(bufImageProcess);
            repaint();
        }
    }

    @Override
    public void setZoomOut() {
        System.out.println("-- Zoom Out");
        if (bufImageRoot != null) {
            JViewport viewPort = (JViewport) SwingUtilities.getAncestorOfClass(JViewport.class, Image.this);
            if (viewPort.getViewRect().width < getPreferredSize().width
                    || viewPort.getViewRect().height < getPreferredSize().height) {
                if (bufImageProcess != null) {
                    bufImageProcess.getGraphics().dispose();
                    bufImageProcess = null;
                    System.gc();
                }
                bufImageProcess = ImageUtil.zoomBufferedImage(bufImageRoot, zoomImage -= zoomUnit);
                set(bufImageProcess);
                repaint();
            }
        }
    }

    public void drawPerfectRect(Graphics g) {
        System.out.println("-- Draw Perfect Rect");
        if (startPoint != null && endPoint != null && KeyPressed.equals("CONTROL")) {
            int px = Math.min(startPoint.x, endPoint.x);
            int py = Math.min(startPoint.y, endPoint.y);
            int pw = Math.abs(startPoint.x - endPoint.x)*zoomImage/100;
            int ph = Math.abs(startPoint.y - endPoint.y)*zoomImage/100;
            g.drawRect(px, py, pw, ph);
            itemRectangle.startPoint = startPoint;
            itemRectangle.endPoint = endPoint;
        } else {
            if (itemRectangle != null && itemRectangle.startPoint != null && itemRectangle.endPoint != null) {
                int px = Math.min(itemRectangle.startPoint.x, itemRectangle.endPoint.x);
                int py = Math.min(itemRectangle.startPoint.y, itemRectangle.endPoint.y);
                int pw = Math.abs(itemRectangle.startPoint.x - itemRectangle.endPoint.x)*zoomImage/100;
                int ph = Math.abs(itemRectangle.startPoint.y - itemRectangle.endPoint.y)*zoomImage/100;
                g.drawRect(px, py, pw, ph);
            }
        }
        
        if (iiEvent != null) {
            iiEvent.doDrawItemRectangle(itemRectangle);
        }
    }
    
    public void addImageInterfaceEvent(ImageInterfaceEvent iiEvent) {
        this.iiEvent = iiEvent;
    }
    
    //==================================================================

//    @Override
//    public synchronized void paintComponent(Graphics g) {
//        super.paintComponent(g); 
//        g.setColor(Color.RED);
//        drawPerfectRect(g);
//    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); 
        g.setColor(Color.RED);
        drawPerfectRect(g);        
    }

    @Override
    public void repaint() {
        super.repaint(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    

    //==================================================================
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        System.out.println("-- Mouse Wheel Moved");
        if (e.getWheelRotation() == -1) {
            setZoomIn();
        } else {
            setZoomOut();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        System.out.println("-- Mouse Pressed");
        startPoint = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        System.out.println("-- Mouse Released");
        endPoint = e.getPoint();
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        System.out.println("-- mouseDragged");
        endPoint = e.getPoint();
        
        if (startPoint != null) {
            JViewport viewPort = (JViewport) SwingUtilities.getAncestorOfClass(JViewport.class, Image.this);
            if (viewPort != null) {
                int deltaX = startPoint.x - e.getX();
                int deltaY = startPoint.y - e.getY();
                Rectangle view = viewPort.getViewRect();
                view.x += deltaX;
                view.y += deltaY;
                Image.this.scrollRectToVisible(view);
            }
        }
        repaint();
    }

    //==================================================================
    @Override
    public void setKeyPressed(String KeyPressed) {
        this.KeyPressed = KeyPressed;
        System.out.println("Image key pressed: " + this.KeyPressed);
    }

    @Override
    public ItemRectangle getItemRectangle() {
        return itemRectangle;
    }

    @Override
    public void setItemRectangle(ItemRectangle itemRectangle) {
        this.itemRectangle = itemRectangle;
    }
    
    

}
=========================================================================
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SourceCode.Application;

/**
 *
 * @author bnson
 */
public interface ImageInterface {
    public void printImageInfo();
    public void setZoomIn();
    public void setZoomOut();

    public void setKeyPressed(String KeyPressed);
    public ItemRectangle getItemRectangle();
    public void setItemRectangle(ItemRectangle itemRectangle);
}
=========================================================================
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SourceCode.Application;

/**
 *
 * @author bnson
 */
public interface ImageInterfaceEvent {
 
    public void doDrawItemRectangle(ItemRectangle itemRectangle);
    
}
=========================================================================
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SourceCode.Application;

import java.awt.image.BufferedImage;
import javax.media.jai.PlanarImage;
import org.imgscalr.Scalr;

/**
 *
 * @author bnson
 */
public class ImageUtil {
    
    public static PlanarImage zoomPlanarImage(BufferedImage img, int value) {
        PlanarImage result = PlanarImage.wrapRenderedImage(Scalr.resize(img, Scalr.Method.SPEED, Math.max(img.getHeight(), img.getWidth()) * value / 100, Scalr.OP_ANTIALIAS));                
        System.gc();
        return result;
    }
    
    public static PlanarImage zoomPlanarImage(PlanarImage img, int value) {
        PlanarImage result = PlanarImage.wrapRenderedImage(Scalr.resize(img.getAsBufferedImage(), Scalr.Method.SPEED, Math.max(img.getHeight(), img.getWidth()) * value / 100, Scalr.OP_ANTIALIAS));                
        System.gc();
        return (result);
    }    
    
    public static BufferedImage zoomBufferedImage(BufferedImage img, int value) {
        BufferedImage result = Scalr.resize(img, Scalr.Method.SPEED, Math.max(img.getHeight(), img.getWidth()) * value / 100, Scalr.OP_ANTIALIAS);
        System.gc();
        return result;
    }    

}
========================================================
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SourceCode.Application;

import java.awt.Point;

/**
 *
 * @author bnson
 */
public class ItemRectangle {
    public int page;
    public Point startPoint, endPoint;
    
    public ItemRectangle() {
        
    }
    
    public ItemRectangle(int page, Point startPoint, Point endPoint) {
        this.page = page;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }    

    @Override
    public String toString() {
        String str = "";
        if (startPoint!=null && endPoint!=null) {
            str = String.valueOf(page) + ";" + String.valueOf(startPoint.x) + ";" + String.valueOf(startPoint.y) + ";" + String.valueOf(endPoint.x) + ";" + String.valueOf(endPoint.y); 
        }
        return str;
    }

}
==================================================
package SourceCode.Application;

/*
 * Copyright 2000,2005 wingS development team.
 *
 * This file is part of wingS (http://wingsframework.org).
 *
 * wingS is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 *
 * Please see COPYING for the complete licence.
 */
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;


/**
 * This source code was taken from a code example at http://javaalmanac.com/ and
 * slightly altered to fit our purpose.
 *
 * @author ole
 *
 */
public class KeystrokeUtil {

    public static String keyStroke2String(KeyEvent key) {
        if (key == null) {
            return "";
        }
        StringBuilder s = new StringBuilder(50);
        int m = key.getModifiers();

        if ((m & (InputEvent.CTRL_DOWN_MASK | InputEvent.CTRL_MASK)) != 0) {
            s.append("Ctrl+");
        }
        if ((m & (InputEvent.META_DOWN_MASK | InputEvent.META_MASK)) != 0) {
            s.append("Meta+");
        }
        if ((m & (InputEvent.ALT_DOWN_MASK | InputEvent.ALT_MASK)) != 0) {
            s.append("Alt+");
        }
        if ((m & (InputEvent.SHIFT_DOWN_MASK | InputEvent.SHIFT_MASK)) != 0) {
            s.append("Shift+");
        }

        if (getKeyText(key.getKeyCode()).equals("CONTROL") 
                || getKeyText(key.getKeyCode()).equals("SHIFT")
                || getKeyText(key.getKeyCode()).equals("ALT")) {
            s.setLength(0);
        } 
        s.append(getKeyText(key.getKeyCode()));

        return s.toString();
    }

    public static String getKeyText(int keyCode) {
        if (keyCode >= KeyEvent.VK_0 && keyCode <= KeyEvent.VK_9
                || keyCode >= KeyEvent.VK_A && keyCode <= KeyEvent.VK_Z) {
            return String.valueOf((char) keyCode);
        }

        switch (keyCode) {
            case KeyEvent.VK_COMMA:
                return "COMMA";
            case KeyEvent.VK_PERIOD:
                return "PERIOD";
            case KeyEvent.VK_SLASH:
                return "SLASH";
            case KeyEvent.VK_SEMICOLON:
                return "SEMICOLON";
            case KeyEvent.VK_EQUALS:
                return "EQUALS";
            case KeyEvent.VK_OPEN_BRACKET:
                return "OPEN_BRACKET";
            case KeyEvent.VK_BACK_SLASH:
                return "BACK_SLASH";
            case KeyEvent.VK_CLOSE_BRACKET:
                return "CLOSE_BRACKET";

            case KeyEvent.VK_ENTER:
                return "ENTER";
            case KeyEvent.VK_BACK_SPACE:
                return "BACK_SPACE";
            case KeyEvent.VK_TAB:
                return "TAB";
            case KeyEvent.VK_CANCEL:
                return "CANCEL";
            case KeyEvent.VK_CLEAR:
                return "CLEAR";
            case KeyEvent.VK_SHIFT:
                return "SHIFT";
            case KeyEvent.VK_CONTROL:
                return "CONTROL";
            case KeyEvent.VK_ALT:
                return "ALT";
            case KeyEvent.VK_PAUSE:
                return "PAUSE";
            case KeyEvent.VK_CAPS_LOCK:
                return "CAPS_LOCK";
            case KeyEvent.VK_ESCAPE:
                return "ESCAPE";
            case KeyEvent.VK_SPACE:
                return "SPACE";
            case KeyEvent.VK_PAGE_UP:
                return "PAGE_UP";
            case KeyEvent.VK_PAGE_DOWN:
                return "PAGE_DOWN";
            case KeyEvent.VK_END:
                return "END";
            case KeyEvent.VK_HOME:
                return "HOME";
            case KeyEvent.VK_LEFT:
                return "LEFT";
            case KeyEvent.VK_UP:
                return "UP";
            case KeyEvent.VK_RIGHT:
                return "RIGHT";
            case KeyEvent.VK_DOWN:
                return "DOWN";

            // numpad numeric keys handled below
            case KeyEvent.VK_MULTIPLY:
                return "MULTIPLY";
            case KeyEvent.VK_ADD:
                return "ADD";
            case KeyEvent.VK_SEPARATOR:
                return "SEPARATOR";
            case KeyEvent.VK_SUBTRACT:
                return "SUBTRACT";
            case KeyEvent.VK_DECIMAL:
                return "DECIMAL";
            case KeyEvent.VK_DIVIDE:
                return "DIVIDE";
            case KeyEvent.VK_DELETE:
                return "DELETE";
            case KeyEvent.VK_NUM_LOCK:
                return "NUM_LOCK";
            case KeyEvent.VK_SCROLL_LOCK:
                return "SCROLL_LOCK";

            case KeyEvent.VK_F1:
                return "F1";
            case KeyEvent.VK_F2:
                return "F2";
            case KeyEvent.VK_F3:
                return "F3";
            case KeyEvent.VK_F4:
                return "F4";
            case KeyEvent.VK_F5:
                return "F5";
            case KeyEvent.VK_F6:
                return "F6";
            case KeyEvent.VK_F7:
                return "F7";
            case KeyEvent.VK_F8:
                return "F8";
            case KeyEvent.VK_F9:
                return "F9";
            case KeyEvent.VK_F10:
                return "F10";
            case KeyEvent.VK_F11:
                return "F11";
            case KeyEvent.VK_F12:
                return "F12";
            case KeyEvent.VK_F13:
                return "F13";
            case KeyEvent.VK_F14:
                return "F14";
            case KeyEvent.VK_F15:
                return "F15";
            case KeyEvent.VK_F16:
                return "F16";
            case KeyEvent.VK_F17:
                return "F17";
            case KeyEvent.VK_F18:
                return "F18";
            case KeyEvent.VK_F19:
                return "F19";
            case KeyEvent.VK_F20:
                return "F20";
            case KeyEvent.VK_F21:
                return "F21";
            case KeyEvent.VK_F22:
                return "F22";
            case KeyEvent.VK_F23:
                return "F23";
            case KeyEvent.VK_F24:
                return "F24";

            case KeyEvent.VK_PRINTSCREEN:
                return "PRINTSCREEN";
            case KeyEvent.VK_INSERT:
                return "INSERT";
            case KeyEvent.VK_HELP:
                return "HELP";
            case KeyEvent.VK_META:
                return "META";
            case KeyEvent.VK_BACK_QUOTE:
                return "BACK_QUOTE";
            case KeyEvent.VK_QUOTE:
                return "QUOTE";

            case KeyEvent.VK_KP_UP:
                return "KP_UP";
            case KeyEvent.VK_KP_DOWN:
                return "KP_DOWN";
            case KeyEvent.VK_KP_LEFT:
                return "KP_LEFT";
            case KeyEvent.VK_KP_RIGHT:
                return "KP_RIGHT";

            case KeyEvent.VK_DEAD_GRAVE:
                return "DEAD_GRAVE";
            case KeyEvent.VK_DEAD_ACUTE:
                return "DEAD_ACUTE";
            case KeyEvent.VK_DEAD_CIRCUMFLEX:
                return "DEAD_CIRCUMFLEX";
            case KeyEvent.VK_DEAD_TILDE:
                return "DEAD_TILDE";
            case KeyEvent.VK_DEAD_MACRON:
                return "DEAD_MACRON";
            case KeyEvent.VK_DEAD_BREVE:
                return "DEAD_BREVE";
            case KeyEvent.VK_DEAD_ABOVEDOT:
                return "DEAD_ABOVEDOT";
            case KeyEvent.VK_DEAD_DIAERESIS:
                return "DEAD_DIAERESIS";
            case KeyEvent.VK_DEAD_ABOVERING:
                return "DEAD_ABOVERING";
            case KeyEvent.VK_DEAD_DOUBLEACUTE:
                return "DEAD_DOUBLEACUTE";
            case KeyEvent.VK_DEAD_CARON:
                return "DEAD_CARON";
            case KeyEvent.VK_DEAD_CEDILLA:
                return "DEAD_CEDILLA";
            case KeyEvent.VK_DEAD_OGONEK:
                return "DEAD_OGONEK";
            case KeyEvent.VK_DEAD_IOTA:
                return "DEAD_IOTA";
            case KeyEvent.VK_DEAD_VOICED_SOUND:
                return "DEAD_VOICED_SOUND";
            case KeyEvent.VK_DEAD_SEMIVOICED_SOUND:
                return "DEAD_SEMIVOICED_SOUND";

            case KeyEvent.VK_AMPERSAND:
                return "AMPERSAND";
            case KeyEvent.VK_ASTERISK:
                return "ASTERISK";
            case KeyEvent.VK_QUOTEDBL:
                return "QUOTEDBL";
            case KeyEvent.VK_LESS:
                return "LESS";
            case KeyEvent.VK_GREATER:
                return "GREATER";
            case KeyEvent.VK_BRACELEFT:
                return "BRACELEFT";
            case KeyEvent.VK_BRACERIGHT:
                return "BRACERIGHT";
            case KeyEvent.VK_AT:
                return "AT";
            case KeyEvent.VK_COLON:
                return "COLON";
            case KeyEvent.VK_CIRCUMFLEX:
                return "CIRCUMFLEX";
            case KeyEvent.VK_DOLLAR:
                return "DOLLAR";
            case KeyEvent.VK_EURO_SIGN:
                return "EURO_SIGN";
            case KeyEvent.VK_EXCLAMATION_MARK:
                return "EXCLAMATION_MARK";
            case KeyEvent.VK_INVERTED_EXCLAMATION_MARK:
                return "INVERTED_EXCLAMATION_MARK";
            case KeyEvent.VK_LEFT_PARENTHESIS:
                return "LEFT_PARENTHESIS";
            case KeyEvent.VK_NUMBER_SIGN:
                return "NUMBER_SIGN";
            case KeyEvent.VK_MINUS:
                return "MINUS";
            case KeyEvent.VK_PLUS:
                return "PLUS";
            case KeyEvent.VK_RIGHT_PARENTHESIS:
                return "RIGHT_PARENTHESIS";
            case KeyEvent.VK_UNDERSCORE:
                return "UNDERSCORE";

            case KeyEvent.VK_FINAL:
                return "FINAL";
            case KeyEvent.VK_CONVERT:
                return "CONVERT";
            case KeyEvent.VK_NONCONVERT:
                return "NONCONVERT";
            case KeyEvent.VK_ACCEPT:
                return "ACCEPT";
            case KeyEvent.VK_MODECHANGE:
                return "MODECHANGE";
            case KeyEvent.VK_KANA:
                return "KANA";
            case KeyEvent.VK_KANJI:
                return "KANJI";
            case KeyEvent.VK_ALPHANUMERIC:
                return "ALPHANUMERIC";
            case KeyEvent.VK_KATAKANA:
                return "KATAKANA";
            case KeyEvent.VK_HIRAGANA:
                return "HIRAGANA";
            case KeyEvent.VK_FULL_WIDTH:
                return "FULL_WIDTH";
            case KeyEvent.VK_HALF_WIDTH:
                return "HALF_WIDTH";
            case KeyEvent.VK_ROMAN_CHARACTERS:
                return "ROMAN_CHARACTERS";
            case KeyEvent.VK_ALL_CANDIDATES:
                return "ALL_CANDIDATES";
            case KeyEvent.VK_PREVIOUS_CANDIDATE:
                return "PREVIOUS_CANDIDATE";
            case KeyEvent.VK_CODE_INPUT:
                return "CODE_INPUT";
            case KeyEvent.VK_JAPANESE_KATAKANA:
                return "JAPANESE_KATAKANA";
            case KeyEvent.VK_JAPANESE_HIRAGANA:
                return "JAPANESE_HIRAGANA";
            case KeyEvent.VK_JAPANESE_ROMAN:
                return "JAPANESE_ROMAN";
            case KeyEvent.VK_KANA_LOCK:
                return "KANA_LOCK";
            case KeyEvent.VK_INPUT_METHOD_ON_OFF:
                return "INPUT_METHOD_ON_OFF";

            case KeyEvent.VK_AGAIN:
                return "AGAIN";
            case KeyEvent.VK_UNDO:
                return "UNDO";
            case KeyEvent.VK_COPY:
                return "COPY";
            case KeyEvent.VK_PASTE:
                return "PASTE";
            case KeyEvent.VK_CUT:
                return "CUT";
            case KeyEvent.VK_FIND:
                return "FIND";
            case KeyEvent.VK_PROPS:
                return "PROPS";
            case KeyEvent.VK_STOP:
                return "STOP";

            case KeyEvent.VK_COMPOSE:
                return "COMPOSE";
            case KeyEvent.VK_ALT_GRAPH:
                return "ALT_GRAPH";
        }

        if (keyCode >= KeyEvent.VK_NUMPAD0 && keyCode <= KeyEvent.VK_NUMPAD9) {
            char c = (char) (keyCode - KeyEvent.VK_NUMPAD0 + '0');
            return "NUMPAD" + c;
        }

        return "unknown(0x" + Integer.toString(keyCode, 16) + ")";
    }
}
========================================

import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;

/**
 *
 * @author bnson
 */
public class Run extends javax.swing.JFrame implements AWTEventListener {

    private final Image image;
  
    /**
     * Creates new form Run
     */
    public Run() {
        initComponents();

        Toolkit.getDefaultToolkit().addAWTEventListener(Run.this, AWTEvent.KEY_EVENT_MASK);
        super.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        
        String imageTifHeader = "O:\\bnson\\project\\puc_925xxx\\form\\SPS_Wallpaper_1920x1080.jpg";
        image = new Image();
        image.load(imageTifHeader);
        scrImage.setViewportView(image);
        
        image.addImageInterfaceEvent((ItemRectangle itemRectangle) -> {
            System.out.println("--------------------------");
            tfItemRectPosition.setText(itemRectangle.toString());
        });
    }

    @Override
    public void eventDispatched(AWTEvent event) {
        KeyEvent keyEV = (KeyEvent) event;
        String[] keyParams = event.paramString().split(",");
        
        if (keyParams[0].equalsIgnoreCase("KEY_PRESSED")) {
            String keyPressed = KeystrokeUtil.keyStroke2String(keyEV);
            //System.out.println("-- Key pressed: " + keyPressed);
            if (image != null) {
                image.setKeyPressed(keyPressed);
            }
        }
        
        if (keyParams[0].equalsIgnoreCase("KEY_RELEASED")) {
            image.setKeyPressed("");
            //String keyReleased = KeystrokeUtil.keyStroke2String(keyEV);
            //System.out.println("-- Key released: " + keyReleased);
        }     
        
    }
    

    

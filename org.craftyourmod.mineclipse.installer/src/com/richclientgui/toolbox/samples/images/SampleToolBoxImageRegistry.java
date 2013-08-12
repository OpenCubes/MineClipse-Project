package com.richclientgui.toolbox.samples.images;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * A sample ImageRegistry that contains the images used in the CoolButton, CoolSlider & Progressindicator samples. 
 */
public class SampleToolBoxImageRegistry {
	private static final Logger logger = Logger.getLogger(SampleToolBoxImageRegistry.class.getPackage().getName());
	
	private static final Map<String, Image> IMAGE_MAP = new HashMap<String, Image>();
	private static final Map<String, Color> COLOR_MAP = new HashMap<String, Color>();
	
	/* SAMPLES*/
	public static final String IMG_FILLED_REGION = "IMG_FILLED_REGION";
	
	public static final String IMG_FILLED_REGION_VERTICAL = "IMG_FILLED_REGION_VERTICAL";
	
	public static final String IMG_PROGRESS_BAR_LEFT_BORDER = "IMG_PROGRESS_BAR_LEFT_BORDER";
	public static final String IMG_PROGRESS_BAR_FILLED = "IMG_PROGRESS_BAR_FILLED";
	public static final String IMG_PROGRESS_BAR_EMPTY = "IMG_PROGRESS_BAR_EMPTY";
	public static final String IMG_PROGRESS_BAR_RIGHT_BORDER = "IMG_PROGRESS_BAR_RIGHT_BORDER";
	
	public static final String IMG_PROGRESS_BAR_BORDER_VERTICAL = "IMG_PROGRESS_BAR_BORDER_VERTICAL";
	public static final String IMG_PROGRESS_BAR_FILLED_VERTICAL = "IMG_PROGRESS_BAR_FILLED_VERTICAL";
	public static final String IMG_PROGRESS_BAR_EMPTY_VERTICAL = "IMG_PROGRESS_BAR_EMPTY_VERTICAL";
	
	public static final String IMG_BUTTON_CHECKBOX_NORMAL = "IMG_BUTTON_CHECKBOX_NORMAL";
	public static final String IMG_BUTTON_CHECKBOX_HOVER = "IMG_BUTTON_CHECKBOX_HOVER";
	public static final String IMG_BUTTON_CHECKBOX_PRESSED = "IMG_BUTTON_CHECKBOX_PRESSED";
	public static final String IMG_BUTTON_CHECKBOX_NORMAL_TOGGLED = "IMG_BUTTON_CHECKBOX_NORMAL_TOGGLED";
	public static final String IMG_BUTTON_CHECKBOX_HOVER_TOGGLED = "IMG_BUTTON_CHECKBOX_HOVER_TOGGLED";
	public static final String IMG_BUTTON_CHECKBOX_PRESSED_TOGGLED = "IMG_BUTTON_CHECKBOX_PRESSED_TOGGLED";
	
	public static final String IMG_BUTTON_LEFT_NORMAL = "IMG_BUTTON_LEFT_NORMAL";
	public static final String IMG_BUTTON_LEFT_HOVER = "IMG_BUTTON_LEFT_HOVER";
	public static final String IMG_BUTTON_LEFT_PRESSED = "IMG_BUTTON_LEFT_PRESSED";
	
	public static final String IMG_BUTTON_RIGHT_NORMAL = "IMG_BUTTON_RIGHT_NORMAL";
	public static final String IMG_BUTTON_RIGHT_HOVER = "IMG_BUTTON_RIGHT_HOVER";
	public static final String IMG_BUTTON_RIGHT_PRESSED = "IMG_BUTTON_RIGHT_PRESSED";
	
	public static final String IMG_BUTTON_SQUARE_BLUE_NORMAL = "IMG_BUTTON_SQUARE_BLUE_NORMAL";
	public static final String IMG_BUTTON_SQUARE_BLUE_HOVER = "IMG_BUTTON_SQUARE_BLUE_HOVER";
	public static final String IMG_BUTTON_SQUARE_BLUE_PRESSED = "IMG_BUTTON_SQUARE_BLUE_PRESSED";
	
	public static final String IMG_BUTTON_CIRCLE_RED_NORMAL = "IMG_BUTTON_CIRCLE_RED_NORMAL";
	public static final String IMG_BUTTON_CIRCLE_RED_HOVER = "IMG_BUTTON_CIRCLE_RED_HOVER";
	public static final String IMG_BUTTON_CIRCLE_RED_PRESSED = "IMG_BUTTON_CIRCLE_RED_PRESSED";
	
	public static final String IMG_SLIDER_RIGHT_MOST = "IMG_SLIDER_RIGHT_MOST";
	public static final String IMG_SLIDER_RIGHT = "IMG_SLIDER_RIGHT";
	public static final String IMG_SLIDER_THUMB = "IMG_SLIDER_THUMB";
	public static final String IMG_SLIDER_THUMB_FOCUS = "IMG_SLIDER_THUMB_FOCUS";
	public static final String IMG_SLIDER_LEFT = "IMG_SLIDER_LEFT";
	public static final String IMG_SLIDER_LEFT_MOST = "IMG_SLIDER_LEFT_MOST";
	
	public static final String IMG_SLIDER_RIGHT_MOST_VERTICAL = "IMG_SLIDER_RIGHT_MOST_VERTICAL";
	public static final String IMG_SLIDER_RIGHT_VERTICAL = "IMG_SLIDER_RIGHT_VERTICAL";
	public static final String IMG_SLIDER_THUMB_VERTICAL = "IMG_SLIDER_THUMB_VERTICAL";
	public static final String IMG_SLIDER_LEFT_VERTICAL = "IMG_SLIDER_LEFT_VERTICAL";
	public static final String IMG_SLIDER_LEFT_MOST_VERTICAL = "IMG_SLIDER_LEFT_MOST_VERTICAL";
	
	public static final String IMG_SLIDER2_RIGHT_MOST = "IMG_SLIDER2_RIGHT_MOST";
	public static final String IMG_SLIDER2_RIGHT = "IMG_SLIDER2_RIGHT";
	public static final String IMG_SLIDER2_THUMB = "IMG_SLIDER2_THUMB";
	public static final String IMG_SLIDER2_LEFT = "IMG_SLIDER2_LEFT";
	public static final String IMG_SLIDER2_LEFT_MOST = "IMG_SLIDER2_LEFT_MOST";
	
	public static final String IMG_SLIDER2_RIGHT_MOST_VERTICAL = "IMG_SLIDER2_RIGHT_MOST_VERTICAL";
	public static final String IMG_SLIDER2_RIGHT_VERTICAL = "IMG_SLIDER2_RIGHT_VERTICAL";
	public static final String IMG_SLIDER2_THUMB_VERTICAL = "IMG_SLIDER2_THUMB_VERTICAL";
	public static final String IMG_SLIDER2_LEFT_VERTICAL = "IMG_SLIDER2_LEFT_VERTICAL";
	public static final String IMG_SLIDER2_LEFT_MOST_VERTICAL = "IMG_SLIDER2_LEFT_MOST_VERTICAL";
	
    public static final String IMG_INDICATOR_A_1 = "indicator_a_1.png";
    public static final String IMG_INDICATOR_A_2 = "indicator_a_2.png";
    public static final String IMG_INDICATOR_A_3 = "indicator_a_3.png";
    public static final String IMG_INDICATOR_A_4 = "indicator_a_4.png";
    public static final String IMG_INDICATOR_A_5 = "indicator_a_5.png";
    public static final String IMG_INDICATOR_A_6 = "indicator_a_6.png";
    public static final String IMG_INDICATOR_A_7 = "indicator_a_7.png";
    public static final String IMG_INDICATOR_A_8 = "indicator_a_8.png";
    
    public static final String IMG_INDICATOR_B_1 = "indicator_b_1.png";
    public static final String IMG_INDICATOR_B_2 = "indicator_b_2.png";
    public static final String IMG_INDICATOR_B_3 = "indicator_b_3.png";
    public static final String IMG_INDICATOR_B_4 = "indicator_b_4.png";
    public static final String IMG_INDICATOR_B_5 = "indicator_b_5.png";
    public static final String IMG_INDICATOR_B_6 = "indicator_b_6.png";
    public static final String IMG_INDICATOR_B_7 = "indicator_b_7.png";
    public static final String IMG_INDICATOR_B_8 = "indicator_b_8.png";
    
    public static final String IMG_INDICATOR_C_1 = "indicator_c_1.png";
    public static final String IMG_INDICATOR_C_2 = "indicator_c_2.png";
    public static final String IMG_INDICATOR_C_3 = "indicator_c_3.png";
    public static final String IMG_INDICATOR_C_4 = "indicator_c_4.png";
    public static final String IMG_INDICATOR_C_5 = "indicator_c_5.png";
    public static final String IMG_INDICATOR_C_6 = "indicator_c_6.png";
    public static final String IMG_INDICATOR_C_7 = "indicator_c_7.png";
    public static final String IMG_INDICATOR_C_8 = "indicator_c_8.png";
    
    public static final String IMG_INDICATOR_D_1 = "indicator_d_1.png";
    public static final String IMG_INDICATOR_D_2 = "indicator_d_2.png";
    public static final String IMG_INDICATOR_D_3 = "indicator_d_3.png";
    public static final String IMG_INDICATOR_D_4 = "indicator_d_4.png";
    public static final String IMG_INDICATOR_D_5 = "indicator_d_5.png";
    public static final String IMG_INDICATOR_D_6 = "indicator_d_6.png";
    public static final String IMG_INDICATOR_D_7 = "indicator_d_7.png";
    public static final String IMG_INDICATOR_D_8 = "indicator_d_8.png";
    public static final String IMG_INDICATOR_D_9 = "indicator_d_9.png";
    public static final String IMG_INDICATOR_D_10 = "indicator_d_10.png";
    public static final String IMG_INDICATOR_D_11 = "indicator_d_11.png";
    public static final String IMG_INDICATOR_D_12 = "indicator_d_12.png";
    
    public static final String IMG_GAUGE_CIRCLE_4 = "gauge_circle4.png";
    public static final String IMG_GAUGE_CIRCLE_4_PIVOT = "gauge_circle4_pivot.png";
    
    public static final String IMG_GAUGE_TOP_HALF_CIRCLE = "gauge_hhalf_circle_1.png";
    public static final String IMG_GAUGE_ORANGE_PIVOT = "pivot.png";
    
    public static final String IMG_GAUGE_LEFT_HALF_CIRCLE = "gauge_vhalf_circle_1.png";
    
    public static final String CURSOR_UP = "CURSOR_UP";
	public static final String CURSOR_DOWN = "CURSOR_DOWN";
	
	public static final String COLOR_DARK_SLIDER = "COLOR_DARK_SLIDER";

    public static final String IMG_BLUE_48 = "IMG_BLUE_48";
    public static final String IMG_BLUE_24 = "IMG_BLUE_24";
    public static final String IMG_BLUE_16 = "IMG_BLUE_16";
    
    public static final String IMG_RED_48 = "IMG_RED_48";   
    public static final String IMG_RED_24 = "IMG_RED_24";
    public static final String IMG_RED_16 = "IMG_RED_16";
    
    public static final String IMG_GREEN_48 = "IMG_GREEN_48";
    public static final String IMG_GREEN_24 = "IMG_GREEN_24";
    public static final String IMG_GREEN_16 = "IMG_GREEN_16";
    
	public static final String IMG_BUTTON_CHECKBOX_HOT_SPOT = "IMG_BUTTON_CHECKBOX_HOT_SPOT";
    public static final String IMG_BUTTON_HOT_SPOT_TOGGLED = "IMG_BUTTON_CHECKBOX_HOT_SPOT_TOGGLED";
    
    public static final String IMG_BUTTON_PUSH_RIGHT_HOT_REGION = "IMG_BUTTON_PUSH_RIGHT_HOT_REGION";
		
	static {
		/** SAMPLES*/
		
		IMAGE_MAP.put(IMG_BUTTON_CHECKBOX_NORMAL, loadImage("checkbox_normal.png"));
		IMAGE_MAP.put(IMG_BUTTON_CHECKBOX_HOVER, loadImage("checkbox_hover.png"));
		IMAGE_MAP.put(IMG_BUTTON_CHECKBOX_PRESSED, loadImage("checkbox_push.png"));
		IMAGE_MAP.put(IMG_BUTTON_CHECKBOX_NORMAL_TOGGLED, loadImage("checkbox_normal_toggled.png"));
		IMAGE_MAP.put(IMG_BUTTON_CHECKBOX_HOVER_TOGGLED, loadImage("checkbox_hover_toggled.png"));
		IMAGE_MAP.put(IMG_BUTTON_CHECKBOX_PRESSED_TOGGLED, loadImage("checkbox_push_toggled.png"));
        IMAGE_MAP.put(IMG_BUTTON_CHECKBOX_HOT_SPOT, loadImage("checkbox_normal_hot_spot.png"));
        IMAGE_MAP.put(IMG_BUTTON_HOT_SPOT_TOGGLED, loadImage("checkbox_hot_spot_toggled.png"));
				
		IMAGE_MAP.put(IMG_BUTTON_SQUARE_BLUE_NORMAL, loadImage("button3_24_normal.png"));
		IMAGE_MAP.put(IMG_BUTTON_SQUARE_BLUE_HOVER, loadImage("button3_24_hover.png"));
		IMAGE_MAP.put(IMG_BUTTON_SQUARE_BLUE_PRESSED, loadImage("button3_24_pressed.png"));
		
		IMAGE_MAP.put(IMG_BUTTON_LEFT_NORMAL, loadImage("left_button_normal.png"));
		IMAGE_MAP.put(IMG_BUTTON_LEFT_HOVER, loadImage("left_button_hover.png"));
		IMAGE_MAP.put(IMG_BUTTON_LEFT_PRESSED, loadImage("left_button_push.png"));
		
		IMAGE_MAP.put(IMG_BUTTON_RIGHT_NORMAL, loadImage("right_button_normal.png"));
		IMAGE_MAP.put(IMG_BUTTON_RIGHT_HOVER, loadImage("right_button_hover.png"));
		IMAGE_MAP.put(IMG_BUTTON_RIGHT_PRESSED, loadImage("right_button_push.png"));
		
		IMAGE_MAP.put(IMG_BUTTON_CIRCLE_RED_NORMAL, loadImage("button5_24_normal.png"));
		IMAGE_MAP.put(IMG_BUTTON_CIRCLE_RED_HOVER, loadImage("button5_24_hover.png"));
		IMAGE_MAP.put(IMG_BUTTON_CIRCLE_RED_PRESSED, loadImage("button5_24_pressed.png"));
		
		IMAGE_MAP.put(IMG_SLIDER_LEFT, loadImage("slider_left_tile.png"));
		IMAGE_MAP.put(IMG_SLIDER_LEFT_MOST, loadImage("slider_leftmost.png"));
		IMAGE_MAP.put(IMG_SLIDER_RIGHT, loadImage("slider_right_tile.png"));
		IMAGE_MAP.put(IMG_SLIDER_RIGHT_MOST, loadImage("slider_rightmost.png"));
		IMAGE_MAP.put(IMG_SLIDER_THUMB, loadImage("slider_thumb.png"));
		IMAGE_MAP.put(IMG_SLIDER_THUMB_FOCUS, loadImage("slider_thumb_focused.png"));
		
		IMAGE_MAP.put(IMG_SLIDER_LEFT_VERTICAL, loadImage("slider_left_tile_vertical.png"));
		IMAGE_MAP.put(IMG_SLIDER_LEFT_MOST_VERTICAL, loadImage("slider_leftmost_vertical.png"));
		IMAGE_MAP.put(IMG_SLIDER_RIGHT_VERTICAL, loadImage("slider_right_tile_vertical.png"));
		IMAGE_MAP.put(IMG_SLIDER_RIGHT_MOST_VERTICAL, loadImage("slider_rightmost_vertical.png"));
		IMAGE_MAP.put(IMG_SLIDER_THUMB_VERTICAL, loadImage("slider_thumb_vertical.png"));
				
		IMAGE_MAP.put(IMG_SLIDER2_LEFT, loadImage("slider1_horizontal_fill.png"));
		IMAGE_MAP.put(IMG_SLIDER2_LEFT_MOST, loadImage("slider1_horizontal_left.png"));
		IMAGE_MAP.put(IMG_SLIDER2_RIGHT, loadImage("slider1_horizontal_empty.png"));
		IMAGE_MAP.put(IMG_SLIDER2_RIGHT_MOST, loadImage("slider1_horizontal_right.png"));
		IMAGE_MAP.put(IMG_SLIDER2_THUMB, loadImage("slider1_horizontal_thumb.png"));		
		
		IMAGE_MAP.put(IMG_SLIDER2_LEFT_VERTICAL, loadImage("slider1_vertical_fill.png"));
		IMAGE_MAP.put(IMG_SLIDER2_LEFT_MOST_VERTICAL, loadImage("slider1_vertical_top.png"));
		IMAGE_MAP.put(IMG_SLIDER2_RIGHT_VERTICAL, loadImage("slider1_vertical_empty.png"));
		IMAGE_MAP.put(IMG_SLIDER2_RIGHT_MOST_VERTICAL, loadImage("slider1_vertical_bottom.png"));
		IMAGE_MAP.put(IMG_SLIDER2_THUMB_VERTICAL, loadImage("slider1_vertical_thumb.png"));
		
		IMAGE_MAP.put(CURSOR_UP, loadImage("cursorA_up_green.png"));
		IMAGE_MAP.put(CURSOR_DOWN, loadImage("cursorA_down_red.png"));
		
		IMAGE_MAP.put(IMG_PROGRESS_BAR_LEFT_BORDER, loadImage("border.png"));
		IMAGE_MAP.put(IMG_PROGRESS_BAR_FILLED, loadImage("filled_region_horizontal.png"));
		IMAGE_MAP.put(IMG_PROGRESS_BAR_EMPTY, loadImage("empty_region.png"));
		IMAGE_MAP.put(IMG_PROGRESS_BAR_RIGHT_BORDER, loadImage("border.png"));
		
		IMAGE_MAP.put(IMG_PROGRESS_BAR_BORDER_VERTICAL, loadImage("border_vertical.png"));
		IMAGE_MAP.put(IMG_PROGRESS_BAR_FILLED_VERTICAL, loadImage("filled_vertical.png"));
		IMAGE_MAP.put(IMG_PROGRESS_BAR_EMPTY_VERTICAL, loadImage("empty_vertical.png"));
		
		IMAGE_MAP.put(IMG_FILLED_REGION, loadImage("filled_region.png"));
		IMAGE_MAP.put(IMG_FILLED_REGION_VERTICAL, loadImage("filled_region_vertical.png"));
		
        IMAGE_MAP.put(IMG_BLUE_16, loadImage("img_blue_16.png"));
        IMAGE_MAP.put(IMG_BLUE_24, loadImage("img_blue_24.png"));
        IMAGE_MAP.put(IMG_BLUE_48, loadImage("img_blue_48.png"));
        
        IMAGE_MAP.put(IMG_RED_16, loadImage("img_red_16.png"));
        IMAGE_MAP.put(IMG_RED_24, loadImage("img_red_24.png"));
        IMAGE_MAP.put(IMG_RED_48, loadImage("img_red_48.png"));
        
        IMAGE_MAP.put(IMG_GREEN_16, loadImage("img_green_16.png"));
        IMAGE_MAP.put(IMG_GREEN_24, loadImage("img_green_24.png"));
        IMAGE_MAP.put(IMG_GREEN_48, loadImage("img_green_48.png"));

        IMAGE_MAP.put(IMG_BUTTON_PUSH_RIGHT_HOT_REGION, loadImage("right_button_push_hot_region.png"));
        
        IMAGE_MAP.put(IMG_INDICATOR_A_1, loadImage(IMG_INDICATOR_A_1));
        IMAGE_MAP.put(IMG_INDICATOR_A_2, loadImage(IMG_INDICATOR_A_2));
        IMAGE_MAP.put(IMG_INDICATOR_A_3, loadImage(IMG_INDICATOR_A_3));
        IMAGE_MAP.put(IMG_INDICATOR_A_4, loadImage(IMG_INDICATOR_A_4));
        IMAGE_MAP.put(IMG_INDICATOR_A_5, loadImage(IMG_INDICATOR_A_5));
        IMAGE_MAP.put(IMG_INDICATOR_A_6, loadImage(IMG_INDICATOR_A_6));
        IMAGE_MAP.put(IMG_INDICATOR_A_7, loadImage(IMG_INDICATOR_A_7));
        IMAGE_MAP.put(IMG_INDICATOR_A_8, loadImage(IMG_INDICATOR_A_8));
        
        IMAGE_MAP.put(IMG_INDICATOR_B_1, loadImage(IMG_INDICATOR_B_1));
        IMAGE_MAP.put(IMG_INDICATOR_B_2, loadImage(IMG_INDICATOR_B_2));
        IMAGE_MAP.put(IMG_INDICATOR_B_3, loadImage(IMG_INDICATOR_B_3));
        IMAGE_MAP.put(IMG_INDICATOR_B_4, loadImage(IMG_INDICATOR_B_4));
        IMAGE_MAP.put(IMG_INDICATOR_B_5, loadImage(IMG_INDICATOR_B_5));
        IMAGE_MAP.put(IMG_INDICATOR_B_6, loadImage(IMG_INDICATOR_B_6));
        IMAGE_MAP.put(IMG_INDICATOR_B_7, loadImage(IMG_INDICATOR_B_7));
        IMAGE_MAP.put(IMG_INDICATOR_B_8, loadImage(IMG_INDICATOR_B_8));
        
        IMAGE_MAP.put(IMG_INDICATOR_C_1, loadImage(IMG_INDICATOR_C_1));
        IMAGE_MAP.put(IMG_INDICATOR_C_2, loadImage(IMG_INDICATOR_C_2));
        IMAGE_MAP.put(IMG_INDICATOR_C_3, loadImage(IMG_INDICATOR_C_3));
        IMAGE_MAP.put(IMG_INDICATOR_C_4, loadImage(IMG_INDICATOR_C_4));
        IMAGE_MAP.put(IMG_INDICATOR_C_5, loadImage(IMG_INDICATOR_C_5));
        IMAGE_MAP.put(IMG_INDICATOR_C_6, loadImage(IMG_INDICATOR_C_6));
        IMAGE_MAP.put(IMG_INDICATOR_C_7, loadImage(IMG_INDICATOR_C_7));
        IMAGE_MAP.put(IMG_INDICATOR_C_8, loadImage(IMG_INDICATOR_C_8));
        
        IMAGE_MAP.put(IMG_INDICATOR_D_1, loadImage(IMG_INDICATOR_D_1));
        IMAGE_MAP.put(IMG_INDICATOR_D_2, loadImage(IMG_INDICATOR_D_2));
        IMAGE_MAP.put(IMG_INDICATOR_D_3, loadImage(IMG_INDICATOR_D_3));
        IMAGE_MAP.put(IMG_INDICATOR_D_4, loadImage(IMG_INDICATOR_D_4));
        IMAGE_MAP.put(IMG_INDICATOR_D_5, loadImage(IMG_INDICATOR_D_5));
        IMAGE_MAP.put(IMG_INDICATOR_D_6, loadImage(IMG_INDICATOR_D_6));
        IMAGE_MAP.put(IMG_INDICATOR_D_7, loadImage(IMG_INDICATOR_D_7));
        IMAGE_MAP.put(IMG_INDICATOR_D_8, loadImage(IMG_INDICATOR_D_8));
        IMAGE_MAP.put(IMG_INDICATOR_D_9, loadImage(IMG_INDICATOR_D_9));
        IMAGE_MAP.put(IMG_INDICATOR_D_10, loadImage(IMG_INDICATOR_D_10));
        IMAGE_MAP.put(IMG_INDICATOR_D_11, loadImage(IMG_INDICATOR_D_11));
        IMAGE_MAP.put(IMG_INDICATOR_D_12, loadImage(IMG_INDICATOR_D_12));
		
        IMAGE_MAP.put(IMG_GAUGE_CIRCLE_4, loadImage(IMG_GAUGE_CIRCLE_4));
        IMAGE_MAP.put(IMG_GAUGE_CIRCLE_4_PIVOT, loadImage(IMG_GAUGE_CIRCLE_4_PIVOT));                
        IMAGE_MAP.put(IMG_GAUGE_TOP_HALF_CIRCLE, loadImage(IMG_GAUGE_TOP_HALF_CIRCLE));
        IMAGE_MAP.put(IMG_GAUGE_ORANGE_PIVOT, loadImage(IMG_GAUGE_ORANGE_PIVOT));
        
        IMAGE_MAP.put(IMG_GAUGE_LEFT_HALF_CIRCLE, loadImage(IMG_GAUGE_LEFT_HALF_CIRCLE));
        
		COLOR_MAP.put(COLOR_DARK_SLIDER, new Color(Display.getDefault(),new RGB(56,62,77)));
		
		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run() {
				final Iterator<Image> iter = IMAGE_MAP.values().iterator();
				while(iter.hasNext()){
					final Image im = iter.next();
					if(im != null){
						im.dispose();
					}
				}
				final Iterator<Color> iterC = COLOR_MAP.values().iterator();
				while(iter.hasNext()){
					final Color im = iterC.next();
					if(im != null){
						im.dispose();
					}
				}
			}
		});
	}
	
	public static Image getImage(String key){
		return IMAGE_MAP.get(key);
	}
	
	public static Color getColor(String key){
		return COLOR_MAP.get(key);
	}
	
	public static Image loadImage (String imageFilename) {
		InputStream stream = SampleToolBoxImageRegistry.class.getResourceAsStream (imageFilename);
		if (stream == null){
			logger.log(Level.WARNING,"The image "+imageFilename+"no longer exists");
			return null;
		}
		Image image = null;
		try {
			image = new Image (Display.getDefault(), stream);
		} catch (SWTException ex) {
			logger.log(Level.SEVERE, "SWTException while trying to load image {0}", imageFilename);
		} finally {
			try {
				stream.close ();
			} catch (IOException ex) {}
		}
		return image;
	}
	
}

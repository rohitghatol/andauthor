/**
 * 
 */
package com.test.batch.model;

import android.graphics.Canvas;
import android.graphics.Picture;

/**
 * @author rohit
 *
 */
public class PictureLayer {

	private Picture picture;
	private Shape shape;
	/**
	 * @param picture
	 * @param shape
	 */
	public PictureLayer(Shape shape) {
		super();
		this.picture= new Picture();
		this.shape = shape;
		
		Canvas canvas = picture.beginRecording((int)shape.getWidth(), (int)shape.getHeight());
		shape.draw(canvas);
		picture.endRecording();
	}
	
	public Picture getPicture(){
		return this.picture;
	}
	
	public Shape getShape(){
		return this.shape;
	}
}

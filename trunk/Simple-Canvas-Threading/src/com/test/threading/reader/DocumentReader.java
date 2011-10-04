/**
 * 
 */
package com.test.threading.reader;

import java.io.File;

import com.test.threading.controller.DrawController;
import com.test.threading.model.ShapeBuilder;
import com.test.threading.model.ShapeType;

/**
 * @author rohit
 *
 */
public class DocumentReader {

	private DrawController controller = null;
	private DocumentReadListener listener;
	public DocumentReader(DrawController controller,DocumentReadListener listener){
		this.controller=controller;
		this.listener=listener;
	}
	
	public void openDocument(File file){
		try {
			Thread.currentThread().sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Simulate slow reading from file
		
		

		controller.addShape(new ShapeBuilder().setX(10).setY(5).setWidth(50)
				.setHeight(50).setShapeType(ShapeType.Triangle).build());

		controller.addShape(new ShapeBuilder().setX(10).setY(60).setWidth(40)
				.setHeight(40).setShapeType(ShapeType.Circle).build());
		controller.addShape(new ShapeBuilder().setX(10).setY(120).setWidth(40)
				.setHeight(40).setShapeType(ShapeType.Star).build());

		controller.addShape(new ShapeBuilder().setX(40).setY(220).setWidth(40)
				.setHeight(60).setShapeType(ShapeType.Rect).build());
		
		listener.documentRead();

	}
}

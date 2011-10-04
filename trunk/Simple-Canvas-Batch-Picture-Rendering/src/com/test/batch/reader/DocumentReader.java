/**
 * 
 */
package com.test.batch.reader;

import java.io.File;

import com.test.batch.controller.DrawController;
import com.test.batch.model.Batch;
import com.test.batch.model.PictureLayer;
import com.test.batch.model.ShapeBuilder;
import com.test.batch.model.ShapeType;

/**
 * @author rohit
 *
 */
public class DocumentReader {

	private DrawController controller = null;
	private DocumentReadListener listener;
	private Batch batch ;
	public DocumentReader(DrawController controller,Batch batch,DocumentReadListener listener){
		this.controller=controller;
		this.listener=listener;
		this.batch=batch;
	}
	
	public void openDocument(File file){
		try {
			Thread.currentThread().sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Simulate slow reading from file
		
		batch.add(new PictureLayer(new ShapeBuilder().setX(10).setY(5).setWidth(50)
				.setHeight(50).setShapeType(ShapeType.Triangle).build()));

		batch.add(new PictureLayer(new ShapeBuilder().setX(10).setY(5).setWidth(50)
				.setHeight(50).setShapeType(ShapeType.Triangle).build()));

		batch.add(new PictureLayer(new ShapeBuilder().setX(10).setY(60).setWidth(40)
				.setHeight(40).setShapeType(ShapeType.Circle).build()));
		batch.add(new PictureLayer(new ShapeBuilder().setX(10).setY(120).setWidth(40)
				.setHeight(40).setShapeType(ShapeType.Star).build()));

		batch.add(new PictureLayer(new ShapeBuilder().setX(40).setY(220).setWidth(40)
				.setHeight(60).setShapeType(ShapeType.Rect).build()));
		
		listener.documentRead();

	}
	
	public Batch getBatch(){
		return batch;
	}
}

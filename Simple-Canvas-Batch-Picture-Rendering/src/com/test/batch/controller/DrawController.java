/**
 * 
 */
package com.test.batch.controller;

import com.test.batch.model.Batch;
import com.test.batch.model.Shape;

/**
 * @author rohit
 *
 */
public interface DrawController {

	void addShape(Shape shape);
	
	Shape hitTest(float x, float y);
	
	void draw(Batch batch);
	
	void clear();
	
	void showSelection(Shape shape);
	
	void clearSelection();
	
	int getLeft();
	int getTop();
	
	
}

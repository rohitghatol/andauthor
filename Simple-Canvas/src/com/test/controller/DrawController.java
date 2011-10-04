/**
 * 
 */
package com.test.controller;

import com.test.model.Shape;

/**
 * @author rohit
 *
 */
public interface DrawController {

	void addShape(Shape shape);
	
	Shape hitTest(float x, float y);
	
	void draw();
	
	void clear();
	
	void showSelection(Shape shape);
	
	void clearSelection();
	
	int getLeft();
	int getTop();
	
	
}

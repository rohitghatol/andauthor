/**
 * 
 */
package com.test.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

import com.test.threading.controller.DrawController;
import com.test.threading.model.Shape;

/**
 * @author rohit
 * 
 */
public class CanvasView extends View implements DrawController {

	private List<Shape> shapes = new ArrayList<Shape>();
	private Shape selectedShape = null;
	private Paint selectionPaint;

	public CanvasView(Context context, AttributeSet attrs) {
		super(context, attrs);
		selectionPaint = new Paint();
		selectionPaint.setStrokeWidth(2);
		selectionPaint.setStyle(Style.STROKE);
		selectionPaint.setPathEffect(new DashPathEffect(new float[] { 15, 5, 8,
				5 }, 4));
		selectionPaint.setColor(Color.WHITE);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onDraw(android.graphics.Canvas)
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		for (Shape shape : shapes) {
			shape.draw(canvas);
		}
		if (null != selectedShape) {
			canvas.drawRect(selectedShape.getLeft(), selectedShape.getTop(),
					selectedShape.getLeft() + selectedShape.getWidth(),
					selectedShape.getTop() + selectedShape.getHeight(),
					selectionPaint);
			System.out.println("canvas.drawRect("+selectedShape.getLeft()+","+ selectedShape.getTop()+","+
					(selectedShape.getLeft() + selectedShape.getWidth())+","+
					(selectedShape.getTop() + selectedShape.getHeight())+","+
					selectionPaint+");");
		}
	}

	/**
	 * 
	 */
	public void addShape(Shape shape) {
		shapes.add(shape);

	}

	/**
	 * @return Shape which contains points x,y else returns null
	 */
	public Shape hitTest(float x, float y) {
		Shape selectedShape = null;
		for (Shape shape : shapes) {
			if (shape.containsPoint(x, y)) {
				selectedShape = shape;
				break;
			}
		}
		return selectedShape;
	}

	/**
	 * 
	 */
	public void draw() {
		this.invalidate();

	}

	/**
	 * 
	 */
	public void clear() {
		this.shapes.clear();
		this.invalidate();

	}

	public void showSelection(Shape shape) {
		this.selectedShape = shape;

	}

	public void clearSelection() {
		this.selectedShape = null;

	}

}

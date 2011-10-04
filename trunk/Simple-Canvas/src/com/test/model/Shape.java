/**
 * 
 */
package com.test.model;

import java.util.List;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;

import com.test.rendering.model.ArcTo;
import com.test.rendering.model.DrawingInstruction;
import com.test.rendering.model.LineTo;
import com.test.rendering.model.MoveTo;

/**
 * @author rohit
 * 
 */
public class Shape {
	private float left, top, width, height;
	private List<DrawingInstruction> drawingInstructions;

	private Paint strokePaint;
	private Paint fillPaint;
	
	public Shape(){
		strokePaint = new Paint();
		fillPaint = new Paint();
		
		strokePaint.setStrokeWidth(1);
		strokePaint.setStyle(Style.STROKE);
		strokePaint.setColor(Color.RED);
		
		
		fillPaint.setStyle(Style.FILL);
		fillPaint.setColor(Color.YELLOW);

	}
	/**
	 * @return the left
	 */
	public float getLeft() {
		return left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(float left) {
		this.left = left;
	}

	/**
	 * @return the top
	 */
	public float getTop() {
		return top;
	}

	/**
	 * @param top the top to set
	 */
	public void setTop(float top) {
		this.top = top;
	}

	/**
	 * @return the width
	 */
	public float getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(float width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public float getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(float height) {
		this.height = height;
	}

	/**
	 * @return the drawingInstructions
	 */
	public List<DrawingInstruction> getDrawingInstructions() {
		return drawingInstructions;
	}

	/**
	 * @param drawingInstructions the drawingInstructions to set
	 */
	public void setDrawingInstructions(List<DrawingInstruction> drawingInstructions) {
		this.drawingInstructions = drawingInstructions;
	}

	public void draw(Canvas canvas){
		canvas.save();
		canvas.translate(left, top);
		canvas.clipRect(new Rect(0, 0, (int)width,(int)height));
		Path path = new Path();
		if(null!=drawingInstructions){
			for(DrawingInstruction instruction:drawingInstructions){
				if(instruction instanceof MoveTo){
					MoveTo moveTo = (MoveTo)instruction;
					path.moveTo(moveTo.getX(),moveTo.getY());
				}
				else if(instruction instanceof LineTo){
					LineTo lineTo = (LineTo)instruction;
					path.lineTo(lineTo.getX(), lineTo.getY());
				}
				else if(instruction instanceof ArcTo){
					ArcTo arcTo = (ArcTo)instruction;
					path.arcTo(new RectF(arcTo.getX1(), arcTo.getY1(), arcTo.getX2(), arcTo.getY2()), arcTo.getStartAngle(), arcTo.getSweepAngle());
				}
			}
		}
		path.close();
		canvas.drawPath(path, fillPaint);
		canvas.drawPath(path, strokePaint);
		canvas.restore();
	}
	/**
	 * 
	 * @param x X Co ordinate
	 * @param y Y Co oridinate
	 * @return true if x,y are with this shape, else false
	 */
	public boolean containsPoint(float x, float y) {
		return ((x > left) && (x < (left + width)))
				&& ((y > top) && (y < (top + height)));
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Shape [left=" + left + ", top=" + top + ", width=" + width
				+ ", height=" + height + "]";
	}
	
	
}

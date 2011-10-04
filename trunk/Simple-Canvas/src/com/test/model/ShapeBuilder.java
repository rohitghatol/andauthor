/**
 * 
 */
package com.test.model;

import java.util.ArrayList;
import java.util.List;

import com.test.rendering.model.ArcTo;
import com.test.rendering.model.DrawingInstruction;
import com.test.rendering.model.LineTo;
import com.test.rendering.model.MoveTo;

/**
 * @author rohit
 * 
 */
public class ShapeBuilder {

	private ShapeType shapeType;
	private float x, y, width, height;

	public ShapeBuilder setShapeType(ShapeType shapeType) {
		this.shapeType = shapeType;
		return this;
	}

	public ShapeBuilder setX(float x) {
		this.x = x;
		return this;
	}

	public ShapeBuilder setY(float y) {
		this.y = y;
		return this;
	}

	public ShapeBuilder setWidth(float width) {
		this.width = width;
		return this;
	}

	public ShapeBuilder setHeight(float height) {
		this.height = height;
		return this;
	}

	public Shape build() {
		Shape shape = new Shape();
		shape.setLeft(x);
		shape.setTop(y);
		shape.setWidth(width);
		shape.setHeight(height);
		List<DrawingInstruction> instructions = new ArrayList<DrawingInstruction>();
		if (ShapeType.Rect == shapeType) {
			instructions.add(new MoveTo(0, 0));
			instructions.add(new LineTo(width, 0));
			instructions.add(new LineTo(width, height));
			instructions.add(new LineTo(0, height));
		} else if (ShapeType.Circle == shapeType) {
			
			instructions.add(new ArcTo(0f, 0f, width, height, 0, 359));

		} else if (ShapeType.Triangle == shapeType) {
			instructions.add(new MoveTo(width/2, 0));
			instructions.add(new LineTo(width, height));
			instructions.add(new LineTo(0, height));

		} else if (ShapeType.Star == shapeType) {
			instructions.add(new MoveTo(width/2, 0));
			instructions.add(new LineTo(width* 3/4, height * 1/3));
			instructions.add(new LineTo(width, height * 1/3));
			instructions.add(new LineTo(width * 8/10, height * 2/3));
			instructions.add(new LineTo(width , height));
			instructions.add(new LineTo(width /2, height * 2/3));
			instructions.add(new LineTo(0 , height));
			instructions.add(new LineTo(width * 2/10, height * 2/3));
			instructions.add(new LineTo(0, height * 1/3));
			instructions.add(new LineTo(width* 1/4, height * 1/3));
		}
		shape.setDrawingInstructions(instructions);
		return shape;
	}

}

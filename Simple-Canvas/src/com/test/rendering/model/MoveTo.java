/**
 * 
 */
package com.test.rendering.model;

/**
 * @author rohit
 *
 */
public class MoveTo implements DrawingInstruction {

	private float x,y;

	/**
	 * @param x
	 * @param y
	 */
	public MoveTo(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x
	 */
	public float getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public float getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(float y) {
		this.y = y;
	}
	
	
}

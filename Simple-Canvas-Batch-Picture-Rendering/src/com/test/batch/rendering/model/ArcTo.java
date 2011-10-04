/**
 * 
 */
package com.test.batch.rendering.model;

/**
 * @author rohit
 *
 */
public class ArcTo implements DrawingInstruction {

	private float x1,y1,x2,y2;
	private int startAngle,sweepAngle;
	/**
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param startAngle
	 * @param sweepAngle
	 */
	public ArcTo(float x1, float y1, float x2, float y2, int startAngle,
			int sweepAngle) {
		super();
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.startAngle = startAngle;
		this.sweepAngle = sweepAngle;
	}
	/**
	 * @return the x1
	 */
	public float getX1() {
		return x1;
	}
	/**
	 * @param x1 the x1 to set
	 */
	public void setX1(float x1) {
		this.x1 = x1;
	}
	/**
	 * @return the y1
	 */
	public float getY1() {
		return y1;
	}
	/**
	 * @param y1 the y1 to set
	 */
	public void setY1(float y1) {
		this.y1 = y1;
	}
	/**
	 * @return the x2
	 */
	public float getX2() {
		return x2;
	}
	/**
	 * @param x2 the x2 to set
	 */
	public void setX2(float x2) {
		this.x2 = x2;
	}
	/**
	 * @return the y2
	 */
	public float getY2() {
		return y2;
	}
	/**
	 * @param y2 the y2 to set
	 */
	public void setY2(float y2) {
		this.y2 = y2;
	}
	/**
	 * @return the startAngle
	 */
	public int getStartAngle() {
		return startAngle;
	}
	/**
	 * @param startAngle the startAngle to set
	 */
	public void setStartAngle(int startAngle) {
		this.startAngle = startAngle;
	}
	/**
	 * @return the sweepAngle
	 */
	public int getSweepAngle() {
		return sweepAngle;
	}
	/**
	 * @param sweepAngle the sweepAngle to set
	 */
	public void setSweepAngle(int sweepAngle) {
		this.sweepAngle = sweepAngle;
	}
	
	
	
}

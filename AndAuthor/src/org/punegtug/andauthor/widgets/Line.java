/**
 * 
 */
package org.punegtug.andauthor.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author rohitghatol
 * 
 */
public class Line extends View {

	private Paint mPaint = null;
	private Point start = null;
	private Point end = null;
	private boolean movingStart = false;
	private boolean movingEnd = false;
	private boolean movingLine = false;
	private int mAscent;

	private Point cursorAtMouseDown=null;
	private Point startAtMouseDown = null;
	private Point endAtMouseDown = null;
	public Line(Context context) {
		super(context, null);
		setup();
	}

	public Line(Context context, Point start, Point end) {
		super(context, null);
		setup();
		this.start = start;
		this.end = end;
	}

	public Line(Context context, AttributeSet attrs) {
		super(context, attrs);
		setup();
	}

	private void setup() {
		start = new Point(0, 0);
		end = new Point(100, 100);
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setColor(Color.GREEN);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onDraw(android.graphics.Canvas)
	 */
	@Override
	protected void onDraw(Canvas canvas) {

		super.onDraw(canvas);

		canvas.drawLine(start.x, start.y, end.x, end.y, mPaint);
		if(movingStart||movingEnd||movingLine){
			canvas.drawCircle(start.x, start.y, 5, mPaint);
			canvas.drawCircle(end.x, end.y, 5, mPaint);
		}
		

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onMeasure(int, int)
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		setMeasuredDimension(measureWidth(widthMeasureSpec),
				measureHeight(heightMeasureSpec));

	}

	/**
	 * Determines the width of this view
	 * 
	 * @param measureSpec
	 *            A measureSpec packed into an int
	 * @return The width of the view, honoring constraints from measureSpec
	 */
	private int measureWidth(int measureSpec) {
		return MeasureSpec.getSize(measureSpec);
	}

	/**
	 * Determines the height of this view
	 * 
	 * @param measureSpec
	 *            A measureSpec packed into an int
	 * @return The height of the view, honoring constraints from measureSpec
	 */
	private int measureHeight(int measureSpec) {
		return MeasureSpec.getSize(measureSpec);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_UP:
			////Log.d("AndAuthor", "Mouse UP");
			if(movingStart||movingEnd||movingLine){
				invalidate();
			}

			movingStart = false;
			movingEnd = false;
			movingLine=false;
			break;
		case MotionEvent.ACTION_OUTSIDE:
			////Log.d("AndAuthor", "Mouse OUT");
			if(movingStart||movingEnd||movingLine){
				invalidate();
			}

			movingStart = false;
			movingEnd = false;
			movingLine=false;
			break;
		case MotionEvent.ACTION_MOVE:
			//Log.d("AndAuthor", "Mouse Move");
			if (movingStart) {
				start.x = (int) event.getX();
				start.y = (int) event.getY();
				//Log.d("AndAuthor", "Moving start point");
				invalidate();
				return true;
			} else if (movingEnd) {
				end.x = (int) event.getX();
				end.y = (int) event.getY();
				//Log.d("AndAuthor", "Moving end point");
				invalidate();
				return true;
			}
			else if(movingLine){
				
				if(cursorAtMouseDown!=null){
					//Log.d("AndAuthor", "Moving Entire Line");
					double diffX=event.getX()-cursorAtMouseDown.x;
					double diffY=event.getY()-cursorAtMouseDown.y;
					//Log.d("AndAuthor", "     diffX="+diffX);
					//Log.d("AndAuthor", "     diffY="+diffY);
					start.x=(int) (startAtMouseDown.x+diffX);
					start.y=(int) (startAtMouseDown.y+diffY);
					end.x=(int) (endAtMouseDown.x+diffX);
					end.y=(int) (endAtMouseDown.y+diffY);
					invalidate();
					return true;
				}

			}
			return false;

		case MotionEvent.ACTION_DOWN:
			cursorAtMouseDown=new Point((int)event.getX(),(int)event.getY());
			startAtMouseDown=new Point(start);
			endAtMouseDown=new Point(end);
			//Log.d("AndAuthor", "Mouse Down");
			if (inRange(start, event)) {
				movingStart = true;
				//Log.d("AndAuthor", "In Start Range, Locking on start point");
				return true;
			} else if (inRange(end, event)) {
				movingEnd = true;
				//Log.d("AndAuthor", "In End Range, Locking on end point");
				return true;
			} else if (isPointOnLine(new Point((int)event.getX(),(int)event.getY()),start,end) ){
				movingLine= true;
				//Log.d("AndAuthor", "Clicked on the Line");
				return true;
			}
			return false;

		default:
			//Log.d("AndAuthor", "Touch event on " + event.getX() + ","
			//		+ event.getY());
			return super.onTouchEvent(event);

		}
		return false;
	}

	private boolean inRange(Point point, MotionEvent event) {
		if (Math.abs(point.x - event.getX()) <= 5
				&& Math.abs(point.y - event.getY()) <= 5) {
			return true;
		} else {
			return false;
		}

	}

	private boolean isPointOnLine(Point point, Point start, Point end) {
		double d1=distance(point,start);
		double d2=distance(point,end);
		double d = distance (start,end);
		//Log.d("AndAuthor","d1="+d1+",d2="+d2+"=="+d);
		if (Math.abs(d-(d1+d2))<3) {
			return true;
		} else {
			return false;
		}
	}

	private double distance(Point p, Point q) {
		double dx = p.x - q.x; // horizontal difference
		
		double dy = p.y - q.y; // vertical difference
		double dist = Math.sqrt(dx * dx + dy * dy); // distance using Pythagoras
													// theorem
		//Log.d("AndAuthor", "distance ="+dist);
		return dist;
	}

}

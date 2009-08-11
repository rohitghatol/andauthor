/**
 * 
 */
package org.punegtug.andauthor.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author rohitghatol
 * 
 */
public class Box extends View {

	private Paint mPaint = null;
	private Point start = null;
	private Point end = null;
	private ResizeMarkers selectedMarker = null;

	enum ResizeMarkers {

		NORTH {
			public boolean inRange(Point point, Point start, Point end) {
				int midX=start.x<end.x?(start.x+(end.x-start.x)/2):(end.x+(start.x-end.x)/2);
				
				
				int y = start.y<end.y?start.y:end.y;
				if(Math.abs(point.y-y)<=5 && Math.abs(point.x-midX)<=5){
					return true;
				}
				else{
					return false;
				}
			}

			public void adjustPoints(Point point, Point start, Point end) {
				if(start.y<end.y){
					start.y=point.y;
				}
				else{
					end.y=point.y;
				}
			}
			int[] getCordinates(Point start, Point end){
				int midX=start.x<end.x?(start.x+(end.x-start.x)/2):(end.x+(start.x-end.x)/2);
				int [] coordinates = new int[4];
				coordinates[0]=midX-5;
				coordinates[1]=start.y<end.y?start.y-5:end.y-5;
				coordinates[2]=midX+5;
				coordinates[3]=start.y<end.y?start.y+5:end.y+5;
				return coordinates;
			}

		},
		SOUTH {
			public boolean inRange(Point point, Point start, Point end) {
				int midX=start.x<end.x?(start.x+(end.x-start.x)/2):(end.x+(start.x-end.x)/2);
				
				
				int y = start.y>end.y?start.y:end.y;
				if(Math.abs(point.y-y)<=5 && Math.abs(point.x-midX)<=5){
					return true;
				}
				else{
					return false;
				}
			}

			public void adjustPoints(Point point, Point start, Point end) {
				if(start.y>end.y){
					start.y=point.y;
				}
				else{
					end.y=point.y;
				}
			}
			int[] getCordinates(Point start, Point end){
				int midX=start.x<end.x?(start.x+(end.x-start.x)/2):(end.x+(start.x-end.x)/2);
				int [] coordinates = new int[4];
				coordinates[0]=midX-5;
				coordinates[1]=start.y>end.y?start.y-5:end.y-5;
				coordinates[2]=midX+5;
				coordinates[3]=start.y>end.y?start.y+5:end.y+5;
				return coordinates;
			}

		},
		WEST {
			public boolean inRange(Point point, Point start, Point end) {
				int midY=start.y<end.y?(start.y+(end.y-start.y)/2):(end.y+(start.y-end.y)/2);
				int x = start.x<end.x?start.x:end.x;
				if(Math.abs(point.x-x)<=5 && Math.abs(point.y-midY)<=5){
					return true;
				}
				else{
					return false;
				}

			}

			public void adjustPoints(Point point, Point start, Point end) {
				if(start.x<end.x){
					start.x=point.x;
				}
				else{
					end.x=point.x;
				}
				
			}
			int[] getCordinates(Point start, Point end){
				int midY=start.y<end.y?(start.y+(end.y-start.y)/2):(end.y+(start.y-end.y)/2);
				int [] coordinates = new int[4];
				coordinates[0]=start.x>end.x?start.x-5:end.x-5;
				coordinates[1]=midY-5;
				coordinates[2]=start.x>end.x?start.x+5:end.x+5;
				coordinates[3]=midY+5;
				return coordinates;
			}

		},
		EAST {
			public boolean inRange(Point point, Point start, Point end) {
				int midY=start.y<end.y?(start.y+(end.y-start.y)/2):(end.y+(start.y-end.y)/2);
				int x = start.x>end.x?start.x:end.x;
				if(Math.abs(point.x-x)<=5 && Math.abs(point.y-midY)<=5){
					return true;
				}
				else{
					return false;
				}
			}

			public void adjustPoints(Point point, Point start, Point end) {
				if(start.x>end.x){
					start.x=point.x;
				}
				else{
					end.x=point.x;
				}
			}
			int[] getCordinates(Point start, Point end){
				int midY=start.y<end.y?(start.y+(end.y-start.y)/2):(end.y+(start.y-end.y)/2);
				int [] coordinates = new int[4];
				coordinates[0]=start.x<end.x?start.x-5:end.y-5;
				coordinates[1]=midY-5;
				coordinates[2]=start.x<end.x?start.x+5:end.y+5;
				coordinates[3]=midY+5;
				return coordinates;
			}

		},
		NORTHEAST {
			public boolean inRange(Point point, Point start, Point end) {
				if(Math.abs(point.y-start.y)<=5 && Math.abs(point.x-end.x)<=5){
					return true;
				}
				else{
					return false;
				}
			}

			public void adjustPoints(Point point, Point start, Point end) {
				end.x=point.x;
				start.y=point.y;
			}
			int[] getCordinates(Point start, Point end){
				
				int [] coordinates = new int[4];
				coordinates[0]=start.x<end.x?start.x-5:end.x-5;
				coordinates[1]=start.y<end.y?start.y-5:end.y-5;
				coordinates[2]=start.x<end.x?start.x+5:end.x+5;
				coordinates[3]=start.y<end.y?start.y+5:end.y+5;
				return coordinates;
			}

		},
		NORTHWEST {
			public boolean inRange(Point point, Point start, Point end) {
				if(Math.abs(point.y-start.y)<=5 && Math.abs(point.x-start.x)<=5){
					return true;
				}
				else{
					return false;
				}
			}

			public void adjustPoints(Point point, Point start, Point end) {
				start.x=point.x;
				start.y=point.y;
			}
			int[] getCordinates(Point start, Point end){
				int [] coordinates = new int[4];
				coordinates[0]=start.x>end.x?start.x-5:end.x-5;
				coordinates[1]=start.y<end.y?start.y-5:end.y-5;
				coordinates[2]=start.x>end.x?start.x+5:end.x+5;
				coordinates[3]=start.y<end.y?start.y+5:end.y+5;
				return coordinates;
			}

		},
		SOUTHEAST {
			public boolean inRange(Point point, Point start, Point end) {
				if(Math.abs(point.y-end.y)<=5 && Math.abs(point.x-end.x)<=5){
					return true;
				}
				else{
					return false;
				}
			}

			public void adjustPoints(Point point, Point start, Point end) {
				end.x=point.x;
				end.y=point.y;

			}
			int[] getCordinates(Point start, Point end){
				int [] coordinates = new int[4];
				coordinates[0]=start.x<end.x?start.x-5:end.x-5;
				coordinates[1]=start.y>end.y?start.y-5:end.y-5;
				coordinates[2]=start.x<end.x?start.x+5:end.x+5;
				coordinates[3]=start.y>end.y?start.y+5:end.y+5;


				return coordinates;
			}
		},
		SOUTHWEST {
			public boolean inRange(Point point, Point start, Point end) {
				if(Math.abs(point.y-end.y)<=5 && Math.abs(point.x-start.x)<=5){
					return true;
				}
				else{
					return false;
				}
			}

			public void adjustPoints(Point point, Point start, Point end) {
				end.y=point.y;
				start.x=point.x;

			}
			int[] getCordinates(Point start, Point end){
				int [] coordinates = new int[4];
				coordinates[0]=start.x>end.x?start.x-5:end.x-5;
				coordinates[1]=start.y>end.y?start.y-5:end.y-5;
				coordinates[2]=start.x>end.x?start.x+5:end.x+5;
				coordinates[3]=start.y>end.y?start.y+5:end.y+5;


				return coordinates;
			}

		};
		
		abstract boolean inRange(Point point, Point start, Point end);

		abstract void adjustPoints(Point point, Point start, Point end);
		
		abstract int[] getCordinates(Point start, Point end);
		
	}

	private boolean movingBox = false;

	private int mAscent;

	private Point cursorAtMouseDown = null;
	private Point startAtMouseDown = null;
	private Point endAtMouseDown = null;

	public Box(Context context) {
		super(context, null);
		setup();
	}

	public Box(Context context, Point start, Point end) {
		super(context, null);
		setup();
		this.start = start;
		this.end = end;
	}

	public Box(Context context, AttributeSet attrs) {
		super(context, attrs);
		setup();
	}

	private void setup() {
		start = new Point(0, 0);
		end = new Point(100, 100);
		mPaint = new Paint();
		mPaint.setColor(Color.GREEN);
		mPaint.setStyle(Style.STROKE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onDraw(android.graphics.Canvas)
	 */
	@Override
	protected void onDraw(Canvas canvas) {

		super.onDraw(canvas);

		canvas.drawRect(start.x, start.y, end.x, end.y, mPaint);
		if(this.movingBox || selectedMarker!=null){
			for(ResizeMarkers marker:ResizeMarkers.values()){
				int[] pts=marker.getCordinates(start, end);
				canvas.drawRect(pts[0],pts[1],pts[2],pts[3],mPaint);
			}
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
			Log.d("AndAuthor-Box", "Mouse UP");
			if(selectedMarker!=null || movingBox==true){
				invalidate();
			}
			selectedMarker = null;
			
			break;
		case MotionEvent.ACTION_OUTSIDE:
			Log.d("AndAuthor-Box", "Mouse OUT");
			if(selectedMarker!=null || movingBox==true){
				invalidate();
			}

			selectedMarker = null;
			
			break;
		case MotionEvent.ACTION_MOVE:
			Log.d("AndAuthor-Box", "Mouse Move");

			if (selectedMarker != null) {
				Point point = new Point((int) event.getX(), (int) event.getY());
				selectedMarker.adjustPoints(point, start, end);
				Log.d("AndAuthor-Box", "Moving "+selectedMarker);
				invalidate();
				return true;

			} else if (movingBox) {

				if (cursorAtMouseDown != null) {
					Log.d("AndAuthor-Box", "Moving Entire Line");
					double diffX = event.getX() - cursorAtMouseDown.x;
					double diffY = event.getY() - cursorAtMouseDown.y;
					Log.d("AndAuthor-Box", "     diffX=" + diffX);
					Log.d("AndAuthor-Box", "     diffY=" + diffY);
					start.x = (int) (startAtMouseDown.x + diffX);
					start.y = (int) (startAtMouseDown.y + diffY);
					end.x = (int) (endAtMouseDown.x + diffX);
					end.y = (int) (endAtMouseDown.y + diffY);
					invalidate();
					return true;
				} 
			}
			return false;

		case MotionEvent.ACTION_DOWN:
			cursorAtMouseDown = new Point((int) event.getX(), (int) event
					.getY());
			startAtMouseDown = new Point(start);
			endAtMouseDown = new Point(end);
			Log.d("AndAuthor-Box", "Mouse Down");
			for(ResizeMarkers marker:ResizeMarkers.values()){
				if(marker.inRange(cursorAtMouseDown, startAtMouseDown, endAtMouseDown)){
					selectedMarker=marker;
					Log.d("AndAuthor-Box", "Clicked "+marker);
					return true;
				}
				
			}
			
			if (selectedMarker==null && isPointInBox(new Point((int) event.getX(), (int) event
					.getY()), start, end)) {
				movingBox = true;
				Log.d("AndAuthor-Box", "Clicked on the Box itself");
				return true;
			}
			return false;

		default:
			Log.d("AndAuthor-Box", "Touch event on " + event.getX() + ","
					+ event.getY());
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

	private boolean isPointInBox(Point point, Point start, Point end) {
		int minX=start.x<end.x?start.x:end.x;
		int maxX=start.x>end.x?start.x:end.x;
		
		int minY=start.y<end.y?start.y:end.y;
		int maxY=start.y>end.y?start.y:end.y;
		
		if (point.x >= minX && point.x <= maxX && point.y >= minX
				&& point.y <= maxY) {
			return true;
		} else {
			return false;
		}
	}



}

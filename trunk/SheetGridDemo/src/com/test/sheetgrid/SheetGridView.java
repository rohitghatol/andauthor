/**
 * 
 */
package com.test.sheetgrid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author rohit
 * 
 */
public class SheetGridView extends View {

	private Paint textPaint;
	private Paint blackTextPaint;
	private Paint strokePaint;
	private Paint cellFillPaint;
	private Paint headerFillPaint;
	private Paint blackStrokePaint;

	int startRow, endRow = 20, startCol, endCol = 20;

	float lastX, lastY;
	float deltaX, deltaY;

	public SheetGridView(Context context) {
		super(context);
		init();
	}

	public SheetGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public SheetGridView(Context context, AttributeSet attrs, int styledef) {
		super(context, attrs, styledef);
		init();
	}

	// Ideally constructors should be wired together using this(), but I am not
	// sure who calls which constructor
	private void init() {
		
		textPaint = new Paint();
		textPaint.setStyle(Style.STROKE);
		textPaint.setStrokeWidth(1);
		textPaint.setColor(Color.WHITE);
		textPaint.setAntiAlias(true);

		blackTextPaint = new Paint();
		blackTextPaint.setStyle(Style.STROKE);
		blackTextPaint.setStrokeWidth(1);
		blackTextPaint.setColor(Color.BLACK);
		blackTextPaint.setAntiAlias(true);

		strokePaint = new Paint();
		strokePaint.setStyle(Style.STROKE);
		strokePaint.setStrokeWidth(2);
		strokePaint.setColor(Color.WHITE);
		strokePaint.setAntiAlias(true);

		blackStrokePaint = new Paint();
		blackStrokePaint.setStyle(Style.STROKE);
		blackStrokePaint.setStrokeWidth(2);
		blackStrokePaint.setColor(Color.BLACK);
		blackStrokePaint.setAntiAlias(true);

		cellFillPaint = new Paint();
		cellFillPaint.setStyle(Style.FILL);
		cellFillPaint.setColor(Color.WHITE);
		cellFillPaint.setAntiAlias(true);

		headerFillPaint = new Paint();
		headerFillPaint.setStyle(Style.FILL);
		headerFillPaint.setColor(Color.GRAY);
		headerFillPaint.setAntiAlias(true);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		System.out.println("Motion event action = "+event.getAction());
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			lastX= event.getRawX();
			lastY= event.getRawY();
			System.out.println("Touched down at "+lastX+","+lastY);
			break;
		case MotionEvent.ACTION_MOVE:
			deltaX = event.getRawX()-lastX;
			deltaY = event.getRawY()-lastY;
			
			if(deltaX>0){
				deltaX=0;
			}
			if(deltaY>0){
				deltaY=0;
			}
			System.out.println("Moving canvas by "+deltaX+","+deltaY);
			invalidate();
			break;
		default:
			break;
		}
		
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onDraw(android.graphics.Canvas)
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		drawCells(canvas);
		drawColumnHeaders(canvas);
		drawRowHeaders(canvas);
		drawTopLeftBlock(canvas);

	}

	private void drawTopLeftBlock(Canvas canvas) {

		headerFillPaint.setColor(Color.RED);
		canvas.drawRect(new Rect(0, 0, 40, 30), headerFillPaint);
		canvas.drawRect(new Rect(0, 0, 40, 30), strokePaint);
		headerFillPaint.setColor(Color.GRAY);
	}

	private void drawRowHeaders(Canvas canvas) {
		canvas.save();
		canvas.translate(0,deltaY);
		canvas.translate(0, 30);
		int rowY;
		for (int row = startRow; row < endRow; row++) {
			rowY = (row) * 30;
			canvas.save();
			canvas.translate(0, rowY);
			canvas.clipRect(0, 0, 40, 30);
			canvas.drawRect(new Rect(0, 0, 40, 30), headerFillPaint);
			canvas.drawRect(new Rect(0, 0, 40, 30), strokePaint);
			canvas.drawText(String.valueOf(row + 1), 10, 20, textPaint);
			canvas.restore();

		}

		canvas.restore();

	}

	private void drawColumnHeaders(Canvas canvas) {
		canvas.save();
		canvas.translate(deltaX,0);
		canvas.translate(40, 0);
		int colX;
		for (int col = startCol; col < endCol; col++) {
			colX = (col) * 40;
			canvas.save();
			canvas.translate(colX, 0);
			canvas.clipRect(0, 0, 40, 30);
			canvas.drawRect(new Rect(0, 0, 40, 30), headerFillPaint);
			canvas.drawRect(new Rect(0, 0, 40, 30), strokePaint);
			canvas.drawText(getColumnRef(col + 1), 10, 20, textPaint);
			canvas.restore();

		}

		canvas.restore();

	}

	private void drawCells(Canvas canvas) {
		canvas.save();
		canvas.translate(deltaX,deltaY);
		canvas.translate(40, 30);
		int rowY;
		int colX;
		for (int row = startRow; row < endRow; row++) {
			rowY = (row) * 30;
			canvas.save();
			canvas.translate(0, rowY);
			for (int col = startCol; col < endCol; col++) {
				colX = (col) * 40;
				canvas.save();
				canvas.translate(colX, 0);
				canvas.clipRect(0, 0, 40, 30);
				canvas.drawRect(new Rect(0, 0, 40, 30), cellFillPaint);
				canvas.drawRect(new Rect(0, 0, 40, 30), blackStrokePaint);
				canvas.drawText(getColumnRef(col + 1) + (row + 1), 10, 20,
						blackTextPaint);
				canvas.restore();

			}
			canvas.restore();
		}

		canvas.restore();

	}

	private String getColumnRef(int columnIndex) {
		final int CONSTANT_ZERO = 0;
		final int CONSTANT_VOCAB = 26;
		final char VOCAB_A = 'A';

		if (CONSTANT_ZERO == columnIndex) {
			return "A";
		}
		final StringBuilder sbColumnName = new StringBuilder();
		while (columnIndex > CONSTANT_ZERO) {
			int val = columnIndex % CONSTANT_VOCAB;
			if (val == CONSTANT_ZERO) {
				val = CONSTANT_VOCAB;
			}
			columnIndex -= val;
			sbColumnName.insert(0, (char) (VOCAB_A + val - 1));
			columnIndex /= CONSTANT_VOCAB;
		}
		return sbColumnName.toString();
	}

}

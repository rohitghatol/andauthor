package com.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;

import com.test.controller.DrawController;
import com.test.model.Shape;
import com.test.model.ShapeBuilder;
import com.test.model.ShapeType;

public class SimpleCanvas extends Activity {
	private ShapeType shapeType;
	private DrawController controller;

	private Shape selectedShape = null;
	private int StatusBarHeight = 45;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		controller = (DrawController) findViewById(R.id.canvas);

		controller.addShape(new ShapeBuilder().setX(10).setY(5).setWidth(50)
				.setHeight(50).setShapeType(ShapeType.Triangle).build());

		controller.addShape(new ShapeBuilder().setX(10).setY(60).setWidth(40)
				.setHeight(40).setShapeType(ShapeType.Circle).build());
		controller.addShape(new ShapeBuilder().setX(10).setY(120).setWidth(40)
				.setHeight(40).setShapeType(ShapeType.Star).build());

		controller.addShape(new ShapeBuilder().setX(40).setY(220).setWidth(40)
				.setHeight(60).setShapeType(ShapeType.Rect).build());

		controller.draw();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.shapemenu, menu);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onPrepareOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		return super.onPrepareOptionsMenu(menu);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onMenuItemSelected(int, android.view.MenuItem)
	 */
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case R.id.circle:
			shapeType = ShapeType.Circle;
			break;
		case R.id.rect:
			shapeType = ShapeType.Rect;
			break;
		case R.id.star:
			shapeType = ShapeType.Star;
			break;
		case R.id.triangle:
			shapeType = ShapeType.Triangle;
			break;
		default:
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		// Since we have selected a shapeType from menu, our mode is draw mode
		if (null != shapeType) {
			drawShape(event);
			controller.clearSelection();
		} else {
			checkForDrag(event);
		}
		return super.onTouchEvent(event);
	}

	private void drawShape(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_UP:
			Shape shape = new ShapeBuilder()
					.setX((float) event.getRawX())
					.setY((float) event.getRawY() - StatusBarHeight)
					.setWidth(50).setHeight(50).setShapeType(shapeType).build();
			controller.addShape(shape);
			controller.draw();
			shapeType = null;
			break;
		}

	}

	private void checkForDrag(MotionEvent event) {

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			controller.clearSelection();
			selectedShape = controller.hitTest(event.getRawX(), event.getRawY()
					- StatusBarHeight);
			if (null != selectedShape) {
				controller.showSelection(selectedShape);
				System.out.println("Selected a Shape " + selectedShape);
			}
			else{
				controller.clearSelection();
			}
			break;
		case MotionEvent.ACTION_MOVE:
			if (null != selectedShape) {
				selectedShape.setLeft(event.getRawX());
				selectedShape.setTop(event.getRawY() - StatusBarHeight);
				controller.draw();
				System.out.println("dragging a Shape " + selectedShape);
			}
			break;

		case MotionEvent.ACTION_UP:

			break;
		}

	}

}
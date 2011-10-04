package com.test.batch;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;

import com.test.batch.model.Batch;
import com.test.batch.model.PictureLayer;
import com.test.batch.model.Shape;
import com.test.batch.model.ShapeBuilder;
import com.test.batch.model.ShapeType;
import com.test.batch.reader.DocumentReadListener;
import com.test.batch.reader.DocumentReader;
import com.test.view.CanvasView;

public class SimpleCanvas extends Activity {
	private ShapeType shapeType;
	private CanvasView canvasView;

	private Shape selectedShape = null;
	private int StatusBarHeight = 45;

	private ProgressDialog progressBar;
	private Batch batch = new Batch();
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		

		canvasView = (CanvasView) findViewById(R.id.canvas);
		
		progressBar = ProgressDialog.show(this, "Loading Document", "Please wait....");
		
		final DocumentReader reader = new DocumentReader(canvasView,batch,new DocumentReadListener() {
			
			public void documentRead() {
				
				 
				runOnUiThread(new Runnable() {
					
					public void run() {
						progressBar.hide();
						canvasView.draw(batch);
						
					}
				});
				
			}
		});
		Thread loadingThread = new Thread(){

			/* (non-Javadoc)
			 * @see java.lang.Thread#run()
			 */
			@Override
			public void run() {
				
				super.run();
				reader.openDocument(null);
			}
			
		};
		loadingThread.start();
		
		
		
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
			canvasView.clearSelection();
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
			batch.add(new PictureLayer(shape));
			canvasView.draw(batch);
			shapeType = null;
			break;
		}

	}

	private void checkForDrag(MotionEvent event) {

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			canvasView.clearSelection();
			selectedShape = canvasView.hitTest(event.getRawX(), event.getRawY()
					- StatusBarHeight);
			if (null != selectedShape) {
				canvasView.showSelection(selectedShape);
				System.out.println("Selected a Shape " + selectedShape);
			}
			else{
				canvasView.clearSelection();
			}
			break;
		case MotionEvent.ACTION_MOVE:
			if (null != selectedShape) {
				selectedShape.setLeft(event.getRawX());
				selectedShape.setTop(event.getRawY() - StatusBarHeight);
				canvasView.draw(batch);
				System.out.println("dragging a Shape " + selectedShape);
			}
			break;

		case MotionEvent.ACTION_UP:

			break;
		}

	}

}
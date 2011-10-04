/**
 * 
 */
package com.test.batch.model;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;

/**
 * @author rohit
 * 
 */
public class Batch {

	private List<PictureLayer> layers = new ArrayList<PictureLayer>();

	/**
	 * @param arg0
	 * @return
	 * @see java.util.List#add(java.lang.Object)
	 */
	public boolean add(PictureLayer pictureLayer) {
		return layers.add(pictureLayer);
	}

	/**
	 * @param arg0
	 * @return
	 * @see java.util.List#get(int)
	 */
	public PictureLayer get(int index) {
		return layers.get(index);
	}

	/**
	 * @param arg0
	 * @return
	 * @see java.util.List#remove(int)
	 */
	public PictureLayer remove(int index) {
		return layers.remove(index);
	}

	/**
	 * @param arg0
	 * @return
	 * @see java.util.List#remove(java.lang.Object)
	 */
	public boolean remove(PictureLayer layer) {
		return layers.remove(layer);
	}

	/**
	 * @return
	 * @see java.util.List#size()
	 */
	public int size() {
		return layers.size();
	}

	public void draw(Canvas canvas) {
		for (PictureLayer layer : layers) {
			canvas.save();
			canvas.translate(layer.getShape().getLeft(), layer.getShape().getTop());
			System.out.println("Rendering picture of "+layer.getShape());
			canvas.drawPicture(layer.getPicture());
			canvas.restore();
		}
	}

}

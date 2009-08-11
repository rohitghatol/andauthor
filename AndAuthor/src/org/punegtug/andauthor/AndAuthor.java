package org.punegtug.andauthor;

import org.punegtug.andauthor.widgets.Box;
import org.punegtug.andauthor.widgets.Line;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.widget.AbsoluteLayout;

public class AndAuthor extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        AbsoluteLayout absoluteLayout = (AbsoluteLayout)findViewById(R.id.root);
        Line line= new Line(this.getApplicationContext());
        Line line2=new Line(this.getApplicationContext(),new Point(20,20),new Point(100,400));
        absoluteLayout.addView(line);
        absoluteLayout.addView(line2);
        Box box=new Box(this.getApplicationContext());
        absoluteLayout.addView(box);
        
    }
}
package com.example.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class Painter extends View {

	private Paint paint;
	private float Px,Py;
	private float PPx,PPy;
	
	public Painter(Context context) {
		super(context);
		paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setStrokeWidth(5);
		PPx = 0;PPy = 0;
		Px = 0;Py = 0;
	}
	
	@Override
	public void onDraw(Canvas canvas)
	{
		canvas.drawLine(PPx,PPy,Px,Py,paint);
	}
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		if(event.getAction() == MotionEvent.ACTION_MOVE){
			PPx = Px;
			PPy = Py;
			Px = event.getX();
			Py = event.getY();
		}
		invalidate();
		return true;
	}
	

}

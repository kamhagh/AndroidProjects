package com.example.moregraphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class Graphics extends View {
	
	private Paint paint;
	private int xMax;
	private int yMax;
	private int spacing;
	
	public Graphics(Context context,AttributeSet attrs)
	{
		super(context,attrs);
		paint = new Paint();
		paint.setHinting(DRAWING_CACHE_QUALITY_HIGH);
		//paint.setStyle(Paint.Style.STROKE);
		paint.setColor(Color.BLACK);
		paint.setStrokeWidth(2);
		paint.setAntiAlias(true);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	    xMax = getMeasuredWidth();
	    yMax = getMeasuredHeight();
	    setMeasuredDimension(xMax, yMax);
	    spacing = 20;
	}

	public void onDraw(Canvas canvas)
	{
		canvas.drawLine(xMax/2 , 0, xMax/2, yMax, paint);
		canvas.drawLine(xMax/2 , 0, xMax/2 + yMax, 0, paint);
		canvas.drawLine(xMax/2 ,yMax, xMax/2 - yMax, yMax, paint);
		
		for(int i = 0; i<= yMax; i += spacing)
		{
			canvas.drawLine(xMax/2 + yMax -i, 0, xMax/2,i,paint);
			canvas.drawLine(xMax/2 - i, yMax, xMax/2,i,paint);
		}
		
		
	}
	
	public void setAntiAlias(boolean Set)
	{
		paint.setAntiAlias(Set);
		invalidate();
	}
	
	public void setSpacing(int spacing)
	{
		if(spacing <= yMax && spacing > 0)
		{
			this.spacing = spacing;
			invalidate();
		}
	}
	
	   /*@Override
	   public void onSizeChanged(int w, int h, int oldW, int oldH) {
	      xMax = w;
	      yMax = h;
	   }*/
	
	

}

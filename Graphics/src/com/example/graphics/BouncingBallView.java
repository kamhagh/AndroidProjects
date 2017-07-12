package com.example.graphics;
  
import android.content.Context;
import android.view.KeyEvent;
import android.view.MotionEvent;

import java.util.Formatter;
import android.graphics.Typeface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
   
public class BouncingBallView extends View {
   private int xMin = 0;          // This view's bounds
   private int xMax;
   private int yMin = 0;
   private int yMax;
   private float ballRadius = 80; // Ball's radius
   private float ballX = ballRadius + 20;  // Ball's center (x,y)
   private float ballY = ballRadius + 40;
   private float ballSpeedX = 11;  // Ball's speed (x,y)
   private float ballSpeedY = 7;
   //private RectF ballBounds;      // Needed for Canvas.drawOval
   private Paint paint;           // The paint (e.g. style, color) used for drawing
// Status message to show Ball's (x,y) position and speed.
   private StringBuilder statusMsg = new StringBuilder();
   private Formatter formatter = new Formatter(statusMsg);  // Formatting the statusMsg
   private float previousX;
   private float previousY;
   private float currentX;
   private float currentY;
   private float scale;
   private int ifdrawcount = 0;
   
   
   // Constructor
   public BouncingBallView(Context context) {
      super(context);
      //ballBounds = new RectF();
      paint = new Paint();
      paint.setTypeface(Typeface.MONOSPACE);
      paint.setTextSize(25);
      paint.setHinting(DRAWING_CACHE_QUALITY_HIGH);
      setFocusableInTouchMode(true);
      //setFocusable(true);
      requestFocus();
   }
   
   /*public boolean onKeyUp(int keyCode, KeyEvent event) {
	      switch (keyCode) {
	         case KeyEvent.KEYCODE_DPAD_RIGHT: // Increase rightward speed
	            ballSpeedX++;
	            break;
	         case KeyEvent.KEYCODE_DPAD_LEFT:  // Increase leftward speed
	            ballSpeedX--;
	            break;
	         case KeyEvent.KEYCODE_DPAD_UP:    // Increase upward speed
	            ballSpeedY--;
	            break;
	         case KeyEvent.KEYCODE_DPAD_DOWN:  // Increase downward speed
	            ballSpeedY++;
	            break;
	         case KeyEvent.KEYCODE_DPAD_CENTER: // Stop
	            ballSpeedX = 0;
	            ballSpeedY = 0;
	            break;
	         case KeyEvent.KEYCODE_A:    // Zoom in
	            // Max radius is about 90% of half of the smaller dimension
	            float maxRadius = (xMax > yMax) ? yMax / 2 * 0.9f  : xMax / 2 * 0.9f;
	            if (ballRadius < maxRadius) {
	               ballRadius *= 1.05;   // Increase radius by 5%
	            }
	            break;
	         case KeyEvent.KEYCODE_Z:    // Zoom out
	            if (ballRadius > 20) {   // Minimum radius
	               ballRadius *= 0.95;   // Decrease radius by 5%
	            }
	            break;
	      }
	      return true;  // Event handled
	   }*/
  
   // Called back to draw the view. Also called by invalidate().
   @Override
   protected void onDraw(Canvas canvas) {
      // Draw the ball
      //ballBounds.set(ballX-ballRadius, ballY-ballRadius, ballX+ballRadius, ballY+ballRadius);
      //paint.setColor(Color.GRAY);
      //canvas.drawOval(ballBounds, paint);
      //canvas.drawCircle(70, yMax -70, 60, paint);
      //canvas.drawCircle(xMax -70, yMax -60, 60, paint);
      
      paint.setColor(Color.GREEN);
      canvas.drawCircle(ballX, ballY, ballRadius, paint);
      
      // Draw the status message
      
      paint.setColor(Color.WHITE);
      paint.setStrokeWidth(2);
      canvas.drawText(statusMsg.toString(), 10, 30, paint);
      if(ifdrawcount > 0){
    	  canvas.drawLine(previousX, previousY, currentX, currentY, paint);
          paint.setStrokeWidth(10);
    	  paint.setColor(Color.BLACK);
    	  canvas.drawPoint(previousX,previousY,paint);
          ifdrawcount--;
      }
     
      
      // Update the position of the ball, including collision detection and reaction.
      update();

      // Delay
      try {  
         Thread.sleep(16);  
      } catch (InterruptedException e) { }
      
      invalidate();  // Force a re-draw
   }
   
   
   // Touch-input handler
   @Override
   public boolean onTouchEvent(MotionEvent event) {
      currentX = event.getX();
      currentY = event.getY();
      ifdrawcount = 10;
      //float deltaX, deltaY;
      scale = 20.0f / ((xMax > yMax) ? yMax : xMax);
      switch (event.getAction()) {
         case MotionEvent.ACTION_DOWN:
        	 previousX = currentX;
        	 previousY = currentY;  
         case MotionEvent.ACTION_MOVE:
        	 ballSpeedY = (currentY - previousY) * scale;
        	 ballSpeedX = (currentX - previousX) * scale;
         //case MotionEvent.ACTION_CANCEL:
        	 //ifdrawcount = 0;
      }
      return true;  // Event handled
   }
   
   // Detect collision and update the position of the ball.
   private void update() {
      // Get new (x,y) position
	  
      ballX += ballSpeedX;
      ballY += ballSpeedY;
      
      /*if(ifdrawcount == 0)
    	  ballSpeedX = 0; ballSpeedY = 0;*/
    	  
      // Detect collision and react
      if (ballX + ballRadius > xMax) {
         ballSpeedX = -ballSpeedX;
         ballX = xMax-ballRadius;
      } else if (ballX - ballRadius < xMin) {
         ballSpeedX = -ballSpeedX;
         ballX = xMin+ballRadius;
      }
      if (ballY + ballRadius > yMax) {
         ballSpeedY = -ballSpeedY;
         ballY = yMax - ballRadius;
      } else if (ballY - ballRadius < yMin) {
         ballSpeedY = -ballSpeedY;
         ballY = yMin + ballRadius;
      }
      
   // Build status message
      statusMsg.delete(0, statusMsg.length());   // Empty buffer
      formatter.format("%3.0f  %3.0f || %3.0f  %3.0f", ballSpeedX, ballSpeedY,ballX,ballY);
      
   }
   
   // Called back when the view is first created or its size changes.
   @Override
   public void onSizeChanged(int w, int h, int oldW, int oldH) {
      // Set the movement bounds for the ball
      xMax = w-1;
      yMax = h-1;
   }
}
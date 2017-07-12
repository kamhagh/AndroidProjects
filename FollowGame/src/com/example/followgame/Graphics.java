package com.example.followgame;
  
import android.content.Context;
import android.os.Handler;
import android.view.MotionEvent;

import java.util.Formatter;
import java.util.Random;

import android.graphics.Typeface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.Toast;
   
public class Graphics extends View {
   private int xMin = 0;          // This view's bounds
   private int xMax;
   private int yMin = 0;
   private int yMax;
   private int score = 0;
   private boolean timer = true;
   private float ballRadius = 80; // Ball's radius
   private float ballX = ballRadius + 20;  // Ball's center (+20 and +40 for starting position)
   private float ballY = ballRadius + 40;
   private float ballSpeedX = 11;  // Ball's speed
   private float ballSpeedY = 7;
   private Paint paint;
   private StringBuilder statusMsg = new StringBuilder(); //Score info
   private Formatter formatter = new Formatter(statusMsg);  // Formatting the statusMsg
   private float currentX;
   private float currentY;
   private float size;
   private Random random;
   
   
   // Constructor
   public Graphics(Context context) {
      super(context);
      random = new Random();
      paint = new Paint();
      paint.setTypeface(Typeface.MONOSPACE);
      paint.setTextSize(25);
      //paint.setHinting(DRAWING_CACHE_QUALITY_HIGH);
      setFocusableInTouchMode(true);
      //setFocusable(true);
      requestFocus();
      timer = true;
      
   }
   @Override
   protected void onDraw(Canvas canvas) {
      // Draw the ball
      //ballBounds.set(ballX-ballRadius, ballY-ballRadius, ballX+ballRadius, ballY+ballRadius);
      //paint.setColor(Color.GRAY);
      //canvas.drawOval(ballBounds, paint);
      //canvas.drawCircle(70, yMax -70, 60, paint);
      //canvas.drawCircle(xMax -70, yMax -60, 60, paint);
      if(!timer){
    	  //Draw the ball
    	  
	      paint.setColor(Color.BLUE);
	      canvas.drawCircle(ballX  - ballRadius, ballY - ballRadius, ballRadius, paint);
	      
	      // Draw the status message
	      
	      paint.setColor(Color.WHITE);
	      paint.setStrokeWidth(2);
	      canvas.drawText(statusMsg.toString(), 10, 30, paint);
      } else {
    	  paint.setTextSize(100);
    	  canvas.drawText(statusMsg.toString(), xMax/2, yMax/2, paint);
    	  }
      
      	
      
      // Update the game stat
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
      size = event.getSize();
      //float deltaX, deltaY;
      //scale = 20.0f / ((xMax > yMax) ? yMax : xMax);
      switch (event.getAction()) {
         case MotionEvent.ACTION_DOWN:
        	 if(timer){ // if timer has reached 10seconds or is firsttime
        		 paint.setTextSize(25);	 // Set back to Default 
        		 ballSpeedX = 11;
        		 ballSpeedY = 7;
        		 score = 0;
        		 Handler handler = new Handler(); // Set timer for 10sec
        		 handler.postDelayed(new Runnable() {
        			 @Override
        			 public void run() {
        				 timer = true; // when its 10sec set timer to true
        			 }
        		 }, 10000);
        		 timer = false;    // timer has just started to its false
        	 }
         case MotionEvent.ACTION_MOVE:
        	 if(currentX - size >= ballX - ballRadius && currentX + size<= ballX + ballRadius){ // if touched X is in cricle
        		 if(currentY -size>= ballY - ballRadius && currentY + size <= ballY + ballRadius){ // if touched Y is in cricle
        			 ballSpeedX = random.nextInt(80);// change balls direction randomaly !
        			 ballSpeedY = random.nextInt(80);
        			 score++; // Increase score 
        		 }
        	 }
         //case MotionEvent.ACTION_CANCEL:
        	 //ifdrawcount = 0;
      }
      return true;  // Event handled
   }
   
   // Detect collision and update the position of the ball.
   private void update() {
      // Get new (x,y) position
	   
	  if(!timer){ 
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
	      
	  }
	   // Build status message
      statusMsg.delete(0,statusMsg.length());
      formatter.format("Score : " + score);
   }
   
   
   
   
   @Override
   public void onSizeChanged(int w, int h, int oldW, int oldH) {
      // Set the movement bounds for the ball
      xMax = w-1;
      yMax = h-1;
   }
}
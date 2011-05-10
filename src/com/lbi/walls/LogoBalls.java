package com.lbi.walls;

import java.util.ArrayList;

import processing.core.*;

import com.lbi.walls.views.Ball;

public class LogoBalls extends PApplet 
{
	//---------------------------------------------------------------------
	public static void main(String args[]) 
	{
	    PApplet.main(new String[] { "--present", "com.lbi.walls.LogoBalls" });
	}
	//---------------------------------------------------------------------
	
	PImage logo;
	ArrayList<PVector> points = new ArrayList<PVector>();
	//PVector points[];
	
	int numBalls = 350;
	int orbit = 14;
	int range = 300;

	float half_w;
	float half_h;
		
	public Ball[] balls;

	public void setup()
	{
		size(screenWidth, screenHeight, P3D);
		
		noStroke();
		fill(255);
		
		setImage();
		setBalls();
	}
	
	public void setImage()
	{
		logo = loadImage( "lbi_logo.gif" );
		image( logo, 0, 0 );

		int numPixels = logo.width * logo.height;
		int row = -1;
		
		logo.loadPixels();
		

		for( int i = 0; i < numPixels; ++i )
		{
		  int c = logo.pixels[i];
		  
		  int x = i % logo.width;
		  if (x == 0) row++;
		  int y = row;
		  
		  if( c < -1 )
		  {  
			 points.add( new PVector(x, y) );
		  }
		}
		
		numBalls = points.size();
		balls = new Ball[numBalls];
		
		println(numBalls);		
		println(points.get(350));
	}
	
	public void setBalls()
	{
	  for( int i = 0; i < numBalls; i++ )
	  {
		  //set balls around circle
		  //float rd    =  random(360);
		  //balls[i]    =  new Ball( this, orbit * cos( TWO_PI * rd/360 ), orbit * sin( TWO_PI * rd/360 ), floor( random(5,15) ), i );
		  PVector pt	=	(PVector) points.get(i);
		  println( i + " " + pt );
		  balls[i]    	=  	new Ball( this, orbit * pt.x, orbit * pt.y, floor( random(6,10) ), i );
	  }
	  
	  //sets a random connection to another ball guarenteeing 
	  //each ball having at least one line
	  for( int i = 0; i < numBalls; i++ )
	  {
	    for( int j = 0; j < numBalls*.1; j++ )
	    {
	      balls[i].makeConnection( floor(random(numBalls)) );
	    }
	  }
	}
	
	public void draw()
	{
		background(255);
		  
		fill(255,0,0);
		  
		//simple camera rotation
		half_w = 200.0f;//width * 0.5f;
		half_h = 150.0f;//height * 0.5f;
		  
		translate( half_w, half_h );
		//rotate( radians(frameCount * 0.35) );
		  
		for( int i = 0; i < numBalls; i++ )
		{
		  balls[i].draw(mouseX - half_w, mouseY - half_h);
		}
	}
	
	public void mousePressed()
	{
		println( frameRate );
		//setBalls();
	}
}

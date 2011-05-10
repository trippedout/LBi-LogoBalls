package com.lbi.walls.views;

import java.util.ArrayList;

import processing.core.PApplet;

import com.lbi.walls.LogoBalls;

public class Ball
{
	LogoBalls parent;
	
	int id;
  	float x, y;
  	float sx, sy, size;
  	
  	int seed;
	int force = 3500;
  	int count = 0;
  	
	Boolean hasLine = false;
	ArrayList lines = new ArrayList();
	
  
  public Ball( LogoBalls p, float Sx, float Sy, float Size, int ID )
  {
	  parent = p;
	  sx = Sx;
	  sy = Sy;
	  x = sx;
	  y = sy;
	  size = Size;
	  id = ID;
	  
	  seed = PApplet.floor( parent.random(10000) );
  }
  
  public void makeConnection( int ID )
  {
    float xDif = parent.balls[ID].x-x;
    float yDif = parent.balls[ID].y-y;
    float distance = PApplet.sqrt(xDif*xDif+yDif*yDif);
    
    if( distance < 40 )
    {
    	hasLine = true;
    	lines.add( new Integer( ID ));
    }
  }
  
  public void draw(float mx, float my)
  {
    count++;
    x = x + 4 * PApplet.sin((seed + count)*.03f);
    y = y + 4 * PApplet.cos((seed + count)*.03f);
    
    //x += ( x - nx ) * 0.3f;
    //y += ( y - ny ) * 0.3f;
    
    float xDif = mx-x;
    float yDif = my-y;
    float distance = PApplet.sqrt(xDif*xDif+yDif*yDif);
    float tempX = x - (force/distance)*(xDif/distance);
    float tempY = y - (force/distance)*(yDif/distance);
    x = (sx - x)/2+tempX;
    y = (sy - y)/2+tempY;

    //2D Circles
    if( hasLine )
    {
    	parent.smooth();
    	parent.stroke(200);
    	parent.line( x, y, 0, parent.balls[(Integer)lines.get(0)].x, parent.balls[(Integer)lines.get(0)].y, 0 );
    	parent.noStroke();    
    	parent.noSmooth();
    }
    
    float s = size * (PApplet.sin( (count+seed) * .05f ) + 2 );
    parent.ellipse( x, y, s, s );
    
    /* 
    //3D Sphere
    pushMatrix();
      translate( x, y );
      sphere( size * (sin( (count+seed) * .02 ) + 2 ) );
      stroke(180);
      line( 0, 0, 0, connect.x - x, connect.y - y, 0 );
      noStroke();
    popMatrix();
    */
  }
}

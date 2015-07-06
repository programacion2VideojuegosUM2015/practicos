package ar.edu.um.vj2015.mco;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class PantallaGameOver {
	private int tics;
	private int timer;

public PantallaGameOver() {
	tics=0;
	timer=99;
 }

//-----------------------------------------------------------------------------------------
// MÉTODOS
//-----------------------------------------------------------------------------------------
public int getTics() { return tics; }
public void setTics(int tics) { this.tics = tics; }	
//-----------------------------------------------------------------------------------------
public int getTimer() { return timer; }
public void setTimer(int timer) { this.timer = timer; }	
//-----------------------------------------------------------------------------------------


public void animacionGameOver() { 	 
  	 Timer.schedule(new Task(){
           @Override
           public void run() {
               tics++;
               
               if(tics > 60){                          	                 	 
                   tics = 0;                   
               }
               
               if(tics%10==0){if(timer>0 && Juego.gameOver==0 && Juego.titleScreen==0){timer--;}}
               if(tics==20 && Juego.gameOver==1){Juego.titleScreen=1;}
               if(tics==20){Juego.gameOver=0;}
                              
            }
       }
       ,0,30/35.0f); 	 
	 }        
}
//-----------------------------------------------------------------------------------------
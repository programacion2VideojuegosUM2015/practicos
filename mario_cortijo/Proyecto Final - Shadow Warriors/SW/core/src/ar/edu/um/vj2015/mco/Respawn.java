package ar.edu.um.vj2015.mco;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class Respawn {
	private boolean enemigoSpamea;
	private int tics;
	private int go;

public Respawn() {
	enemigoSpamea = true;
	tics=1;
	go=0;
 }

//-----------------------------------------------------------------------------------------
// MÉTODOS
//-----------------------------------------------------------------------------------------
public boolean getEnemigoSpamea() { return enemigoSpamea; }
public void setEnemigoSpamea(boolean enemigoSpamea) { this.enemigoSpamea = enemigoSpamea; }	
//-----------------------------------------------------------------------------------------
public int getTics() { return tics; }
public void setTics(int tics) { this.tics = tics; }	
//-----------------------------------------------------------------------------------------
public int getParpadeoGo() { return go; }
public void setParpadeoGo(int go) { this.go = go; }	
//-----------------------------------------------------------------------------------------

public void RespawnEnemy() { 	 
  	 Timer.schedule(new Task(){
           @Override
           public void run() {
               tics++;
               if(tics > 25){                          	                 	 
                   tics = 1;
               }
                if (tics == 1) {enemigoSpamea = true;}
                if (tics == 2) {enemigoSpamea = true;}
                if (tics == 3) {enemigoSpamea = true;}
                if (tics == 5) {enemigoSpamea = false;}
                if(tics % 2 == 0) {go=1;} else {go=0;}
               }
       }
       ,0,30/30.0f); 	 
	 }        
}
//-----------------------------------------------------------------------------------------


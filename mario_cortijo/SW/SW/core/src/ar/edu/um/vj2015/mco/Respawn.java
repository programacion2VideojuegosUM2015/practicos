package ar.edu.um.vj2015.mco;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class Respawn {
	private boolean enemigoSpamea;
	private int tics;
	private int go;

public Respawn() {
	enemigoSpamea = true;
	tics=0;
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
               if(tics > 20){                          	                 	 
                   tics = 1;
               }
                if (tics == 1) {enemigoSpamea = true; go=0;}
                if (tics == 2) {go=1;}
                if (tics == 3) {enemigoSpamea = false; go=0;}
                if (tics == 4) {go=1;}
                if (tics == 5) {enemigoSpamea = false; go=0;}
                if (tics == 6) {go=1;}
                if (tics == 7) {enemigoSpamea = false; go=0;}
                if (tics == 8) {go=1;}
                if (tics == 9) {enemigoSpamea = false; go=0;}
                if (tics == 10) {go=1;}
                if (tics == 11) {enemigoSpamea = false; go=0;}
                if (tics == 12) {go=1;}
                if (tics == 13) {enemigoSpamea = false; go=0;}
                if (tics == 14) {go=1;}
                if (tics == 15) {enemigoSpamea = false; go=0;}
                if (tics == 16) {go=1;}
                if (tics == 17) {enemigoSpamea = false; go=0;}
                if (tics == 18) {go=1;}
                if (tics == 19) {enemigoSpamea = false; go=0;}
                if (tics == 20) {go=1;}
               }
       }
       ,0,30/30.0f); 	 
	 }        
}
//-----------------------------------------------------------------------------------------


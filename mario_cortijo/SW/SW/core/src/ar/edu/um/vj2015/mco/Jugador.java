package ar.edu.um.vj2015.mco;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class Jugador {
	private Texture ryuCurrentFrame;
	private Texture ryuStance;
	private Texture ryuPunch01;
	private Texture ryuPunch02;
	private Texture ryuPunch03;
	private Texture ryuPunch04;
	private Texture ryuRun01;
	private Texture ryuRun02;
	private Texture ryuRun03;
	private Texture ryuRun04;
	private Texture ryuRun05;
	private Texture ryuRun06;
	private Texture ryuWalk01;
	private Texture ryuWalk02;
	private Texture ryuWalk03;
	private Texture ryuWalk04;
	private Texture ryuWalk05;
	private Texture ryuWalk06;
	private Texture ryuDie01;
	private Texture ryuDie02;
	private Texture ryuDie03;
	private Texture ryuDie04;
	private Texture ryuDie05;
	private Texture ryuBlank;
	private Texture ryuGet01;
	private Texture ryuGet02;
	private Texture ryuKick01;
	private Texture ryuKick02;
	private Texture ryuKick03;
	private Texture ryuKick04;
	private Texture ryuHurt01;
	private Texture ryuHurt02;
	private Sound soundFall1;
	private Sound soundRoundhouse1;
	private Sound soundRoundhouse3;
	private Sound soundMalehurt2;
	private Sound soundMalesfx3;
	private Sound soundHit1;
	private Sound soundHit6;
	private Sound soundHit14;
	private Rectangle ryuHitBox;
	private String testString;
	private String ryuVidasString;
	private String cantidadHP;
	private int kills;
	private int score;
	private int frame;
	private int lastFrame;
	private int damage;
	private int combo;
	private boolean xscale;
	private int xscaleInt;
	private String ryuEstado;
	private int hp;
	private int vidas;
	private float speedTimer;
	

	public Jugador () {
		ryuHitBox = new Rectangle();
		ryuHitBox.x = 50; 
		ryuHitBox.y = 80;
		ryuHitBox.width = 40;
		ryuHitBox.height = 3;
		kills = 0;
		score = 0;
		frame = 0;
		damage = 0;
		combo = 0;
		xscale = false;
		xscaleInt = 1;
		testString = " ";
		ryuVidasString = "HP: 3 - KILLS: 0";
		cantidadHP = "iiiiiiiii";
		ryuEstado = "Stance";
		hp = 9;
		vidas = 3;
		speedTimer = 2/30.0f;
		
	}

	//-----------------------------------------------------------------------------------------
	// MÉTODOS
	//-----------------------------------------------------------------------------------------
	public Texture getRyuCurrentFrame() { return ryuCurrentFrame; }
    public void setRyuCurrentFrame(Texture bucketImage) { this.ryuCurrentFrame = bucketImage; }	
	//-----------------------------------------------------------------------------------------
	public Rectangle getRyuHitBox() { return ryuHitBox; }
    public void setRyuHitBox(Rectangle ryuHitBox) { this.ryuHitBox = ryuHitBox; }	
	//-----------------------------------------------------------------------------------------
	public float getRyuX() { return ryuHitBox.x; }
    public void setRyuX(float ryux) { this.ryuHitBox.x = ryux; }	
	//-----------------------------------------------------------------------------------------
	public float getRyuY() { return ryuHitBox.y; }
    public void setRyuY(float ryuy) { this.ryuHitBox.y = ryuy; }	
	//-----------------------------------------------------------------------------------------
	public String getRyuTESTstring() { return testString; }
    public void setRyuTESTstring(String scoreName) { this.testString = scoreName; }	
	//-----------------------------------------------------------------------------------------
	public String getRyuVidasString() { return ryuVidasString; }
    public void setRyuVidasString(String ryuVidasString) { this.ryuVidasString = ryuVidasString; }	
	//-----------------------------------------------------------------------------------------
	public int getRyuScore() { return score; }
    public void setRyuScore(int score) { this.score = score; }	
	//-----------------------------------------------------------------------------------------
	public int getRyuKills() { return kills; }
    public void setRyuKills(int kills) { this.kills = kills; }	
	//-----------------------------------------------------------------------------------------
	public int getRyuFrame() { return frame; }
    public void setRyuFrame(int frame) { this.frame = frame; }	
	//-----------------------------------------------------------------------------------------
    public int getRyuLastFrame() { return lastFrame; }
    public void setRyuLastFrame(int lastFrame) { this.lastFrame = lastFrame; }	
	//-----------------------------------------------------------------------------------------
    public int getRyuDamage() { return damage; }
    public void setRyuDamage(int damage) { this.damage = damage; }	
	//-----------------------------------------------------------------------------------------
    public int getRyuCombo() { return combo; }
    public void setRyuCombo(int combo) { this.combo = combo; }	
	//-----------------------------------------------------------------------------------------
    public boolean getRyuXScale() { return xscale; }
    public void setRyuXScale(boolean xscale) { this.xscale = xscale; }	
    //-----------------------------------------------------------------------------------------
    public int getRyuXScaleInt() { return xscaleInt; }
    public void setRyuXScaleInt(int xscaleInt) { this.xscaleInt = xscaleInt; }	
    //-----------------------------------------------------------------------------------------
    public String getRyuEstado() { return ryuEstado; }
    public void setRyuEstado(String ryuEstado) { this.ryuEstado = ryuEstado; }	
	//-----------------------------------------------------------------------------------------
    public int getRyuHP() { return hp; }
    public void setRyuHP(int hp) { this.hp = hp; }	
    //-----------------------------------------------------------------------------------------
    public String getRyuCantidadHP() { return cantidadHP; }
    public void setRyuCantidadHP(String cantidadHP) { this.cantidadHP = cantidadHP; }	
    //-----------------------------------------------------------------------------------------
    public int getRyuVidas() { return vidas; }
    public void setRyuVidas(int vidas) { this.vidas = vidas; }	
    //-----------------------------------------------------------------------------------------
    
    
    public void createJugador() { 
    	ryuCurrentFrame = new Texture(Gdx.files.internal("ryu/ryuStance.png")); 
    	ryuStance = new Texture(Gdx.files.internal("ryu/ryuStance.png"));
    	ryuPunch01 = new Texture(Gdx.files.internal("ryu/ryuPunch01.png")); 
    	ryuPunch02 = new Texture(Gdx.files.internal("ryu/ryuPunch02.png")); 
    	ryuPunch03 = new Texture(Gdx.files.internal("ryu/ryuPunch03.png")); 
    	ryuPunch04 = new Texture(Gdx.files.internal("ryu/ryuPunch04.png")); 
    	ryuKick01 = new Texture(Gdx.files.internal("ryu/ryuKick01.png")); 
    	ryuKick02 = new Texture(Gdx.files.internal("ryu/ryuKick02.png")); 
    	ryuKick03 = new Texture(Gdx.files.internal("ryu/ryuKick03.png")); 
    	ryuKick04 = new Texture(Gdx.files.internal("ryu/ryuKick04.png")); 
    	ryuRun01 = new Texture(Gdx.files.internal("ryu/ryuRun01.png"));
    	ryuRun02 = new Texture(Gdx.files.internal("ryu/ryuRun02.png")); 
    	ryuRun03 = new Texture(Gdx.files.internal("ryu/ryuRun03.png")); 
    	ryuRun04 = new Texture(Gdx.files.internal("ryu/ryuRun04.png")); 
    	ryuRun05 = new Texture(Gdx.files.internal("ryu/ryuRun05.png")); 
    	ryuRun06 = new Texture(Gdx.files.internal("ryu/ryuRun06.png"));
    	ryuWalk01 = new Texture(Gdx.files.internal("ryu/ryuWalk01.png"));
    	ryuWalk02 = new Texture(Gdx.files.internal("ryu/ryuWalk02.png")); 
    	ryuWalk03 = new Texture(Gdx.files.internal("ryu/ryuWalk03.png")); 
    	ryuWalk04 = new Texture(Gdx.files.internal("ryu/ryuWalk04.png")); 
    	ryuWalk05 = new Texture(Gdx.files.internal("ryu/ryuWalk05.png")); 
    	ryuWalk06 = new Texture(Gdx.files.internal("ryu/ryuWalk06.png"));
    	ryuDie01 = new Texture(Gdx.files.internal("ryu/ryuDie01.png"));
    	ryuDie02 = new Texture(Gdx.files.internal("ryu/ryuDie02.png")); 
    	ryuDie03 = new Texture(Gdx.files.internal("ryu/ryuDie03.png")); 
    	ryuDie04 = new Texture(Gdx.files.internal("ryu/ryuDie04.png"));
    	ryuDie05 = new Texture(Gdx.files.internal("ryu/ryuDie05.png")); 
    	ryuBlank = new Texture(Gdx.files.internal("ryu/ryuBlank.png"));
    	ryuGet01 = new Texture(Gdx.files.internal("ryu/ryuGet01.png")); 
    	ryuGet02 = new Texture(Gdx.files.internal("ryu/ryuGet02.png"));
    	ryuHurt01 = new Texture(Gdx.files.internal("ryu/ryuHurt01.png")); 
    	ryuHurt02 = new Texture(Gdx.files.internal("ryu/ryuHurt02.png"));
    	//SONIDOS
  	    soundFall1 = Gdx.audio.newSound(Gdx.files.internal("sonidos/fall1.mp3"));
  	    soundRoundhouse1 = Gdx.audio.newSound(Gdx.files.internal("sonidos/roundhouse1.mp3"));
  	    soundRoundhouse3 = Gdx.audio.newSound(Gdx.files.internal("sonidos/roundhouse3.mp3"));
  	    soundMalehurt2 = Gdx.audio.newSound(Gdx.files.internal("sonidos/malehurt2.mp3"));
  	    soundMalesfx3 = Gdx.audio.newSound(Gdx.files.internal("sonidos/malesfx3.mp3"));
  	    soundHit1 = Gdx.audio.newSound(Gdx.files.internal("sonidos/hit1.mp3"));
  	    soundHit6 = Gdx.audio.newSound(Gdx.files.internal("sonidos/hit6.mp3"));
  	    soundHit14 = Gdx.audio.newSound(Gdx.files.internal("sonidos/hit14.mp3"));
    }
    //-----------------------------------------------------------------------------------------
    public void SetPlayer2() { 
		ryuHitBox.x = 480; // LO MUEVO FUERA DE LA VISTA POR AHORA HASTA IMPLEMENTARLO COMO SE DEBE
    }
    //-----------------------------------------------------------------------------------------
     public void TestAnimation() { 	 
    	 Timer.schedule(new Task(){
             @Override
             public void run() {
                 frame++;
                 if(hp==9){cantidadHP = "iiiiiiiii";}
                 if(hp==8){cantidadHP = "iiiiiiii";}
                 if(hp==7){cantidadHP = "iiiiiii";}
                 if(hp==6){cantidadHP = "iiiiii";}
                 if(hp==5){cantidadHP = "iiiii";}
                 if(hp==4){cantidadHP = "iiii";}
                 if(hp==3){cantidadHP = "iii";}
                 if(hp==2){cantidadHP = "ii";}
                 if(hp==1){cantidadHP = "i";}
                 if(hp==0){cantidadHP = " ";}

                 ryuVidasString = "player x " + vidas + " - KILLS: " + kills;
                 if(frame > lastFrame){                          	                 	 
                     ryuEstado="Stance";
                     frame = 0;
                 }
               //----------------------------------------------------------
               //  ANIMACION TROMPADA
               //----------------------------------------------------------
                 if(ryuEstado=="Punch") {
                	 if (frame == 1) {ryuCurrentFrame = ryuPunch01; if(combo==4){combo = 0;} } 
                     if (frame == 2) {ryuCurrentFrame = ryuPunch02; if(damage==0){damage=1;} if(combo<4){combo++;} soundRoundhouse1.play(); }
                     if (frame == 3) {ryuCurrentFrame = ryuPunch03; damage=0;}
                     if (frame == 4) {ryuCurrentFrame = ryuPunch04;} 
                 }
                 //----------------------------------------------------------
                 //  ANIMACION PATADA
                 //----------------------------------------------------------
                 
                 if(ryuEstado=="Kick") {
                	 if (frame == 1) {ryuCurrentFrame = ryuKick01; if(combo==4){combo = 0;} }
                     if (frame == 2) {ryuCurrentFrame = ryuKick02; if(damage==0){damage=1;} if(combo<4){combo++;} soundRoundhouse3.play(); }
                     if (frame == 3) {ryuCurrentFrame = ryuKick03; damage=0;}
                     if (frame == 4) {ryuCurrentFrame = ryuKick04;} 
                 }         
                 //----------------------------------------------------------
                 //  ANIMACION CORRER
                 //----------------------------------------------------------
                   if(ryuEstado=="Run") {
                  	 if (frame == 1) {ryuCurrentFrame = ryuRun01;}
                  	 if (frame == 2) {ryuCurrentFrame = ryuRun02;}	 
                     if (frame == 3) {ryuCurrentFrame = ryuRun03;}
                     if (frame == 4) {ryuCurrentFrame = ryuRun04;}
                     if (frame == 5) {ryuCurrentFrame = ryuRun05;}
                     if (frame == 6) {ryuCurrentFrame = ryuRun06;} 
                  }
                  //----------------------------------------------------------
                  //  ANIMACION CORRER
                  //----------------------------------------------------------
                    if(ryuEstado=="Walk") {
                  	 if (frame == 1) {ryuCurrentFrame = ryuWalk01;}
                   	 if (frame == 3) {ryuCurrentFrame = ryuWalk02;}	 
                     if (frame == 5) {ryuCurrentFrame = ryuWalk03;}
                     if (frame == 7) {ryuCurrentFrame = ryuWalk04;}
                     if (frame == 9) {ryuCurrentFrame = ryuWalk05;}
                     if (frame == 11) {ryuCurrentFrame = ryuWalk06;} 
                   } 
                  //----------------------------------------------------------
                  //  ANIMACION GOLPEADO
                  //----------------------------------------------------------
                     if(ryuEstado=="Hurt") {
                       if (frame == 1) {ryuCurrentFrame = ryuHurt01; hp = hp-1; combo=0;} 
                       if (frame == 4) {ryuCurrentFrame = ryuHurt02; soundHit1.play();}
                       if (frame == 7) {ryuCurrentFrame = ryuHurt01; hp = hp-1;}
                       if (frame == 10) {ryuCurrentFrame = ryuHurt02; soundHit1.play();} 
                       if (frame == 13) {ryuCurrentFrame = ryuDie01;}
                       if (frame == 14) {ryuCurrentFrame = ryuDie02; ryuHitBox.x+=-6 * xscaleInt; ryuHitBox.y+=11; soundHit6.play();}	 
                       if (frame == 15) {ryuCurrentFrame = ryuDie03; ryuHitBox.x+=-6 * xscaleInt; ryuHitBox.y+=16;}	
                       if (frame == 16) {ryuCurrentFrame = ryuDie03; ryuHitBox.x+=-6 * xscaleInt; ryuHitBox.y+=11;}
                       if (frame == 17) {ryuCurrentFrame = ryuDie03; ryuHitBox.x+=-6 * xscaleInt; ryuHitBox.y+=6;}
                       if (frame == 18) {ryuCurrentFrame = ryuDie03; ryuHitBox.x+=-12 * xscaleInt;}
                       if (frame == 19) {ryuCurrentFrame = ryuDie03; ryuHitBox.x+=-6 * xscaleInt; ryuHitBox.y+=-6;}
                       if (frame == 20) {ryuCurrentFrame = ryuDie04; ryuHitBox.x+=-6 * xscaleInt; ryuHitBox.y+=-11;}
                       if (frame == 21) {ryuCurrentFrame = ryuDie04; ryuHitBox.x+=-6 * xscaleInt; ryuHitBox.y+=-16;}
                       if (frame == 22) {ryuCurrentFrame = ryuDie04; ryuHitBox.x+=-6 * xscaleInt; ryuHitBox.y+=-11;}
                       if (frame == 23) {ryuCurrentFrame = ryuGet01; ryuHitBox.x+=-6 * xscaleInt; soundFall1.play();}
                       if (frame == 24) {ryuCurrentFrame = ryuGet01; ryuHitBox.x+=-6 * xscaleInt;}
                       if (frame == 25) {ryuCurrentFrame = ryuGet02; ryuHitBox.x+=-6 * xscaleInt;}
                       if (frame == 26) {ryuCurrentFrame = ryuGet02; ryuHitBox.x+=-6 * xscaleInt;}
                       if (frame == 27) {ryuCurrentFrame = ryuStance; hp = hp-1;}   
                      }  
                   
                 //----------------------------------------------------------
                 //  ANIMACION MORIR
                 //----------------------------------------------------------
                   if(ryuEstado=="Dead") {
                       if (frame == 1) {ryuCurrentFrame = ryuHurt01; hp = hp-1; combo=0;} 
                       if (frame == 4) {ryuCurrentFrame = ryuHurt02; soundHit1.play();}
                       if (frame == 7) {ryuCurrentFrame = ryuHurt01; hp = hp-1;}
                       if (frame == 10) {ryuCurrentFrame = ryuHurt02; soundHit1.play();}                        
                       if (frame == 11) {ryuCurrentFrame = ryuDie01; hp = hp-1;}
                       if (frame == 12) {ryuCurrentFrame = ryuDie02; ryuHitBox.x+=-6 * xscaleInt; ryuHitBox.y+=11; soundHit6.play(); }	 
                       if (frame == 13) {ryuCurrentFrame = ryuDie03; ryuHitBox.x+=-6 * xscaleInt; ryuHitBox.y+=16;}	
                       if (frame == 14) {ryuCurrentFrame = ryuDie03; ryuHitBox.x+=-6 * xscaleInt; ryuHitBox.y+=11; soundMalehurt2.play();}
                       if (frame == 15) {ryuCurrentFrame = ryuDie03; ryuHitBox.x+=-6 * xscaleInt; ryuHitBox.y+=6;}
                       if (frame == 16) {ryuCurrentFrame = ryuDie03; ryuHitBox.x+=-12 * xscaleInt;}
                       if (frame == 17) {ryuCurrentFrame = ryuDie03; ryuHitBox.x+=-6 * xscaleInt; ryuHitBox.y+=-6;}
                       if (frame == 18) {ryuCurrentFrame = ryuDie04; ryuHitBox.x+=-6 * xscaleInt; ryuHitBox.y+=-11;}
                       if (frame == 19) {ryuCurrentFrame = ryuDie04; ryuHitBox.x+=-6 * xscaleInt; ryuHitBox.y+=-16;}
                       if (frame == 20) {ryuCurrentFrame = ryuDie04; ryuHitBox.x+=-6 * xscaleInt; ryuHitBox.y+=-11;}
                       if (frame == 21) {ryuCurrentFrame = ryuGet01; ryuHitBox.x+=-6 * xscaleInt; soundFall1.play();}                      
                       if (frame == 22) {ryuCurrentFrame = ryuDie05;}                         
                       if (frame == 31) {ryuCurrentFrame = ryuDie05;} 
                       if (frame == 32) {ryuCurrentFrame = ryuBlank;} 
                       if (frame == 33) {ryuCurrentFrame = ryuDie05;} 
                       if (frame == 34) {ryuCurrentFrame = ryuBlank;} 
                       if (frame == 35) {ryuCurrentFrame = ryuDie05;} 
                       if (frame == 36) {ryuCurrentFrame = ryuBlank;
                                          if(vidas>0){
                    	                  ryuEstado="Salto";
                    	                  frame=0;
                    	                  lastFrame=10;
                    	                  ryuHitBox.x = 50;
                    	                  ryuHitBox.y = 80;
                    	                  hp = 9;
                    	                  vidas = vidas-1; 
                    	                  xscale=false;
                    	                  xscaleInt=1;}
                                          } 
                     
                    }  
               //----------------------------------------------------------
               //  ANIMACION IDLE
               //----------------------------------------------------------
                 if(ryuEstado=="Stance") {
                	 if (frame == 1) {ryuCurrentFrame = ryuStance;}
                     if (frame == 3) {ryuCurrentFrame = ryuStance; combo = 0;}
                 }
               //----------------------------------------------------------
               //  ANIMACION SALTO
               //----------------------------------------------------------  
                 if(ryuEstado=="Salto") {
                	 if (frame == 1) {ryuCurrentFrame = ryuStance;}
                     if (frame == 2) {ryuCurrentFrame = ryuBlank;}
                	 if (frame == 3) {ryuCurrentFrame = ryuStance;}
                     if (frame == 4) {ryuCurrentFrame = ryuBlank;}	 
                	 if (frame == 5) {ryuCurrentFrame = ryuStance;}
                     if (frame == 6) {ryuCurrentFrame = ryuBlank;}	 
                	 if (frame == 7) {ryuCurrentFrame = ryuStance;}
                     if (frame == 8) {ryuCurrentFrame = ryuBlank;}	 
                	 if (frame == 9) {ryuCurrentFrame = ryuStance;}
                     if (frame == 10) {ryuCurrentFrame = ryuBlank; ryuEstado="Stance";}	 
                 }
               //------------------------------------------------------------------
               //------------------------------------------------------------------
             }
         }
         ,0,speedTimer); 	 
	 }        
     //-----------------------------------------------------------------------------------------
     public void resetJugador() { 
 		ryuHitBox.x = 50;
 		ryuHitBox.y = 80;
 		kills = 0;
 		score = 0;
 		frame = 0;
 		damage = 0;
 		combo = 0;
 		xscale = false;
 		xscaleInt = 1;
 		testString = " ";
 		ryuVidasString = "HP: 3 - KILLS: 0";
 		cantidadHP = "iiiiiiiii";
 		ryuEstado = "Stance";
 		hp = 9;
 		vidas = 3;
 		speedTimer = 2/30.0f;
     }
     //-----------------------------------------------------------------------------------------
     public void dispose() {
     	ryuCurrentFrame.dispose(); 
     	ryuStance.dispose();
     	ryuPunch01.dispose();
     	ryuPunch02.dispose();
     	ryuPunch03.dispose();
     	ryuPunch04.dispose();
     	ryuKick01.dispose();
     	ryuKick02.dispose();
     	ryuKick03.dispose();
     	ryuKick04.dispose();
     	ryuRun01.dispose();
     	ryuRun02.dispose();
     	ryuRun03.dispose();
     	ryuRun04.dispose();
     	ryuRun05.dispose();
     	ryuRun06.dispose();
     	ryuWalk01.dispose();
     	ryuWalk02.dispose();
     	ryuWalk03.dispose();
     	ryuWalk04.dispose();
     	ryuWalk05.dispose();
     	ryuWalk06.dispose();
     	ryuDie01.dispose();
     	ryuDie02.dispose();
     	ryuDie03.dispose();
     	ryuDie04.dispose();
     	ryuDie05.dispose();
     	ryuBlank.dispose();
     	ryuGet01.dispose();
     	ryuGet02.dispose();
     	ryuHurt01.dispose();
     	ryuHurt02.dispose();
   	    soundFall1.dispose();
   	    soundRoundhouse1.dispose();
   	    soundRoundhouse3.dispose();
   	    soundMalehurt2.dispose();
   	    soundMalesfx3.dispose();
   	    soundHit1.dispose();
   	    soundHit6.dispose();
   	    soundHit14.dispose();
     }
}

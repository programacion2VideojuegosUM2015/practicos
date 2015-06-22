package ar.edu.um.vj2015.mco;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class NinjaEnemigo {
	
	private Texture ninjaCurrentFrame;
	private Texture ninjaStance;
	private Texture ninjaPunch01;
	private Texture ninjaPunch02;
	private Texture ninjaPunch03;
	private Texture ninjaPunch04;
	private Texture ninjaRun01;
	private Texture ninjaRun02;
	private Texture ninjaRun03;
	private Texture ninjaRun04;
	private Texture ninjaRun05;
	private Texture ninjaRun06;
	private Texture ninjaDie00;
	private Texture ninjaDie01;
	private Texture ninjaDie02;
	private Texture ninjaDie03;
	private Texture ninjaDie04;
	private Texture ninjaBlank;
	private Texture ninjaGet01;
	private Texture ninjaKick01;
	private Texture ninjaKick02;
	private Texture ninjaKick03;
	private Texture ninjaKick04;
	private Texture ninjaJump01;
	private Texture ninjaJump02;
	private Texture ninjaHurt01;
	private Texture ninjaHurt02;
	private Sound soundFall1;
	private Sound soundRoundhouse1;
	private Sound soundRoundhouse3;
	private Sound soundMalesfx3;
	private Sound soundHit1;
	private Sound soundHit6;
	private int yaSalto;
	private int alturaCaida;
	private float x = 0;
	private float y = 480;
	private boolean xscale = false;
	private int xscaleInt = 1;
	private int width = 58;
	private int height = 58;
	private int vida = 6;
	private String estadoEnemy = "Salto";
	private int frame;
	private int lastFrame;
	private int combo;
	private int conducta;
	private String cantidadHP;
	private Rectangle enemyHitbox;
	

	public NinjaEnemigo (){
	  enemyHitbox = new Rectangle (getEnemyX(), getEnemyY(), 40, 3);
  	  ninjaCurrentFrame = new Texture(Gdx.files.internal("ninja/ninjaStance.png")); 
  	  ninjaStance = new Texture(Gdx.files.internal("ninja/ninjaStance.png"));
  	  ninjaPunch01 = new Texture(Gdx.files.internal("ninja/ninjaPunch01.png")); 
  	  ninjaPunch02 = new Texture(Gdx.files.internal("ninja/ninjaPunch02.png")); 
  	  ninjaPunch03 = new Texture(Gdx.files.internal("ninja/ninjaPunch03.png")); 
  	  ninjaPunch04 = new Texture(Gdx.files.internal("ninja/ninjaPunch04.png")); 
  	  ninjaKick01 = new Texture(Gdx.files.internal("ninja/ninjaKick01.png")); 
  	  ninjaKick02 = new Texture(Gdx.files.internal("ninja/ninjaKick02.png")); 
  	  ninjaKick03 = new Texture(Gdx.files.internal("ninja/ninjaKick03.png")); 
  	  ninjaKick04 = new Texture(Gdx.files.internal("ninja/ninjaKick04.png")); 
  	  ninjaRun01 = new Texture(Gdx.files.internal("ninja/ninjaWalk01.png"));
  	  ninjaRun02 = new Texture(Gdx.files.internal("ninja/ninjaWalk02.png")); 
  	  ninjaRun03 = new Texture(Gdx.files.internal("ninja/ninjaWalk03.png")); 
  	  ninjaRun04 = new Texture(Gdx.files.internal("ninja/ninjaWalk04.png")); 
  	  ninjaRun05 = new Texture(Gdx.files.internal("ninja/ninjaWalk05.png")); 
  	  ninjaRun06 = new Texture(Gdx.files.internal("ninja/ninjaWalk06.png"));
  	  ninjaDie00 = new Texture(Gdx.files.internal("ninja/ninjaDie00.png"));
  	  ninjaDie01 = new Texture(Gdx.files.internal("ninja/ninjaDie01.png"));
  	  ninjaDie02 = new Texture(Gdx.files.internal("ninja/ninjaDie02.png")); 
  	  ninjaDie03 = new Texture(Gdx.files.internal("ninja/ninjaDie03.png")); 
  	  ninjaDie04 = new Texture(Gdx.files.internal("ninja/ninjaDie04.png"));
  	  ninjaBlank = new Texture(Gdx.files.internal("ryu/ryuBlank.png")); 
   	  ninjaGet01 = new Texture(Gdx.files.internal("ninja/ninjaGet01.png")); 
   	  ninjaJump01 = new Texture(Gdx.files.internal("ninja/ninjaJump01.png")); 
  	  ninjaJump02 = new Texture(Gdx.files.internal("ninja/ninjaJump02.png"));
   	  ninjaHurt01 = new Texture(Gdx.files.internal("ninja/ninjaHurt01.png")); 
  	  ninjaHurt02 = new Texture(Gdx.files.internal("ninja/ninjaHurt02.png")); 
	  soundFall1 = Gdx.audio.newSound(Gdx.files.internal("sonidos/fall1.mp3"));
	  soundRoundhouse1 = Gdx.audio.newSound(Gdx.files.internal("sonidos/roundhouse1.mp3"));
	  soundRoundhouse3 = Gdx.audio.newSound(Gdx.files.internal("sonidos/roundhouse3.mp3"));
	  soundMalesfx3 = Gdx.audio.newSound(Gdx.files.internal("sonidos/malesfx3.mp3"));
	  soundHit1 = Gdx.audio.newSound(Gdx.files.internal("sonidos/hit1.mp3"));
	  soundHit6 = Gdx.audio.newSound(Gdx.files.internal("sonidos/hit6.mp3"));
	  frame = 0;
	  lastFrame = 6;
	  combo = 0;
	  conducta = MathUtils.random(1,2);
	  cantidadHP = "iiiiiiiii";
	  yaSalto = 0;
	}
	
	public void dispose() {
     ninjaCurrentFrame.dispose();
	 ninjaStance.dispose();
	 ninjaPunch01.dispose();
	 ninjaPunch02.dispose();
	 ninjaPunch03.dispose();
	 ninjaPunch04.dispose();
	 ninjaRun01.dispose();
	 ninjaRun02.dispose();
	 ninjaRun03.dispose();
	 ninjaRun04.dispose();
	 ninjaRun05.dispose();
	 ninjaRun06.dispose();
	 ninjaDie00.dispose();
 	 ninjaDie01.dispose();
	 ninjaDie02.dispose();
	 ninjaDie03.dispose();
	 ninjaDie04.dispose();
	 ninjaBlank.dispose();
	 ninjaGet01.dispose();
	 ninjaKick01.dispose();
	 ninjaKick02.dispose();
	 ninjaKick03.dispose();
	 ninjaKick04.dispose();
	 ninjaJump01.dispose();
	 ninjaJump02.dispose();
	 ninjaHurt01.dispose();
	 ninjaHurt02.dispose();
	 soundFall1.dispose();
	 soundRoundhouse1.dispose();
	 soundRoundhouse3.dispose();
	 soundMalesfx3.dispose();
	 soundHit1.dispose();
	 soundHit6.dispose();
	}
	
	//-----------------------------------------------------------------------------------------
	// MÉTODOS
	//-----------------------------------------------------------------------------------------
	public Texture getEnemyCurrentFrame() { return ninjaCurrentFrame; }
    public void setEnemyCurrentFrame(Texture ninjaCurrentFrame) { this.ninjaCurrentFrame = ninjaCurrentFrame; }	
	//-----------------------------------------------------------------------------------------
	public Rectangle getEnemyHitBox(){ return enemyHitbox; }
	//-----------------------------------------------------------    	
	public int getEnemyYaSalto() { return yaSalto; }
	public void seEnemyYaSalto(int yaSalto) { this.yaSalto = yaSalto; }
	//-----------------------------------------------------------
	public int getAlturaCaida() { return alturaCaida; }
	public void setAlturaCaida(int alturaCaida) { this.alturaCaida = alturaCaida; }
	//-----------------------------------------------------------    
	public float getEnemyX() { return x; }
	public void setEnemyX(float x) { this.x = x; }
	//-----------------------------------------------------------    
	public float getEnemyY() { return y; }
	public void setEnemyY(float y) { this.y = y; }
	//-----------------------------------------------------------
	public boolean getEnemyXscale() { return xscale; }
	public void setEnemyXscale(boolean xscale) { this.xscale = xscale; }
	//-----------------------------------------------------------
	public int getEnemyXscaleInt() { return xscaleInt; }
	public void setEnemyXscaleInt(int xscaleInt) { this.xscaleInt = xscaleInt; }
	//-----------------------------------------------------------   
	public int getWidth() {	return width; }
	public void setWidth(int width) { this.width = width; }
	//-----------------------------------------------------------    
	public int getHeight() { return height; }
	public void setHeight(int height) {	this.height = height; }
	//-----------------------------------------------------------
	public int getEnemyVida() { return vida; }
	public void setEnemyVida(int vida) { this.vida = vida; }
	//-----------------------------------------------------------
	public String getEnemyEstado() { return estadoEnemy; }
	public void setEnemyEstado(String estadoEnemy) { this.estadoEnemy = estadoEnemy; }
	//-----------------------------------------------------------
	public int getEnemyFrame() { return frame; }
	public void setEnemyFrame(int frame) { this.frame = frame; }
	//-----------------------------------------------------------
	public int getEnemyLastFrame() { return lastFrame; }
	public void setEnemyLastFrame(int lastFrame) { this.lastFrame = lastFrame; }
	//-----------------------------------------------------------
	public int getEnemyCombo() { return combo; }
	public void setEnemyCombo(int combo) { this.combo = combo; }
	//-----------------------------------------------------------
	public int getEnemyConducta() { return conducta; }
	public void setEnemyConducta(int conducta) { this.conducta = conducta; }
	//-----------------------------------------------------------
    public String getEnemyCantidadHP() { return cantidadHP; }
    public void setEnemyCantidadHP(String cantidadHP) { this.cantidadHP = cantidadHP; }	
    //-----------------------------------------------------------------------------------------
	
	public void AnimationEnemy() { 	 
   	 Timer.schedule(new Task(){
            @Override
            public void run() {
                frame++;
                if(vida==9){cantidadHP = "iiiiiiiii";}
                if(vida==8){cantidadHP = "iiiiiiii";}
                if(vida==7){cantidadHP = "iiiiiii";}
                if(vida==6){cantidadHP = "iiiiii";}
                if(vida==5){cantidadHP = "iiiii";}
                if(vida==4){cantidadHP = "iiii";}
                if(vida==3){cantidadHP = "iii";}
                if(vida==2){cantidadHP = "ii";}
                if(vida==1){cantidadHP = "i";}
                if(vida==0){cantidadHP = " ";}
                if(frame > lastFrame){                          	                 	 
                    estadoEnemy="Stance";
                    frame = 0;
                }
              //----------------------------------------------------------
              //  ANIMACION ATAQUE
              //----------------------------------------------------------
                if(estadoEnemy=="Ataque") {
                 if (frame == 1) {ninjaCurrentFrame = ninjaStance;}
                 if (frame == 5) {ninjaCurrentFrame = ninjaPunch01;combo=1;}
                 if (frame == 6) {ninjaCurrentFrame = ninjaPunch02;soundRoundhouse1.play();}
                 if (frame == 8) {ninjaCurrentFrame = ninjaPunch03;}
                 if (frame == 9) {ninjaCurrentFrame = ninjaPunch04;}
               	 if (frame == 10) {ninjaCurrentFrame = ninjaPunch01;}
                 if (frame == 11) {ninjaCurrentFrame = ninjaPunch02;soundRoundhouse1.play();}
                 if (frame == 13) {ninjaCurrentFrame = ninjaPunch03;}
                 if (frame == 14) {ninjaCurrentFrame = ninjaPunch04;} 
               	 if (frame == 15) {ninjaCurrentFrame = ninjaKick01;}
                 if (frame == 16) {ninjaCurrentFrame = ninjaKick02;soundRoundhouse3.play();}
                 if (frame == 18) {ninjaCurrentFrame = ninjaKick03;}
                 if (frame == 19) {ninjaCurrentFrame = ninjaKick04;}
                 if (frame == 20) {ninjaCurrentFrame = ninjaStance;combo=0;} 
                }
                //----------------------------------------------------------
                //  ANIMACION CORRER
                //----------------------------------------------------------
                if(estadoEnemy=="Run") {
                  if (frame == 1) {ninjaCurrentFrame = ninjaRun01;}
                  if (frame == 2) {ninjaCurrentFrame = ninjaRun02;}	 
                  if (frame == 3) {ninjaCurrentFrame = ninjaRun03;}
                  if (frame == 4) {ninjaCurrentFrame = ninjaRun04;}
                  if (frame == 5) {ninjaCurrentFrame = ninjaRun05;}
                  if (frame == 6) {ninjaCurrentFrame = ninjaRun06; conducta = MathUtils.random(1,2);} 
                  }
                //----------------------------------------------------------
                //  ANIMACION GOLPEADO
                //----------------------------------------------------------
                  if(estadoEnemy=="BeingHit") {
                	if (frame == 1) {ninjaCurrentFrame = ninjaHurt01; }
                	if (frame == 3) {ninjaCurrentFrame = ninjaHurt02; if(vida>0){vida=vida-1;} soundHit1.play();}
                   } 
                  //----------------------------------------------------------
                  //  ANIMACION CAE GOLPEADO
                  //----------------------------------------------------------
                    if(estadoEnemy=="Fall") {                   	
                        if (frame == 1) {ninjaCurrentFrame = ninjaDie00; if(vida>0){vida=vida-1;}; soundHit6.play(); }
                        if (frame == 2) {ninjaCurrentFrame = ninjaDie01; x+=-6 * xscaleInt; y+=10+1;}	 
                        if (frame == 3) {ninjaCurrentFrame = ninjaDie02; x+=-6 * xscaleInt; y+=15+1;}	
                        if (frame == 4) {ninjaCurrentFrame = ninjaDie02; x+=-6 * xscaleInt; y+=10+1;}
                        if (frame == 5) {ninjaCurrentFrame = ninjaDie02; x+=-6 * xscaleInt; y+=5+1;}
                        if (frame == 6) {ninjaCurrentFrame = ninjaDie02; x+=-12 * xscaleInt;}
                        if (frame == 7) {ninjaCurrentFrame = ninjaDie02; x+=-6 * xscaleInt; y+=-5-1;}
                        if (frame == 8) {ninjaCurrentFrame = ninjaDie03; x+=-6 * xscaleInt; y+=-10-1;}
                        if (frame == 9) {ninjaCurrentFrame = ninjaDie03; x+=-6 * xscaleInt; y+=-15-1;}
                        if (frame == 10) {ninjaCurrentFrame = ninjaDie03; x+=-6 * xscaleInt; y+=-10-1;}
                        if (frame == 11) {ninjaCurrentFrame = ninjaGet01; x+=-6 * xscaleInt; soundFall1.play();}
                        if (frame == 12) {ninjaCurrentFrame = ninjaGet01; x+=-6 * xscaleInt;}
                        if (frame == 13) {ninjaCurrentFrame = ninjaDie04; }
                        if (frame == 25) {ninjaCurrentFrame = ninjaJump01;}
                        if (frame == 27) {ninjaCurrentFrame = ninjaStance;} 
                   }
                  //----------------------------------------------------------
                  //  ANIMACION MUERTEO
                  //----------------------------------------------------------
                      if(estadoEnemy=="Die") {
                          if (frame == 1) {ninjaCurrentFrame = ninjaDie00; }
                          if (frame == 2) {ninjaCurrentFrame = ninjaDie01; x+=-6 * xscaleInt; y+=11; soundHit6.play(); }	 
                          if (frame == 3) {ninjaCurrentFrame = ninjaDie02; x+=-6 * xscaleInt; y+=16; soundMalesfx3.play();}	
                          if (frame == 4) {ninjaCurrentFrame = ninjaDie02; x+=-6 * xscaleInt; y+=11;}
                          if (frame == 5) {ninjaCurrentFrame = ninjaDie02; x+=-6 * xscaleInt; y+=6;}
                          if (frame == 6) {ninjaCurrentFrame = ninjaDie02; x+=-12 * xscaleInt;}
                          if (frame == 7) {ninjaCurrentFrame = ninjaDie02; x+=-6 * xscaleInt; y+=-6;}
                          if (frame == 8) {ninjaCurrentFrame = ninjaDie03; x+=-6 * xscaleInt; y+=-11;}
                          if (frame == 9) {ninjaCurrentFrame = ninjaDie03; x+=-6 * xscaleInt; y+=-16;}
                          if (frame == 10) {ninjaCurrentFrame = ninjaDie03; x+=-6 * xscaleInt; y+=-11;}
                          if (frame == 11) {ninjaCurrentFrame = ninjaGet01; x+=-6 * xscaleInt; soundFall1.play();}
                          if (frame == 12) {ninjaCurrentFrame = ninjaGet01; x+=-6 * xscaleInt;}
                          if (frame == 13) {ninjaCurrentFrame = ninjaDie04; soundFall1.play();}                         
                          if (frame == 22) {ninjaCurrentFrame = ninjaDie04;} 
                          if (frame == 23) {ninjaCurrentFrame = ninjaBlank;} 
                          if (frame == 24) {ninjaCurrentFrame = ninjaDie04;} 
                          if (frame == 25) {ninjaCurrentFrame = ninjaBlank;} 
                          if (frame == 26) {ninjaCurrentFrame = ninjaDie04;} 
                          if (frame == 27) {ninjaCurrentFrame = ninjaBlank; if(vida==0){vida=-1;}} 
                     }   
                  
              //----------------------------------------------------------
              //  ANIMACION IDLE
              //----------------------------------------------------------
                if(estadoEnemy=="Stance") {
                    if (frame == 1) {ninjaCurrentFrame = ninjaStance;}
                    if (frame == 2) {ninjaCurrentFrame = ninjaStance;}	 
                    if (frame == 3) {ninjaCurrentFrame = ninjaStance;}
                    if (frame == 4) {ninjaCurrentFrame = ninjaStance;}
                    if (frame == 5) {ninjaCurrentFrame = ninjaStance;}
                    if (frame == 6) {ninjaCurrentFrame = ninjaStance; conducta = MathUtils.random(1,2); }  
                }
              //----------------------------------------------------------
              //  ANIMACION SALTO
              //----------------------------------------------------------
                if(estadoEnemy=="Salto") {
                	ninjaCurrentFrame = ninjaJump02;	 
                }
              //----------------------------------------------------------
              //  ANIMACION FIN SALTO
              //----------------------------------------------------------
                  if(estadoEnemy=="FinSalto") {
                    if (frame == 1) {ninjaCurrentFrame = ninjaJump02; yaSalto=1;}
                    if (frame == 2) {ninjaCurrentFrame = ninjaJump01;}	 
                    if (frame == 5) {ninjaCurrentFrame = ninjaStance;}
                    if (frame == 6) {ninjaCurrentFrame = ninjaStance; estadoEnemy="Stance"; frame=0;}
                  }  
            }
        }
        ,0,2/31.0f); 	 
	 }        
    //-----------------------------------------------------------------------------------------

    //-----------------------------------------------------------------------------------------


}
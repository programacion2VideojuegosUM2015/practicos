package ar.edu.um.vj2015;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;


public class Bullets {
	private Texture bluebullet;	
	private Bullet bullet,bullet1;
	private Array<Bullet>bullets;
	private Array<Bullet>fallenBullets;
	private Spaceship spaceship;
	private Monsters monsters;
	private long lastShootTime;
	private long lastBulletTime;
	private SoundResources resources;
	
	
 public Bullets(){
	 
	 bluebullet = new Texture(Gdx.files.internal("bluebullet.png"));
	 spaceship = new Spaceship();
	 monsters = new Monsters();
	 bullets = new Array<Bullet>();	 
	 fallenBullets = new Array<Bullet>();	 
	 resources = new SoundResources();
	 
 }
 public void update(){
	 float deltaTime = Gdx.graphics.getDeltaTime();
	 if (TimeUtils.nanoTime() - lastShootTime > 1000000000 ){
		 
		 
		 if(Gdx.app.getType() == ApplicationType.Desktop)
				inputDesktop();
			else if(Gdx.app.getType() == ApplicationType.Android)
				inputAndroid();
	
	 }
		 for(Bullet bullet: bullets){
		 bullet.getBulletOutline().y += bullet.getBulletSpeed() * deltaTime;
	 if (bullet.getBulletOutline().y > 450 - 64)
		 bullets.removeValue(bullet, true);
	
		 }
		 if (TimeUtils.nanoTime() - lastBulletTime > 1000000000)
		 fallenBulletSpawn();
		
		 
		 for(Bullet bullet1: fallenBullets){
			 bullet1.getBulletOutline().y -= bullet1.getBulletSpeed() * deltaTime; 
			 if(bullet1.getBulletOutline().y < 10)
				fallenBullets.removeValue(bullet1, true); 
		 }
		
		//actualiza el movimiento de la nave
	 spaceship.updateMovement();
	
 }
 private void inputAndroid() {
	 float coordX = Gdx.input.getX();
		
		if(Gdx.input.isTouched()){
			if(coordX > 400){
				 bulletSpawn();
				 resources.getShot().play();				
			}			
		}
	
}
private void inputDesktop() {
	 
	 if(Gdx.input.isKeyPressed(Keys.SPACE)){		
		 bulletSpawn();
		 resources.getShot().play();
	 }
	
}
public void draw(SpriteBatch batch ){
	 for(Bullet bullet : bullets){
	 batch.draw(bluebullet, bullet.getBulletOutline().getX(),
			 bullet.getBulletOutline().getY());
	 }
	 for(Bullet bullet1 :fallenBullets){
		 batch.draw(bluebullet, bullet1.getBulletOutline().getX(), bullet1.getBulletOutline().getY());
	 }
 }
 // metodo con el q aparecen las balas cuando dispara la nave
 public void bulletSpawn(){
	 bullet = new Bullet(bluebullet, 200, spaceship.getSpaceshipOutline().getX()+25, spaceship.getSpaceshipOutline().getY()+32);
	 bullets.add(bullet);
	 lastShootTime= TimeUtils.nanoTime();
 }
 //metodo con el q aparecen las balas q disparan los monstruos
 public void fallenBulletSpawn(){
		
	  for(int i =0;i<monsters.getMonsters().size ;i++){
		  if(monsters.getMonsters().get(i) != null && monsters.getMonsters().size != 0){
			  
			  int probability = MathUtils.random(0,800);
			
			  if(probability < 5){	 
					float locX = monsters.getMonsters().get(i).getMonstersOutline().getX()+ monsters.getMonster().getTexture().getWidth()/2;
				 	float locY = monsters.getMonsters().get(i).getMonstersOutline().getY();		
				 	bullet1 = new Bullet(bluebullet, 100, locX,locY);
				 	
				 	fallenBullets.add(bullet1);
				 	lastBulletTime= TimeUtils.nanoTime();
			 		}
				}
	  		}
	 	}
 
 //metodo de colision entre las balas de los monstruos y la nave
 public void detectCollision(Player player){
		for (Bullet bullet1 :fallenBullets) {
		if (bullet1.getBulletOutline().overlaps(spaceship.getSpaceshipOutline())){
			resources.getExplosion2().play();
			fallenBullets.removeValue(bullet1, true);
			player.removeLives();
			if(player.getLives() == 0){	
				
				Screens.game.setScreen(Screens.loserScreen);
			}
			}	
		}
 }
 
 
 



public Bullet getBullet() {
	
	return bullet;
}



public Array<Bullet> getBullets() {
	return bullets;
}
public void dispose(){
	bluebullet.dispose();
	resources.getShot().dispose();
}


 
 
}
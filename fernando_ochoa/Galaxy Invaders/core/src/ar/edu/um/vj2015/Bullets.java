package ar.edu.um.vj2015;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
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
	private long lastDropTime;
	
	private Sound shot;

 public Bullets(){
	 
	 bluebullet = new Texture(Gdx.files.internal("bluebullet.png"));
	 spaceship = new Spaceship();
	 monsters = new Monsters();
	 bullets = new Array<Bullet>();	 
	 fallenBullets = new Array<Bullet>();	 
	 shot = Gdx.audio.newSound(Gdx.files.internal("misil.mp3"));
	 
 }
 public void update(){
	 if (TimeUtils.nanoTime() - lastShootTime > 1000000000){
		 
	 if(Gdx.input.isKeyPressed(Keys.SPACE)){		
		 bulletSpawn();
		 shot.play();
	 }
	 }
		 for(Bullet bullet: bullets){
		 bullet.getBulletOutline().y += bullet.getBulletSpeed() * Gdx.graphics.getDeltaTime();
	 if (bullet.getBulletOutline().y > 450 - 64)
		 bullets.removeValue(bullet, true);
	
		 }
		 if (TimeUtils.nanoTime() - lastDropTime > 1000000000)
		 fallenBulletSpawn();
		
		 
		 for(Bullet bullet1: fallenBullets){
			 bullet1.getBulletOutline().y -= bullet1.getBulletSpeed() * Gdx.graphics.getDeltaTime(); 
			 if(bullet1.getBulletOutline().y +64 <0)
				fallenBullets.removeValue(bullet1, true); 
		 }
		
		//actualiza el movimiento de la nave
	 spaceship.updateMovement();
	
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
	 for(Monster monster: monsters.getMonsters()){
		 int probability = MathUtils.random(0,800);
		 int r = MathUtils.random(0,59);
		 
	if(probability < 5){	 
	float locX = (monsters.getMonsters().get(r).getMonstersOutline().getX()+20);
	float locY = monsters.getMonsters().get(r).getMonstersOutline().getY();		
	 bullet1 = new Bullet(bluebullet, 100, locX,locY);
	 fallenBullets.add(bullet1);
	 lastDropTime= TimeUtils.nanoTime();
	}
 }
 }
 
 //metodo de colision entre las balas de los monstruos y la nave
 public void detectCollision(Player player){
		for (Bullet bullet1 :fallenBullets) {
		if (bullet1.getBulletOutline().overlaps(spaceship.getSpaceshipOutline())){			
			fallenBullets.removeValue(bullet1, true);
			player.removeLives();
			if(player.getLives() == 0)
				Screens.game.setScreen(Screens.loserScreen);
			}	
		}
 }
 
 //Metodo de colision entre las balas de la nave con los monstruos
 public void detectCollision2(Monsters monsters){
	 for(Bullet bullet: bullets){
	 if(bullet.getBulletOutline().overlaps(monsters.getMonster().getMonstersOutline())){
		 bullets.removeValue(bullet, true);
		 monsters.getMonsters().removeValue(monsters.getMonster(), true);
	 }
	 }
 }
 
 



public Bullet getBullet() {
	return bullet;
}

public void dispose(){
	bluebullet.dispose();
	shot.dispose();
}


 
 
}
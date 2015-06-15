package ar.edu.um.vj2015;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;


public class Bullets {
	private Texture bluebullet;
	private Bullet bullet;
	private Array<Bullet>bullets;
	private Array<Bullet>fallenBullets;
	private Spaceship spaceship;
	private Monsters monsters;
	private long lastShootTime;
	private long lastDropTime;
	private Player player;

 public Bullets(){
	 
	 bluebullet = new Texture(Gdx.files.internal("bluebullet.png"));
	 spaceship = new Spaceship();
	 monsters = new Monsters();
	 bullets = new Array<Bullet>();	
	 fallenBullets = new Array<Bullet>();
	 player = new Player();
	 
 }
 public void update(){
	 if (TimeUtils.nanoTime() - lastShootTime > 1000000000){
		 
	 if(Gdx.input.isKeyPressed(Keys.SPACE))		
		 bulletSpawn();
	 }
		 for(Bullet bullet: bullets){
		 bullet.getBulletOutline().y += bullet.getBulletSpeed() * Gdx.graphics.getDeltaTime();
	 if (bullet.getBulletOutline().y > 450 - 64)
		 bullets.removeValue(bullet, true);
	 
		 }
		 if (TimeUtils.nanoTime() - lastDropTime > 1000000000)
		 fallenBulletSpawn();
		 for(Bullet bullet: fallenBullets){
			 bullet.getBulletOutline().y -= bullet.getBulletSpeed() * Gdx.graphics.getDeltaTime(); 
			 if(bullet.getBulletOutline().y +64 <0)
				fallenBullets.removeValue(bullet, true); 
		 }
	 spaceship.updateMovement();
	 
 }
 public void draw(SpriteBatch batch ){
	 for(Bullet bullet : bullets){
	 batch.draw(bluebullet, bullet.getBulletOutline().getX(),
			 bullet.getBulletOutline().getY());
	 }
	 for(Bullet bullet :fallenBullets){
		 batch.draw(bluebullet, bullet.getBulletOutline().getX(), bullet.getBulletOutline().getY());
	 }
 }
 public void bulletSpawn(){
	 bullet = new Bullet(bluebullet, 200, spaceship.getSpaceshipOutline().getX()+25, spaceship.getSpaceshipOutline().getY());
	 bullets.add(bullet);
	 lastShootTime= TimeUtils.nanoTime();
 }
 public void fallenBulletSpawn(){
	 for(Monster monster: monsters.getMonsters()){
		 int probability = MathUtils.random(0,800);
		 int r = MathUtils.random(0,59);
		 
	if(probability < 5){	 
	float locX = (monsters.getMonsters().get(r).getMonstersOutline().getX()+20);
	float locY = monsters.getMonsters().get(r).getMonstersOutline().getY();		
	 bullet = new Bullet(bluebullet, 100, locX,locY);
	 fallenBullets.add(bullet);
	 lastDropTime= TimeUtils.nanoTime();
	}
 }
 }
 public void detectCollision(Player player){
		for (Bullet bullet :fallenBullets) {
		if (bullet.getBulletOutline().overlaps(spaceship.getSpaceshipOutline())){			
			fallenBullets.removeValue(bullet, true);
			player.removeLives();
			}	
		}
 }
public Bullet getBullet() {
	return bullet;
}


 
 
}
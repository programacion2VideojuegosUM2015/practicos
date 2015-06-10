package ar.edu.um.vj2015;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Bullets {
	private Texture bluebullet;
	private BulletTemplate bulletTemplate;	
	private Rectangle bulletOutline;	
	private Spaceship spaceship;

 public Bullets(float x , float y){
	 
	 bluebullet = new Texture(Gdx.files.internal("bluebullet.png"));
	 spaceship = new Spaceship();	
	 bulletTemplate = new BulletTemplate(bluebullet, 3500);
	 bulletOutline = new Rectangle(x ,y ,bulletTemplate.getTexture().getWidth() ,bulletTemplate.getTexture().getHeight());            
 }
 public void update(){
	 if(Gdx.input.isKeyPressed(Keys.SPACE))
		 bulletOutline.y += bulletTemplate.getBulletSpeed() * Gdx.graphics.getDeltaTime();
	 if (bulletOutline.y > 450 - 64){
		 bulletOutline.y = spaceship.getSpaceshipOutline().y;
		 bulletOutline.x = spaceship.getSpaceshipOutline().x;
	 }
	 spaceship.updateMovement();
 }
 public void draw(SpriteBatch batch ){
	 batch.draw(bluebullet, bulletOutline.x + ((spaceship.getSpaceshipOutline().getWidth())/2),
			 bulletOutline.y + spaceship.getSpaceshipOutline().getHeight()/2);
 }
public Rectangle getBulletOutline() {
	return bulletOutline;
}


 
 
}
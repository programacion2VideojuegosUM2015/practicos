package ar.edu.um.vj2015;



import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;


public class Drops {
	
	protected Texture textureWater;
	protected Texture textureIce;
	protected Texture textureFire;
	protected Texture textureGift;
	public Array<Drop> drops;
	protected long lastDropTime;
	protected Sound dropSound;
	private Array<DropTemplate> dropTemplate;
	
	public Drops(){
		textureWater = new Texture(Gdx.files.internal("droplet.png"));
		textureIce = new Texture(Gdx.files.internal("copo.png"));
		textureFire = new Texture(Gdx.files.internal("fuego.png"));
		textureGift = new Texture(Gdx.files.internal("regalo.png"));
		
		drops = new Array<Drop>();
		
		dropTemplate = new Array<DropTemplate>();
		dropTemplate.add(new DropTemplate(1, 300, textureWater));
		dropTemplate.add(new DropTemplate(2, 400, textureFire));
		dropTemplate.add(new DropTemplate(3, 500, textureIce));
		dropTemplate.add(new DropTemplate(20, 800, textureGift));
		
		
	}
	//creamos metodo para no pasar todos los parametros al metodo render
	public void draw(SpriteBatch batch){
		for (Drop drop : drops) {
			batch.draw(drop.getTextura(), drop.getRectangulo().getX(), drop.getRectangulo().getY());
		}
		
	}
	
	public void update(){
		if (TimeUtils.nanoTime() - lastDropTime > 1000000000)
			spawnDrop();
		
		//iterator de fire
		Iterator<Drop> iter = drops.iterator();
		while (iter.hasNext()) {
		Drop drop = iter.next();
			drop.getRectangulo().setY(drop.getRectangulo().getY()-drop.getVelocidadCaida() * Gdx.graphics.getDeltaTime()); 	
		if (drop.getRectangulo().getY() + 64 < 0){
			iter.remove();
			}
		}
	}
	private void spawnDrop() {
		
		float x = MathUtils.random(5, 650 - 64);
		float y = 480;
		int temp = MathUtils.random(0,3);
		DropTemplate t = dropTemplate.get(temp);
		Drop drop = new Drop(t.getTextura(),t.getPuntaje(),t.getVelocidadCaida(),x,y);
		drops.add(drop);
		lastDropTime = TimeUtils.nanoTime();
	}
	public void detectCollision(Bucket bucket){
		Iterator<Drop> iter = drops.iterator();
		while (iter.hasNext()) {
		Drop drop = iter.next();	
		if (drop.getRectangulo().overlaps(bucket.getBucket())) {
			//dropSound.play();
			iter.remove();
			}	
		}
	
	}
	public void dispose() {
		textureWater.dispose();
		textureIce.dispose();
		textureFire.dispose();
		dropSound.dispose();
		textureGift.dispose();
	}
	
}

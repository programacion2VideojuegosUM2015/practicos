package ar.edu.um.vj2015;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;



public class Monsters {
	private Texture monstership;
	private Monster monster;
	private float constanteX = 30;
	public static Array<Monster> monsters;
	private SoundResources resources;
	
	
	public Monsters(){
		monstership = new Texture(Gdx.files.internal("mspaceship2.png"));
		monsters = new Array<Monster>();
		resources = new SoundResources();

		this.monsterSpawn();
		
	}

	public void draw(SpriteBatch batch){
		for (Monster monster : monsters) {
		batch.draw(monster.getTexture() ,monster.getMonstersOutline().getX() ,monster.getMonstersOutline().getY());
		}
	}
	
	public void monsterSpawn(){
		
		float x = constanteX;
		float y =350;
		
		for(int i=0; i < 60;i++){
			if(i>0)
			x+=50;
			if(i==15){				
			y=300;
			x=constanteX;
			}
			if(i==30){
				y=250;
				x=constanteX;
			}
			if(i==45){
				y=200;
			    x=constanteX;
			}
			monster = new Monster( monstership, x, y);
			monsters.add(monster);
		}
	}
	
	

	public void detectCollision(Bullets bullets, Player player){
		
		
		for (Monster monster: monsters) { 
			if (monster!= null && bullets.getBullet() != null){
			if (monster.getMonstersOutline().overlaps(bullets.getBullet().getBulletOutline())){
				resources.getExplosion().play();
				monsters.removeValue(monster, true);
				bullets.getBullets().removeValue(bullets.getBullet(), true);				
				player.AddScore();
			if(monsters.size == 0)
				Screens.game.setScreen(Screens.winnerScreen);
			}
			}
		}

			
	}
	
	

	public Array<Monster> getMonsters() {
		return monsters;
	}
	
	public void dispose(){
		monstership.dispose();
		resources.getExplosion().dispose();
	}

	public Monster getMonster() {
		return monster;
	}
	
	

}


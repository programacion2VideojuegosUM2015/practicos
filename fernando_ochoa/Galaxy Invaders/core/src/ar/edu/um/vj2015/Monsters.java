package ar.edu.um.vj2015;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;


public class Monsters {
	private Texture monstership;
	private Monster monster;
	private float constanteX = 30;
	private Array<Monster> monsters;
	
	
	public Monsters(){
		monstership = new Texture(Gdx.files.internal("mspaceship2.png"));
		monsters = new Array<Monster>();	
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
	
	public Monster getMonster(){	 
		
		for(Monster monster: monsters){			
		
			return monster;
		}
		return monster;
		
		
	}
	

	public Array<Monster> getMonsters() {
		return monsters;
	}
	
	public void dispose(){
		monstership.dispose();
	}
	

}


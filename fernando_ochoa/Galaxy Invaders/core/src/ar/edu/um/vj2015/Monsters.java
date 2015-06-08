package ar.edu.um.vj2015;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Monsters {
	private Texture monstership;	
	private Monster monsters [] = new Monster[60];
	private Monster monster;
	
	public Monsters(){
		monstership = new Texture(Gdx.files.internal("mspaceship2.png"));
		
	}
	public void draw(SpriteBatch batch){
		for (Monster monster : monsters) {
		batch.draw(monster.getTexture() ,monster.getMonstersOutline().getX() ,monster.getMonstersOutline().getY());
		}
	}
	
	public void MonsterSpawn(){
		
		float x =20;
		float y =400;
		
		for(int i=0; i < monsters.length;i++){
			if(i>0)
			x+=50;
			if(i==15){				
			y=350;
			x=20;
			}
			if(i==30){
				y=300;
				x=20;
			}
			if(i==45){
				y=250;
			    x=20;
			}
	
			monster = new Monster(monstership ,x , y);
			monsters[i]= monster;
			
			
		}
	}
}


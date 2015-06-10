package ar.edu.um.vj2015;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;


public class Monsters {
	private Texture monstership;
	private Monster monster;
	
	private Array<Monster> monsters;
	
	
	public Monsters(){
		monstership = new Texture(Gdx.files.internal("mspaceship2.png"));
		monsters = new Array<Monster>();	
		
	}
	public void updateMonsters(){
		monsterSpawn();
	}
	public void draw(SpriteBatch batch){
		for (Monster monster : monsters) {
		batch.draw(monster.getTexture() ,monster.getMonstersOutline().getX() ,monster.getMonstersOutline().getY());
		}
	}
	
	public void monsterSpawn(){
		
		FileHandle file = Gdx.files.internal("monstersLocation.txt");
		String monstersLocation = file.readString();
		int x = 2;
		int y = 700;
		String[] lines = monstersLocation.split("\n");
		
	
		for (String line : lines) {
			String[] monstersLocs = line.split(",");
			for (String monstersLoc : monstersLocs) {
				
				if (monstersLoc.trim().equals("x")) {
					x += 64;
					continue;
				}
				monster = new Monster(getMonstersTexture(monstersLoc.trim()), x, y);
				monsters.add(monster);
				
				x += monster.getTexture().getHeight();
			}
			x = 2;
			
			y -= 50;
		}
			
		}
	
	public void detectCollision(Bullets bullets){
		for (Monster monster : monsters) {
		if (monster.getMonstersOutline().overlaps(bullets.getBulletOutline())) {			
			monsters.removeValue(monster, true);
			}	
		}
}
	private Texture getMonstersTexture(String monstersLoc ){
		return monstership;
		
	}
	
}


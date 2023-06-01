package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_HP_Potion extends Entity{

	GamePanel gp;
	
	public OBJ_HP_Potion(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_pickUpOnly;
		name = "HP Potion";
		value = 1;
		idle_left1 = setup("/objects/hp_potion", gp.tileSize/2, gp.tileSize/2);
	}

	public void use(Entity entity) {
		
		gp.playSE(1);
		gp.player.life += value;
	}
}

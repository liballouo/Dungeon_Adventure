package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Exp_Normal extends Entity{

	GamePanel gp;
	
	public OBJ_Exp_Normal(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_pickUpOnly;
		name = "Normal Exp";
		value = 1;
		idle_left1 = setup("/objects/exp_normal", gp.tileSize/2, gp.tileSize/2);
	}
	
	public void use(Entity entity) {
		
		gp.playSE(1);
		gp.player.exp += value;
	}

}

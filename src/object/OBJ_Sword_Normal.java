package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Normal extends Entity{

	public OBJ_Sword_Normal(GamePanel gp) {
		super(gp);
		
		name = "Normal Sword";
		down1 = setup("/objects/sword_normal", gp.tileSize, gp.tileSize);
		down2 = setup("/objects/sword_normal", gp.tileSize/2, gp.tileSize/2);
		attackValue = 1;
		description = "[" + name + "]\nAn old sword.";
	}

}

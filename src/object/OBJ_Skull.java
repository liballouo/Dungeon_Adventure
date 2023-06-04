package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Skull extends Entity{

	GamePanel gp;
	
	public OBJ_Skull(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		getImage();
	}
	public void getImage() {
		image = setup("/objects/skull", gp.tileSize, gp.tileSize);
	}

}

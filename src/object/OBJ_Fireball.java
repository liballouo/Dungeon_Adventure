package object;

import entity.Projectile;
import main.GamePanel;

public class OBJ_Fireball extends Projectile{
	
	GamePanel gp;

	public OBJ_Fireball(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		imageWidth = gp.tileSize;
		imageHeight = gp.tileSize;
		solidArea.width = gp.tileSize;
		solidArea.height = gp.tileSize;
		name = "Fireball";
		level = 0;
		maxLevel = 10;
		speed = 3;
		maxRange = 140;
		random = true;
		range = maxRange;
		attackCD = 60;
		maxTargetValue = 2;
		targetValue = maxTargetValue;
		attack = 2;
		alive = false;
//		description = "[" + name + "]\nFireeeeeeballlllll!!!.\nAttack: "+attack+", target: "+targetValue;
		description = "Fireeeeeeballlllll!!!.\nAttack: "+attack+", target: "+targetValue;

		getImage();
	}

	public void getImage() {
		image = setup("/projectile/fireball_left_1", gp.tileSize, gp.tileSize);
		up1 = setup("/projectile/fireball_up_1", gp.tileSize/2, gp.tileSize/2);
		up2 = setup("/projectile/fireball_up_2", gp.tileSize/2, gp.tileSize/2);
		down1 = setup("/projectile/fireball_down_1", gp.tileSize/2, gp.tileSize/2);
		down2 = setup("/projectile/fireball_down_2", gp.tileSize/2, gp.tileSize/2);
		left1 = setup("/projectile/fireball_left_1", gp.tileSize/2, gp.tileSize/2);
		left2 = setup("/projectile/fireball_left_2", gp.tileSize/2, gp.tileSize/2);
		right1 = setup("/projectile/fireball_right_1", gp.tileSize/2, gp.tileSize/2);
		right2 = setup("/projectile/fireball_right_2", gp.tileSize/2, gp.tileSize/2);

	}

}

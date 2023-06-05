package object;

import entity.Projectile;
import main.GamePanel;

public class OBJ_Fireball extends Projectile{
	
	GamePanel gp;

	public OBJ_Fireball(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		isProjectile = true;
		imageOffsetX = 8;
		imageOffsetY = 3;
		imageWidth = gp.tileSize/2;
		imageHeight = gp.tileSize/2;
		solidArea.width = gp.tileSize;
		solidArea.height = gp.tileSize;
		name = "Fireball";
		level = 0;
		maxLevel = 10;
		speed = 3;
		maxRangeList = new int[] {140, 145, 150, 150, 155, 155, 160, 170};
		range = maxRangeList[0];
		attackCDList = new int[] {60, 60, 55, 50, 50, 45, 45, 40}; 
		attackCD = attackCDList[0];
		maxTargetValueList = new int[] {2, 2, 2, 2, 3, 3, 3, 4};
		targetValue = maxTargetValueList[0];
		random = true;
		range = maxRange;
		attackList = new int[] {2, 2, 3, 4, 5, 6, 8, 10};
		attack = attackList[0];
		alive = false;
//		description = "[" + name + "]\nFireeeeeeballlllll!!!.\nAttack: "+attack+", target: "+targetValue;
		description = "Shot a fireball randomly.\n ";

		getImage();
	}

	public void getImage() {
		image = setup("/projectile/fireball_left_1", gp.tileSize/2+10, gp.tileSize/2+10);
		up1 = setup("/projectile/fireball_up_1", gp.tileSize/2, gp.tileSize/2);
		up2 = setup("/projectile/fireball_up_2", gp.tileSize/2, gp.tileSize/2);
		up3 = setup("/projectile/fireball_up_1", gp.tileSize/2, gp.tileSize/2);
		up4 = setup("/projectile/fireball_up_2", gp.tileSize/2, gp.tileSize/2);
		down1 = setup("/projectile/fireball_down_1", gp.tileSize/2, gp.tileSize/2);
		down2 = setup("/projectile/fireball_down_2", gp.tileSize/2, gp.tileSize/2);
		down3 = setup("/projectile/fireball_down_1", gp.tileSize/2, gp.tileSize/2);
		down4 = setup("/projectile/fireball_down_2", gp.tileSize/2, gp.tileSize/2);
		left1 = setup("/projectile/fireball_left_1", gp.tileSize/2, gp.tileSize/2);
		left2 = setup("/projectile/fireball_left_2", gp.tileSize/2, gp.tileSize/2);
		left3 = setup("/projectile/fireball_left_1", gp.tileSize/2, gp.tileSize/2);
		left4 = setup("/projectile/fireball_left_2", gp.tileSize/2, gp.tileSize/2);
		right1 = setup("/projectile/fireball_right_1", gp.tileSize/2, gp.tileSize/2);
		right2 = setup("/projectile/fireball_right_2", gp.tileSize/2, gp.tileSize/2);
		right3 = setup("/projectile/fireball_right_1", gp.tileSize/2, gp.tileSize/2);
		right4 = setup("/projectile/fireball_right_2", gp.tileSize/2, gp.tileSize/2);

	}

}

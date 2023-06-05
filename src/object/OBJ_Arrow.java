package object;

import entity.Projectile;
import main.GamePanel;

public class OBJ_Arrow extends Projectile{

	GamePanel gp;
	
	public OBJ_Arrow(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		isProjectile = true;
		imageWidth = 14;
		imageHeight = 42;
		imageOffsetX = 20;
		imageOffsetY = 2;
		solidArea.width = 14;
		solidArea.height = 42;
		name = "Bow";
		level = 0;
		maxLevel = 10;
		speed = 5;
		maxRangeList = new int[] {80, 85, 90, 90, 95, 95, 100, 110};
		range = maxRangeList[0];
		attackCDList = new int[] {40, 40, 35, 30, 30, 25, 25, 20}; 
		attackCD = attackCDList[0];
		maxTargetValueList = new int[] {1, 1, 1, 1, 2, 2, 2, 3};
		targetValue = maxTargetValueList[0];
		attackList = new int[] {1, 2, 3, 4, 5, 6, 7, 8};
		attack = attackList[0];
		alive = false;
//		description = "[" + name + "]\nA normal bow.\nAttack: "+attack+", target: "+targetValue;
//		description = "A normal bow.\nAttack: "+attack+", target: "+targetValue;
		description = "Shot an arrow to the \ndirection you're facing.";
		
		getImage();
	}

	public void getImage() {
		image = setup("/weapon/bow", 14, 42);
		up1 = setup("/projectile/arrow_up", 14, 42);
		up2 = setup("/projectile/arrow_up", 14, 42);
		up3 = setup("/projectile/arrow_up", 14, 42);
		up4 = setup("/projectile/arrow_up", 14, 42);
		down1 = setup("/projectile/arrow_down", 14, 42);
		down2 = setup("/projectile/arrow_down", 14, 42);
		down3 = setup("/projectile/arrow_down", 14, 42);
		down4 = setup("/projectile/arrow_down", 14, 42);
		left1 = setup("/projectile/arrow_left", 42, 14);
		left2 = setup("/projectile/arrow_left", 42, 14);
		left3 = setup("/projectile/arrow_left", 42, 14);
		left4 = setup("/projectile/arrow_left", 42, 14);
		right1 = setup("/projectile/arrow_right", 42, 14);
		right2 = setup("/projectile/arrow_right", 42, 14);
		right3 = setup("/projectile/arrow_right", 42, 14);
		right4 = setup("/projectile/arrow_right", 42, 14);}
}

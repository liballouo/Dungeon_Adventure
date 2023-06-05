package object;

import entity.Projectile;
import main.GamePanel;

public class OBJ_Sword extends Projectile{
	
	GamePanel gp;
	
	public OBJ_Sword(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		isProjectile = false;
		attackOffsetX = 30;
		attackOffsetY = 20;
		imageOffsetX = 20;
		imageOffsetY = 2;
		imageWidth = 14;
		imageHeight = 42;
		solidArea.x = 20;
		solidArea.y = 20;
		solidAreaDefaultWidth = 100;
		solidAreaDefaultHeight = 60;
		solidArea.width = solidAreaDefaultWidth;
		solidArea.height = solidAreaDefaultHeight;
		name = "Sword";
		level = 0;
		maxLevel = 10;
		attackCDList = new int[] {60, 55, 55, 50, 50, 35, 35, 30}; 
		attackCD = attackCDList[0];
		maxTargetValueList = new int[] {5, 5, 5, 5, 5, 5, 5, 5};
		targetValue = maxTargetValueList[0];
		maxRangeList = attackCDList;
		range = maxRangeList[0];
		attackList = new int[] {1, 2, 3, 4, 5, 6, 7, 8};
		attack = attackList[0];
		alive = false;
//		description = "[" + name + "]\nA normal bow.\nAttack: "+attack+", target: "+targetValue;
//		description = "A normal bow.\nAttack: "+attack+", target: "+targetValue;
		description = "Attack the targets nearby.\n ";
		
		getImage();
	}
	
	public void getImage() {
		image = setup("/weapon/sword", 14, 42);
		up1 = setup("/objects/sword_01", 86, 48); // 57, 32
		up2 = setup("/objects/sword_02", 86, 48);
		up3 = setup("/objects/sword_03", 86, 48);
		up4 = setup("/objects/transparent", 86, 48);
		down1 = setup("/objects/sword_01", 86, 48);
		down2 = setup("/objects/sword_02", 86, 48);
		down3 = setup("/objects/sword_03", 86, 48);
		down4 = setup("/objects/transparent", 86, 48);
		left1 = setup("/objects/sword_left_01", 86, 48);
		left2 = setup("/objects/sword_left_02", 86, 48);
		left3 = setup("/objects/sword_left_03", 86, 48);
		left4 = setup("/objects/transparent", 86, 48);
		right1 = setup("/objects/sword_01", 86, 48);
		right2 = setup("/objects/sword_02", 86, 48);
		right3 = setup("/objects/sword_03", 86, 48);
		right4 = setup("/objects/transparent", 86, 48);
	}

}

package object;

import entity.Projectile;
import main.GamePanel;

public class OBJ_Ax extends Projectile{
	
	GamePanel gp;
	
	public OBJ_Ax(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		isProjectile = false;
		attackOffsetX = 30;
		attackOffsetY = 10;
		imageOffsetX = 20;
		imageOffsetY = 2;
		imageWidth = 14;
		imageHeight = 42;
		solidAreaDefaultWidth = 100;
		solidAreaDefaultHeight = 48;
		solidArea.width = solidAreaDefaultWidth;
		solidArea.height = solidAreaDefaultHeight;
		name = "Ax";
		level = 0;
		maxLevel = 10;
		attackCDList = new int[] {120, 110, 110, 100, 100, 95, 95, 90}; 
		attackCD = attackCDList[0];
		maxTargetValueList = new int[] {10, 10, 10, 10, 10, 10, 10, 10};
		targetValue = maxTargetValueList[0];
		maxRangeList = attackCDList;
		range = maxRangeList[0];
		attackList = new int[] {5, 6, 7, 8, 9, 10, 11, 12};
		attack = attackList[0];
		alive = false;
//		description = "[" + name + "]\nA normal bow.\nAttack: "+attack+", target: "+targetValue;
//		description = "A normal bow.\nAttack: "+attack+", target: "+targetValue;
		description = "Hit three times in a row!\n ";
		
		getImage();
	}
	
	public void getImage() {
		image = setup("/weapon/ax", 14, 42);
		up1 = setup("/objects/ax_01", 86, 48); // 57, 32
		up2 = setup("/objects/ax_02", 86, 48);
		up3 = setup("/objects/ax_03", 86, 48);
		up4 = setup("/objects/transparent", 86, 48);
		down1 = setup("/objects/ax_01", 86, 48);
		down2 = setup("/objects/ax_02", 86, 48);
		down3 = setup("/objects/ax_03", 86, 48);
		down4 = setup("/objects/transparent", 86, 48);
		left1 = setup("/objects/ax_left_01", 86, 48);
		left2 = setup("/objects/ax_left_02", 86, 48);
		left3 = setup("/objects/ax_left_03", 86, 48);
		left4 = setup("/objects/transparent", 86, 48);
		right1 = setup("/objects/ax_01", 86, 48);
		right2 = setup("/objects/ax_02", 86, 48);
		right3 = setup("/objects/ax_03", 86, 48);
		right4 = setup("/objects/transparent", 86, 48);
	}

}

package object;

import entity.Projectile;
import main.GamePanel;

public class OBJ_Arrow extends Projectile{

	GamePanel gp;
	
	public OBJ_Arrow(GamePanel gp) {
		super(gp);
		this.gp = gp;

		imageWidth = 14;
		imageHeight = 42;
		solidArea.width = 14;
		solidArea.height = 42;
		name = "Bow";
		level = 0;
		maxLevel = 10;
		speed = 5;
		maxRange = 80;
		range = maxRange;
		attackCD = 60;
		maxTargetValue = 1;
		targetValue = maxTargetValue;
		attack = 1;
		alive = false;
//		description = "[" + name + "]\nA normal bow.\nAttack: "+attack+", target: "+targetValue;
		description = "A normal bow.\nAttack: "+attack+", target: "+targetValue;

		getImage();
	}
	
	public void getImage() {
		image = setup("/weapon/bow", 14, 42);
		up1 = setup("/projectile/arrow_up", 14, 42);
		up2 = setup("/projectile/arrow_up", 14, 42);
		down1 = setup("/projectile/arrow_down", 14, 42);
		down2 = setup("/projectile/arrow_down", 14, 42);
		left1 = setup("/projectile/arrow_left", 42, 14);
		left2 = setup("/projectile/arrow_left", 42, 14);
		right1 = setup("/projectile/arrow_right", 42, 14);
		right2 = setup("/projectile/arrow_right", 42, 14);
	}

}

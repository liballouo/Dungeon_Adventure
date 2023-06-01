package object;

import entity.Projectile;
import main.GamePanel;

public class OBJ_Arrow extends Projectile{

	GamePanel gp;
	
	public OBJ_Arrow(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = "Arrow";
		speed = 5;
		maxRange = 80;
		range = maxRange;
		attackCD = 60;
		targetValue = 1;
		attack = 1;
		alive = false;
		
		getImage();
	}
	
	public void getImage() {
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

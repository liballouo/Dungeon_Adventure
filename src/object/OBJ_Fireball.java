package object;

import entity.Projectile;
import main.GamePanel;

public class OBJ_Fireball extends Projectile{
	
	GamePanel gp;

	public OBJ_Fireball(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = "Fireball";
		speed = 3;
		maxRange = 120;
		range = maxRange;
		attackCD = 60;
		targetValue = 1;
		attack = 0;
		alive = false;
		
		getImage();
	}
	
	public void getImage() {
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

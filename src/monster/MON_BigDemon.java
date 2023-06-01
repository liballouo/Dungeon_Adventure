package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class MON_BigDemon extends Entity{

	GamePanel gp;
	
	public MON_BigDemon(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_monster;
		name = "Green Silme";
		speed = 1;
		maxLife = 3;
		life = maxLife;
		
		solidArea.x = 3;
		solidArea.y = 18;
		solidArea.width = 42;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
	}
	
	public void getImage() {
		
		up1 = setup("/monster/big_demon_idle_anim_f0", gp.tileSize, gp.tileSize);
		up2 = setup("/monster/big_demon_idle_anim_f1", gp.tileSize, gp.tileSize);
		down1 = setup("/monster/big_demon_idle_anim_f0", gp.tileSize, gp.tileSize);
		down2 = setup("/monster/big_demon_idle_anim_f1", gp.tileSize, gp.tileSize);
		left1 = setup("/monster/big_demon_idle_anim_f0", gp.tileSize, gp.tileSize);
		left2 = setup("/monster/big_demon_idle_anim_f1", gp.tileSize, gp.tileSize);
		right1 = setup("/monster/big_demon_idle_anim_f0", gp.tileSize, gp.tileSize);
		right2 = setup("/monster/big_demon_idle_anim_f1", gp.tileSize, gp.tileSize);
	}
	
	public void setAction() {
		
		actionLockCounter++;
		
		if(actionLockCounter == 120) {
			Random random = new Random();
			int i = random.nextInt(100)+1; // pick a number from 1 to 100
			
			if(i <= 25) {
				direction = "up";
			}
			if(i > 25 && i <= 50) {
				direction = "down";
			}
			if(i > 50 && i <= 75) {
				direction = "left";
			}
			if(i >75 && i <= 100) {
				direction = "right";
			}
			
			actionLockCounter = 0;
		}
	}
	
	public void damageRection() {
		
		
		//actionLockCounter = 0;
		//direction = gp.player.direction; // hit back
	}
}

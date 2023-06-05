package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Exp_Normal;
import object.OBJ_HP_Potion;

public class MON_GreenSlime extends Entity{

	GamePanel gp;
	
	public MON_GreenSlime(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_monster;
		name = "Green Silme";
		speed = 2;
		maxLife = 3;
		life = maxLife;
		attack = 1;
		defense = 0;
		exp = 2;
		
		solidArea.x = 3;
		solidArea.y = 18;
		solidArea.width = 42;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
	}
	
	public void getImage() {
		
		up1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
		up2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
		up3 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
		up4 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
		down1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
		down3 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
		down4 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
		left2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
		left3 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
		left4 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
		right1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
		right2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
		right3 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
		right4 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
	}

	public void setAction() {
		
		actionLockCounter++;
		
		if(actionLockCounter == 30) {
//			Random random = new Random();
//			int i = random.nextInt(100)+1; // pick a number from 1 to 100
			
			if(this.worldX > gp.player.worldX && this.worldY > gp.player.worldY) {
				direction = "up_left";
			}
			if(this.worldX < gp.player.worldX && this.worldY > gp.player.worldY) {
				direction = "up_right";
			}
			if(this.worldX > gp.player.worldX && this.worldY < gp.player.worldY) {
				direction = "down_left";
			}
			if(this.worldX < gp.player.worldX && this.worldY < gp.player.worldY) {
				direction = "down_right";
			}
			
//			if(i <= 25) {
//				direction = "up";
//			}
//			if(i > 25 && i <= 50) {
//				direction = "down";
//			}
//			if(i > 50 && i <= 75) {
//				direction = "left";
//			}
//			if(i >75 && i <= 100) {
//				direction = "right";
//			}
			
			actionLockCounter = 0;
		}
	}
	
	public void damageRection() {
		//actionLockCounter = 0;
		//direction = gp.player.direction; // hit back
	}
	public void checkDrop() {
		
		// Cast a die
		int i = new Random().nextInt(100)+1;

		// Set the monster drop
		if(i < 50) {
			dropItem(new OBJ_Exp_Normal(gp));
		}
		if(i >= 50 && i < 100) {
			dropItem(new OBJ_HP_Potion(gp));
		}
	}
}

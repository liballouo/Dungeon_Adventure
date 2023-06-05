package entity;

import java.util.Random;

import main.GamePanel;
import object.OBJ_Exp_Normal;
import object.OBJ_HP_Potion;

public class Projectile extends Entity{

	Entity user;
	public boolean random;
	public boolean unstable;
	
	int attackSpriteCounter = 0;
	int counter = 1;

	public Projectile(GamePanel gp) {
		super(gp);
	
		type = type_projectile;
	}
	
	public void set(int worldX, int worldY, int level, String direction, boolean alive, Entity user) {
		
		this.worldX = worldX;
		this.worldY = worldY;
		this.direction = direction;
		this.alive = alive;
		this.user = user;
		this.level = level;
		this.range = this.maxRangeList[this.level];
		this.targetValue = this.maxTargetValueList[this.level];
		this.attack = this.attackList[this.level];
	}
	
	public void update() {
		
		if(user == gp.player) {
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			if(monsterIndex != 999) {
				gp.player.damageMonster(monsterIndex, attack, this);
				if(targetValue <= 0) {
					alive = false;
				}
			}
		}
		

		if(isProjectile == true) {
			switch(direction) {
			case "up": worldY -= speed; break;
			case "down": worldY += speed; break;
			case "left": worldX -= speed; break;
			case "right": worldX += speed; break;
//			case "up_left": worldY -= speed/2; worldX -= speed/2; break;
//			case "up_right": worldY -= speed/2; worldX += speed/2; break;
//			case "down_left": worldY += speed/2; worldX -= speed/2; break;
//			case "down_right": worldY += speed/2; worldX += speed/2; break;
			case "idle":
				if(gp.player.faceDirection == "up") worldY -= speed;
				else if(gp.player.faceDirection == "down") worldY += speed;
				else if(gp.player.faceDirection == "left") worldX -= speed;
				else if(gp.player.faceDirection == "right") worldX += speed;
				break;
			}
		
			// unstable 
			if(unstable == true) {
			int i = new Random().nextInt(100)+1;
		
			// Set random direction
			if(i < 25) {direction = "up";}
			if(i >= 25 && i < 50) {direction = "down";}
			if(i >= 50 && i < 75) {direction = "left";}
			if(i >= 75 && i < 100) {direction = "right";}
			
			}
			
			// random
			if(random == true && range == maxRangeList[level]) {
				int i = new Random().nextInt(100)+1;

				// Set random direction
				if(i < 25) {direction = "up";}
				if(i >= 25 && i < 50) {direction = "down";}
				if(i >= 50 && i < 75) {direction = "left";}
				if(i >= 75 && i < 100) {direction = "right";}
				
			}
			

		}
		// not projectile
		else {

			switch(gp.player.direction) {
			case "up": direction = "up"; break;
			case "down": direction = "down"; break;
			case "left": direction = "left"; break;
			case "right": direction = "right"; break;
			}
			worldX = gp.player.worldX;
			worldY = gp.player.worldY;
			
			if(counter > 0) {
				if(counter / 5 == 1) {spriteNum = 1;solidArea.width = solidAreaDefaultWidth;
				 solidArea.height = solidAreaDefaultHeight;}
				else if(counter / 5 == 2) {spriteNum = 2;}
				else if(counter / 5 == 3) {spriteNum = 3;}
				else if(counter / 5 == 4) {spriteNum = 4; solidArea.width = 0;
				 solidArea.height = 0;}
				counter++;
				if(counter>24) counter = 0;
			}
			
		}
		
		range--;
		if(range <= 0) {
			counter = 1;
			alive = false;
		}
		
	}
	
}

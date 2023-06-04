package entity;

import java.util.Random;

import main.GamePanel;
import object.OBJ_Exp_Normal;
import object.OBJ_HP_Potion;

public class Projectile extends Entity{

	Entity user;
	public boolean random;
	public boolean unstable;
	
	public Projectile(GamePanel gp) {
		super(gp);
		
		type = type_projectile;
	}
	
	public void set(int worldX, int worldY, String direction, boolean alive, Entity user) {
		
		this.worldX = worldX;
		this.worldY = worldY;
		this.direction = direction;
		this.alive = alive;
		this.user = user;
		this.range = this.maxRange;
		this.targetValue = this.maxTargetValue;
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
		

	
		switch(direction) {
		case "up": worldY -= speed; break;
		case "down": worldY += speed; break;
		case "left": worldX -= speed; break;
		case "right": worldX += speed; break;
//		case "up_left": worldY -= speed/2; worldX -= speed/2; break;
//		case "up_right": worldY -= speed/2; worldX += speed/2; break;
//		case "down_left": worldY += speed/2; worldX -= speed/2; break;
//		case "down_right": worldY += speed/2; worldX += speed/2; break;
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
		if(random == true && range == maxRange) {
			int i = new Random().nextInt(100)+1;
			
			// Set random direction
			if(i < 25) {direction = "up";}
			if(i >= 25 && i < 50) {direction = "down";}
			if(i >= 50 && i < 75) {direction = "left";}
			if(i >= 75 && i < 100) {direction = "right";}
			
		}
		
//		switch(gp.player.faceDirection) {
//		case "up": worldY -= speed; break;
//		case "down": worldY += speed; break;
//		case "left": worldX -= speed; break;
//		case "right": worldX += speed; break;
//		case "up_left": worldY -= speed; worldX -= speed; break;
//		case "up_right": worldY -= speed; worldX += speed; break;
//		case "down_left": worldY += speed; worldX -= speed; break;
//		case "down_right": worldY += speed; worldX += speed; break;
//		}
		
		range--;
		if(range <= 0) {
			alive = false;
		}
		
		spriteCounter++;
		if(spriteCounter > 12) {
			if(spriteNum == 1) {
				spriteNum = 2;
			}
		}
	}
	
}

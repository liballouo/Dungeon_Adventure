package entity;

import main.GamePanel;

public class Projectile extends Entity{

	Entity user;

	
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
	}
	
	public void update() {
		
		if(user == gp.player) {
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			if(monsterIndex != 999) {
				gp.player.damageMonster(monsterIndex, attack);
				targetValue--;
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
		case "up_left": worldY -= speed; worldX -= speed; break;
		case "up_right": worldY -= speed; worldX += speed; break;
		case "down_left": worldY += speed; worldX -= speed; break;
		case "down_right": worldY += speed; worldX += speed; break;
		case "idle":
			if(gp.player.faceDirection == "up") worldY -= speed;
			else if(gp.player.faceDirection == "down") worldY += speed;
			else if(gp.player.faceDirection == "left") worldX -= speed;
			else if(gp.player.faceDirection == "right") worldX += speed;
			break;
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

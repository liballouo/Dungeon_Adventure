package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {
	
	GamePanel gp;
	
	public int worldX, worldY;
	
	public BufferedImage idle_left1, idle_left2, idle_left3, idle_left4, up1, up2, up3, up4, down1, down2, down3, down4, idle_right1, idle_right2, idle_right3, idle_right4,    
							left1, left2, left3, left4, right1, right2, right3, right4, upleft1, upleft2, upright1, upright2, downleft1, downleft2,
							downright1, downright2;
	public BufferedImage elf_right1, elf_right2, elf_right3, elf_right4, wizzard_right1, wizzard_right2, wizzard_right3, wizzard_right4, 
							knight_right1, knight_right2, knight_right3, knight_right4, dwarf_right1, dwarf_right2, dwarf_right3, dwarf_right4;
	public int imageWidth, imageHeight;
	
	public String direction = "idle";

	public int spriteNum = 1;

	public BufferedImage image, image2, image3;
	public boolean collision = false;
	
	// Collision Setting
	public Rectangle solidArea = new Rectangle(0, 0, 40, 40);
	public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
	public int solidAreaDefaultX, solidAreaDefaultY, solidAreaDefaultWidth, solidAreaDefaultHeight;
	public boolean collisionOn = false;
	public boolean invincible = false;
	boolean attacking = false;
	
	public boolean alive = true;
	public boolean dying = false;
	int monsterAmount;
	int preMonsterAmount;
//	public boolean hpBarOn = false;
	
	// counter
	int spriteCounter = 0;
	public int actionLockCounter = 0;
	public int invincibleCounter = 0;
	public int shotAvailableCounter = 0;
	public int attackAvailableCounter = 0;
	int dyingCounter = 0;
//	int hpBarCounter = 0;

	// dialogue
	String dialogues[] = new String[20];
	int dialogueIndex = 0;
	
	// Character  status
	public String name;
	public int maxLife;
	public int life;
	public int speed;
	public int level;
	public int maxLevel;
	public int attack;
	public int strength;
	public int dexterity;
	public int defense;
	public int exp;
	public int expRate;
	public int nextLevelExp;
	public int coin;
	public int cooldown;
	public int killAmount;
	public Entity currentWeapon;
	public Entity currentShield;
	
	// weapons
	public ArrayList<ArrayList<Projectile>> weaponList = new ArrayList<ArrayList<Projectile>>();
	public Projectile[][] projectile = new Projectile[4][5];
	public boolean isProjectile;
	public int attackOffsetX = 0;
	public int attackOffsetY = 0;
	public int imageOffsetX = 0;
	public int imageOffsetY = 0;
	
	// character image
	public BufferedImage[] elf_idle;
	public BufferedImage[] elf_idle_left;
	public BufferedImage[] wizzard_idle;
	public BufferedImage[] wizzard_idle_left;
	
	// item attributes
	public int value;
	public int targetValue; // number of targets being attacked
	public int maxTargetValue;
	public int[] maxTargetValueList;
	public int range;
	public int maxRange;
	public int[] maxRangeList;
	public int attackCD;
	public boolean attackAvailable;
	public int[] attackCDList;
	public int attackValue;
	public int[] attackList;
	public int defenseValue;
	public String description = "";
	
	// type
	public int type; // 0: player; 1 = NPC; 2 = monster
	public final int type_player = 0;
	public final int type_npc = 1;
	public final int type_monster = 2;
	public final int type_pickUpOnly = 3;
	public final int type_projectile = 4;
	
	// player class
	public int player_class = -1;
	public int class_elf = 0;
	public int class_wizard = 1;
	public int class_knight = 2;
	public int class_dwarf = 3;
	
	public Entity(GamePanel gp) {
		this.gp = gp;
		
	}
	
	public void setAction() {} // can be overridden by different character 
	public void damageRection() {}
	public void use(Entity entity) {}
	public void checkDrop() {}
	public void dropItem(Entity droppedItem) {
		
		for(int i=0; i<gp.obj.length; i++) {
			if(gp.obj[i] == null) {
				gp.obj[i] = droppedItem;
				// the dead monster's position
				gp.obj[i].worldX = worldX; 
				gp.obj[i].worldY = worldY;
				break;
			}
		}
	}
	
	public void speak() {

		if(dialogues[dialogueIndex] == null) {
			dialogueIndex = 0;
		}
		gp.ui.currentDialogue = dialogues[dialogueIndex];
		dialogueIndex++;
		
		switch(gp.player.direction) {
		case "up":
			direction = "down";
			break;
		case "down":
			direction = "up";
			break;
		case "left":
			direction = "right";
			break;
		case "right":
			direction = "left";
			break;
		}
	}

	public void update() {
		
		setAction();
		
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this, gp.npc);
		gp.cChecker.checkEntity(this, gp.monster);
		boolean contactPlayer = gp.cChecker.checkPlayer(this);
		
		// monster collision
		if(this.type == type_monster && contactPlayer == true) {
			if(gp.player.invincible == false) {
				// give damage
				gp.playSE(6);
				
				int damage = attack - gp.player.defense;
				if(damage < 0) {
					damage = 0;
				}
				
				gp.player.life -= damage;
				
				gp.player.invincible = true;
			}
		}
		
		// if collision false, player can move
//		if(collisionOn == false) {
		switch(direction) {
		case "up": worldY -= speed; break;
		case "down": worldY += speed; break;
		case "left": worldX -= speed; break;
		case "right": worldX += speed; break;
		case "up_left": worldY -= speed/2; worldX -= speed/2; break;
		case "up_right": worldY -= speed/2; worldX += speed/2; break;
		case "down_left": worldY += speed/2; worldX -= speed/2; break;
		case "down_right": worldY += speed/2; worldX += speed/2; break;
		}

//		}
		
		spriteCounter++;
		if(spriteCounter > 10) {
			if(spriteNum == 1) {
				spriteNum = 2;
			}
			else if(spriteNum == 2) {
				spriteNum = 3;
			}
			else if(spriteNum == 3) {
				spriteNum = 4;
			}
			else if(spriteNum == 4) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
		
		// This needs to be outside of key's if statement
		if(invincible == true) {
			invincibleCounter++;
			if(invincibleCounter > 20) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage  image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
		   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
		   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
		   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			
			switch(direction) {
			case "up":
				if(spriteNum == 1) {image = up1;}
				if(spriteNum == 2) {image = up2;}
				if(spriteNum == 3) {image = up3;}
				if(spriteNum == 4) {image = up4;}
				break;
			case "down":
				if(spriteNum == 1) {image = down1;}
				if(spriteNum == 2) {image = down2;}
				if(spriteNum == 3) {image = down3;}
				if(spriteNum == 4) {image = down4;}
				break;
			case "left":
				if(spriteNum == 1) {image = left1;}
				if(spriteNum == 2) {image = left2;}
				if(spriteNum == 3) {image = left3;}
				if(spriteNum == 4) {image = left4;}
				break;
			case "right":
				if(spriteNum == 1) {image = right1;}
				if(spriteNum == 2) {image = right2;}
				if(spriteNum == 3) {image = right3;}
				if(spriteNum == 4) {image = right4;}
				break;
			case "up_left":
				if(spriteNum == 1) {image = left1;}
				if(spriteNum == 2) {image = left2;}
				if(spriteNum == 3) {image = left3;}
				if(spriteNum == 4) {image = left4;}
				break;
			case "up_right":
				if(spriteNum == 1) {image = right1;}
				if(spriteNum == 2) {image = right2;}
				if(spriteNum == 3) {image = right3;}
				if(spriteNum == 4) {image = right4;}
				break;
			case "down_left":
				if(spriteNum == 1) {image = left1;}
				if(spriteNum == 2) {image = left2;}
				if(spriteNum == 3) {image = left3;}
				if(spriteNum == 4) {image = left4;}
				break;
			case "down_right":
				if(spriteNum == 1) {image = right1;}
				if(spriteNum == 2) {image = right2;}
				if(spriteNum == 3) {image = right3;}
				if(spriteNum == 4) {image = right4;}
				break;
		
			case "idle":
				if(type == type_player || type == type_pickUpOnly) {
					if(spriteNum == 1) {image = idle_left1;}
					if(spriteNum == 2) {image = idle_left2;}
					if(spriteNum == 3) {image = idle_left3;}
					if(spriteNum == 4) {image = idle_left4;}
				}
				if(type == type_projectile) {
//					if(spriteNum == 1) {image = left1;}
//					if(spriteNum == 2) {image = left2;}
//					if(spriteNum == 3) {image = left3;}
//					if(spriteNum == 4) {image = left4;}
				}
				break;
			}
			
			if(invincible == true) {
//				hpBarOn = true;
//				hpBarCounter = 0;
				changeAlpha(g2, 0.4f);
			}
			if(dying == true) {

				// check monster amount
				monsterAmount = gp.monster.length;
				if(monsterAmount > preMonsterAmount) {
					gp.player.killAmount++;
				}
				dyingAnimation(g2);
				preMonsterAmount = gp.monster.length;
			}
			
			g2.drawImage(image, screenX-attackOffsetX, screenY+10+attackOffsetY, null);
			
			changeAlpha(g2, 1f);
		}
	}
	
	public void dyingAnimation(Graphics2D g2) {
		
		dyingCounter++;
		
		int i = 5;
		
		if(dyingCounter <= i) {changeAlpha(g2, 0f);}
		if(dyingCounter > i && dyingCounter <= i*2) {changeAlpha(g2, 1f);}
		if(dyingCounter > i*2 && dyingCounter <= i*3) {changeAlpha(g2, 0f);}
		if(dyingCounter > i*3 && dyingCounter <= i*4) {changeAlpha(g2, 1f);}
		if(dyingCounter > i*4 && dyingCounter <= i*5) {changeAlpha(g2, 0f);}
		if(dyingCounter > i*5 && dyingCounter <= i*6) {changeAlpha(g2, 1f);}
//		if(dyingCounter > i*6 && dyingCounter <= i*7) {changeAlpha(g2, 0f);}
//		if(dyingCounter > i*7 && dyingCounter <= i*8) {changeAlpha(g2, 1f);}
		if(dyingCounter > i*6) {
			alive = false;
		}
	}
	
	public void changeAlpha(Graphics2D g2, float alphaValue) {
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
	}
	
	public BufferedImage setup(String imagePath, int width, int height) {
		
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = uTool.scaleImage(image, width, height);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}

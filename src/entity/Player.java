package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Arrow;
import object.OBJ_Fireball;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;

public class Player extends Entity{

	KeyHandler keyH;	
	public final int screenX;
	public final int screenY;
	
	public ArrayList<Entity> inventory = new ArrayList<>();
	public final int maxInventorySize = 4;
	
	// level up selection option
	public int[] options = new int[3];
	
	String preDirection;
	String faceDirection;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		super(gp);
		 
		this.keyH = keyH;
		
		type = 0;
		
		// the middle of the screen
		screenX = gp.screenWidth/2 - (gp.tileSize/2); // left-top of the image need to adjust
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		// collision setting
		solidArea = new Rectangle(8, 16, 15, 32);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;

		attackArea.width = 36;
		attackArea.height = 36;
				
		setDefaultValues();
		getPlayerImage();
//		getPlayerAttackImage();
		setItems();
		setWeaponList();
	}
	
	public void setDefaultValues() {
		
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 2;
		direction = "right";
		preDirection = "right";
		faceDirection = "right";
				
		// player status
		level = 1;
		maxLife = 6;
		life = maxLife; // current life
		exp = 0;
		expRate = 1;
		nextLevelExp = 5;
		coin = 0;
		killAmount = 0;
//		currentWeapon = new OBJ_Sword_Normal(gp);
//		currentShield = new OBJ_Shield_Wood(gp);
		if(player_class == class_elf) {
			projectile[0][0] = new OBJ_Arrow(gp);
		}
		if(player_class == class_wizard) {
			projectile[0][0] = new OBJ_Fireball(gp);
		}

		attack = getAttack();
	}
	
	public void setItems() {
		
		if(player_class == class_elf) {
			projectile[0][0] = new OBJ_Arrow(gp);
			if(projectile[0][0] != null) inventory.add(projectile[0][0]);
		}
		if(player_class == class_wizard) {
			projectile[0][0] = new OBJ_Fireball(gp);
			if(projectile[0][0] != null) inventory.add(projectile[0][0]);
		}

//		inventory.add(currentWeapon);
//		inventory.add(currentShield);
	}
	
	public void setWeaponList() {
		
		ArrayList<Projectile> characterWeapons1 = new ArrayList<>();
		ArrayList<Projectile> characterWeapons2 = new ArrayList<>();
		ArrayList<Projectile> characterWeapons3 = new ArrayList<>();
		ArrayList<Projectile> characterWeapons4 = new ArrayList<>();

		weaponList.add(characterWeapons1);
		weaponList.add(characterWeapons2);
		weaponList.add(characterWeapons3);
		weaponList.add(characterWeapons4);

		
		// Elf
		weaponList.get(0).add(new OBJ_Arrow(gp));
		weaponList.get(0).add(new OBJ_Arrow(gp));
		weaponList.get(0).add(new OBJ_Arrow(gp));
		weaponList.get(0).add(new OBJ_Arrow(gp));
		weaponList.get(0).add(new OBJ_Arrow(gp));
//		weaponList.get(0).add(new OBJ_Arrow(gp));
//		weaponList.get(0).add(new OBJ_Arrow(gp));
//		weaponList.get(0).add(new OBJ_Arrow(gp));
//		weaponList.get(0).add(new OBJ_Arrow(gp));
//		weaponList.get(0).add(new OBJ_Arrow(gp));
		weaponList.get(0).add(new OBJ_Fireball(gp));
		weaponList.get(0).add(new OBJ_Fireball(gp));
		weaponList.get(0).add(new OBJ_Fireball(gp));
		weaponList.get(0).add(new OBJ_Fireball(gp));
		weaponList.get(0).add(new OBJ_Fireball(gp));

		
		// Wizard
		weaponList.get(1).add(new OBJ_Fireball(gp));
		weaponList.get(1).add(new OBJ_Fireball(gp));
		weaponList.get(1).add(new OBJ_Fireball(gp));
		weaponList.get(1).add(new OBJ_Fireball(gp));
		weaponList.get(1).add(new OBJ_Fireball(gp));
		weaponList.get(1).add(new OBJ_Fireball(gp));
		weaponList.get(1).add(new OBJ_Fireball(gp));
		weaponList.get(1).add(new OBJ_Fireball(gp));
		weaponList.get(1).add(new OBJ_Fireball(gp));
		weaponList.get(1).add(new OBJ_Fireball(gp));
		
		
	}
	
	public int getAttack() {
//		attackArea = currentWeapon.attackArea;
		return attack; //= strength * currentWeapon.attackValue;
	}

	public int  getDefense() {
		return defense; //= dexterity * currentShield.defenseValue;
	}
	
	public void getPlayerImage() {
		if(player_class == class_elf) {
			idle_left1 = setup("/player/elf_m_idle_left_anim_f0", 28, gp.tileSize);
			idle_left2 = setup("/player/elf_m_idle_left_anim_f1", 28, gp.tileSize);
			idle_left3 = setup("/player/elf_m_idle_left_anim_f2", 28, gp.tileSize);
			idle_left4 = setup("/player/elf_m_idle_left_anim_f3", 28, gp.tileSize);
			idle_right1 = setup("/player/elf_m_idle_anim_f0", 28, gp.tileSize);
			idle_right2 = setup("/player/elf_m_idle_anim_f1", 28, gp.tileSize);
			idle_right3 = setup("/player/elf_m_idle_anim_f2", 28, gp.tileSize);
			idle_right4 = setup("/player/elf_m_idle_anim_f3", 28, gp.tileSize);
			left1 = setup("/player/elf_m_run_left_anim_f0", 28, gp.tileSize);
			left2 = setup("/player/elf_m_run_left_anim_f1", 28, gp.tileSize);
			left3 = setup("/player/elf_m_run_left_anim_f2", 28, gp.tileSize);
			left4 = setup("/player/elf_m_run_left_anim_f3", 28, gp.tileSize);
			right1 = setup("/player/elf_m_run_anim_f0", 28, gp.tileSize);
			right2 = setup("/player/elf_m_run_anim_f1", 28, gp.tileSize);
			right3 = setup("/player/elf_m_run_anim_f2", 28, gp.tileSize);
			right4 = setup("/player/elf_m_run_anim_f3", 28, gp.tileSize);
		}
		
		if(player_class == class_wizard) {
			idle_left1 = setup("/player/wizard_m_idle_left_anim_f0", 28, gp.tileSize);
			idle_left2 = setup("/player/wizard_m_idle_left_anim_f1", 28, gp.tileSize);
			idle_left3 = setup("/player/wizard_m_idle_left_anim_f2", 28, gp.tileSize);
			idle_left4 = setup("/player/wizard_m_idle_left_anim_f3", 28, gp.tileSize);
			idle_right1 = setup("/player/wizard_m_idle_anim_f0", 28, gp.tileSize);
			idle_right2 = setup("/player/wizard_m_idle_anim_f1", 28, gp.tileSize);
			idle_right3 = setup("/player/wizard_m_idle_anim_f2", 28, gp.tileSize);
			idle_right4 = setup("/player/wizard_m_idle_anim_f3", 28, gp.tileSize);
			left1 = setup("/player/wizard_m_run_left_anim_f0", 28, gp.tileSize);
			left2 = setup("/player/wizard_m_run_left_anim_f1", 28, gp.tileSize);
			left3 = setup("/player/wizard_m_run_left_anim_f2", 28, gp.tileSize);
			left4 = setup("/player/wizard_m_run_left_anim_f3", 28, gp.tileSize);
			right1 = setup("/player/wizard_m_run_anim_f0", 28, gp.tileSize);
			right2 = setup("/player/wizard_m_run_anim_f1", 28, gp.tileSize);
			right3 = setup("/player/wizard_m_run_anim_f2", 28, gp.tileSize);
			right4 = setup("/player/wizard_m_run_anim_f3", 28, gp.tileSize);
		}
		
		if(player_class == class_knight) {
			idle_left1 = setup("/player/knight_m_idle_left_anim_f0", 28, gp.tileSize);
			idle_left2 = setup("/player/knight_m_idle_left_anim_f1", 28, gp.tileSize);
			idle_left3 = setup("/player/knight_m_idle_left_anim_f2", 28, gp.tileSize);
			idle_left4 = setup("/player/knight_m_idle_left_anim_f3", 28, gp.tileSize);
			idle_right1 = setup("/player/knight_m_idle_anim_f0", 28, gp.tileSize);
			idle_right2 = setup("/player/knight_m_idle_anim_f1", 28, gp.tileSize);
			idle_right3 = setup("/player/knight_m_idle_anim_f2", 28, gp.tileSize);
			idle_right4 = setup("/player/knight_m_idle_anim_f3", 28, gp.tileSize);
			left1 = setup("/player/knight_m_run_left_anim_f0", 28, gp.tileSize);
			left2 = setup("/player/knight_m_run_left_anim_f1", 28, gp.tileSize);
			left3 = setup("/player/knight_m_run_left_anim_f2", 28, gp.tileSize);
			left4 = setup("/player/knight_m_run_left_anim_f3", 28, gp.tileSize);
			right1 = setup("/player/knight_m_run_anim_f0", 28, gp.tileSize);
			right2 = setup("/player/knight_m_run_anim_f1", 28, gp.tileSize);
			right3 = setup("/player/knight_m_run_anim_f2", 28, gp.tileSize);
			right4 = setup("/player/knight_m_run_anim_f3", 28, gp.tileSize);
		}
		
		if(player_class == class_dwarf) {
			idle_left1 = setup("/player/dwarf_m_idle_left_anim_f0", 28, gp.tileSize);
			idle_left2 = setup("/player/dwarf_m_idle_left_anim_f1", 28, gp.tileSize);
			idle_left3 = setup("/player/dwarf_m_idle_left_anim_f2", 28, gp.tileSize);
			idle_left4 = setup("/player/dwarf_m_idle_left_anim_f3", 28, gp.tileSize);
			idle_right1 = setup("/player/dwarf_m_idle_anim_f0", 28, gp.tileSize);
			idle_right2 = setup("/player/dwarf_m_idle_anim_f1", 28, gp.tileSize);
			idle_right3 = setup("/player/dwarf_m_idle_anim_f2", 28, gp.tileSize);
			idle_right4 = setup("/player/dwarf_m_idle_anim_f3", 28, gp.tileSize);
			left1 = setup("/player/dwarf_m_run_left_anim_f0", 28, gp.tileSize);
			left2 = setup("/player/dwarf_m_run_left_anim_f1", 28, gp.tileSize);
			left3 = setup("/player/dwarf_m_run_left_anim_f2", 28, gp.tileSize);
			left4 = setup("/player/dwarf_m_run_left_anim_f3", 28, gp.tileSize);
			right1 = setup("/player/dwarf_m_run_anim_f0", 28, gp.tileSize);
			right2 = setup("/player/dwarf_m_run_anim_f1", 28, gp.tileSize);
			right3 = setup("/player/dwarf_m_run_anim_f2", 28, gp.tileSize);
			right4 = setup("/player/dwarf_m_run_anim_f3", 28, gp.tileSize);
		}
		
		elf_right1 = setup("/player/elf_m_idle_anim_f0", 28, gp.tileSize);
		elf_right2 = setup("/player/elf_m_idle_anim_f1", 28, gp.tileSize);
		elf_right3 = setup("/player/elf_m_idle_anim_f2", 28, gp.tileSize);
		elf_right4 = setup("/player/elf_m_idle_anim_f3", 28, gp.tileSize);
			
		wizzard_right1 = setup("/player/wizard_m_idle_anim_f0", 28, gp.tileSize);
		wizzard_right2 = setup("/player/wizard_m_idle_anim_f1", 28, gp.tileSize);
		wizzard_right3 = setup("/player/wizard_m_idle_anim_f2", 28, gp.tileSize);
		wizzard_right4 = setup("/player/wizard_m_idle_anim_f3", 28, gp.tileSize);
			
		knight_right1 = setup("/player/knight_m_idle_anim_f0", 28, gp.tileSize);
		knight_right2 = setup("/player/knight_m_idle_anim_f1", 28, gp.tileSize);
		knight_right3 = setup("/player/knight_m_idle_anim_f2", 28, gp.tileSize);
		knight_right4 = setup("/player/knight_m_idle_anim_f3", 28, gp.tileSize);
		
		dwarf_right1 = setup("/player/dwarf_m_idle_anim_f0", 28, gp.tileSize);
		dwarf_right2 = setup("/player/dwarf_m_idle_anim_f1", 28, gp.tileSize);
		dwarf_right3 = setup("/player/dwarf_m_idle_anim_f2", 28, gp.tileSize);
		dwarf_right4 = setup("/player/dwarf_m_idle_anim_f3", 28, gp.tileSize);
		
	}

	
	public void update() {
		
		if(attacking == true) {
			attacking();
		}
		if(keyH.upPressed == true || keyH.downPressed == true || 
				keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true) {
			
			if(keyH.upPressed == true) {
				direction = "up";
				faceDirection = "up";
			}
			if(keyH.downPressed == true) {
				direction = "down";
				faceDirection = "down";
			}
			if(keyH.leftPressed == true){
				direction = "left";
				preDirection = "left";
				faceDirection = "left";
			}
			if(keyH.rightPressed == true) {
				direction = "right";
				preDirection = "right";
				faceDirection = "right";
			}
			if(keyH.upPressed == true && keyH.leftPressed == true) {
				direction = "up_left";
				preDirection = "up_left";
				faceDirection = "left";
			}
			if(keyH.upPressed == true && keyH.rightPressed == true) {
				direction = "up_right";
				preDirection = "up_right";
				faceDirection = "right";
			}
			if(keyH.downPressed == true && keyH.leftPressed == true){
				direction = "down_left";
				preDirection = "down_left";
				faceDirection = "left";
			}
			if(keyH.downPressed == true && keyH.rightPressed == true) {
				direction = "down_right";
				preDirection = "down_right";
				faceDirection = "right";
			}
			
			// Check tile collision
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			// Check object collision
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
//			// Check NPC collision
//			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
//			interactNPC(npcIndex);
			
			// Check monster collision
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			contactMonster(monsterIndex);
			
			// Check event
//			gp.eHandler.checkEvent();
			
			// if collision false, player can move
//			if(collisionOn == false && keyH.enterPressed == false) {
				
			switch(direction) {
			case "up": worldY -= speed; break;
			case "down": worldY += speed; break;
			case "left": worldX -= speed; break;
			case "right": worldX += speed; break;
			case "up_left": worldY -= speed; worldX -= speed; break;
			case "up_right": worldY -= speed; worldX += speed; break;
			case "down_left": worldY += speed; worldX -= speed; break;
			case "down_right": worldY += speed; worldX += speed; break;
			}

//			}
				
			gp.keyH.enterPressed = false;
			
			spriteCounter++;
			if(spriteCounter > 5) {
				if(spriteNum == 1) {spriteNum = 2;}
				else if(spriteNum == 2) {spriteNum = 3;}
				else if(spriteNum == 3) {spriteNum = 4;}
				else if(spriteNum == 4) {spriteNum = 1;}
				spriteCounter = 0;
			}
		}
		else {
			
			direction = "idle";
			spriteCounter++;
			if(spriteCounter > 5) {
				if(spriteNum == 1) {spriteNum = 2;}
				else if(spriteNum == 2) {spriteNum = 3;}
				else if(spriteNum == 3) {spriteNum = 4;}
				else if(spriteNum == 4) {spriteNum = 1;}
				spriteCounter = 0;
			}
		}
		
		for(int i=0; i<projectile.length; i++) {
			for(int j=0; j<gp.player.inventory.size(); j++) {
				if(projectile[j][i] != null) {
					projectile[j][i].attackAvailableCounter++;
					
					if(projectile[j][i].attackAvailableCounter >= projectile[j][i].attackCD) {
					
						// Set default coordinates, direction and user
						projectile[j][i].set(worldX, worldY, faceDirection, true, this);
						
						// Add it to the list
						gp.projectileList.add(projectile[j][i]);
						
						projectile[j][i].attackAvailableCounter = 0;
						
						
						if(projectile[j][i].alive == false) {
							projectile[j][i] = null;
						}
//						gp.playSE(10);
					}
					
				}
			}
			
			
		}
		
		// This needs to be outside of key's if statement
		if(invincible == true) {
			invincibleCounter++;
			if(invincibleCounter > 60) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
		if(life > maxLife) {
			life = maxLife;
		}
		
	}
	
	public void attacking() {
		
		
//		spriteCounter++;
//		
//		if(spriteCounter <= 5) {
//			spriteNum = 1;
//		}
//		if(spriteCounter > 5 && spriteCounter <= 25) {
//			spriteNum = 2;
//			
		// save the current worldX, worldY, solidArea for the player
		int currentWorldX = worldX;
		int currentWorldY = worldY;
		int solidAreaWidth = solidArea.width;
		int solidAreaHeight = solidArea.height;
		
		// Adjust player's worldX/Y for the attackArea
		switch(direction) {
		case "up": worldY -= attackArea.height; break;
		case "down": worldY += attackArea.height; break;
		case "left": worldX -= attackArea.height; break;
		case "right": worldX += attackArea.height; break;
		}
		// attackArea becomes solidArea
		solidArea.width = attackArea.width;
		solidArea.height = attackArea.height;
		// Check monster collision with updated worldX/Y and solidArea
		int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
//		damageMonster(monsterIndex, attack);
		
		worldX = currentWorldX;
		worldY = currentWorldY;
		solidArea.width = solidAreaWidth;
		solidArea.height = solidAreaHeight;
//		}
		
		if(spriteCounter > 25) {
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
		}
	}
	
	public void pickUpObject(int i) {
		
		// Touch the object (999 -> not touch any object)
		if(i != 999) {
			
			// pickup only items
			if(gp.obj[i].type == type_pickUpOnly) {
				
				gp.obj[i].use(this);
				checkLevelUp();
				gp.obj[i] = null;
			}
			// inventory items
			else {
				String text;

				if(inventory.size() != maxInventorySize) {
					
					inventory.add(gp.obj[i]);
					gp.playSE(1);
					text = "Get a "+gp.obj[i].name+"!";
				}
				else {
					text = "You cannot carry any more!";
				}
				gp.ui.addMessage(text);
				gp.obj[i] = null;
			}
			
		}
	}

	public void contactMonster(int i) {
		
		if(i != 999) {
			
			if(invincible == false && gp.monster[i].dying == true) {
				gp.playSE(6);
				
				int damage = gp.monster[i].attack - gp.player.defense;
				if(damage < 0) {
					damage = 0;
				}
				
				life -= damage;
				invincible = true;
			}
		}
	}
	
	public void damageMonster(int i, int attack, Projectile projectile) {
		
		if(i != 999) {
			
			if(gp.monster[i].invincible == false) {
				
				gp.playSE(5);
				
				int damage = attack - gp.monster[i].defense;
				if(damage < 0) {
					damage = 0;
				}
				
				gp.monster[i].life -= damage;
				gp.ui.addMessage(damage + "damage!");
				
				gp.monster[i].invincible = true;
				gp.monster[i].damageRection();
				
				projectile.targetValue--;
				
				if(gp.monster[i].life <= 0) {
					gp.monster[i].dying = true;
					gp.ui.addMessage("killed the "+gp.monster[i].name+"!");
					exp += gp.monster[i].exp;
					checkLevelUp();
				}
			}
		}
	}
	
	public void checkLevelUp() {
		
		if(exp >= nextLevelExp) {
			
			level++;
			exp -= nextLevelExp;
			nextLevelExp += 2;
			maxLife += 2;
			strength++;
			dexterity++;
			attack = getAttack();
			defense = getDefense();
			
//			gp.playSE(8);
			gp.gameState = gp.levelUpState;
			for(int i=0; i<options.length; i++) {
				options[i] = new Random().nextInt(10); // 0~10 weapon option, 11~20 ability option
			}
			
			
		}
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage  image = null;
		int tmpScreenX = screenX;
		int tmpScreenY = screenY;
		
		switch(direction) {
		case "up":
			if(preDirection == "left" || preDirection == "up_left" || preDirection == "down_left") {
				if(spriteNum == 1) {image = left1;}
				if(spriteNum == 2) {image = left2;}
				if(spriteNum == 3) {image = left3;}
				if(spriteNum == 4) {image = left4;}
			}
			if(preDirection == "right" || preDirection == "up_right" || preDirection == "down_right") {
				if(spriteNum == 1) {image = right1;}
				if(spriteNum == 2) {image = right2;}
				if(spriteNum == 3) {image = right3;}
				if(spriteNum == 4) {image = right4;}
			}
			break;
		case "down":
			if(preDirection == "left" || preDirection == "up_left" || preDirection == "down_left") {
				if(spriteNum == 1) {image = left1;}
				if(spriteNum == 2) {image = left2;}
				if(spriteNum == 3) {image = left3;}
				if(spriteNum == 4) {image = left4;}
			}
			if(preDirection == "right" || preDirection == "up_right" || preDirection == "down_right") {
				if(spriteNum == 1) {image = right1;}
				if(spriteNum == 2) {image = right2;}
				if(spriteNum == 3) {image = right3;}
				if(spriteNum == 4) {image = right4;}
			}
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
		case "idle":
			if(preDirection == "left" || preDirection == "up_left" || preDirection == "down_left") {
				if(spriteNum == 1) {image = idle_left1;}
				if(spriteNum == 2) {image = idle_left2;}
				if(spriteNum == 3) {image = idle_left3;}
				if(spriteNum == 4) {image = idle_left4;}
			}
			if(preDirection == "right" || preDirection == "up_right" || preDirection == "down_right") {
				if(spriteNum == 1) {image = idle_right1;}
				if(spriteNum == 2) {image = idle_right2;}
				if(spriteNum == 3) {image = idle_right3;}
				if(spriteNum == 4) {image = idle_right4;}
			}
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
		}
		

		// player HP bar
		double oneScale =(double)gp.tileSize/maxLife;
		double hpBarValue = oneScale*life;
		
		g2.setColor(new Color(35,35,35));
		g2.fillRect(screenX-11, screenY-6, gp.tileSize+2, 10);
		
		g2.setColor(new Color(255,0,30));
		g2.fillRect(screenX-10, screenY-5, (int)hpBarValue, 8);
		
//			hpBarCounter++;
//			
//			if(hpBarCounter > 600) { // 10s
//				hpBarCounter = 0;
//				hpBarOn = false;
//			}
		
		if(invincible == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		}
		
		g2.drawImage(image, tmpScreenX, tmpScreenY, null);
		
		// Reset alpha
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
	}
}

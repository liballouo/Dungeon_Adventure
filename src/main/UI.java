package main;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entity.Entity;
import object.OBJ_Skull;

public class UI {

	GamePanel gp;
	Graphics2D g2;
	Font maruMonica;
	Font arial_40, arial_80B;
//	BufferedImage keyImage ;
//	BufferedImage heart_full, heart_half, heart_blank;
	BufferedImage skullImage;
	public boolean messageOn = false;
//	public String message = "";
//	int messageCounter = 0;
	ArrayList<String> message = new ArrayList<>();
	ArrayList<Integer> messageCounter = new ArrayList<>();
	public boolean gameFinished = false;
	public String currentDialogue = "";
	public int commandNum = 0; // title selection
	public int titleScreenState = 0; // 0: the first screen, 1: the second screen
	public int slotCol = 0;
	public int slotRow = 0;
	
	
	// character image
	int spriteNum = 1;
	int spriteCounter = 0;
	public int characterWidth = 28;
	public int characterHeight = 48; 
	int characterImageWidth = characterWidth*2; 
	int characterImageHeight = characterHeight*2;
	
	// time
	Timer timer;
	boolean startTimer;
	public int second, minute;
	String ddSecond, ddMinute;	
	DecimalFormat dFormat = new DecimalFormat("00");
	
	public UI(GamePanel gp) {
		this.gp = gp;
	
		try {
			InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
			maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80B = new Font("Airal", Font.BOLD, 80);
		
		startTimer = true;
		
		OBJ_Skull skull = new OBJ_Skull(gp);
		skullImage = skull.image;
//		OBJ_Key key = new OBJ_Key(gp);
//		keyImage = key.image;
		
		// create hud object
//		Entity heart = new OBJ_Heart(gp);
//		heart_full = heart.image;
//		heart_half = heart.image2;
//		heart_blank = heart.image3;
	}
	
	public void addMessage(String text) {
		
//		message = text;
//		messageOn = true;
		
		message.add(text);
		messageCounter.add(0);
	}
	
	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		
		g2.setFont(maruMonica);
		g2.setColor(Color.white);
		
		// title state
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
			//drawMessage();
		}
		
		// play state
		if(gp.gameState == gp.playState) {
			//drawPlayerLife();
			drawInformations();
		}
		// pause state
		if(gp.gameState == gp.pauseState) {
			//drawPlayerLife();
			timer.stop();
			drawInformations();
			drawPauseScreen();
		}
		// dialogue state
		if(gp.gameState == gp.dialogueState) {
			//drawPlayerLife();
			timer.stop();
			drawInformations();
//			drawDialogueScreen();
		}
		// character state
		if(gp.gameState == gp.characterState) {
			timer.stop();
			drawInformations();
			drawInventory();
			drawCharacterScreen();
		}
		// level up state
		if(gp.gameState == gp.levelUpState) {
			timer.stop();
			drawInformations();
			drawLevelUpScreen();
		}
		// gameOver state
		if(gp.gameState == gp.gameOverState) {
			drawGameOverScreen();
		}
		
	}

//	public void drawMessage() {
//		
//		int messageX = gp.tileSize;
//		int messageY = gp.tileSize*4;
//		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
//		
//		for(int i=0; i<message.size(); i++) {
//			
//			if(message.get(i) != null) {
//				
//				g2.setColor(Color.white);
//				g2.drawString(message.get(i), messageX, messageY);
//				
//				int counter = messageCounter.get(i) + 1; // messageCounter ++
//				messageCounter.set(i,counter); // set the counter to the array\
//				messageY += 50;
//				
//				if(messageCounter.get(i) > 180) {
//					message.remove(i);
//					messageCounter.remove(i);
//				}
//			}
//		}
//	}
	
	
	public void drawInformations() {
		drawExpBar();
		drawKillAmount();
		drawItem();
		drawPlayTime();
	}
	
	public void resetTimer() {
		
		timer.restart();
		int x = gp.screenWidth/2-gp.tileSize;
		int y = 65;

		second = 0;
		minute = 0;
		ddSecond = dFormat.format(second);
		ddMinute = dFormat.format(minute);
		String text;

		text = ddMinute + ":" + ddSecond;
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
		g2.drawString(text, x, y);
	}
	
	public void drawPlayTime() {
		
		if(startTimer == true) {
			timer = new Timer(1000, new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					second++;
					
					ddSecond = dFormat.format(second);
					ddMinute = dFormat.format(minute);				
					
					if(second==60) {
						second=0;
						minute++;
						
						ddSecond = dFormat.format(second);
						ddMinute = dFormat.format(minute);
					}
				}
			});
			startTimer = false;
		}
		
		if(gp.gameState == gp.playState) {

			// start timer
			timer.start();
		}
		int x = gp.screenWidth/2-gp.tileSize;
		int y = 65;
		
		String text;
		if(ddMinute == null) {
			ddMinute = "00";
			ddSecond = "00";
		}
		text = ddMinute + ":" + ddSecond;
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
		g2.drawString(text, x, y);
		
	}
	
	public void drawTitleScreen() {
				
		if(titleScreenState == 0) {
			// background
			g2.setColor(new Color(0, 0, 0));
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
			// title name
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
			String text = "Dungeon Adventure";
			int x = getXforCenteredText(text);
			int y = gp.tileSize*3;
			
			// shadow
			g2.setColor(Color.gray);
			g2.drawString(text, x+4, y+4);
			// main color
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			
			// character image
			drawCharacterImage(0, -1);
			
			// menu
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
			
			text = "NEW GAME";
			x = getXforCenteredText(text);
			y += gp.tileSize*6;
			g2.drawString(text, x, y);
			if(commandNum == 0) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "LOAD GAME";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 1) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "QUIT";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 2) {
				g2.drawString(">", x-gp.tileSize, y);
			}
		}
		else if(titleScreenState == 1) {
			
			// class selection screen
			g2.setColor(Color.white);
			g2.setFont(g2.getFont().deriveFont(42F));
			
			String text = "Select your character class!";
			int x = getXforCenteredText(text);
			int y = gp.tileSize + 20;
			g2.setFont(g2.getFont().deriveFont(Font.BOLD));
			g2.drawString(text, x, y);
			
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN));
			
			
			text = "Elf";
			x = getXforCenteredText(text);
			y += gp.tileSize*3;
			g2.drawString(text, x, y);
			if(commandNum == 0) {
				g2.drawString(">", x-gp.tileSize, y);
				drawCharacterImage(1, commandNum);
				drawCharacterDescription(commandNum);
			}
			
			text = "Wizzard";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 1) {
				g2.drawString(">", x-gp.tileSize, y);
				drawCharacterImage(1, commandNum);
				drawCharacterDescription(commandNum);
			}
			
			text = "Knight";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 2) {
				g2.drawString(">", x-gp.tileSize, y);
				drawCharacterImage(1, commandNum);
				drawCharacterDescription(commandNum);
			}
			
			text = "Dwarf";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 3) {
				g2.drawString(">", x-gp.tileSize, y);
				drawCharacterImage(1, commandNum);
				drawCharacterDescription(commandNum);
			}
			
			text = "Back";
			x = getXforCenteredText(text);
			y += gp.tileSize*2;
			g2.drawString(text, x, y);
			if(commandNum == 4) {
				g2.drawString(">", x-gp.tileSize, y);
			}
		}
	}
	
	public void drawCharacterImage(int titleScreen, int selection) {
		if(titleScreen == 0) {
			int x = gp.screenWidth/2 - gp.tileSize*4;
			int y = gp.tileSize*5;
			
//			BufferedImage elf1, elf2, elf3, elf4, wizzard1, wizzard2, wizzard3, wizzard4;
			
			spriteCounter++;
			if(spriteCounter > 7) {
				if(spriteNum == 1) {spriteNum = 2;}
				else if(spriteNum == 2) {spriteNum = 3;}
				else if(spriteNum == 3) {spriteNum = 4;}
				else if(spriteNum == 4) {spriteNum = 1;}
				spriteCounter = 0;
			}
			switch(spriteNum) {
			case 1: g2.drawImage(gp.player.elf_right1, x, y, characterImageWidth, characterImageHeight, null); 
					g2.drawImage(gp.player.wizzard_right1, x+gp.tileSize*2, y, characterImageWidth, characterImageHeight, null);
					g2.drawImage(gp.player.knight_right1, x+gp.tileSize*4, y, characterImageWidth, characterImageHeight, null);
					g2.drawImage(gp.player.dwarf_right1, x+gp.tileSize*6, y, characterImageWidth, characterImageHeight, null);

					break;
			case 2: g2.drawImage(gp.player.elf_right2, x, y, characterImageWidth, characterImageHeight, null);
					g2.drawImage(gp.player.wizzard_right2, x+gp.tileSize*2, y, characterImageWidth, characterImageHeight, null);
					g2.drawImage(gp.player.knight_right2, x+gp.tileSize*4, y, characterImageWidth, characterImageHeight, null);
					g2.drawImage(gp.player.dwarf_right2, x+gp.tileSize*6, y, characterImageWidth, characterImageHeight, null);

					break;
			case 3: g2.drawImage(gp.player.elf_right3, x, y, characterImageWidth, characterImageHeight, null);
					g2.drawImage(gp.player.wizzard_right3, x+gp.tileSize*2, y, characterImageWidth, characterImageHeight, null);
					g2.drawImage(gp.player.knight_right3, x+gp.tileSize*4, y, characterImageWidth, characterImageHeight, null);
					g2.drawImage(gp.player.dwarf_right3, x+gp.tileSize*6, y, characterImageWidth, characterImageHeight, null);

					break;
			case 4: g2.drawImage(gp.player.elf_right4, x, y, characterImageWidth, characterImageHeight, null);
					g2.drawImage(gp.player.wizzard_right4, x+gp.tileSize*2, y, characterImageWidth, characterImageHeight, null);
					g2.drawImage(gp.player.knight_right4, x+gp.tileSize*4, y, characterImageWidth, characterImageHeight, null);
					g2.drawImage(gp.player.dwarf_right4, x+gp.tileSize*6, y, characterImageWidth, characterImageHeight, null);

					break;
			}
			
		}
		if(titleScreen == 1) {
			int x = gp.screenWidth/2 - gp.tileSize*6;
			int y = gp.tileSize*4;
						
			spriteCounter++;
			if(spriteCounter > 7) {
				if(spriteNum == 1) {spriteNum = 2;}
				else if(spriteNum == 2) {spriteNum = 3;}
				else if(spriteNum == 3) {spriteNum = 4;}
				else if(spriteNum == 4) {spriteNum = 1;}
				spriteCounter = 0;
			}
			switch(spriteNum) {
			case 1: switch(selection) {
					case 0: g2.drawImage(gp.player.elf_right1, x, y, characterImageWidth*2, characterImageHeight*2, null); break;
					case 1: g2.drawImage(gp.player.wizzard_right1, x, y, characterImageWidth*2, characterImageHeight*2, null); break;
					case 2:	g2.drawImage(gp.player.knight_right1, x, y, characterImageWidth*2, characterImageHeight*2, null); break;
					case 3:	g2.drawImage(gp.player.dwarf_right1, x, y, characterImageWidth*2, characterImageHeight*2, null); break;
					}

					break;
			case 2: switch(selection) {
					case 0: g2.drawImage(gp.player.elf_right2, x, y, characterImageWidth*2, characterImageHeight*2, null); break;
					case 1: g2.drawImage(gp.player.wizzard_right2, x, y, characterImageWidth*2, characterImageHeight*2, null); break;
					case 2:	g2.drawImage(gp.player.knight_right2, x, y, characterImageWidth*2, characterImageHeight*2, null); break;
					case 3:	g2.drawImage(gp.player.dwarf_right2, x, y, characterImageWidth*2, characterImageHeight*2, null); break;
					}
					break;
			case 3: switch(selection) {
					case 0: g2.drawImage(gp.player.elf_right3, x, y, characterImageWidth*2, characterImageHeight*2, null); break;
					case 1: g2.drawImage(gp.player.wizzard_right3, x, y, characterImageWidth*2, characterImageHeight*2, null); break;
					case 2:	g2.drawImage(gp.player.knight_right3, x, y, characterImageWidth*2, characterImageHeight*2, null); break;
					case 3:	g2.drawImage(gp.player.dwarf_right3, x, y, characterImageWidth*2, characterImageHeight*2, null); break;
					}
					break;
			case 4: switch(selection) {
					case 0: g2.drawImage(gp.player.elf_right4, x, y, characterImageWidth*2, characterImageHeight*2, null); break;
					case 1: g2.drawImage(gp.player.wizzard_right4, x, y, characterImageWidth*2, characterImageHeight*2, null); break;
					case 2:	g2.drawImage(gp.player.knight_right4, x, y, characterImageWidth*2, characterImageHeight*2, null); break;
					case 3:	g2.drawImage(gp.player.dwarf_right4, x, y, characterImageWidth*2, characterImageHeight*2, null); break;
					}
					break;
			}
		}
		
	}
	public void drawCharacterDescription(int selection) {
		
		int speed=0, maxLife=0, strength=0, defense=0;
		String initWeapon="";
		
		g2.setFont(g2.getFont().deriveFont(25F));
		int x = gp.screenWidth/2 - gp.tileSize*7+20;
		int y = gp.tileSize*10;
		String text = "";
		switch(selection) {
		case 0: text = "Swift and accurate, \nthe Elf rains arrows from afar."; 
				speed = gp.player.classSpeed[gp.player.class_elf];
				maxLife = gp.player.classMaxLife[gp.player.class_elf];
				strength = gp.player.classStrength[gp.player.class_elf];
				defense = gp.player.classDefense[gp.player.class_elf];
				initWeapon = "Arrow";
				break;
		case 1: text = "Wisdom and powerful, \nthe Wizard casts fiery spells.";
				speed = gp.player.classSpeed[gp.player.class_wizard];
				maxLife = gp.player.classMaxLife[gp.player.class_wizard];
				strength = gp.player.classStrength[gp.player.class_wizard];
				defense = gp.player.classDefense[gp.player.class_wizard];
				initWeapon = "Fireball";
				break;
		case 2: text = "Honorable and strong, \nthe Knight wields a sharp sword."; 
				speed = gp.player.classSpeed[gp.player.class_knight];
				maxLife = gp.player.classMaxLife[gp.player.class_knight];
				strength = gp.player.classStrength[gp.player.class_knight];
				defense = gp.player.classDefense[gp.player.class_knight];
				initWeapon = "Sword";
				break;
		case 3: text = "Stout and resilient, \nthe Dwarf wields a deadly axe.";
				speed = gp.player.classSpeed[gp.player.class_dwarf];
				maxLife = gp.player.classMaxLife[gp.player.class_dwarf];
				strength = gp.player.classStrength[gp.player.class_dwarf];
				defense = gp.player.classDefense[gp.player.class_dwarf];
				initWeapon = "Ax";
				break;
		}
		for(String line: text.split("\n")) {
			g2.drawString(line, x, y);
			x -= 35;
			y += 30;
		}
		
		int frameX = gp.screenWidth/2+gp.tileSize*3-20;
		int frameY = gp.tileSize*2;
		int frameWidth = gp.tileSize*5;
		int frameHeight = gp.tileSize*8;
		drawSubWindow(frameX, frameY, frameWidth ,frameHeight , 2);
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
		int textX = frameX+20;
		int textY = frameY+gp.tileSize;
		
		g2.drawString("SPEED:", textX, textY);
		textY += 40;
		g2.drawString("MAXLIFE:", textX, textY);
		textY += 40;
		g2.drawString("STRENGTH:", textX, textY);
		textY += 40;
		g2.drawString("DEFENSE:", textX, textY);
		textY += 80;
		g2.drawString("WEAPON:", textX, textY);
		
		int tailX = (frameX + frameWidth) - 30;
		textY = frameY + gp.tileSize;
		String value;
		
		value = String.valueOf(speed);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += 40;
		
		value = String.valueOf(maxLife);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += 40;
		
		value = String.valueOf(strength);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += 40;
		
		value = String.valueOf(defense);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += 80;
		
		value = String.valueOf(initWeapon);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += 40;
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 42F));

	}
	public void drawPauseScreen() {
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
		String text = "PAUSED";
		int x = getXforCenteredText(text);
		int y = gp.screenHeight/2;
		
		g2.drawString(text, x, y);
		
		g2.setColor(Color.black);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3F));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
	}
	
	public void drawDialogueScreen() {
		
		// window
		int x = gp.tileSize*2;
		int y = gp.tileSize/2;
		int width = gp.screenWidth - (gp.tileSize*4);
		int height = gp.tileSize*4;
		drawSubWindow(x, y, width, height, 5);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
		x += gp.tileSize;
		y += gp.tileSize;
		
		for(String line : currentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y += 40;
		}
	}
	
	public void drawLevelUpScreen() {
		// Create a frame
		final int frameX = gp.screenWidth/2-gp.tileSize*4;
		final int frameY = gp.tileSize;
		final int frameWidth = gp.screenWidth/2;
		final int frameHeight = gp.screenHeight-gp.tileSize*2;
		
		drawSubWindow(frameX, frameY, frameWidth, frameHeight, 5);
		
		// text
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(40F));
		String text = "level Up!";
		int x = getXforCenteredText(text);
		int textX = x;
		int textY = frameY + gp.tileSize;
		final int lineHeight = 35;
		g2.drawString(text, textX, textY);
		
		// options
		x = getXforCenteredText(text)-gp.tileSize*2;
		int y = gp.tileSize*3-20;
		int windowWidth = 325;
		int windowHeight = 125;
		
		// option windows
		for(int i=0; i<3; i++) {
			drawSubWindow(x, y, windowWidth, windowHeight, 0.8f);
			y += windowHeight;
		}
		y = gp.tileSize*3 + windowHeight/2-5;
		
		// cursors
		Entity[] weapons = new Entity[3];
		for(int i=0; i<3; i++) {
			weapons[i] = gp.player.weaponList.get(gp.player.player_class).get(gp.player.options[i]);
		}
		x -= 17;
		if(commandNum == 0) {g2.drawString(">", x, y);}
		y += windowHeight;
		if(commandNum == 1) {g2.drawString(">", x, y);}
		y += windowHeight;
		if(commandNum == 2) {g2.drawString(">", x, y);}
		
		// option sub window
		int subWindowWidth = gp.tileSize+15;
		int subWindowHeight = gp.tileSize+15;
		x += gp.tileSize-25;
		y = gp.tileSize*3-12;
		for(int i=0; i<3; i++) {
			drawSubWindow(x, y, subWindowWidth, subWindowHeight, 0.8f);
			y += windowHeight;
		}
		// option image
		x += 5;
		y = gp.tileSize*3-2;
		for(int i=0 ;i<3; i++) {
			g2.drawImage(weapons[i].image, x+weapons[i].imageOffsetX, y+weapons[i].imageOffsetY, weapons[i].imageWidth, weapons[i].imageHeight, null);
			y += windowHeight;
		}
		// option description
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
		y = gp.tileSize*2-7+subWindowHeight*2;
		
		boolean[] newWeapon = new boolean[3];
		int[] index = new int[] {-1, -1, -1};
		for(int i=0 ;i<3; i++) {
			newWeapon[i] = true;
			for(int j=0; j<gp.player.inventory.size(); j++) {
				if(gp.player.inventory.get(j) != null) {
					if(gp.player.inventory.get(j).getClass().equals(weapons[i].getClass())){
						newWeapon[i] = false;
						index[i] = j;
					}
				}
			}
			
			if(newWeapon[i] == true) {
				text = weapons[i].description;
			}
			else {
				text = "Attack: "+weapons[i].attackList[gp.player.weaponLevel[index[i]]+1]+", Targets: "+weapons[i].maxTargetValueList[gp.player.weaponLevel[index[i]]+1]
						+"\nAttack CD: "+weapons[i].attackCDList[gp.player.weaponLevel[index[i]]+1];
				if(weapons[i].isProjectile == true) {
					text += ", Range: "+weapons[i].maxRangeList[gp.player.weaponLevel[index[i]]+1];
				}
			}
			for(String line: text.split("\n")) {
				g2.drawString(line, x, y);
				y += 20;
			}
			y += subWindowHeight+20;
		}
		// option name
		x += subWindowWidth+20;
		y = gp.tileSize*3+8;
		for(int i=0 ;i<3; i++) {
			g2.drawString(weapons[i].name, x, y);
			y += windowHeight;
		}
		// new or level up
		x += subWindowWidth*2;
		y = gp.tileSize*3+8;
		for(int i=0; i<3; i++) {
			if(newWeapon[i] == true) {
				text = "NEW!!";
				g2.setColor(Color.yellow);
				g2.drawString(text, x, y);
				g2.setColor(Color.white);
			}
			else {
				text = "[Level: "+(gp.player.weaponLevel[index[i]]+1)+"]";
				g2.drawString(text, x, y);
			}
			y += windowHeight;
		}
		
		
//		g2.drawImage(weapon1.image, x, y, gp.tileSize, gp.tileSize, null);

//		x += gp.tileSize;
//		
//		text = weapon1.name;
//		x += gp.tileSize;
//		y = gp.tileSize*3;
//		g2.setFont(g2.getFont().deriveFont(25F));
//		g2.drawString(text, x+20, y);
		
		
	}
	
	public void drawCharacterScreen() {
		
		// Create a frame
		final int frameX = gp.tileSize;
		final int frameY = gp.tileSize;
		final int frameWidth = gp.tileSize*5;
		final int frameHeight = gp.tileSize*10;
		
		drawSubWindow(frameX, frameY, frameWidth, frameHeight, 5);
		
		// text
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int textX = frameX + 20;
		int textY = frameY + gp.tileSize;
		final int lineHeight = 35; 
		
		// names
		g2.drawString("Level", textX, textY);
		textY += lineHeight*2;
		g2.drawString("Life", textX, textY);
		textY += lineHeight*2;
		g2.drawString("Strength", textX, textY);
		textY += lineHeight*2;
		g2.drawString("Defense", textX, textY);
		textY += lineHeight*2;
		g2.drawString("Exp", textX, textY);
		textY += lineHeight*2;
		g2.drawString("Coin", textX, textY);
		textY += lineHeight*2;
//		g2.drawString("Weapon", textX, textY);
//		textY += lineHeight;
		
		// values
		int tailX = (frameX + frameWidth) - 30;
		// Reset textY
		textY = frameY + gp.tileSize;
		String value;
		
		value = String.valueOf(gp.player.level);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight*2;
		
		value = String.valueOf(gp.player.life+"/"+gp.player.maxLife);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight*2;
		
		value = String.valueOf(gp.player.strength);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight*2;
		
		value = String.valueOf(gp.player.defense);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight*2;
		
		value = String.valueOf(gp.player.exp);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight*2;
		
		value = String.valueOf(gp.player.coin);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight*2;		
		
//		g2.drawImage(gp.player.currentWeapon.down1, tailX-gp.tileSize, textY-10, null);
	}
	
	public void drawGameOverScreen() {
		g2.setColor(new Color(0,0,0,150));
		g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
		
		int x;
		int y;
		String text;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,110f));
		
		text = "Game Over";
		//shadow
		g2.setColor(Color.black);
		x = getXforCenteredText(text);
		y = gp.tileSize*4;
		g2.drawString(text, x, y);
		//Main
		g2.setColor(Color.white);
		g2.drawString(text, x-4, y-4);
		
		//Retry
		g2.setFont(g2.getFont().deriveFont(50f));
		text = "Retry";
		x = getXforCenteredText(text);
		y +=gp.tileSize*4;
		g2.drawString(text, x, y);
		if(commandNum == 0 ) {
			g2.drawString(">", x-40, y);
		}
		
		//Back to title screen
		text = "Quit";
		x = getXforCenteredText(text);
		y += 55;
		if(commandNum == 1) {
			g2.drawString(">", x-40, y);
		}
		g2.drawString(text, x, y);
	}
	
	public void drawExpBar() {
		// player exp bar
		double oneScale =(double)gp.screenWidth/gp.player.nextLevelExp;
		double expBarValue = oneScale*gp.player.exp;
		
		g2.setColor(new Color(35,35,35));
		g2.fillRect(0, 0, gp.screenWidth, 32);
		
		g2.setColor(new Color(0, 197, 205));
		g2.fillRect(1, 1, (int)expBarValue, 30);
	}
	
	public void drawItem() {
		
		int slotX = 5;
		int slotY = 35;
		int slotSize = gp.tileSize/2;
		
		// Draw player's items
		for(int i=0; i<gp.player.inventory.size(); i++) {
			
			g2.drawImage(gp.player.inventory.get(i).image, slotX, slotY, gp.player.inventory.get(i).image.getWidth(), gp.player.inventory.get(i).image.getHeight(), null);
			slotX += gp.player.inventory.get(i).image.getWidth()+5;
			
			if(i % 5 == 4) {
				slotX = 0;
				slotY += slotSize;
			}
		}
	}
	public void drawKillAmount() {

		int x = gp.screenWidth - gp.tileSize*3;
		int y = 20;
		
		// Draw skull image
		g2.drawImage(skullImage, x, y, null);
		
		String text;
		x += gp.tileSize;
		y = 60;
		text = "X " + gp.player.killAmount;
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
		g2.drawString(text, x, y);
	}
	public void drawInventory() {
		
		// frame
		int frameX = gp.tileSize*9;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize*6;
		int frameHeight = gp.tileSize*5;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight, 5);
		
		// slot
		final int slotXStart = frameX + 20;
		final int slotYStart = frameY + 20;
		int slotX = slotXStart;
		int slotY = slotYStart;
		int slotSize = gp.tileSize+3;
		
		String[] text = new String[4];
		
		// Draw player's items
		for(int i=0; i<gp.player.inventory.size(); i++) {
			
			g2.drawImage(gp.player.inventory.get(i).image, slotX+gp.player.inventory.get(i).imageOffsetX, slotY+gp.player.inventory.get(i).imageOffsetY, null);
			slotX += slotSize;
			
			if(i % 5 == 4) {
				slotX = slotXStart;
				slotY += slotSize;
			}
		}
		
		// cursor
		int cursorX = slotXStart + (slotSize*slotCol);
		int cursorY = slotYStart + (slotSize*slotRow);
		int cursorWidth = gp.tileSize;
		int cursorHeight = gp.tileSize;
		// Draw cursor
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(2));
		g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
		
		// description
		int dFrameX = frameX;
		int dFrameY = frameY + frameHeight;
		int dFrameWidth = frameWidth;
		int dFrameHeight = gp.tileSize*3;
		// Draw
		int textX = dFrameX + 20;
		int textY = dFrameY + gp.tileSize;
		g2.setFont(g2.getFont().deriveFont(25F));
		
		int itemIndex = getItemIndexOnSlot();
		
		
		
		if(itemIndex < gp.player.inventory.size()) {
			
			drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight, 5);

			for(String line: gp.player.inventory.get(itemIndex).description.split("\n")) {
				
				g2.drawString(line, textX, textY);
				textY += 32;
			}
		}
		
	}
	
	public int getItemIndexOnSlot() {
		int itemIndex = slotCol + (slotRow*5);
		return itemIndex;
	}
	public void drawSubWindow(int x, int y, int width, int height, float strokeNum) {
		
		Color color = new Color(0,0,0,220);
		g2.setColor(color);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		color = new Color(255,255,255);
		g2.setColor(color);
		g2.setStroke(new BasicStroke(strokeNum));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
	}
	
	public int getXforCenteredText(String text) {
		
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		
		return x;
	}
	
	public int getXforAlignToRightText(String text, int tailX) {
		
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - length;
		
		return x;
	}
}

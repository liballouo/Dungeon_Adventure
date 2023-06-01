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

import entity.Entity;
import object.OBJ_Heart;
import object.OBJ_Key;

public class UI {

	GamePanel gp;
	Graphics2D g2;
	Font maruMonica;
	Font arial_40, arial_80B;
//	BufferedImage keyImage ;
	BufferedImage heart_full, heart_half, heart_blank;
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
	
	double playTime;
	DecimalFormat dFormat = new DecimalFormat("#0.00");
	
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
			drawMessage();
		}
		
		// play state
		if(gp.gameState == gp.playState) {
			//drawPlayerLife();
			drawExpBar();
		}
		// pause state
		if(gp.gameState == gp.pauseState) {
			//drawPlayerLife();
			drawExpBar();
			drawPauseScreen();

		}
		// dialogue state
		if(gp.gameState == gp.dialogueState) {
			//drawPlayerLife();
			drawExpBar();
			drawDialogueScreen();
		}
		// character state
		if(gp.gameState == gp.characterState) {
			drawCharacterScreen();
			drawInventory();
		}
		
	}

	
	public void drawMessage() {
		
		int messageX = gp.tileSize;
		int messageY = gp.tileSize*4;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
		
		for(int i=0; i<message.size(); i++) {
			
			if(message.get(i) != null) {
				
				g2.setColor(Color.white);
				g2.drawString(message.get(i), messageX, messageY);
				
				int counter = messageCounter.get(i) + 1; // messageCounter ++
				messageCounter.set(i,counter); // set the counter to the array\
				messageY += 50;
				
				if(messageCounter.get(i) > 180) {
					message.remove(i);
					messageCounter.remove(i);
				}
			}
		}
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
			drawCharacterImage();
			
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
			int y = gp.tileSize*3;
			g2.drawString(text, x, y);
			
			text = "Elf";
			x = getXforCenteredText(text);
			y += gp.tileSize*3;
			g2.drawString(text, x, y);
			if(commandNum == 0) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "Wizzard";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 1) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "Knight";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 2) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "Dwarf";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 3) {
				g2.drawString(">", x-gp.tileSize, y);
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
	
	public void drawCharacterImage() {
		int x = gp.screenWidth/2 - gp.tileSize*4;
		int y = gp.tileSize*5;
		
		BufferedImage elf1, elf2, elf3, elf4, wizzard1, wizzard2, wizzard3, wizzard4;
		
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
		drawSubWindow(x, y, width, height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
		x += gp.tileSize;
		y += gp.tileSize;
		
		for(String line : currentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y += 40;
		}
	}
	
	public void drawCharacterScreen() {
		
		// Create a frame
		final int frameX = gp.tileSize;
		final int frameY = gp.tileSize;
		final int frameWidth = gp.tileSize*5;
		final int frameHeight = gp.tileSize*10;
		
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		// text
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int textX = frameX + 20;
		int textY = frameY + gp.tileSize;
		final int lineHeight = 35; 
		
		// names
		g2.drawString("Level", textX, textY);
		textY += lineHeight;
		g2.drawString("Life", textX, textY);
		textY += lineHeight;
		g2.drawString("Strength", textX, textY);
		textY += lineHeight;
		g2.drawString("Attack", textX, textY);
		textY += lineHeight;
		g2.drawString("Exp", textX, textY);
		textY += lineHeight;
		g2.drawString("Coin", textX, textY);
		textY += lineHeight + 20;
		g2.drawString("Weapon", textX, textY);
		textY += lineHeight;
		
		// values
		int tailX = (frameX + frameWidth) - 30;
		// Reset textY
		textY = frameY + gp.tileSize;
		String value;
		
		value = String.valueOf(gp.player.level);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.life+"/"+gp.player.maxLife);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.strength);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.attack);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.exp);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.coin);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		g2.drawImage(gp.player.currentWeapon.down1, tailX-gp.tileSize, textY-10, null);
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
	
	public void drawInventory() {
		
		// frame
		int frameX = gp.tileSize*9;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize*6;
		int frameHeight = gp.tileSize*5;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		// slot
		final int slotXStart = frameX + 20;
		final int slotYStart = frameY + 20;
		int slotX = slotXStart;
		int slotY = slotYStart;
		int slotSize = gp.tileSize+3;
		
		// Draw player's items
		for(int i=0; i<gp.player.inventory.size(); i++) {
			
			g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, null);
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
		g2.setStroke(new BasicStroke(3));
		g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
		
		// description
		int dFrameX = frameX;
		int dFrameY = frameY + frameHeight;
		int dFrameWidth = frameWidth;
		int dFrameHeight = gp.tileSize*3;
		// Draw
		int textX = dFrameX + 20;
		int textY = dFrameY + gp.tileSize;
		g2.setFont(g2.getFont().deriveFont(28F));
		
		int itemIndex = getItemIndexOnSlot();
		
		if(itemIndex < gp.player.inventory.size()) {
			
			drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);

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
	public void drawSubWindow(int x, int y, int width, int height) {
		
		Color color = new Color(0,0,0,220);
		g2.setColor(color);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		color = new Color(255,255,255);
		g2.setColor(color);
		g2.setStroke(new BasicStroke(5));
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

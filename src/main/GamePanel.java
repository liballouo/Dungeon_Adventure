package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	// Screen settings
	final int originalTileSize = 16; // 16x16 pixel
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; // 48*48 tile
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
	public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

	// World settings
	public final int maxWorldCol = 500;
	public final int maxWorldRow = 500;

	// FPS
	int FPS = 60;
	
	TileManager tileM = new TileManager(this);
	public KeyHandler keyH = new KeyHandler(this);
	Sound music = new Sound();
	Sound se = new Sound();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	Thread gameThread;
	// UI
	public UI ui = new UI(this);
	// Event
	public EventHandler eHandler = new EventHandler(this);
	// Entity and Object
	public Player player = new Player(this, keyH);
	public Entity obj[] = new Entity[20]; // display up to 10 object at he same time
	public Entity attackObj[] = new Entity[20];
	public Entity npc[] = new Entity[10];
	public Entity monster[] = new Entity[100];
	public ArrayList<Entity> projectileList = new ArrayList<>();
	public ArrayList<Entity> entityList = new ArrayList<>();
	
	// Game state
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	public final int characterState = 4;
	public final int levelUpState = 5;
	public final int gameOverState = 6;
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); // for better render performance
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	// called before game starts
	public void setupGame() {
		
		aSetter.setObject();
//		aSetter.setNPC();
		aSetter.setMonster();
		// play music
		playMusic(0);
		gameState = titleState;
	}
	
	public void retry() {
		player.setDefaultPositions();
		player.restoreLife();
		aSetter.setMonster();
	}
	
	public void restart() {
		player.setDefaultValues();
		player.restoreLife();
		player.setValues();
		aSetter.setObject();
		aSetter.setMonster();
		ui.resetTimer();
		playMusic(0);
		//aSetter.setInteractiveTile();
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		
		// draw the screen every 0.01666 seconds
		double drawInterval = 1000000000/FPS; // 1second/FPS
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		long drawCount =0;
		
		
		// Create game loop
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
						
			if(delta >= 1) {
				// 1. UPDATE: update information such as characters' positions
				update();
				// 2. DRAW: draw the screen with the updated information according the FPS
				repaint();
				delta--;
				drawCount++;
			}
			
			if(timer >= 1000000000) {
				//System.out.println("FPS:" + drawCount);
				drawCount = 0;
				timer = 0;
			}
			
		}
	
	}
	
	public void update() {
		
		if(gameState == playState) {
			// player
			player.update();
			// NPC
			for(int i=0; i<npc.length; i++) {
				if(npc[i] != null) {
					npc[i].update();
				}
			}
			// monster
			for(int i=0; i<monster.length; i++) {
				if(monster[i] != null) {
					if(monster[i].alive == true && monster[i].dying == false) {
						monster[i].update();
					}
					if(monster[i].alive == false) {
						monster[i].checkDrop();
						monster[i] = null;
					}
				}
			}
			// projectile
			for(int i=0; i<projectileList.size(); i++) {
				if(projectileList.get(i) != null) {
					if(projectileList.get(i).alive == true) {
						projectileList.get(i).update();
					}
					if(projectileList.get(i).alive == false) {
						projectileList.remove(i);
					}
				}
			}
		}
		if(gameState == pauseState) {
			// nothing
		}
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		// tile screen
		if(gameState == titleState) {
			ui.draw(g2);
		}
		// others
		else {
			// tile
			tileM.draw(g2);
			
			// add entities to the entity list
			// player
			entityList.add(player);
			// NPC
//			for(int i=0; i<npc.length; i++) {
//				if(npc[i] != null) {
//					entityList.add(npc[i]);
//				}
//			}
			// objects
			for(int i=0; i<obj.length; i++) {
				if(obj[i] != null) {
					entityList.add(obj[i]);
				}
			}
			// monster
			for(int i=0; i<monster.length; i++) {
				if(monster[i] != null) {
					entityList.add(monster[i]);
				}
			}
			// projectile
			for(int i=0; i<projectileList.size(); i++) {
				if(projectileList.get(i) != null) {
					entityList.add(projectileList.get(i));
				}
			}
			
			// sort
			Collections.sort(entityList, new Comparator<Entity>() {
				
				@Override
				public int compare(Entity e1, Entity e2) {
					
					int result = Integer.compare(e1.worldY, e2.worldY);
					return result;
				}
			});
			
			// Draw entities
			for(int i=0; i<entityList.size(); i++) {
				entityList.get(i).draw(g2);
			}
			// Empty the entity list
			entityList.clear();
			
			// UI
			ui.draw(g2);
		}
		
		
		
		// debug
//		if(keyH.checkDrawTime == true) {
//			long drawEnd = System.nanoTime();
//			long passed = drawEnd - drawStart;
//			g2.setColor(Color.white);
//			g2.drawString("Draw Time: "+passed, 10, 400);
//			System.out.println("Draw Time:"+passed);
//		}
		
		g2.dispose();
	}
	
	public void playMusic(int i) {
		
		music.setFile(i);
		music.play();
		music.loop();
	}
	
	public void stopMusic() {
		
		music.stop();
	}
	
	public void playSE(int i) {
		
		se.setFile(i);
		se.play();
	}
}

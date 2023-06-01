package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shotKeyPressed;
	//debug 
//	boolean checkDrawTime = false;
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int code = e.getKeyCode();
		
		// title state
		if(gp.gameState == gp.titleState) {
			titleState(code);
		}
		
		// play state
		if(gp.gameState == gp.playState) {
			playState(code);
		}
		// pause state
		else if(gp.gameState == gp.pauseState) {
			pauseState(code);
		}
		// dialogue state
		else if(gp.gameState == gp.dialogueState) {
			dialogueState(code);
		}
		// character state
		else if(gp.gameState == gp.characterState) {
			characterState(code);
		}
		// debug
//		if(code == KeyEvent.VK_T) {
//			if(checkDrawTime == false) {
//				checkDrawTime = true;
//			}
//			else if (checkDrawTime == true) {
//				checkDrawTime = false;
//			}
//		}
				
	}
	
	public void titleState(int code) {
		
		if(gp.ui.titleScreenState == 0) {
			if(code == KeyEvent.VK_W  || code == KeyEvent.VK_UP) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 2;
				}
			}
			if(code == KeyEvent.VK_S  || code == KeyEvent.VK_DOWN) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 2) {
					gp.ui.commandNum = 0;
				}
			}
			if(code == KeyEvent.VK_ENTER) {
				// new game
				if(gp.ui.commandNum == 0) {
					gp.ui.titleScreenState = 1;
				}
				// load game
				if(gp.ui.commandNum == 1) {
					//load game
				}
				// quit
				if(gp.ui.commandNum == 2) {
					System.exit(0);
				}
			}
		}
		// select class screen
		else if(gp.ui.titleScreenState == 1) {
			if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 4;
				}
			}
			if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 4) {
					gp.ui.commandNum = 0;
				}
			}
			if(code == KeyEvent.VK_ENTER) {
				// elf
				if(gp.ui.commandNum == 0) {
					System.out.println("Elf");
					gp.gameState = gp.playState;
//					gp.playMusic(0);
				}
				// wizzard
				if(gp.ui.commandNum == 1) {
					System.out.println("Wizzard");
					gp.gameState = gp.playState;
//					gp.playMusic(0);
				}
				// knight
				if(gp.ui.commandNum == 2) {
					System.out.println("Knight");
					gp.gameState = gp.playState;
//					gp.playMusic(0);
				}
				// dwarf
				if(gp.ui.commandNum == 3) {
					System.out.println("Dwarf");
					gp.gameState = gp.playState;
//					gp.playMusic(0);
				}
				// back
				if(gp.ui.commandNum == 4) {
					gp.ui.titleScreenState = 0;
					gp.ui.commandNum = 0;
				}
			}
		}
	}
	public void playState(int code) {

		if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			upPressed = true;
		}
		if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			downPressed = true;		
		}
		if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			leftPressed = true;
		}
		if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			rightPressed = true;
		}
		if(code == KeyEvent.VK_P) {
			gp.gameState = gp.pauseState;
		}
		if(code == KeyEvent.VK_C) {
			gp.gameState = gp.characterState;
		}
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		if(code == KeyEvent.VK_F) {
			shotKeyPressed = true;
		}
	}
	public void pauseState(int code) {
		if(code == KeyEvent.VK_P) {
			gp.gameState = gp.playState;
		}
	}
	public void dialogueState(int code) {
		if(code == KeyEvent.VK_ENTER) {
			gp.gameState = gp.playState;
		}
	}
	public void characterState(int code) {
		if(code == KeyEvent.VK_C) {
			gp.gameState = gp.playState;
		}
		if(code == KeyEvent.VK_W) {
			if(gp.ui.slotRow != 0) {
				gp.ui.slotRow--;
				gp.playSE(9);
			}
		}
		if(code == KeyEvent.VK_A) {
			if(gp.ui.slotCol != 0) {
				gp.ui.slotCol--;
				gp.playSE(9);
			}
		}
		if(code == KeyEvent.VK_S) {
			if(gp.ui.slotRow != 3) {
				gp.ui.slotRow++;
				gp.playSE(9);
			}
		}
		if(code == KeyEvent.VK_D) {
			if(gp.ui.slotCol != 4) {
				gp.ui.slotCol++;
				gp.playSE(9);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			downPressed = false;		
		}
		if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			leftPressed = false;
		}
		if(code == KeyEvent.VK_D  || code == KeyEvent.VK_RIGHT) {
			rightPressed = false;
		}
		if(code == KeyEvent.VK_F) {
			shotKeyPressed = false;
		}
		
	}
}

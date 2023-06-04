package entity;

import java.util.Random;

import main.GamePanel;

public class AbilitySelection extends Entity{

	public AbilitySelection(GamePanel gp) {
		super(gp);
		
	}
	
	public int selectAbility(int characterClass) {
		
		int selection;
		int i = new Random().nextInt(10)+1;
		
		// 0~10 weapon option
		selection = i;
		// 11~20 ability option
		
		
		return selection;
	}

}

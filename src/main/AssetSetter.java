package main;

import monster.MON_BigDemon;
import monster.MON_GreenSlime;
import object.OBJ_Exp_Normal;
import object.OBJ_HP_Potion;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
	
		int i = 0;
		gp.obj[i] = new OBJ_Exp_Normal(gp);
		gp.obj[i].worldX = gp.tileSize*25;
		gp.obj[i].worldY = gp.tileSize*23;
		i++;
		gp.obj[i] = new OBJ_Exp_Normal(gp);
		gp.obj[i].worldX = gp.tileSize*21;
		gp.obj[i].worldY = gp.tileSize*19;
		i++;
		gp.obj[i] = new OBJ_Exp_Normal(gp);
		gp.obj[i].worldX = gp.tileSize*25;
		gp.obj[i].worldY = gp.tileSize*23;
		i++;
		gp.obj[i] = new OBJ_Exp_Normal(gp);
		gp.obj[i].worldX = gp.tileSize*23;
		gp.obj[i].worldY = gp.tileSize*19;
		i++;
		gp.obj[i] = new OBJ_Exp_Normal(gp);
		gp.obj[i].worldX = gp.tileSize*23;
		gp.obj[i].worldY = gp.tileSize*23;
		i++;
		gp.obj[i] = new OBJ_Exp_Normal(gp);
		gp.obj[i].worldX = gp.tileSize*24;
		gp.obj[i].worldY = gp.tileSize*19;
		i++;
		gp.obj[i] = new OBJ_HP_Potion(gp);
		gp.obj[i].worldX = gp.tileSize*26;
		gp.obj[i].worldY = gp.tileSize*21;
		i++;
		
	}
	
	public void setNPC() {
		
	}
	
	public void setMonster() {
		
		int i =0;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize*23;
		gp.monster[i].worldY = gp.tileSize*36;
		i++;
		gp.monster[i] = new MON_BigDemon(gp);
		gp.monster[i].worldX = gp.tileSize*23;
		gp.monster[i].worldY = gp.tileSize*39;
		i++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize*23;
		gp.monster[i].worldY = gp.tileSize*45;
		i++;
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize*24;
		gp.monster[i].worldY = gp.tileSize*37;
		i++;
//		gp.monster[i] = new MON_GreenSlime(gp);
//		gp.monster[i].worldX = gp.tileSize*36;
//		gp.monster[i].worldY = gp.tileSize*42;
//		i++;
//		gp.monster[i] = new MON_GreenSlime(gp);
//		gp.monster[i].worldX = gp.tileSize*23;
//		gp.monster[i].worldY = gp.tileSize*24;
//		i++;
//		gp.monster[i] = new MON_GreenSlime(gp);
//		gp.monster[i].worldX = gp.tileSize*25;
//		gp.monster[i].worldY = gp.tileSize*24;
//		i++;
//		gp.monster[i] = new MON_GreenSlime(gp);
//		gp.monster[i].worldX = gp.tileSize*22;
//		gp.monster[i].worldY = gp.tileSize*22;
//		i++;
//		gp.monster[i] = new MON_GreenSlime(gp);
//		gp.monster[i].worldX = gp.tileSize*20;
//		gp.monster[i].worldY = gp.tileSize*19;
//		i++;
//		gp.monster[i] = new MON_GreenSlime(gp);
//		gp.monster[i].worldX = gp.tileSize*26;
//		gp.monster[i].worldY = gp.tileSize*22;
//		i++;
	}
}

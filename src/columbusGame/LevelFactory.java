package columbusGame;

import java.util.ArrayList;
import java.util.List;

public abstract class LevelFactory {
	//ArrayList<Entity>entitiesList;
	abstract LevelEntity createLevel(int type);
	
	public LevelEntity buildLevel(int type){

		LevelEntity level = createLevel(type);
		//anything that needs to be done to Npc's can be done here
		
		
        return level;
	
     }
}

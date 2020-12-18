package columbusGame;

import java.util.ArrayList;
import java.util.List;

public class LevelEntity {
	int difficulty;//1==easy, 2==Normal, 3==hard
	//all entities on board
	List<Entity> entitiesList;
	
	public LevelEntity() {
		entitiesList=new ArrayList<Entity>();
	}

	
}

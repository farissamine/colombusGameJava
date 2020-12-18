package columbusGame;

public class ConcreteLevelFactory extends LevelFactory {
	
	EntityFactory factory;
	
	public ConcreteLevelFactory() {
		this.factory= new EntityFactory();
	}

	@Override
	//a level is equal to an array of entities
	LevelEntity createLevel(int type) {
		LevelEntity level=new LevelEntity();
		
		level.difficulty=type;

		if(type==0) {//testing difficulty
			level.entitiesList.add(factory.createEntity(3));//pirate
			level.entitiesList.add(factory.createEntity(7));
		
			}
		
		if(type==1) {//easy difficulty
			level.entitiesList.add(factory.createEntity(3));//pirate
			level.entitiesList.add(factory.createEntity(3));//pirate
			level.entitiesList.add(factory.createEntity(3));//pirate
			level.entitiesList.add(factory.createEntity(7));

			}
		
		if(type==2) {//Normal difficulty
			level.entitiesList.add(factory.createEntity(3));//pirate
			level.entitiesList.add(factory.createEntity(3));//pirate
			level.entitiesList.add(factory.createEntity(3));//pirate
			level.entitiesList.add(factory.createEntity(7));

		}
		if(type==3) {//Normal difficulty
			level.entitiesList.add(factory.createEntity(3));//pirate
			level.entitiesList.add(factory.createEntity(3));//pirate
			level.entitiesList.add(factory.createEntity(3));//pirate
			level.entitiesList.add(factory.createEntity(4));//pirate
			level.entitiesList.add(factory.createEntity(7));

		}
		
		return level;
	}

}

package club.cogs.demos.COGSBusAdventure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import club.cogs.demos.textengine.Actions.Action;
import club.cogs.demos.textengine.Characters.Character;
import club.cogs.demos.textengine.Exception.NameConflictException;
import club.cogs.demos.textengine.Game.Level;
import club.cogs.demos.textengine.Map.Location;

public class BusCharacter extends Character {
	
	private static HashMap<String, BusCharacter> BusCharacterCache = new HashMap<String, BusCharacter>();
	private static String DEFAULT_SPAWN_NAME = "depot";
	
	private Location riderLocation;
	
	public BusCharacter(String name, Level level) throws NameConflictException{
		this(name, level, DEFAULT_SPAWN_NAME);
	}
	
	public BusCharacter(String name, Level level, String spawn) throws NameConflictException {
		super(name, level, spawn);
		
		this.riderLocation = new BusLocation("On Bus", this);
		
		BusCharacterCache.put(this.getName(), this);
		
		this.preventRandomAction();
	}
	
	public static BusCharacter get(String name){
		return BusCharacterCache.get(name);
	}
	
	public static BusCharacter[] getBusses(){
		BusCharacter[] busses = new BusCharacter[BusCharacterCache.size()];
		int i = 0;
		for(String s : BusCharacterCache.keySet()){
			busses[i++] = (BusCharacter) BusCharacterCache.get(s);
		}
		
		return busses;
	}
	
	public Action chooseAction(ArrayList<Action> actions) {
			
		Action s_action = super.chooseAction(actions);
		if(s_action != null){
			return s_action;
		}
		
		ArrayList<BusTravelAction> travel = new ArrayList<BusTravelAction>();
		for(Action a : actions){
			if(a instanceof BusTravelAction){
				travel.add((BusTravelAction) a);
			}
		}
		
		if(travel.size() > 0){
			return travel.get(new Random().nextInt(travel.size()));
		}
		
		return null;
	}

	public Location getRiderLocation() {
		return this.riderLocation;
	}
	

}

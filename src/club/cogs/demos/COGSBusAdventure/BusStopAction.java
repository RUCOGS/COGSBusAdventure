package club.cogs.demos.COGSBusAdventure;

import java.util.ArrayList;

import club.cogs.demos.textengine.Actions.Action;
import club.cogs.demos.textengine.Characters.Character;
import club.cogs.demos.textengine.Map.Location;

public class BusStopAction extends Action {

	public BusStopAction() {
		super("Get on a Bus");
	}

	public boolean canBePerformedBy(Character c, Location l) {
		
		BusCharacter[] busses = BusCharacter.getBusses();
		
		for(BusCharacter b : busses){
			if(b.getCurrentLocation() == l && c.canTravelTo(b.getRiderLocation())){
				return true;
			}
		}
		
		return false;
	}

	public void preform(Character c) {
		BusCharacter[] busses = BusCharacter.getBusses();
		ArrayList<Action> actions = new ArrayList<Action>();
		
		for(BusCharacter b : busses){
			if(c.canTravelTo(b.getRiderLocation())){
				actions.add(new BoardBusAction(b));
			}
		}
		
		c.chooseAction(actions).preform(c);
	}

}

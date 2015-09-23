package club.cogs.demos.COGSBusAdventure;

import club.cogs.demos.textengine.Actions.Action;
import club.cogs.demos.textengine.Characters.Character;
import club.cogs.demos.textengine.Map.Location;

public class LeaveBusAction extends Action {
	
	private BusCharacter bus;

	public LeaveBusAction(BusCharacter b) {
		super("Leave Bus");
		
		this.bus = b;
	}

	public boolean canBePerformedBy(Character c, Location l) {
		
		if(c.canTravelTo(bus.getCurrentLocation())){
			return true;
		}
		return false;
	}

	public void preform(Character c) {

		if(c.canTravelTo(bus.getCurrentLocation())){
			c.setCurrentLocation(bus.getCurrentLocation());
		}
	}

}

package club.cogs.demos.COGSBusAdventure;

import club.cogs.demos.textengine.Actions.Action;
import club.cogs.demos.textengine.Characters.Character;

public class BoardBusAction extends Action {

	private BusCharacter bus;
	
	public BoardBusAction(BusCharacter b) {
		super("Get on " + b.getName());
		
		this.bus = b;
		
	}

	public void preform(Character c) {
		if(c.canTravelTo(this.bus.getRiderLocation()))
			c.setCurrentLocation(this.bus.getRiderLocation());
	}

}

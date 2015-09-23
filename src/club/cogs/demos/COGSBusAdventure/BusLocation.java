package club.cogs.demos.COGSBusAdventure;

import club.cogs.demos.textengine.Map.Location;

public class BusLocation extends Location {

	public BusLocation(String name, BusCharacter b) {
		super(name);
		
		this.addAction(new LeaveBusAction(b));
	}
	
}

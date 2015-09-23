package club.cogs.demos.COGSBusAdventure;

import club.cogs.demos.textengine.Actions.Action;
import club.cogs.demos.textengine.Characters.Character;
import club.cogs.demos.textengine.Map.Location;

public class BusTravelAction extends Action {
	
	private Location next;

	public BusTravelAction(Location l) {
		super("NEXT");
		this.next = l;
	}
	
	public boolean canBePerformedBy(Character c, Location l){
		if(c instanceof BusCharacter){
			return true;
		}
		return false;
	}
	
	public void preform(Character c){
		if(c instanceof BusCharacter){
			BusCharacter b = (BusCharacter) c;
			if(b.canTravelTo(this.next)){

				b.setCurrentLocation(this.next);
			}
		}
	}

}

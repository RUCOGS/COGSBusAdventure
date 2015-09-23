package club.cogs.demos.COGSBusAdventure;

import java.util.HashMap;

import club.cogs.demos.textengine.Actions.TravelAction;
import club.cogs.demos.textengine.Characters.Character;
import club.cogs.demos.textengine.Characters.ConsolePlayer;
import club.cogs.demos.textengine.Characters.Player;
import club.cogs.demos.textengine.Exception.NameConflictException;
import club.cogs.demos.textengine.Game.ConsoleGame;
import club.cogs.demos.textengine.Game.Level;
import club.cogs.demos.textengine.Map.AccessPriv;
import club.cogs.demos.textengine.Map.Location;
import club.cogs.demos.textengine.Map.Map;

public class COGSBusAdventure extends ConsoleGame {

	
	public static void main(String[] args){
		try {
			(new Thread(new COGSBusAdventure(new ConsolePlayer("Player 1", null)))).start();
		} catch (NameConflictException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Player player;
	public Level level;
	
	public COGSBusAdventure(ConsolePlayer p) {
		super(p);
		
		this.player = p;


		try {
			Map m_walking = new Map("Rutgers_Walking");
			Map m_busses = new Map("Busses");
			Map m_busRoute = new Map("Bus_Route");
			Map m_roads = new Map("Roads");
			Map m_hill = new Map("Hill_Center");
			
			AccessPriv p_walking = new AccessPriv("WalkingPaths");
			AccessPriv p_busses = new AccessPriv("ImABuss");
			AccessPriv p_car = new AccessPriv("ImACar");
			
			AccessPriv p_lars = new AccessPriv("Lars");
			
	
			//Walking Paths 
			//College Ave
			Location l_w_casc = new Location("College Ave Student Center");
			Location l_w_bartlett = new Location("Bartlett St");
			Location l_w_bishop = new Location("Bishop Pl");
			Location l_w_mine = new Location("Mine St");
			Location l_w_scott = new Location("Scott Hall");
			Location l_w_zim = new Location("Zimmerli Art Museum");
			Location l_w_honors = new Location("Rutgers Honors College");
			Location l_w_sac = new Location("Student Activities Center");
			
			//Busch
			Location l_w_hill = new Location("The Hill Center");
			Location l_w_hill_cy = new Location("The Hill Center Court Yard");
			Location l_w_hill_bw = new Location("The Hill Center Breezeway");
			Location l_w_hill_br = new Location("The Hill Center Bridge");
			Location l_w_hill_cv = new Location("The Cave");
			Location l_w_hill_f2 = new Location("The Hill Center Floor 2");
			Location l_w_hill_f3 = new Location("The Hill Center Floor 3");
			Location l_w_hill_cb = new Location("Core Bridge");
			Location l_w_hill_cf = new Location("Core Floor 3");
			
			//Bus Route
			Location l_b_george = new Location("George St North");
			Location l_b_18Bush = new Location("Busch Campus Exit");
			Location l_b_bushc = new Location("Busch Campus circle");
			Location l_b_werblin = new Location("Werblin");
			Location l_b_stad = new Location("RU Stadium West");
			Location l_b_vc = new Location("RU Visitors Center");
			Location l_b_18bridge = new Location("18 South");
			Location l_b_sci = new Location("SC&I Building");
	
			HashMap<String,Location> spawns = new HashMap<String,Location>();
			
			spawns.put("default", l_w_zim);
			spawns.put("player_default", l_w_casc);
			spawns.put("cave", l_w_hill_cv);

			spawns.put("depot", l_w_casc);
			
			spawns.put("CASC", l_w_casc);
			spawns.put("SAC", l_w_sac);
			spawns.put("BuschCircle", l_b_bushc);
			spawns.put("18 South", l_b_18bridge);
	
			this.level = new Level(spawns);
			
			this.player.setLevel(this.level);
		

			Character c_s = new Character("Sesh", this.level);
			Character c_t = new Character("Tjang", this.level);
			Character c_l = new Character("BigLars", this.level, "cave"); //The cave is like a home to him

			BusCharacter b_1 = new BusCharacter("Bus 1", level, "CASC");
			BusCharacter b_2 = new BusCharacter("Bus 2", level, "SAC");
			BusCharacter b_3 = new BusCharacter("Bus 3", level, "BuschCircle");
			BusCharacter b_4 = new BusCharacter("Bus 4", level, "18 South");

			c_s.allowPriv(p_walking);
			c_t.allowPriv(p_walking);
			c_l.allowPriv(p_lars);
			
			b_1.allowPriv(p_busses);
			b_2.allowPriv(p_busses);
			b_3.allowPriv(p_busses);
			b_4.allowPriv(p_busses);

			b_1.getRiderLocation().linkMap(m_walking);
			b_2.getRiderLocation().linkMap(m_walking);
			b_3.getRiderLocation().linkMap(m_walking);
			b_4.getRiderLocation().linkMap(m_walking);

			this.AddCharacter(c_s);
			this.AddCharacter(c_t);
			this.AddCharacter(c_l);
			
			this.AddCharacter(b_1);
			this.AddCharacter(b_2);
			this.AddCharacter(b_3);
			this.AddCharacter(b_4);

			//grant access to maps
			m_walking.GrantAccess(p_walking);
			m_busses.GrantAccess(p_walking);
			m_busRoute.GrantAccess(p_busses);
			m_busRoute.GrantAccess(p_car);
			m_roads.GrantAccess(p_car);
			m_hill.GrantAccess(p_lars);
			m_hill.GrantAccess(p_walking);
	
			//Connect Maps
			l_w_casc.linkMap(m_walking);
			l_w_bartlett.linkMap(m_walking);
			l_w_bishop.linkMap(m_walking);
			l_w_mine.linkMap(m_walking);
			l_w_scott.linkMap(m_walking);
			l_w_zim.linkMap(m_walking);
			l_w_honors.linkMap(m_walking);
			l_w_sac.linkMap(m_walking);
			l_w_hill.linkMap(m_walking);
			
			l_w_hill_cy.linkMap(m_hill);
			l_w_hill_bw.linkMap(m_hill);
			l_w_hill_br.linkMap(m_hill);
			l_w_hill_cv.linkMap(m_hill);
			l_w_hill_f2.linkMap(m_hill);
			l_w_hill_f3.linkMap(m_hill);
			l_w_hill_cb.linkMap(m_hill);
			l_w_hill_cf.linkMap(m_hill);
	
			l_w_casc.linkMap(m_busRoute);
			l_w_scott.linkMap(m_busRoute);
			l_w_sac.linkMap(m_busRoute);
			l_b_george.linkMap(m_busRoute);
			l_b_18Bush.linkMap(m_busRoute);
			l_b_bushc.linkMap(m_busRoute);
			l_b_werblin.linkMap(m_busRoute);
			l_w_hill.linkMap(m_busRoute);
			l_b_stad.linkMap(m_busRoute);
			l_b_vc.linkMap(m_busRoute);
			l_b_18bridge.linkMap(m_busRoute);
			l_b_sci.linkMap(m_busRoute);
			

			//link nodes
			//for walking
			l_w_casc.addAction(new TravelAction("Walk to Bartlett St", l_w_bartlett));

			l_w_bartlett.addAction(new TravelAction("Walk to Bishop Pl", l_w_bishop));
			l_w_bartlett.addAction(new TravelAction("Walk to Mine St", l_w_mine));
			l_w_bartlett.addAction(new TravelAction("Walk to the Student Center", l_w_casc));

			l_w_bishop.addAction(new TravelAction("Walk to The SAC", l_w_sac));
			l_w_bishop.addAction(new TravelAction("Walk to The Honors College", l_w_honors));
			l_w_bishop.addAction(new TravelAction("Walk to Bartlett St", l_w_bartlett));
			l_w_bishop.addAction(new TravelAction("Walk to Mine St", l_w_mine));

			l_w_mine.addAction(new TravelAction("Walk to Bartlett St", l_w_bartlett));
			l_w_mine.addAction(new TravelAction("Walk to Scott Hall", l_w_scott));
			l_w_mine.addAction(new TravelAction("Walk to Bishop Pl", l_w_bishop));

			l_w_scott.addAction(new TravelAction("Walk to Zimmerli Art Museum", l_w_zim));
			l_w_scott.addAction(new TravelAction("Walk to Mine St", l_w_mine));
			
			l_w_zim.addAction(new TravelAction("Walk to The Honors College", l_w_honors));
			l_w_zim.addAction(new TravelAction("Walk to Scott Hall", l_w_scott));
			
			l_w_honors.addAction(new TravelAction("Walk to The SAC", l_w_sac));
			l_w_honors.addAction(new TravelAction("Walk to Bishop Pl", l_w_bishop));
			l_w_honors.addAction(new TravelAction("Walk to Zimmerli Art Museum", l_w_zim));

			l_w_sac.addAction(new TravelAction("Walk to Bishop Pl", l_w_bishop));
			l_w_sac.addAction(new TravelAction("Walk to The Honors College", l_w_honors));
			

			l_w_casc.addAction(new BusStopAction());
			l_w_scott.addAction(new BusStopAction());
			l_w_sac.addAction(new BusStopAction());
			l_b_werblin.addAction(new BusStopAction());
			l_w_hill.addAction(new BusStopAction());
			l_b_stad.addAction(new BusStopAction());
			l_b_vc.addAction(new BusStopAction());
			
			//and the busses!
			l_w_casc.addAction(new BusTravelAction(l_w_scott));
			l_w_scott.addAction(new BusTravelAction(l_w_sac));
			l_w_sac.addAction(new BusTravelAction(l_b_george));
			l_b_george.addAction(new BusTravelAction(l_b_18Bush));
			l_b_18Bush.addAction(new BusTravelAction(l_b_bushc));
			l_b_bushc.addAction(new BusTravelAction(l_b_werblin));
			l_b_werblin.addAction(new BusTravelAction(l_w_hill));
			l_w_hill.addAction(new BusTravelAction(l_b_stad));
			l_b_stad.addAction(new BusTravelAction(l_b_vc));
			l_b_vc.addAction(new BusTravelAction(l_b_18bridge));
			l_b_18bridge.addAction(new BusTravelAction(l_b_sci));
			l_b_sci.addAction(new BusTravelAction(l_w_casc));

		} catch (NameConflictException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public void loop(){
		super.loop();
		
		for(Character c : this.getCharacters()){
			player.sendMessage(c.getName(), "Moved to " + c.getCurrentLocation().getName());
		}
	}

}

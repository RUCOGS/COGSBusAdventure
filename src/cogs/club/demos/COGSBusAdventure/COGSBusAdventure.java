package cogs.club.demos.COGSBusAdventure;

import java.util.HashMap;

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

		HashMap<String,Location> spawns = new HashMap<String,Location>();
		
		spawns.put("default", l_w_zim);
		spawns.put("player_default", l_w_casc);
		spawns.put("cave", l_w_hill_cv);

		this.level = new Level(spawns);
		
		this.player.setLevel(this.level);
		

		try {
			Character c_s = new Character("Sesh", this.level);
			c_s.allowPriv(p_walking);
			Character c_t = new Character("Tjang", this.level);
			c_t.allowPriv(p_walking);
			Character c_l = new Character("BigLars", this.level, "cave"); //The cave is like a home to him
			c_l.allowPriv(p_lars);

			this.AddCharacter(c_s);
			this.AddCharacter(c_t);
			this.AddCharacter(c_l);
		} catch (NameConflictException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
		
		//Link Actions
		//TODO Link Actions
		
		//
		
		
		
		
	}

}

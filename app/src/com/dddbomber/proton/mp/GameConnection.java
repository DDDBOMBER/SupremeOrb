package com.dddbomber.proton.mp;

import org.net.Msg.Msg;
import org.net.p2p.*;

import com.dddbomber.proton.Game;
import com.dddbomber.proton.cpu.NameGenerator;
import com.dddbomber.proton.entity.Colors;
import com.dddbomber.proton.menu.Menu;

public class GameConnection extends StandardConnection{

	public GameConnection(String ip, int port) {
		super(ip, port);
	}

}

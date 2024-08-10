package com.csye7374.inventory.async.factory;

import com.csye7374.inventory.async.command.Communication;

// Implementing Lazy Factory Pattern
public class CommunicationInstanceFactory extends AbstractFactoryAPI{

	private static Communication comm;
	@Override
	public Communication getObject() {
		
		if(comm == null) {
			comm = new 	Communication();
		}
		return comm;
	}

}

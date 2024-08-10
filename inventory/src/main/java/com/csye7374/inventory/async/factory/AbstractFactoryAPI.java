package com.csye7374.inventory.async.factory;

import com.csye7374.inventory.async.command.Communication;

public abstract class AbstractFactoryAPI {
	/**
	 * Returns an object
	 */
	public abstract Communication getObject();
}

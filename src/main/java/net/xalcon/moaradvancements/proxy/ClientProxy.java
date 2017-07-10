package net.xalcon.moaradvancements.proxy;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.xalcon.moaradvancements.client.KeyHandler;

public class ClientProxy implements IProxy
{
	@Override
	public void onPreInit(FMLPreInitializationEvent event)
	{
		ClientRegistry.registerKeyBinding(KeyHandler.BINDING);
	}
}

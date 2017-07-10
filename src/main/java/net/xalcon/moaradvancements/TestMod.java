package net.xalcon.moaradvancements;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.xalcon.moaradvancements.proxy.IProxy;

@Mod(modid = "test", name = "test", version = "0.0.1")
public class TestMod
{
	@SidedProxy(clientSide = "net.xalcon.moaradvancements.proxy.ClientProxy", serverSide = "net.xalcon.moaradvancements.proxy.ServerProxy")
	public static IProxy proxy;

	@Mod.EventHandler
	public void onPreInit(FMLPreInitializationEvent event)
	{
		proxy.onPreInit(event);
	}
}

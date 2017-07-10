package net.xalcon.moaradvancements.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.xalcon.moaradvancements.client.gui.GuiScreenAdvancementsEx;
import org.lwjgl.input.Keyboard;

@Mod.EventBusSubscriber(modid = "test")
public class KeyHandler
{
	public static KeyBinding BINDING = new KeyBinding("advc", Keyboard.KEY_K, "key.categories.misc");

	@SubscribeEvent
	public static void keyPress(InputEvent.KeyInputEvent event)
	{
		if(BINDING.isPressed())
		{
			Minecraft.getMinecraft().displayGuiScreen(new GuiScreenAdvancementsEx(Minecraft.getMinecraft().player.connection.getAdvancementManager()));
		}
	}
}

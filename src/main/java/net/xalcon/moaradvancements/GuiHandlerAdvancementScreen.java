package net.xalcon.moaradvancements;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.advancements.GuiScreenAdvancements;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class GuiHandlerAdvancementScreen
{
    public static void onGuiRender(GuiScreenEvent.DrawScreenEvent.Post event)
    {
    }

    @SubscribeEvent
    public static void onGuiInit(GuiScreenEvent.InitGuiEvent event)
    {
        if(event.getGui() instanceof GuiScreenAdvancements)
            event.getButtonList().add(new GuiButton(0, 112, 0, "Hello World!"));
    }

    public static void onGuiInit(GuiScreenEvent.ActionPerformedEvent event)
    {

    }
}

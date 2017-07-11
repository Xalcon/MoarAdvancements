package net.xalcon.moaradvancements;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.advancements.AdvancementTabType;
import net.minecraft.client.gui.advancements.GuiScreenAdvancements;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.reflect.Field;

@Mod.EventBusSubscriber
public class GuiEventHandlerAdvancementScreen
{
    private final static int GUI_WIDTH = 252;
    private final static int GUI_HEIGHT = 140;

    private final static Field selectedPageField = ReflectionHelper.findField(GuiScreenAdvancements.class, "selectedPage");

    @SubscribeEvent
    public static void onGuiRender(GuiScreenEvent.DrawScreenEvent.Post event)
    {
        if(event.getGui() instanceof GuiScreenAdvancements)
        {
            try
            {
                int j = (event.getGui().height - GUI_HEIGHT) / 2;

                int maxPages = (((GuiScreenAdvancements) event.getGui()).tabs.size() / AdvancementTabType.MAX_TABS) + 1;
                int page = (int) selectedPageField.get(event.getGui()) + 1;

                GlStateManager.disableLighting();
                int strWidth = Minecraft.getMinecraft().fontRenderer.getStringWidth(page + " / " + maxPages);
                Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(page + " / " + maxPages, (event.getGui().width / 2) - (strWidth / 2), j - 42, -1);
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }
    }

    @SubscribeEvent
    public static void onGuiInit(GuiScreenEvent.InitGuiEvent.Post event)
    {
        if(event.getGui() instanceof GuiScreenAdvancements)
        {
            int i = (event.getGui().width - GUI_WIDTH) / 2;
            int j = (event.getGui().height - GUI_HEIGHT) / 2;

            event.getButtonList().add(new GuiButton(0, i + 60, j - 49, 20, 20, "<"));
            event.getButtonList().add(new GuiButton(1, i + GUI_WIDTH - 20 - 60, j - 49, 20, 20, ">"));
        }
    }

    @SubscribeEvent
    public static void onAction(GuiScreenEvent.ActionPerformedEvent.Post event)
    {
        if(event.getGui() instanceof GuiScreenAdvancements && (event.getButton().id == 0 || event.getButton().id == 1))
        {
            try
            {
                int maxPages = (((GuiScreenAdvancements) event.getGui()).tabs.size() / AdvancementTabType.MAX_TABS) + 1;
                int page = (int) selectedPageField.get(event.getGui());
                if(event.getButton().id == 0)
                    page = page > 0 ? page - 1 : maxPages - 1;
                else
                    page = (page + 1) % maxPages;

                selectedPageField.set(event.getGui(), page);
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }
    }
}

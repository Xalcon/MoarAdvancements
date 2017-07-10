import net.minecraft.advancements.Advancement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.advancements.AdvancementTabType;
import net.minecraft.client.gui.advancements.GuiAdvancementTab;
import net.minecraft.client.gui.advancements.GuiScreenAdvancements;
import net.xalcon.moaradvancements.client.gui.GuiAdvancementTabEx;

import javax.annotation.Nullable;

public class Dummy {

    @Nullable
    public static GuiAdvancementTab create(Minecraft mc, GuiScreenAdvancements screen, int index, Advancement advancement)
    {
        if (advancement.getDisplay() == null)
        {
            return null;
        }
        else
        {
            int page = index / AdvancementTabType.MAX_TABS;
            int pageIndex = index % AdvancementTabType.MAX_TABS;

            for (AdvancementTabType advancementtabtype : AdvancementTabType.values())
            {
                if (pageIndex < advancementtabtype.getMax())
                {
                    return new GuiAdvancementTabEx(mc, screen, advancementtabtype, pageIndex, advancement, advancement.getDisplay(), page);
                }

                pageIndex -= advancementtabtype.getMax();
            }

            return null;
        }
    }
}

package net.xalcon.moaradvancements.asm.gui;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.advancements.AdvancementTabType;
import net.minecraft.client.gui.advancements.GuiAdvancementTab;
import net.minecraft.client.gui.advancements.GuiScreenAdvancements;
import net.minecraft.client.renderer.RenderItem;
import net.xalcon.moaradvancements.asm.Utils;

import javax.annotation.Nullable;
import java.lang.invoke.MethodHandle;
import java.util.function.Supplier;

public class GuiAdvancementsTabEx extends GuiAdvancementTab
{
	private int page;
	private Supplier<Integer> selectedPageSupplier;

	private GuiAdvancementsTabEx(Minecraft mc, GuiScreenAdvancements screen, AdvancementTabType tabType, int index, Advancement advancement, int page)
	{
		super(mc, screen, tabType, index, advancement, advancement.getDisplay());
		this.page = page;

		MethodHandle getter = Utils.createFieldGetter("selectedPage", GuiScreenAdvancements.class);

		this.selectedPageSupplier = () ->
		{
			try
			{
				return (int)getter.invoke(screen);
			} catch (Throwable throwable)
			{
				throwable.printStackTrace();
			}
			return -1;
		};
	}

	@Override
	public void drawTab(int p_191798_1_, int p_191798_2_, boolean p_191798_3_)
	{
		if(this.selectedPageSupplier.get() == page)
			super.drawTab(p_191798_1_, p_191798_2_, p_191798_3_);
	}

	@Override
	public void drawIcon(int p_191796_1_, int p_191796_2_, RenderItem p_191796_3_)
	{
		if(this.selectedPageSupplier.get() == page)
			super.drawIcon(p_191796_1_, p_191796_2_, p_191796_3_);
	}

	@Override
	public boolean isMouseOver(int p_191793_1_, int p_191793_2_, int p_191793_3_, int p_191793_4_)
	{
		return this.selectedPageSupplier.get() == page && super.isMouseOver(p_191793_1_, p_191793_2_, p_191793_3_, p_191793_4_);
	}

	@Nullable
	public static GuiAdvancementTab createEx(Minecraft mc, GuiScreenAdvancements screen, int index, Advancement advancement)
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
					return new GuiAdvancementsTabEx(mc, screen, advancementtabtype, pageIndex, advancement, page);
				}

				pageIndex -= advancementtabtype.getMax();
			}

			return null;
		}
	}
}

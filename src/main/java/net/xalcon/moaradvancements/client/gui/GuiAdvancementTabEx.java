package net.xalcon.moaradvancements.client.gui;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.advancements.AdvancementTabType;
import net.minecraft.client.gui.advancements.GuiAdvancementTab;
import net.minecraft.client.gui.advancements.GuiScreenAdvancements;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;

import javax.annotation.Nullable;

public class GuiAdvancementTabEx extends GuiAdvancementTab
{
	private int page;
	private GuiScreenAdvancementsEx screen;

	public GuiAdvancementTabEx(Minecraft mc, GuiScreenAdvancementsEx screen, AdvancementTabType tabType, int index, Advancement advancement, DisplayInfo display, int page)
	{
		super(mc, screen, tabType, index, advancement, display);
		this.page = page;
		this.screen = screen;
	}

	@Nullable
	public static GuiAdvancementTab createEx(Minecraft minecraft, GuiScreenAdvancementsEx guiScreen, int index, Advancement advancement)
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
					return new GuiAdvancementTabEx(minecraft, guiScreen, advancementtabtype, pageIndex, advancement, advancement.getDisplay(), page);
				}

				pageIndex -= advancementtabtype.getMax();
			}

			return null;
		}
	}

	@Override
	public void drawTab(int p_191798_1_, int p_191798_2_, boolean p_191798_3_)
	{
		if(this.screen.getSelectedPage() == this.page)
			super.drawTab(p_191798_1_, p_191798_2_, p_191798_3_);
	}

	@Override
	public void drawIcon(int p_191796_1_, int p_191796_2_, RenderItem p_191796_3_)
	{
		if(this.screen.getSelectedPage() == this.page)
			super.drawIcon(p_191796_1_, p_191796_2_, p_191796_3_);
	}

	@Override
	public boolean isMouseOver(int p_191793_1_, int p_191793_2_, int p_191793_3_, int p_191793_4_)
	{
		return this.screen.getSelectedPage() == this.page && super.isMouseOver(p_191793_1_, p_191793_2_, p_191793_3_, p_191793_4_);
	}
}

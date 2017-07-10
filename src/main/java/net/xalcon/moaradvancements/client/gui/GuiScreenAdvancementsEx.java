package net.xalcon.moaradvancements.client.gui;

import net.minecraft.advancements.Advancement;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.advancements.AdvancementTabType;
import net.minecraft.client.gui.advancements.GuiAdvancementTab;
import net.minecraft.client.gui.advancements.GuiScreenAdvancements;
import net.minecraft.client.multiplayer.ClientAdvancementManager;

import java.io.IOException;

public class GuiScreenAdvancementsEx extends GuiScreenAdvancements
{
	public GuiScreenAdvancementsEx(ClientAdvancementManager clientAdvancementManager)
	{
		super(clientAdvancementManager);
	}

	public int selectedPage = 0;
}

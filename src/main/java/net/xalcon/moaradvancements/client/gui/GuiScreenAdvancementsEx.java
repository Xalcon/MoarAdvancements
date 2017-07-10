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

	private int selectedPage = 0;

	public int getSelectedPage() { return this.selectedPage; }

	/**
	 * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
	 * window resizes, the buttonList is cleared beforehand.
	 */
	@Override
	public void initGui()
	{
		super.initGui();
		this.buttonList.add(new GuiButton(0, 112, 0, "N"));
	}

	/**
	 * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
	 *
	 * @param button
	 */
	@Override
	protected void actionPerformed(GuiButton button) throws IOException
	{
		if(button.id == 0)
		{
			int pages = ((this.tabs.size() / AdvancementTabType.MAX_TABS) + 1);
			this.selectedPage = (this.selectedPage + 1) % pages;
			button.displayString = (this.selectedPage + 1) + " / " + pages;
		}
	}

	@Override
	public void rootAdvancementAdded(Advancement advancementIn)
	{
		GuiAdvancementTab guiadvancementtab = GuiAdvancementTabEx.createEx(this.mc, this, this.tabs.size(), advancementIn);

		if (guiadvancementtab != null)
		{
			this.tabs.put(advancementIn, guiadvancementtab);
		}
	}

	@Override
	public void renderWindow(int mouseX, int mouseY)
	{
		super.renderWindow(mouseX, mouseY);

		for (int i = 0; i < this.buttonList.size(); ++i)
		{
			((GuiButton)this.buttonList.get(i)).drawButton(this.mc, mouseX, mouseY, 0);
		}
	}
}

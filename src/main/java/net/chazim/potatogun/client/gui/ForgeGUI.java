package net.chazim.potatogun.client.gui;

import net.chazim.potatogun.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class ForgeGUI extends GuiContainer
{
	public ForgeGUI(Container container)
	{
		super(container);
	}

	@Override
	public void initGui()
	{
		super.initGui();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y)
	{

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{
		mc.getTextureManager()
				.bindTexture(new ResourceLocation(Reference.MOD_ID.toLowerCase().concat(":textures/gui/forge.png")));
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}
}

package net.chazim.potatogun.client.gui;

import net.chazim.potatogun.Reference;
import net.chazim.potatogun.common.tileentity.ForgeTE;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ForgeGUI extends GuiContainer
{
	private ForgeTE tileEntity;

	public ForgeGUI(Container container, ForgeTE tileEntity)
	{
		super(container);
		this.tileEntity = tileEntity;
	}

	@Override
	public void initGui()
	{
		super.initGui();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y)
	{
		super.drawGuiContainerForegroundLayer(x, y);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(
				new ResourceLocation(Reference.MOD_ID.toLowerCase().concat(":textures/gui/forge.png")));
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

		if (this.tileEntity.isWorking())
		{
			this.drawTexturedModalRect(x + 79, y + 34, 176, 0, (int)(this.tileEntity.getPercentage() * 24f), 16);
		}
	}
}

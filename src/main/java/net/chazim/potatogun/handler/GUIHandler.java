package net.chazim.potatogun.handler;

import cpw.mods.fml.common.network.IGuiHandler;
import net.chazim.potatogun.client.gui.ForgeGUI;
import net.chazim.potatogun.common.inventory.ForgeContainer;
import net.chazim.potatogun.common.tileentity.ForgeTE;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.lang.reflect.InvocationTargetException;

public class GUIHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		Container container = null;
		switch(ID)
		{
			case 0:
				container = new ForgeContainer((ForgeTE) tileEntity, player.inventory);
				break;
		}
		return container;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch (ID)
		{
			case 0:
				return new ForgeGUI((Container) getServerGuiElement(ID, player, world, x, y, z), (ForgeTE) world.getTileEntity(x, y, z));
		}
		return null;
	}
}

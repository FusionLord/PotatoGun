package net.chazim.potatogun.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class ForgeContainer extends Container
{
	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return false;
	}
}

package net.chazim.potatogun.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.chazim.potatogun.init.ModBlocks;
import net.minecraft.block.BlockCauldron;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class ModEventHandler
{
	@SubscribeEvent
	public void onItemUse(PlayerInteractEvent event)
	{
		ItemStack currentStack = event.entityPlayer.getHeldItem();
		if (event.world.getBlock(event.x, event.y, event.z) instanceof BlockCauldron)
		{
			if (currentStack != null && currentStack.getItem() == Items.lava_bucket)
			{
				event.world.setBlock(event.x, event.y, event.z, ModBlocks.forge);
				event.entityPlayer.inventory.consumeInventoryItem(Items.lava_bucket);
				event.entityPlayer.inventory.addItemStackToInventory(new ItemStack(Items.bucket));
			}
		}
	}
}

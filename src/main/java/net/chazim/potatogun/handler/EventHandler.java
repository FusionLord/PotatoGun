package net.chazim.potatogun.handler;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.chazim.potatogun.init.ModBlocks;
import net.chazim.potatogun.util.RayTracing;
import net.minecraft.block.BlockCauldron;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;

public class EventHandler
{

	public static void init()
	{
		System.out.println("XOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXO");
		MinecraftForge.EVENT_BUS.register(new EventHandler());
		//FMLCommonHandler.instance().bus().register(new EventHandler());
	}

	@SubscribeEvent
	public void onItemUse(PlayerUseItemEvent.Start event)
	{
		System.out.println("Passing");
		RayTracing.instance().fire(4D);
		MovingObjectPosition rayTrace = RayTracing.instance().getTarget();
		if (event.entityPlayer.getEntityWorld().getBlock(rayTrace.blockX, rayTrace.blockY, rayTrace.blockZ) instanceof BlockCauldron)
		{
			if (event.item.getItem() == Items.lava_bucket)
			{
				event.entityPlayer.getEntityWorld().setBlock(rayTrace.blockX, rayTrace.blockY, rayTrace.blockZ,
						ModBlocks.forge);
				event.entityPlayer.inventory.setInventorySlotContents(event.entityPlayer.inventory.currentItem, new ItemStack(Items.bucket));
			}
		}
	}
}

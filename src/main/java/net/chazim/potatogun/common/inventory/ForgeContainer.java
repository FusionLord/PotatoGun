package net.chazim.potatogun.common.inventory;

import net.chazim.potatogun.common.tileentity.ForgeTE;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ForgeContainer extends Container
{

	protected ForgeTE tile_entity;

	public ForgeContainer(ForgeTE tile_entity, InventoryPlayer player_inventory)
	{
		this.tile_entity = tile_entity;
		addSlotToContainer(new Slot(tile_entity, 0, 23, 35));
		addSlotToContainer(new Slot(tile_entity, 1, 54, 35));
		addSlotToContainer(new Slot(tile_entity, 2, 116, 35));
		bindPlayerInventory(player_inventory);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return tile_entity.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int i)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(i);

		if(slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack().copy();
			itemstack = itemstack1.copy();

			if(i < 9)
			{
				if(!this.mergeItemStack(itemstack1, 9, 45, true))
				{
					return null;
				}
			}
			else
			{
				if(!this.mergeItemStack(itemstack1, 0, 9, false))
				{
					return null;
				}
			}

			if(itemstack1.stackSize == 0)
			{
				slot.putStack(null);
			}
			else
			{
				slot.onSlotChanged();
			}

			if(itemstack1.stackSize == itemstack.stackSize)
			{
				return null;
			}

			slot.onPickupFromSlot(player, itemstack1);
		}

		return itemstack;
	}

	protected void bindPlayerInventory(InventoryPlayer player_inventory)
	{
		for(int var6 = 0; var6 < 3; ++var6)
		{
			for(int var7 = 0; var7 < 9; ++var7)
			{
				this.addSlotToContainer(new Slot(player_inventory, var7 + var6 * 9 + 9, 8 + var7 * 18, 84 + var6 * 18));
			}
		}

		for(int var6 = 0; var6 < 9; ++var6)
		{
			this.addSlotToContainer(new Slot(player_inventory, var6, 8 + var6 * 18, 142));
		}

	}
}
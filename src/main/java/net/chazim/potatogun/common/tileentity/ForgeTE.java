package net.chazim.potatogun.common.tileentity;

import net.chazim.potatogun.Reference;
import net.chazim.potatogun.init.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;

public class ForgeTE extends TileEntity implements IInventory
{
	private ItemStack[] inventory;
	private boolean hasLava, isWorking;
	private int weildTime, processTime;

	public ForgeTE()
	{
		super();
		inventory = new ItemStack[3];
	}

	@Override
	public void updateEntity()
	{
		if (isWorking)
		{
			if (inventory[0] == null || inventory[2] == null)
			{
				return;
			}
			if (weildTime++ > processTime)
			{
				inventory[2] = getResult(inventory[0], inventory[1]);
				inventory[0] = null;
				inventory[1] = null;
				isWorking = false;
				weildTime = 0;
				return;
			}
		}
		if (inventory[0] != null && inventory[2] != null)
		{
			ItemStack result = getResult(inventory[0], inventory[1]);
			if (result != null && inventory[2].isItemEqual(result))
				isWorking = true;
		}
	}

	private ItemStack getResult(ItemStack itemStack, ItemStack itemStack1)
	{
		ItemStack result = null;
		result = new ItemStack(ModItems.potatoGun, itemStack.getItemDamage() + itemStack1.getItemDamage());
		return result;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		hasLava = tag.getBoolean("hasLava");
		for (int i = 0; i < inventory.length; i++)
			inventory[i] = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("slot".concat(String.valueOf(i))));
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		tag.setBoolean("hasLava", hasLava);
		NBTTagCompound temp;
		for (int i = 0; i < inventory.length; i++)
		{
			temp = new NBTTagCompound();
			inventory[i].writeToNBT(temp);
			tag.setTag("slot".concat(String.valueOf(i)), temp);
		}
	}

	@Override
	public int getSizeInventory()
	{
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount)
	{
		inventory[slot].stackSize -= amount;
		return inventory[slot];
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		return inventory[slot];
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack)
	{
		inventory[slot] = stack;
	}

	@Override
	public String getInventoryName()
	{
		return StatCollector
				.translateToLocal("container.".concat(Reference.MOD_ID.toLowerCase()).concat(":forge.name"));
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 0;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return true;
	}

	@Override
	public void openInventory()
	{

	}

	@Override
	public void closeInventory()
	{

	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack)
	{
		return false;
	}

	public boolean hasLava()
	{
		return hasLava;
	}

	public void setHasLava(boolean hasLava)
	{
		this.hasLava = hasLava;
	}
}

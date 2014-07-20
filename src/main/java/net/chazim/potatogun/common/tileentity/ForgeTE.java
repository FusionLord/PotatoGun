package net.chazim.potatogun.common.tileentity;

import net.chazim.potatogun.Reference;
import net.chazim.potatogun.common.item.PotatoGun;
import net.chazim.potatogun.common.item.PotatoGunMaterial;
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
	private boolean isWorking;
	private int weildTime, processTime;
	private boolean working;

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
			if (processTime == 0)
				processTime = PotatoGunMaterial.values()[getResult(inventory[0], inventory[1]).getItemDamage() % PotatoGunMaterial.count()].getProcessTime();

			if (inventory[0] == null || inventory[2] == null)
			{
				isWorking = false;
				weildTime = 0;
				processTime = 0;
				return;
			}
			if (weildTime++ > processTime)
			{
				inventory[2] = getResult(inventory[0], inventory[1]);
				inventory[0] = null;
				inventory[1] = null;
				isWorking = false;
				weildTime = 0;
				processTime = 0;
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

	private ItemStack getResult(ItemStack stock, ItemStack barrel)
	{
		ItemStack result = null;
		if (stock.getItemDamage() == 0 || barrel.getItemDamage() == PotatoGunMaterial.count() + 1)
			return null;
		if (stock.getItemDamage() == barrel.getItemDamage() - PotatoGunMaterial.count())
			result = new ItemStack(ModItems.potatoGun, barrel.getItemDamage() + PotatoGunMaterial.count());
		return result;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		for (int i = 0; i < inventory.length; i++)
			inventory[i] = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("slot".concat(String.valueOf(i))));
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		NBTTagCompound temp;
		for (int i = 0; i < inventory.length; i++)
		{
			if (inventory[i] == null) continue;
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
		return StatCollector.translateToLocal("container.".concat(Reference.MOD_ID.toLowerCase()).concat(":forge.name"));
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
	{}

	@Override
	public void closeInventory()
	{}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack)
	{
		int count = PotatoGunMaterial.count();
		return stack.getItem() instanceof PotatoGun &&
				(
					(slot == 0 && stack.getItemDamage() < count - 1) ||
					(slot == 1 && stack.getItemDamage() > count && stack.getItemDamage() < count * 2) ||
					(slot == 2 && stack.getItemDamage() > count * 2 - 1)
				);
	}

	public float getPercentage()
	{
		return weildTime / processTime;
	}

	public boolean isWorking()
	{
		return working;
	}
}

package net.chazim.potatogun.common.tileentity;

import net.chazim.potatogun.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;

public class ForgeTE extends TileEntity implements IInventory
{
    private ItemStack[] inventory;
    private int lavaAmount;

    public ForgeTE()
    {
        super();
        inventory = new ItemStack[2];
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        lavaAmount = tag.getInteger("lavaAmount");
        inventory[0] = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("slot0"));
        inventory[1] = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("slot1"));
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger("lavaAmount", lavaAmount);
        NBTTagCompound temp = new NBTTagCompound();
        inventory[0].writeToNBT(temp);
        tag.setTag("slot0", temp);
        temp = new NBTTagCompound();
        inventory[1].writeToNBT(temp);
        tag.setTag("slot1", temp);
    }

    public void addLava()
	{
		lavaAmount += 1000;
	}

    @Override
    public int getSizeInventory() {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inventory[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        inventory[slot].stackSize -= amount;
        return inventory[slot];
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        return inventory[slot];
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        inventory[slot] = stack;
    }

    @Override
    public String getInventoryName() {
        return StatCollector.translateToLocal("container.".concat(Reference.MOD_ID.toLowerCase()).concat(":forge.name"));
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 0;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return false;
    }
}

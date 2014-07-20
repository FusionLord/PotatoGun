package net.chazim.potatogun.common.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

public class PotatoGun extends PGItem
{
	public PotatoGun()
	{
		super("potatogun");
		hasSubtypes = true;
	}

	@Override
	public int getMaxDamage(ItemStack stack)
	{
		int count = PotatoGunMaterial.count();
		if (stack.getItemDamage() < count * 2)
			return 999999;
		return PotatoGunMaterial.values()[stack.getItemDamage() % count].getDamage();
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack)
	{
		if (stack.getMaxDamage() == 999999)
			return 1;

		double damage = 0;
		if (stack.stackTagCompound != null)
			damage = stack.stackTagCompound.getInteger("damage");
		return damage / getMaxDamage(stack);
	}

	@Override
	public void setDamage(ItemStack stack, int damage)
	{
		if (stack.getMaxDamage() == 999999)
			return;
		if (stack.stackTagCompound == null)
			stack.stackTagCompound = new NBTTagCompound();
		stack.stackTagCompound.setInteger("damage", damage);
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack)
	{
		return PotatoGunMaterial.values()[stack.getItemDamage() % PotatoGunMaterial.count()].getName().concat(" ")
				.concat(super.getItemStackDisplayName(stack));
	}

	@Override
	public void getSubItems(Item item, CreativeTabs creativeTab, List list)
	{
		int count = PotatoGunMaterial.count();
		for (int i = 0; i < count; i++)
		{
			list.add(new ItemStack(this, 0, i));
			list.add(new ItemStack(this, 0, i + count));
			list.add(new ItemStack(this, 0, i + count * 2));
		}
	}
}

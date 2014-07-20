package net.chazim.potatogun.common.item;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public enum PotatoGunMaterial
{
	Wood(Item.getItemFromBlock(Blocks.planks), 256, 0),
	Iron(Items.iron_ingot, 768, 0),
	Gold(Items.gold_ingot, 512, 2),
	Diamond(Items.diamond, 1536, 10),
	Emerald(Items.emerald, 999999, 20),
	;

	private Item material;
	private int damage;
	private int processTime;

	private PotatoGunMaterial(Item item, int damage, int processTime)
	{
		material = item;
		this.damage = damage;
		this.processTime = processTime;
	}

	public static int count()
	{
		return values().length;
	}

	public String getName()
	{
		String name = material.getItemStackDisplayName(new ItemStack(material));
		switch (this)
		{
			case Wood:
				name = name.substring(name.indexOf(" "), name.lastIndexOf(" "));
				break;
			case Iron:
			case Gold:
				name = name.substring(0, name.indexOf(" "));
				break;
		}
		return name;
	}

	public Item getMaterial()
	{
		return material;
	}

	public int getDamage()
	{
		return damage;
	}

	public int getProcessTime()
	{
		return (processTime * 20) * 60;
	}
}

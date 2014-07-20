package net.chazim.potatogun.init;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import net.chazim.potatogun.common.item.PotatoGunMaterial;

public class ModRecieps
{
	public static void init()
	{
		int count = PotatoGunMaterial.count();
		ItemStack material;
		for (int i = 0; i < count; i++)
		{
			material = new ItemStack(PotatoGunMaterial.values()[i].getMaterial());
			GameRegistry.addRecipe(new ItemStack(ModItems.potatoGun, 1, i), "   ", " mm", "m  ", 'm', material);
			GameRegistry.addRecipe(new ItemStack(ModItems.potatoGun, 1, i + count), " mm", "   ", " mm", 'm', material);
		}
		GameRegistry.addRecipe(new ItemStack(ModItems.potatoGun, 1, 9), "ij ", "   ", "   ", 'i', new ItemStack(ModItems.potatoGun, 1, 0), 'j', new ItemStack(ModItems.potatoGun, 1, 5));
	}
}

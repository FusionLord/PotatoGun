package net.chazim.potatogun.init;

import net.chazim.potatogun.common.item.PGItem;
import net.chazim.potatogun.common.item.PotatoGunPart;

public class ModItems
{
    public static PGItem parts;

	public static void init()
	{
		parts = new PotatoGunPart();
	}
}

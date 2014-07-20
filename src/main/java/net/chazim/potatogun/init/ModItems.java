package net.chazim.potatogun.init;

import net.chazim.potatogun.common.item.PGItem;
import net.chazim.potatogun.common.item.PotatoGun;

public class ModItems
{
	public static PGItem potatoGun;

	public static void init()
	{
		potatoGun = new PotatoGun();
	}
}

package net.chazim.potatogun.init;

import net.chazim.potatogun.common.item.PGItem;
import net.chazim.potatogun.common.item.PotatoGun;
import net.chazim.potatogun.common.item.PotatoGunPart;

public class ModItems
{
	public static PGItem potatoGunParts;
	public static PGItem potatoGun;

	public static void init()
	{
		potatoGunParts = new PotatoGunPart();
		potatoGun = new PotatoGun();
	}
}

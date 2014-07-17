package net.chazim.potatogun.init;

import net.chazim.potatogun.common.block.Forge;
import net.minecraft.block.Block;

public class ModBlocks
{
    public static Block forge;

	public static void init()
	{
		forge = new Forge();
	}
}

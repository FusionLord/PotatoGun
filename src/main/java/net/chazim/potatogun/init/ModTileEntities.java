package net.chazim.potatogun.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.chazim.potatogun.Reference;
import net.chazim.potatogun.common.tileentity.ForgeTE;

public class ModTileEntities
{
    public static void init()
    {
        GameRegistry.registerTileEntity(ForgeTE.class, Reference.MOD_ID.toLowerCase().concat(":forgetileentity"));
    }
}

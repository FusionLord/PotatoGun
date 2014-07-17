package net.chazim.potatogun;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.chazim.potatogun.common.CommonProxy;

@Mod(modid=Reference.MOD_ID, name="Potato Gun")
public class PotatoGun
{
    @Mod.Instance(Reference.MOD_ID)
    public PotatoGun instance;

    @SidedProxy(clientSide = Reference.CLIENTPROXY, serverSide = Reference.COMMONPROXY)
    public CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {

    }

    @Mod.EventHandler
     public void init(FMLInitializationEvent event)
    {

    }

    @Mod.EventHandler
     public void postInit(FMLPostInitializationEvent event)
    {

    }
}

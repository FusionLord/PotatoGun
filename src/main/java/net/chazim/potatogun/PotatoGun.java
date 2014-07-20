package net.chazim.potatogun;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.chazim.potatogun.common.CommonProxy;
import net.chazim.potatogun.handler.GUIHandler;
import net.chazim.potatogun.handler.ModEventHandler;
import net.chazim.potatogun.init.ModBlocks;
import net.chazim.potatogun.init.ModItems;
import net.chazim.potatogun.init.ModRecieps;
import net.chazim.potatogun.init.ModTileEntities;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Reference.MOD_ID, name = "Potato Gun", version = "0.0")
public class PotatoGun
{
	@Instance(Reference.MOD_ID)
	public static PotatoGun instance;

	@SidedProxy(clientSide = Reference.CLIENTPROXY, serverSide = Reference.COMMONPROXY)
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{

	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		ModItems.init();
		ModBlocks.init();
		ModTileEntities.init();
		ModRecieps.init();

		MinecraftForge.EVENT_BUS.register(new ModEventHandler());

		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GUIHandler());
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{

	}
}

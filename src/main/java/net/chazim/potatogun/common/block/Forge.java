package net.chazim.potatogun.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.chazim.potatogun.PotatoGun;
import net.chazim.potatogun.Reference;
import net.chazim.potatogun.common.tileentity.ForgeTE;
import net.chazim.potatogun.handler.GUIHandler;
import net.minecraft.block.BlockCauldron;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fluids.IFluidContainerItem;

public class Forge extends BlockCauldron
{
    public Forge()
    {
        super();
        setCreativeTab(Reference.PotatoGunTab);
        GameRegistry.registerBlock(this, "forge");
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if (player.inventory.getCurrentItem().getItem() instanceof IFluidContainerItem)
		{
			ForgeTE fte = (ForgeTE) world.getTileEntity(x, y, z);
			fte.addLava();
			player.inventory.consumeInventoryItem(player.inventory.getCurrentItem().getItem());
        }
        else
        {
			player.openGui(PotatoGun.instance, 0, world, x, y, z);
        }
        return true;
    }

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		int lavalevel = world.getBlockMetadata(x, y, z);
		float f = (float)y + (6.0F + (float)(3 * lavalevel)) / 16.0F;

		if (!world.isRemote && !entity.isBurning() && lavalevel > 0 && entity.boundingBox.minY <= (double)f)
		{
			entity.setFire(15);
		}
	}

    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}

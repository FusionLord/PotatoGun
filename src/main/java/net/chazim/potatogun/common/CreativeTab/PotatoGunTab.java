package net.chazim.potatogun.common.CreativeTab;

import net.chazim.potatogun.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class PotatoGunTab extends CreativeTabs
{
    public PotatoGunTab() {
        super(Reference.MOD_ID.toLowerCase());
    }

    @Override
    public Item getTabIconItem() {
        return Items.potato;
    }
}

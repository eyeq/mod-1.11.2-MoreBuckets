package eyeq.morebuckets.item;

import eyeq.util.item.UItemMilk;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMilkMultiple extends UItemMilk {
    @Override
    public ItemStack onItemUseFinish(ItemStack itemStack, World world, EntityLivingBase entity) {
        ItemStack ret = super.onItemUseFinish(itemStack.copy(), world, entity);
        int damage = itemStack.getItemDamage();
        if(damage <= 0) {
            return ret;
        }
        itemStack.setItemDamage(damage - 1);
        return itemStack;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack itemStack) {
        double max =itemStack.getMaxDamage();
        return (max - itemStack.getItemDamage()) / max;
    }
}

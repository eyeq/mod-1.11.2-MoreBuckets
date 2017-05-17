package eyeq.morebuckets.event;

import eyeq.morebuckets.MoreBuckets;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MoreBucketsEventHandler {
    @SubscribeEvent
    public void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        EntityPlayer player = event.getEntityPlayer();
        if(player.getEntityWorld().isRemote || player.isCreative()) {
            return;
        }
        if(!(event.getTarget() instanceof EntityCow)) {
            return;
        }

        EnumHand hand = event.getHand();
        ItemStack itemStack = player.getHeldItem(hand);
        Item item = itemStack.getItem();

        ItemStack milk;
        if(item == MoreBuckets.woodenBucket) {
            milk = new ItemStack(MoreBuckets.woodenBucketMilk);
        } else if(item == MoreBuckets.goldenBucket) {
            milk = new ItemStack(MoreBuckets.goldenBucketMilk);
        } else if(item == MoreBuckets.goldenBucketMilk) {
            int damage = itemStack.getItemDamage();
            if(itemStack.getMaxDamage() > damage) {
                itemStack.setItemDamage(damage + 1);
            }
            return;
        } else {
            return;
        }

        itemStack.shrink(1);
        if(itemStack.getCount() < 1) {
            player.setHeldItem(hand , milk);
        } else if(!player.inventory.addItemStackToInventory(milk)) {
            player.dropItem(milk, false);
        }
    }
}

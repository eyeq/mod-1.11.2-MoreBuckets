package eyeq.morebuckets.item;

import eyeq.morebuckets.MoreBuckets;
import eyeq.util.item.UItemBucket;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemBucketWood extends UItemBucket {
    public ItemBucketWood(Block contain) {
        super(contain);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ActionResult<ItemStack> result = super.onItemRightClick(world, player, hand);
        if(result.getResult().getItem() == getBucketLava()) {
            world.setBlockState(player.getPosition(), Blocks.FLOWING_LAVA.getDefaultState());
            return new ActionResult<>(result.getType(), ItemStack.EMPTY);
        }
        return result;
    }

    @Override
    public Item getBucketEmpty() {
        return MoreBuckets.woodenBucket;
    }

    @Override
    public Item getBucketMilk() {
        return MoreBuckets.woodenBucketMilk;
    }

    @Override
    public Item getBucketWater() {
        return MoreBuckets.woodenBucketWater;
    }

    @Override
    public Item getBucketLava() {
        return MoreBuckets.woodenBucketLava;
    }
}

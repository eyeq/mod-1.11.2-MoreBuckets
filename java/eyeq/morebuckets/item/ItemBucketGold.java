package eyeq.morebuckets.item;

import eyeq.morebuckets.MoreBuckets;
import eyeq.util.item.UItemBucket;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class ItemBucketGold extends UItemBucket {
    public ItemBucketGold(Block contain) {
        super(contain);
    }

    @Override
    public Item getBucketEmpty() {
        return MoreBuckets.goldenBucket;
    }

    @Override
    public Item getBucketMilk() {
        return MoreBuckets.goldenBucketMilk;
    }

    @Override
    public Item getBucketWater() {
        return MoreBuckets.goldenBucketWater;
    }

    @Override
    public Item getBucketLava() {
        return MoreBuckets.goldenBucketLava;
    }
}

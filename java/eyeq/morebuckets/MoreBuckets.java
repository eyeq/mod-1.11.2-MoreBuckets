package eyeq.morebuckets;

import eyeq.morebuckets.event.MoreBucketsEventHandler;
import eyeq.morebuckets.item.ItemBucketGold;
import eyeq.morebuckets.item.ItemBucketWood;
import eyeq.morebuckets.item.ItemMilkMultiple;
import eyeq.util.client.model.UModelCreator;
import eyeq.util.client.model.UModelLoader;
import eyeq.util.client.model.gson.ItemmodelJsonFactory;
import eyeq.util.client.renderer.ResourceLocationFactory;
import eyeq.util.client.resource.ULanguageCreator;
import eyeq.util.client.resource.lang.LanguageResourceManager;
import eyeq.util.item.UItemMilk;
import eyeq.util.item.crafting.FuelHandler;
import eyeq.util.oredict.CategoryTypes;
import eyeq.util.oredict.UOreDictionary;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;

import static eyeq.morebuckets.MoreBuckets.MOD_ID;

@Mod(modid = MOD_ID, version = "1.0", dependencies = "after:eyeq_util")
@Mod.EventBusSubscriber
public class MoreBuckets {
    public static final String MOD_ID = "eyeq_morebuckets";

    @Mod.Instance(MOD_ID)
    public static MoreBuckets instance;

    private static final ResourceLocationFactory resource = new ResourceLocationFactory(MOD_ID);

    public static Item woodenBucket;
    public static Item woodenBucketMilk;
    public static Item woodenBucketLava; //dummy
    public static Item woodenBucketWater;
    public static Item goldenBucket;
    public static Item goldenBucketMilk;
    public static Item goldenBucketLava;
    public static Item goldenBucketWater;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new MoreBucketsEventHandler());
        addRecipes();
        if(event.getSide().isServer()) {
            return;
        }
        renderItemModels();
        createFiles();
    }

    @SubscribeEvent
    protected static void registerItems(RegistryEvent.Register<Item> event) {
        woodenBucket = new ItemBucketWood(Blocks.AIR).setMaxStackSize(16).setUnlocalizedName("woodenBucket");
        woodenBucketMilk = new UItemMilk().setRestItem(new ItemStack(woodenBucket)).setContainerItem(woodenBucket).setUnlocalizedName("woodenBucketMilk");
        woodenBucketLava = new Item();
        woodenBucketWater = new ItemBucketWood(Blocks.FLOWING_WATER).setContainerItem(woodenBucket).setUnlocalizedName("woodenBucketWater");

        goldenBucket = new ItemBucketGold(Blocks.AIR).setMaxStackSize(16).setUnlocalizedName("goldenBucket");
        goldenBucketMilk = new ItemMilkMultiple().setRestItem(new ItemStack(goldenBucket)).setMaxDamage(3).setContainerItem(goldenBucket).setUnlocalizedName("goldenBucketMilk");
        goldenBucketLava = new ItemBucketGold(Blocks.FLOWING_LAVA).setMaxDamage(3).setMaxDamage(3).setContainerItem(goldenBucket).setUnlocalizedName("goldenBucketLava");
        goldenBucketWater = new ItemBucketGold(Blocks.FLOWING_WATER).setMaxDamage(3).setMaxDamage(3).setContainerItem(goldenBucket).setUnlocalizedName("goldenBucketWater");

        GameRegistry.register(woodenBucket, resource.createResourceLocation("wood_bucket_empty"));
        GameRegistry.register(woodenBucketMilk, resource.createResourceLocation("wood_bucket_milk"));
        GameRegistry.register(woodenBucketLava, resource.createResourceLocation("wood_bucket_lava"));
        GameRegistry.register(woodenBucketWater, resource.createResourceLocation("wood_bucket_water"));
        GameRegistry.register(goldenBucket, resource.createResourceLocation("gold_bucket_empty"));
        GameRegistry.register(goldenBucketMilk, resource.createResourceLocation("gold_bucket_milk"));
        GameRegistry.register(goldenBucketLava, resource.createResourceLocation("gold_bucket_lava"));
        GameRegistry.register(goldenBucketWater, resource.createResourceLocation("gold_bucket_water"));

        UOreDictionary.registerOre(CategoryTypes.MILK, "bucket", woodenBucketMilk);
        UOreDictionary.registerOre(CategoryTypes.MILK, "bucket", goldenBucketMilk);
    }

    public static void addRecipes() {
        GameRegistry.addShapedRecipe(new ItemStack(woodenBucket), "X X", " X ",
                'X', Items.BOWL);
        GameRegistry.addShapedRecipe(new ItemStack(goldenBucket), "X X", " X ",
                'X', Items.GOLD_INGOT);

        GameRegistry.registerFuelHandler(new FuelHandler(woodenBucket, 300));
        GameRegistry.registerFuelHandler(new FuelHandler(goldenBucketLava, 20000));
    }

	@SideOnly(Side.CLIENT)
    public static void renderItemModels() {
        UModelLoader.setCustomModelResourceLocation(woodenBucket);
        UModelLoader.setCustomModelResourceLocation(woodenBucketMilk);
        ModelLoader.setCustomModelResourceLocation(woodenBucketLava, 0, ResourceLocationFactory.createModelResourceLocation(woodenBucket));
        UModelLoader.setCustomModelResourceLocation(woodenBucketWater);
        UModelLoader.setCustomModelResourceLocation(goldenBucket);
        UModelLoader.setCustomModelResourceLocation(goldenBucketMilk);
        UModelLoader.setCustomModelResourceLocation(goldenBucketLava);
        UModelLoader.setCustomModelResourceLocation(goldenBucketWater);
    }
	
    public static void createFiles() {
    	File project = new File("../1.11.2-MoreBuckets");
    	
        LanguageResourceManager language = new LanguageResourceManager();

        language.register(LanguageResourceManager.EN_US, woodenBucket, "Wooden Bucket");
        language.register(LanguageResourceManager.JA_JP, woodenBucket, "木のバケツ");
        language.register(LanguageResourceManager.EN_US, woodenBucketMilk, "Milk Wooden Bucket");
        language.register(LanguageResourceManager.JA_JP, woodenBucketMilk, "牛乳入り木のバケツ");
        language.register(LanguageResourceManager.EN_US, woodenBucketWater, "Water Wooden Bucket");
        language.register(LanguageResourceManager.JA_JP, woodenBucketWater, "水入り木のバケツ");
        language.register(LanguageResourceManager.EN_US, goldenBucket, "Golden Bucket");
        language.register(LanguageResourceManager.JA_JP, goldenBucket, "金のバケツ");
        language.register(LanguageResourceManager.EN_US, goldenBucketMilk, "Milk Golden Bucket");
        language.register(LanguageResourceManager.JA_JP, goldenBucketMilk, "牛乳入り金のバケツ");
        language.register(LanguageResourceManager.EN_US, goldenBucketLava, "Lava Golden Bucket");
        language.register(LanguageResourceManager.JA_JP, goldenBucketLava, "溶岩入り金のバケツ");
        language.register(LanguageResourceManager.EN_US, goldenBucketWater, "Water Golden Bucket");
        language.register(LanguageResourceManager.JA_JP, goldenBucketWater, "水入り金のバケツ");
        ULanguageCreator.createLanguage(project, MOD_ID, language);

        UModelCreator.createItemJson(project, woodenBucket, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, woodenBucketMilk, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, woodenBucketWater, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, goldenBucket, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, goldenBucketMilk, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, goldenBucketLava, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, goldenBucketWater, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
    }
}

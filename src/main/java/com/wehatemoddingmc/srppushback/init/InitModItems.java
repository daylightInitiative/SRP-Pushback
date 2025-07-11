
package com.wehatemoddingmc.srppushback.init;

//import com.wehatemoddingmc.srppushback.client.models.ModelSlimeBag;
//import com.wehatemoddingmc.srppushback.items.ItemCowHide;
//import com.wehatemoddingmc.srppushback.items.ItemHorseHide;
import com.wehatemoddingmc.srppushback.items.*;
import com.wehatemoddingmc.srppushback.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
        import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
        import net.minecraftforge.registries.IForgeRegistry;

import static com.wehatemoddingmc.srppushback.util.Reference.MOD_ID;

// TODO: Auto-generated Javadoc
/**
 * Instances and registration class
 *
 * @author jabelar
 */
@ObjectHolder(MOD_ID)
public class InitModItems
{
    public final static ItemMedicalGauze medical_gauze = null;
    public final static ItemRedPhosphorusPowder red_phosphorus_powder = null;
    public final static ItemWhitePhosphorusPowder white_phosphorus_powder = null;
    public final static ItemRedPhosphorusShard red_phosphorus_shard = null;

//    public final static ItemCowHide cow_hide = null;
//    public final static ItemSheepSkin sheep_skin = null;
//    public final static ItemPigSkin pig_skin = null;
//    public final static ItemHorseHide horse_hide = null;
//    public final static ItemSwordExtended sword_extended = null;
//    public final static ItemSlimeBag slime_bag = null;

    public static void registerItemModel(Item item, int metadata, String modelName) {
        ModelLoader.setCustomModelResourceLocation(
                item, metadata,
                new ModelResourceLocation(MOD_ID + ":" + modelName, "inventory")
        );
    }

    public static void registerBlockItemModel(Block block, int metadata, String modelName) {
        ModelLoader.setCustomModelResourceLocation(
                Item.getItemFromBlock(block), metadata,
                new ModelResourceLocation(MOD_ID + ":" + modelName, "inventory")
        );
    }


    @Mod.EventBusSubscriber(modid = MOD_ID)
    public static class RegistrationHandler
    {
        /**
         * Register this mod's {@link Item}s.
         *
         * @param event The event
         */
        @SubscribeEvent
        public static void onEvent(final RegistryEvent.Register<Item> event)
        {
            final IForgeRegistry<Item> registry = event.getRegistry();

            System.out.println("Registering items");

            // BLOCKS
            registry.register(InitModBlocks.PHOSPHORUS_ORE.createItemBlock());

            // MATERIALS

            registry.register(new ItemRedPhosphorusPowder("red_phosphorus_powder", CreativeTabs.MATERIALS));
            registry.register(new ItemWhitePhosphorusPowder("white_phosphorus_powder", CreativeTabs.MATERIALS));
            registry.register(new ItemRedPhosphorusShard("red_phosphorus_shard", CreativeTabs.MATERIALS));

            // MISC
            registry.register(new ItemMedicalGauze("medical_gauze", CreativeTabs.COMBAT));
            registry.register(new ItemMolotovCocktail("molotov_cocktail", CreativeTabs.COMBAT));
//            registry.register(Reference.setItemName(new ItemSheepSkin(), "sheep_skin"));
//            registry.register(Reference.setItemName(new ItemPigSkin(), "pig_skin"));
//            registry.register(Reference.setItemName(new ItemHorseHide(), "horse_hide"));
//            registry.register(Reference.setItemName(new ItemSwordExtended(ToolMaterial.IRON), "sword_extended"));
//            registry.register(Reference.setItemName(new ItemSlimeBag(), "slime_bag"));
        }

        @SubscribeEvent
        public static void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
            EntityPlayer player = event.player;
            ItemStack craftedItem = event.crafting;

            // Check if the crafted item is an instance of ItemMedicalGauze
            if (craftedItem.getItem() instanceof ItemMedicalGauze) {
                // Determine the state of the gauze based on its metadata
                int isDisinfected = craftedItem.getMetadata();
                if(isDisinfected == 0) {
                    craftedItem.setTranslatableName("Undisinfected Gauze");
                }
            }
        }

        /**
         * ModelRegistryEvent handler.
         *
         * @param event the event
         */
        @SubscribeEvent
        @SideOnly(Side.CLIENT)
        public static void onModelEvent(final ModelRegistryEvent event)
        {
            // DEBUG
            System.out.println("Registering item models");

            /*
             *  Register standard model items
             */

            registerBlockItemModel(InitModBlocks.PHOSPHORUS_ORE, 0, "phosphorus_ore");

            registerItemModel(medical_gauze, 0, "medical_gauze_dirty");
            registerItemModel(medical_gauze, 1, "medical_gauze_clean");
            registerItemModel(red_phosphorus_powder, 0, "red_phosphorus_powder");
            registerItemModel(white_phosphorus_powder, 0, "white_phosphorus_powder");

            registerItemModel(red_phosphorus_shard, 0, "red_phosphorus_shard");

//            registerItemModel(sheep_skin);
//            registerItemModel(pig_skin);
//            registerItemModel(horse_hide);
//            registerItemModel(sword_extended);

            /*
             *  Register custom model items
             */
            // DEBUG
            System.out.println("Registering custom item models");
            //ModelLoaderRegistry.registerLoader(ModelSlimeBag.CustomModelLoader.INSTANCE);
            //ModelLoader.setCustomMeshDefinition(slime_bag, stack -> ModelSlimeBag.LOCATION);
            //ModelBakery.registerItemVariants(slime_bag, ModelSlimeBag.LOCATION);
        }

        /**
         * ModelBakeEvent handler.
         *
         * @param event the event
         */
        @SubscribeEvent
        @SideOnly(Side.CLIENT)
        public static void onModelEvent(final ModelBakeEvent event)
        {
            // DEBUG
            System.out.println("Models have been baked");
        }
    }

//    /**
//     * Register item model.
//     *
//     * @param parItem
//     *            the par item
//     */
//    @SideOnly(Side.CLIENT)
//    public static void registerItemModel(Item parItem)
//    {
//        registerItemModel(parItem, 0);
//    }


//    public static void registerItemModel(Item item, int metadataValue, String itemID)
//    {
////        // DEBUG
//        System.out.println("Registering item model for: " + itemID);
//
//        ModelLoader.setCustomModelResourceLocation(item, metadataValue,
//                new ModelResourceLocation(itemID, "inventory"));
//    }

    /**
     * Register ore dictionary entries.
     */
    public static void registerOreDictionaryEntries()
    {
        /*
         * Look in the OreDictionary class to check the strings for vanilla items.
         */
        //OreDictionary.registerOre("blockSlime", slime_bag);
    }
}
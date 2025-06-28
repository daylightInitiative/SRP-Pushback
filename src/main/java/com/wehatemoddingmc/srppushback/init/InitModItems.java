
package com.wehatemoddingmc.srppushback.init;

//import com.wehatemoddingmc.srppushback.client.models.ModelSlimeBag;
//import com.wehatemoddingmc.srppushback.items.ItemCowHide;
//import com.wehatemoddingmc.srppushback.items.ItemHorseHide;
import com.wehatemoddingmc.srppushback.items.ItemMedicalGauze;
import com.wehatemoddingmc.srppushback.util.Reference;

        import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
        import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
        import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
        import net.minecraftforge.registries.IForgeRegistry;

// TODO: Auto-generated Javadoc
/**
 * Instances and registration class
 *
 * @author jabelar
 */
@ObjectHolder(Reference.MOD_ID)
public class InitModItems
{
    public final static ItemMedicalGauze medical_gauze = null;
//    public final static ItemCowHide cow_hide = null;
//    public final static ItemSheepSkin sheep_skin = null;
//    public final static ItemPigSkin pig_skin = null;
//    public final static ItemHorseHide horse_hide = null;
//    public final static ItemSwordExtended sword_extended = null;
//    public final static ItemSlimeBag slime_bag = null;

    @Mod.EventBusSubscriber(modid = Reference.MOD_ID)
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

            registry.register(Reference.setItemName(new ItemMedicalGauze(), "medical_gauze"));
//            registry.register(Reference.setItemName(new ItemSheepSkin(), "sheep_skin"));
//            registry.register(Reference.setItemName(new ItemPigSkin(), "pig_skin"));
//            registry.register(Reference.setItemName(new ItemHorseHide(), "horse_hide"));
//            registry.register(Reference.setItemName(new ItemSwordExtended(ToolMaterial.IRON), "sword_extended"));
//            registry.register(Reference.setItemName(new ItemSlimeBag(), "slime_bag"));
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
            registerItemModel(medical_gauze);
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

    /**
     * Register item model.
     *
     * @param parItem
     *            the par item
     */
    @SideOnly(Side.CLIENT)
    public static void registerItemModel(Item parItem)
    {
        registerItemModel(parItem, 0);
    }

    /**
     * Register item model.
     *
     * @param parItem the par item
     * @param parMetaData the par meta data
     */
    @SideOnly(Side.CLIENT)
    public static void registerItemModel(Item parItem, int parMetaData)
    {
//        // DEBUG
//        System.out.println("Registering item model for: " + parItem.getRegistryName());

        ModelLoader.setCustomModelResourceLocation(parItem, parMetaData,
                new ModelResourceLocation(parItem.getRegistryName(), "inventory"));
    }

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
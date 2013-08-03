package k2b6s9j.BoatCraft;

import java.util.logging.Level;

import k2b6s9j.BoatCraft.item.boat.BoatBirch;
import k2b6s9j.BoatCraft.item.boat.BoatJungle;
import k2b6s9j.BoatCraft.item.boat.BoatOak;
import k2b6s9j.BoatCraft.item.boat.BoatSpruce;
import k2b6s9j.BoatCraft.item.stick.StickBirch;
import k2b6s9j.BoatCraft.item.stick.StickJungle;
import k2b6s9j.BoatCraft.item.stick.StickSpruce;
import k2b6s9j.BoatCraft.proxy.ClientProxy;
import k2b6s9j.BoatCraft.proxy.CommonProxy;
import k2b6s9j.BoatCraft.utilities.CraftingUtilities;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = "BoatCraft", name = "BoatCraft", version = "1.0.0")
@NetworkMod(channels = {"BoatCraft"}, clientSideRequired = true, serverSideRequired = true)
public class BoatCraft {
	@Instance("BoatCraft")
    public static BoatCraft instance;
	
	@SidedProxy(clientSide="k2b6s9j.BoatCraft.proxy.ClientProxy", serverSide="k2b6s9j.BoatCraft.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	//Config File Strings
	public final String itemBoats = "Boats in Item Form";
	public final String sticks = "Sticks";
	
	//Boat Items
	public BoatBirch birchBoat;
	public BoatJungle jungleBoat;
	public BoatOak oakBoat;
	public BoatSpruce spruceBoat;
	
	//Stick Items
	public StickSpruce spruceStick;
	public StickBirch birchStick;
	public StickJungle jungleStick;

	@EventHandler
	public void PreInit (FMLPreInitializationEvent event)
	{
		FMLLog.log(Level.INFO, "BoatCraft");
		FMLLog.log(Level.INFO, "Copyright Kepler Sticka-Jones 2013");
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
        try
        {
        	birchBoat.ID = cfg.getItem(itemBoats, "Birch Boat", 25500).getInt(25500);
        	jungleBoat.ID = cfg.getItem(itemBoats, "Jungle Boat", 25501).getInt(25501);
        	oakBoat.ID = cfg.getItem(itemBoats, "Oak Boat", 25502).getInt(25502);
        	spruceBoat.ID = cfg.getItem(itemBoats, "Spruce Boat", 25503).getInt(25503);
        	spruceStick.ID = cfg.getItem(sticks, "Spruce Sticks", 25505).getInt(25505);
        	birchStick.ID = cfg.getItem(sticks, "Birch Sticks", 25506).getInt(25506);
        	jungleStick.ID = cfg.getItem(sticks, "Jungle Sticks", 25507).getInt(25507);
        }
        catch (Exception e)
        {
            FMLLog.log(Level.SEVERE, e, "BoatCraft had a problem loading it's configuration");
        }
        finally
        {
            if (cfg.hasChanged())
                cfg.save();
        }
        InitItems();
        RegisterRecipes();
		proxy.registerRenderers();
	}
	
	public void InitItems() {
		//Boats
		oakBoat = new BoatOak(oakBoat.ID);
		spruceBoat = new BoatSpruce(spruceBoat.ID);
		birchBoat = new BoatBirch(birchBoat.ID);
		jungleBoat = new BoatJungle(jungleBoat.ID);
		
		//Sticks
		spruceStick = new StickSpruce(spruceStick.ID);
		birchStick = new StickBirch(birchStick.ID);
		jungleStick = new StickJungle(jungleStick.ID);
	}
	
	public void RegisterRecipes() {
		//Boat Recipes
		CraftingUtilities.RemoveRecipe(new ItemStack(Item.boat));
        GameRegistry.addRecipe(new ItemStack(oakBoat), "W W", "WWW", Character.valueOf('W'), new ItemStack(Block.planks, 1, 0));
        GameRegistry.addRecipe(new ItemStack(spruceBoat), "W W", "WWW", Character.valueOf('W'), new ItemStack(Block.planks, 1, 1));
        GameRegistry.addRecipe(new ItemStack(birchBoat), "W W", "WWW", Character.valueOf('W'), new ItemStack(Block.planks, 1, 2));
        GameRegistry.addRecipe(new ItemStack(jungleBoat), "W W", "WWW", Character.valueOf('W'), new ItemStack(Block.planks, 1, 3));
        
        //Stick Recipes
        CraftingUtilities.RemoveRecipe(new ItemStack(Item.stick));
        GameRegistry.addRecipe(new ItemStack(Item.stick), "W", "W", Character.valueOf('W'), new ItemStack(Block.planks, 1, 0));
        GameRegistry.addRecipe(new ItemStack(spruceStick), "W", "W", Character.valueOf('W'), new ItemStack(Block.planks, 1, 1));
        GameRegistry.addRecipe(new ItemStack(birchStick), "W", "W", Character.valueOf('W'), new ItemStack(Block.planks, 1, 2));
        GameRegistry.addRecipe(new ItemStack(jungleStick), "W", "W", Character.valueOf('W'), new ItemStack(Block.planks, 1, 3));
	}

	@EventHandler
	public void Init (FMLInitializationEvent event)
	{
		//Boats
		LanguageRegistry.addName(oakBoat, "Oak Wood Boat");
		LanguageRegistry.addName(spruceBoat, "Spruce Wood Boat");
		LanguageRegistry.addName(birchBoat, "Birch Wood Boat");
		LanguageRegistry.addName(jungleBoat, "Jungle Wood Boat");
		
		//Sticks
		LanguageRegistry.addName(Item.stick, "Oak Wood Sticks");
		LanguageRegistry.addName(spruceStick, "Spruce Wood Sticks");
		LanguageRegistry.addName(birchStick, "Birch Wood Sticks");
		LanguageRegistry.addName(jungleStick, "Jungle Wood Sticks");
	}

	@EventHandler
	public void PostInit (FMLPostInitializationEvent event)
	{

	}

}

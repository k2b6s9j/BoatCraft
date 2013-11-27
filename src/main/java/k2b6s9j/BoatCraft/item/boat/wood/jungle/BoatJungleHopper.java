package k2b6s9j.BoatCraft.item.boat.wood.jungle;

import cpw.mods.fml.common.registry.GameRegistry;
import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;
import k2b6s9j.BoatCraft.entity.boat.wood.jungle.EntityBoatJungleHopper;
import k2b6s9j.BoatCraft.item.boat.ItemCustomBoat;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class BoatJungleHopper extends ItemCustomBoat {
	
	public static int ID;
	public static int shiftedID;
	
	public BoatJungleHopper(int id) {
		super(id);
		setUnlocalizedName("boat.wood.jungle.hopper");
        func_111206_d("boatcraft:boat.wood.jungle.hopper");
    	GameRegistry.registerItem(this, "Jungle Hopper Boat");
    	shiftedID = this.itemID;
    	OreDictionary.registerOre("itemBoat", new ItemStack(this));
    	OreDictionary.registerOre("boat", new ItemStack(this));
    	OreDictionary.registerOre("boatJungle", new ItemStack(this));
    	OreDictionary.registerOre("boatHopper", new ItemStack(this));
    	OreDictionary.registerOre("boatJungleHopper", new ItemStack(this));
	}
	
	@Override
	public EntityCustomBoat getEntity(World world, int x, int y, int z) {
		EntityBoatJungleHopper entity = new EntityBoatJungleHopper(world, (double)((float)x + 0.5F), (double)((float)y + 1.0F), (double)((float)z + 0.5F));
		return entity;
	}
}

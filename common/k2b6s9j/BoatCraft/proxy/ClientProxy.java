package k2b6s9j.BoatCraft.proxy;

import k2b6s9j.BoatCraft.entity.item.EntityBoatChest;
import k2b6s9j.BoatCraft.entity.item.EntityBoatFurnace;
import k2b6s9j.BoatCraft.entity.item.EntityBoatHopper;
import k2b6s9j.BoatCraft.entity.item.EntityBoatTNT;
import k2b6s9j.BoatCraft.entity.item.EntityCustomBoat;
import k2b6s9j.BoatCraft.item.boat.BoatBirch;
import k2b6s9j.BoatCraft.item.boat.BoatChest;
import k2b6s9j.BoatCraft.item.boat.BoatFurnace;
import k2b6s9j.BoatCraft.item.boat.BoatHopper;
import k2b6s9j.BoatCraft.item.boat.BoatJungle;
import k2b6s9j.BoatCraft.item.boat.BoatOak;
import k2b6s9j.BoatCraft.item.boat.BoatSpruce;
import k2b6s9j.BoatCraft.item.boat.BoatTNT;
import k2b6s9j.BoatCraft.render.RenderChestBoat;
import k2b6s9j.BoatCraft.render.RenderCustomBoat;
import k2b6s9j.BoatCraft.render.RenderFurnaceBoat;
import k2b6s9j.BoatCraft.render.RenderHopperBoat;
import k2b6s9j.BoatCraft.render.RenderTNTBoat;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.RenderingRegistry;


public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerRenderers() {
		//Regular Boats
		RenderingRegistry.registerEntityRenderingHandler(EntityCustomBoat.class, new RenderCustomBoat());
		
		//Special Boats
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatChest.class, new RenderChestBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatFurnace.class, new RenderFurnaceBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatTNT.class, new RenderTNTBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatHopper.class, new RenderHopperBoat());
		
		//Register Item Renderers
		MinecraftForgeClient.registerItemRenderer(BoatOak.shiftedID, new RenderCustomBoat());
		MinecraftForgeClient.registerItemRenderer(BoatSpruce.shiftedID, new RenderCustomBoat());
		MinecraftForgeClient.registerItemRenderer(BoatBirch.shiftedID, new RenderCustomBoat());
		MinecraftForgeClient.registerItemRenderer(BoatJungle.shiftedID, new RenderCustomBoat());
		MinecraftForgeClient.registerItemRenderer(BoatChest.shiftedID, new RenderChestBoat());
		MinecraftForgeClient.registerItemRenderer(BoatFurnace.shiftedID, new RenderFurnaceBoat());
		MinecraftForgeClient.registerItemRenderer(BoatTNT.shiftedID, new RenderTNTBoat());
		MinecraftForgeClient.registerItemRenderer(BoatHopper.shiftedID, new RenderHopperBoat());
	}

}

package boatcraft.compatibility

import java.util

import scala.collection.JavaConversions.asScalaBuffer

import boatcraft.api.boat.ItemCustomBoat
import boatcraft.api.modifiers.Block
import boatcraft.api.modifiers.Material
import boatcraft.compatibility.vanilla.VanillaGuiHandler
import boatcraft.compatibility.vanilla.modifiers.blocks._
import boatcraft.compatibility.vanilla.modifiers.materials.crystal._
import boatcraft.compatibility.vanilla.modifiers.materials.metal._
import boatcraft.compatibility.vanilla.modifiers.materials.wood._
import boatcraft.core.BoatCraft
import boatcraft.core.GUIHandler
import boatcraft.core.modifiers.blocks.Empty
import boatcraft.core.utilities.Helper
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.CraftingManager
import net.minecraft.item.crafting.IRecipe
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.oredict.ShapedOreRecipe

object Vanilla extends CompatModule {

	private var useOreDictWood = false

	var materials = Array[Material]()

	override def doPreInit(event: FMLPreInitializationEvent) {
		readConfig

		replaceBoatRecipe

		GUIHandler.handlerMap.put(code, VanillaGuiHandler)

		if (!useOreDictWood) {
			materials = materials ++ woodMaterials
		}
		else {
			materials = materials ++ List[Material](OreDict_Wood)
		}
		materials = materials ++ metalMaterials
		materials = materials ++ crystalMaterials
	}

	override def doPostInit(event: FMLPostInitializationEvent) {
		val toRemove = new util.ArrayList[IRecipe]

		for (recipe <- CraftingManager.getInstance.getRecipeList) {
			recipe match {
				case recipe: IRecipe
					if !recipe.isInstanceOf[ShapedOreRecipe] && recipe.getRecipeOutput != null
						&& recipe.getRecipeOutput.getTagCompound != null
						&& recipe.getRecipeOutput.getTagCompound.getString("material") != null
						&& recipe.getRecipeOutput.getTagCompound.getString("material").equals(OreDict_Wood toString) =>
					toRemove add recipe
					log info "Removed non-oredict ore-dict wood boat recipe: " + recipe
				case _ =>
			}
		}

		CraftingManager.getInstance.getRecipeList removeAll toRemove
	}

	override protected def getMaterials = materials

	private val woodMaterials: List[Material] = List[Material](
		Oak,
		Spruce,
		Birch,
		Jungle,
		Acacia,
		DarkOak
	)

	private val metalMaterials: List[Material] = List[Material](
		Iron,
		Gold
	)

	private val crystalMaterials: List[Material] = List[Material](
		Obsidian,
		Diamond,
		Emerald
	)

	override protected def getBlocks =
		Array[Block](
			Chest,
			Furnace,
			Workbench,
			TNT)
	
	private def replaceBoatRecipe {
		Helper.Recipe.removeRecipe(new ItemStack(Items.boat))

		val stack = new ItemStack(ItemCustomBoat)

		var stackTagCompound = new NBTTagCompound
		stackTagCompound setString("material", Oak.toString)
		stackTagCompound setString("block", Empty.toString)
		stack.setTagCompound(stackTagCompound)

		GameRegistry addShapelessRecipe(stack, Items.boat)
	}
	
	private def readConfig {
		useOreDictWood = BoatCraft.config get("Vanilla.General", "useOreDictWoods", false,
			"If set to true, the different wood types will not be generated.\n" +
				"Instead, there will be only one \"wood\" Boat") getBoolean false

		if (BoatCraft.config.hasChanged)
			BoatCraft.config.save
	}
}

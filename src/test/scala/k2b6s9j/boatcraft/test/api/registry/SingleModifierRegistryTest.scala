package k2b6s9j.boatcraft.api.registry.test

import org.junit.runner.RunWith
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import org.scalatest.junit.JUnitRunner

import k2b6s9j.boatcraft.api.registry.ModifierRegistry
import k2b6s9j.boatcraft.test.api.traits.examples.ExampleModifier
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound

@RunWith(classOf[JUnitRunner])
class SingleModifierRegistryTest extends FlatSpec with Matchers with BeforeAndAfter
{
	before
	{
		ModifierRegistry addModifier ExampleModifier
	}

	"A Modifier" should "be added to the registered Modifiers map." in
	{
		ModifierRegistry.modifiers should contain("test", ExampleModifier)
	}

	it should "be returned when searched by name." in
	{
		ModifierRegistry getModifier "test" shouldBe ExampleModifier
	}

	it should "be returned when searched by ItemStack." in
	{
		val stack = new ItemStack(Blocks.bedrock)
		stack.stackTagCompound = new NBTTagCompound
		stack.stackTagCompound setString ("modifier", "test")
		ModifierRegistry getModifier stack shouldBe ExampleModifier
	}

}

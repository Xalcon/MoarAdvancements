package net.xalcon.moaradvancements.asm;

import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;

public class Utils
{
	/*public static MethodHandle createFieldGetterSrg(String srgName, Class<?> clazz)
	{
		try
		{
			String mcpName = null;
			if (Launch.blackboard.get("fml.deobfuscatedEnvironment") == Boolean.TRUE)
			{
				mcpName = FMLDeobfuscatingRemapper.INSTANCE.mapFieldName(clazz.getName().replace('.', '/'), srgName, null);
			}

			Field field = ReflectionHelper.findField(clazz, srgName, mcpName);
			return MethodHandles.lookup().unreflectGetter(field);
		}
		catch (IllegalAccessException e)
		{
			throw new RuntimeException("Unable to get field '"+srgName+"' from " + clazz.getName(), e);
		}
	}*/

	public static MethodHandle createFieldGetter(String name, Class<?> clazz)
	{
		try
		{
			Field field = ReflectionHelper.findField(clazz, name);
			return MethodHandles.lookup().unreflectGetter(field);
		}
		catch (IllegalAccessException e)
		{
			throw new RuntimeException("Unable to get field '"+name+"' from " + clazz.getName(), e);
		}
	}
}

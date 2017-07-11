package net.xalcon.moaradvancements.asm;

import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.launchwrapper.Launch;
import net.xalcon.moaradvancements.asm.patcher.GuiScreenAdvancementsPatcher;

public class MAClassTransformer implements IClassTransformer
{
    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass)
    {
        if("net.minecraft.client.gui.advancements.GuiScreenAdvancements".equals(transformedName))
            return GuiScreenAdvancementsPatcher.patchClass(basicClass, (boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment"));

        return basicClass;
    }
}

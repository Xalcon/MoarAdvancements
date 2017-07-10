package net.xalcon.moaradvancements.asm.patcher;

import java.io.FileOutputStream;
import java.io.IOException;

public class GuiAdvancementTabPatcher
{
    public static byte[] patchClass(byte[] classBytes)
    {
        return classBytes;
    }

    private static void dump(String path, byte[] data)
    {
        try
        {
            FileOutputStream fos = new FileOutputStream(path);
            fos.write(data);
            fos.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

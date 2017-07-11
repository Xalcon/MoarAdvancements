package net.xalcon.moaradvancements.asm.patcher;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.*;

import java.io.FileOutputStream;
import java.io.IOException;

import static org.objectweb.asm.Opcodes.*;


public class GuiScreenAdvancementsPatcher
{
    public static byte[] patchClass(byte[] classBytes)
    {
        ClassReader cr = new ClassReader(classBytes);
        ClassNode cn = new ClassNode(ASM5);
        cr.accept(cn, 0);

        // add 'public int selectedPage = 0;' to class
        cn.fields.add(new FieldNode(ACC_PUBLIC, "selectedPage", "I", null, 1));

        // add super.drawScreen() before the call to renderToolTips()
        MethodNode mn = cn.methods.stream().filter(m -> "drawScreen".equals(m.name)).findFirst()
                .orElseThrow(() -> new RuntimeException("Unable to find method 'drawScreen'"));

        for(AbstractInsnNode node = mn.instructions.getFirst(); node.getNext() != null; node = node.getNext())
        {
            if(node instanceof  MethodInsnNode)
            {
                MethodInsnNode methodInsnNode = (MethodInsnNode) node;
                if("renderToolTips".equals(methodInsnNode.name))
                {
                    InsnList list = new InsnList();
                    list.add(new VarInsnNode(ALOAD, 0));
                    list.add(new VarInsnNode(ILOAD, 1));
                    list.add(new VarInsnNode(ILOAD, 2));
                    list.add(new VarInsnNode(FLOAD, 3));
                    list.add(new MethodInsnNode(INVOKESPECIAL, "net/minecraft/client/gui/GuiScreen", "drawScreen", "(IIF)V", false));
                    mn.instructions.insertBefore(methodInsnNode, list);
                    break;
                }
            }
        }

        // we also need to patch the method `rootAdvancementAdded` to call our createEx instead of create
        MethodNode mn2 = cn.methods.stream().filter(m -> "rootAdvancementAdded".equals(m.name)).findFirst()
                .orElseThrow(() -> new RuntimeException("Unable to find method 'rootAdvancementAdded'"));

        for(AbstractInsnNode node = mn2.instructions.getFirst(); node.getNext() != null; node = node.getNext())
        {
            if(node instanceof MethodInsnNode)
            {
                MethodInsnNode mNode = (MethodInsnNode) node;
                if("create".equals(mNode.name))
                {
                    mNode.name = "createEx";
                    mNode.owner = "net/xalcon/moaradvancements/asm/gui/GuiAdvancementsTabEx";
                    break;
                }
            }
        }

        // we are done :)
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        cn.accept(cw);

        // now crash :3
        return cw.toByteArray();
    }
}

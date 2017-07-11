package net.xalcon.moaradvancements.asm.patcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.*;

import java.io.FileOutputStream;
import java.io.IOException;

import static org.objectweb.asm.Opcodes.*;


public class GuiScreenAdvancementsPatcher
{
    private final static Logger log = LogManager.getLogger("moaradvancements:Core");

    public static byte[] patchClass(byte[] classBytes, boolean deopfEnvironment)
    {
        log.info("Patching GuiScreenAdvancements...");
        final String DRAW_SCREEN_METHODNAME = deopfEnvironment ? "drawScreen" : "func_73863_a";
        final String DRAW_SCREEN_METHODDESC = "(IIF)V";
        final String RENDERTOOLTIPS_METHODNAME = deopfEnvironment ? "renderToolTips" : "func_191937_d";
        final String RENDERTOOLTIPS_METHODDESC ="(IIII)V";
        final String ROOT_ADVANCEMENT_ADDED_METHODNAME = deopfEnvironment ? "rootAdvancementAdded" : "func_191931_a";
        final String ROOT_ADVANCEMENT_ADDED_METHODDESC = "(Lnet/minecraft/advancements/Advancement;)V";
        final String CREATE_METHODNAME = deopfEnvironment ? "create" : "func_193936_a";
        final String CREATE_METHODDESC = "(Lnet/minecraft/client/Minecraft;Lnet/minecraft/client/gui/advancements/GuiScreenAdvancements;ILnet/minecraft/advancements/Advancement;)Lnet/minecraft/client/gui/advancements/GuiAdvancementTab;";

        ClassReader cr = new ClassReader(classBytes);
        ClassNode cn = new ClassNode(ASM5);
        cr.accept(cn, 0);

        // add 'public int selectedPage = 0;' to class
        cn.fields.add(new FieldNode(ACC_PUBLIC, "selectedPage", "I", null, 1));
        log.info("Added 'selectedPage' field (1/3)");

        // add super.drawScreen() before the call to renderToolTips()
        MethodNode mn = cn.methods.stream().filter(m -> DRAW_SCREEN_METHODNAME.equals(m.name) && DRAW_SCREEN_METHODDESC.equals(m.desc)).findFirst()
                .orElseThrow(() -> new RuntimeException("Unable to find method 'drawScreen'"));

        for(AbstractInsnNode node = mn.instructions.getFirst(); node.getNext() != null; node = node.getNext())
        {
            if(node instanceof  MethodInsnNode)
            {
                MethodInsnNode methodInsnNode = (MethodInsnNode) node;
                if(RENDERTOOLTIPS_METHODNAME.equals(methodInsnNode.name) && RENDERTOOLTIPS_METHODDESC.equals(methodInsnNode.desc))
                {
                    InsnList list = new InsnList();
                    list.add(new VarInsnNode(ALOAD, 0));
                    list.add(new VarInsnNode(ILOAD, 1));
                    list.add(new VarInsnNode(ILOAD, 2));
                    list.add(new VarInsnNode(FLOAD, 3));
                    list.add(new MethodInsnNode(INVOKESPECIAL, "net/minecraft/client/gui/GuiScreen", DRAW_SCREEN_METHODNAME, "(IIF)V", false));
                    mn.instructions.insertBefore(methodInsnNode, list);
                    log.info("Added super.drawScreen() call to drawScreen() (2/3)");
                    break;
                }
            }
        }

        // we also need to patch the method `rootAdvancementAdded` to call our createEx instead of create
        MethodNode mn2 = cn.methods.stream().filter(m -> ROOT_ADVANCEMENT_ADDED_METHODNAME.equals(m.name) && ROOT_ADVANCEMENT_ADDED_METHODDESC.equals(m.desc)).findFirst()
                .orElseThrow(() -> new RuntimeException("Unable to find method 'rootAdvancementAdded'"));

        for(AbstractInsnNode node = mn2.instructions.getFirst(); node.getNext() != null; node = node.getNext())
        {
            if(node instanceof MethodInsnNode)
            {
                MethodInsnNode mNode = (MethodInsnNode) node;
                if(CREATE_METHODNAME.equals(mNode.name) && CREATE_METHODDESC.equals(mNode.desc))
                {
                    mNode.name = "createEx";
                    mNode.owner = "net/xalcon/moaradvancements/asm/gui/GuiAdvancementsTabEx";
                    log.info("Detoured create() call to createEx() in rootAdvancementAdded() (3/3)");
                    break;
                }
            }
        }

        // we are done :)
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        cn.accept(cw);

        // now crash :3
        log.info("Patching done");
        return cw.toByteArray();
    }
}

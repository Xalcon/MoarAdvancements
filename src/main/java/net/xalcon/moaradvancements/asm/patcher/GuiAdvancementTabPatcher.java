package net.xalcon.moaradvancements.asm.patcher;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.tree.*;

import java.io.FileOutputStream;
import java.io.IOException;

import static org.objectweb.asm.Opcodes.*;

public class GuiAdvancementTabPatcher
{
    public static byte[] patchClass(byte[] classBytes)
    {
        ClassReader cr = new ClassReader(classBytes);
        ClassNode cn = new ClassNode(ASM5);
        cr.accept(cn, 0);

        cn.fields.add(new FieldNode(ACC_PUBLIC, "page", "I", null, 0));

        {
            MethodVisitor mv = cn.visitMethod(ACC_PUBLIC, "<init>", "(Lnet/minecraft/client/Minecraft;Lnet/minecraft/client/gui/advancements/GuiScreenAdvancements;Lnet/minecraft/client/gui/advancements/AdvancementTabType;ILnet/minecraft/advancements/Advancement;Lnet/minecraft/advancements/DisplayInfo;I)V", null, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitVarInsn(ILOAD, 4);
            mv.visitVarInsn(ALOAD, 5);
            mv.visitVarInsn(ALOAD, 6);
            mv.visitMethodInsn(INVOKESPECIAL, "net/minecraft/client/gui/advancements/GuiAdvancementTab", "<init>", "(Lnet/minecraft/client/Minecraft;Lnet/minecraft/client/gui/advancements/GuiScreenAdvancements;Lnet/minecraft/client/gui/advancements/AdvancementTabType;ILnet/minecraft/advancements/Advancement;Lnet/minecraft/advancements/DisplayInfo;)V", false);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ILOAD, 7);
            mv.visitFieldInsn(PUTFIELD, "net/minecraft/client/gui/advancements/GuiAdvancementTab", "page", "I");
            mv.visitInsn(RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }
        {
            cn.methods.remove(cn.methods.stream().filter(m -> m.name.equals("create")).findFirst().orElse(null));
            MethodVisitor mv = cn.visitMethod(ACC_PUBLIC + ACC_STATIC, "create", "(Lnet/minecraft/client/Minecraft;Lnet/minecraft/client/gui/advancements/GuiScreenAdvancements;ILnet/minecraft/advancements/Advancement;)Lnet/minecraft/client/gui/advancements/GuiAdvancementTab;", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(15, l0);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/advancements/Advancement", "getDisplay", "()Lnet/minecraft/advancements/DisplayInfo;", false);
            Label l1 = new Label();
            mv.visitJumpInsn(IFNONNULL, l1);
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLineNumber(17, l2);
            mv.visitInsn(ACONST_NULL);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l1);
            mv.visitLineNumber(21, l1);
            mv.visitFrame(F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ILOAD, 2);
            mv.visitFieldInsn(GETSTATIC, "net/minecraft/client/gui/advancements/AdvancementTabType", "MAX_TABS", "I");
            mv.visitInsn(IDIV);
            mv.visitVarInsn(ISTORE, 4);
            Label l3 = new Label();
            mv.visitLabel(l3);
            mv.visitLineNumber(22, l3);
            mv.visitVarInsn(ILOAD, 2);
            mv.visitFieldInsn(GETSTATIC, "net/minecraft/client/gui/advancements/AdvancementTabType", "MAX_TABS", "I");
            mv.visitInsn(IREM);
            mv.visitVarInsn(ISTORE, 5);
            Label l4 = new Label();
            mv.visitLabel(l4);
            mv.visitLineNumber(24, l4);
            mv.visitMethodInsn(INVOKESTATIC, "net/minecraft/client/gui/advancements/AdvancementTabType", "values", "()[Lnet/minecraft/client/gui/advancements/AdvancementTabType;", false);
            mv.visitVarInsn(ASTORE, 6);
            mv.visitVarInsn(ALOAD, 6);
            mv.visitInsn(ARRAYLENGTH);
            mv.visitVarInsn(ISTORE, 7);
            mv.visitInsn(ICONST_0);
            mv.visitVarInsn(ISTORE, 8);
            Label l5 = new Label();
            mv.visitLabel(l5);
            mv.visitFrame(F_FULL, 9, new Object[]{"net/minecraft/client/Minecraft", "net/minecraft/client/gui/advancements/GuiScreenAdvancements", INTEGER, "net/minecraft/advancements/Advancement", INTEGER, INTEGER, "[Lnet/minecraft/client/gui/advancements/AdvancementTabType;", INTEGER, INTEGER}, 0, new Object[]{});
            mv.visitVarInsn(ILOAD, 8);
            mv.visitVarInsn(ILOAD, 7);
            Label l6 = new Label();
            mv.visitJumpInsn(IF_ICMPGE, l6);
            mv.visitVarInsn(ALOAD, 6);
            mv.visitVarInsn(ILOAD, 8);
            mv.visitInsn(AALOAD);
            mv.visitVarInsn(ASTORE, 9);
            Label l7 = new Label();
            mv.visitLabel(l7);
            mv.visitLineNumber(26, l7);
            mv.visitVarInsn(ILOAD, 5);
            mv.visitVarInsn(ALOAD, 9);
            mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/gui/advancements/AdvancementTabType", "getMax", "()I", false);
            Label l8 = new Label();
            mv.visitJumpInsn(IF_ICMPGE, l8);
            Label l9 = new Label();
            mv.visitLabel(l9);
            mv.visitLineNumber(28, l9);
            mv.visitTypeInsn(NEW, "net/minecraft/client/gui/advancements/GuiAdvancementTab");
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 9);
            mv.visitVarInsn(ILOAD, 5);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/advancements/Advancement", "getDisplay", "()Lnet/minecraft/advancements/DisplayInfo;", false);
            mv.visitVarInsn(ILOAD, 4);
            mv.visitMethodInsn(INVOKESPECIAL, "net/minecraft/client/gui/advancements/GuiAdvancementTab", "<init>", "(Lnet/minecraft/client/Minecraft;Lnet/minecraft/client/gui/advancements/GuiScreenAdvancements;Lnet/minecraft/client/gui/advancements/AdvancementTabType;ILnet/minecraft/advancements/Advancement;Lnet/minecraft/advancements/DisplayInfo;I)V", false);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l8);
            mv.visitLineNumber(31, l8);
            mv.visitFrame(F_APPEND, 1, new Object[]{"net/minecraft/client/gui/advancements/AdvancementTabType"}, 0, null);
            mv.visitVarInsn(ILOAD, 5);
            mv.visitVarInsn(ALOAD, 9);
            mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/gui/advancements/AdvancementTabType", "getMax", "()I", false);
            mv.visitInsn(ISUB);
            mv.visitVarInsn(ISTORE, 5);
            Label l10 = new Label();
            mv.visitLabel(l10);
            mv.visitLineNumber(24, l10);
            mv.visitIincInsn(8, 1);
            mv.visitJumpInsn(GOTO, l5);
            mv.visitLabel(l6);
            mv.visitLineNumber(34, l6);
            mv.visitFrame(F_FULL, 6, new Object[]{"net/minecraft/client/Minecraft", "net/minecraft/client/gui/advancements/GuiScreenAdvancements", INTEGER, "net/minecraft/advancements/Advancement", INTEGER, INTEGER}, 0, new Object[]{});
            mv.visitInsn(ACONST_NULL);
            mv.visitInsn(ARETURN);
            Label l11 = new Label();
            mv.visitLabel(l11);
            mv.visitLocalVariable("advancementtabtype", "Lnet/minecraft/client/gui/advancements/AdvancementTabType;", null, l7, l10, 9);
            mv.visitLocalVariable("page", "I", null, l3, l11, 4);
            mv.visitLocalVariable("pageIndex", "I", null, l4, l11, 5);
            mv.visitLocalVariable("mc", "Lnet/minecraft/client/Minecraft;", null, l0, l11, 0);
            mv.visitLocalVariable("screen", "Lnet/minecraft/client/gui/advancements/GuiScreenAdvancements;", null, l0, l11, 1);
            mv.visitLocalVariable("index", "I", null, l0, l11, 2);
            mv.visitLocalVariable("advancement", "Lnet/minecraft/advancements/Advancement;", null, l0, l11, 3);
            mv.visitMaxs(9, 10);
            mv.visitEnd();
        }

        // patch drawTab
        {
            cn.methods.remove(cn.methods.stream().filter(m -> m.name.equals("drawTab")).findFirst().orElse(null));
            MethodVisitor mv = cn.visitMethod(ACC_PUBLIC, "drawTab", "(IIZ)V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            //mv.visitLineNumber(62, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "net/minecraft/client/gui/advancements/GuiAdvancementTab", "screen", "Lnet/minecraft/client/gui/advancements/GuiScreenAdvancements;");
            mv.visitFieldInsn(GETFIELD, "net/minecraft/client/gui/advancements/GuiScreenAdvancements", "selectedPage", "I");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "net/minecraft/client/gui/advancements/GuiAdvancementTab", "page", "I");
            Label l1 = new Label();
            mv.visitJumpInsn(IF_ICMPEQ, l1);
            mv.visitInsn(RETURN);
            mv.visitLabel(l1);
            //mv.visitLineNumber(63, l1);
            mv.visitFrame(F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "net/minecraft/client/gui/advancements/GuiAdvancementTab", "type", "Lnet/minecraft/client/gui/advancements/AdvancementTabType;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitVarInsn(ILOAD, 2);
            mv.visitVarInsn(ILOAD, 3);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "net/minecraft/client/gui/advancements/GuiAdvancementTab", "index", "I");
            mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/gui/advancements/AdvancementTabType", "draw", "(Lnet/minecraft/client/gui/Gui;IIZI)V", false);
            Label l2 = new Label();
            mv.visitLabel(l2);
            //mv.visitLineNumber(64, l2);
            mv.visitInsn(RETURN);
            Label l3 = new Label();
            mv.visitLabel(l3);
            mv.visitLocalVariable("this", "Lnet/minecraft/client/gui/advancements/GuiAdvancementTab;", null, l0, l3, 0);
            mv.visitLocalVariable("p_191798_1_", "I", null, l0, l3, 1);
            mv.visitLocalVariable("p_191798_2_", "I", null, l0, l3, 2);
            mv.visitLocalVariable("p_191798_3_", "Z", null, l0, l3, 3);
            mv.visitMaxs(4, 4);
            mv.visitEnd();
        }

        // path drawIcon
        {
            cn.methods.remove(cn.methods.stream().filter(m -> m.name.equals("drawIcon")).findFirst().orElse(null));
            MethodVisitor mv = cn.visitMethod(ACC_PUBLIC, "drawIcon", "(IILnet/minecraft/client/renderer/RenderItem;)V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(69, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "net/minecraft/client/gui/advancements/GuiAdvancementTab", "screen", "Lnet/minecraft/client/gui/advancements/GuiScreenAdvancements;");
            mv.visitFieldInsn(GETFIELD, "net/minecraft/client/gui/advancements/GuiScreenAdvancements", "selectedPage", "I");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "net/minecraft/client/gui/advancements/GuiAdvancementTab", "page", "I");
            Label l1 = new Label();
            mv.visitJumpInsn(IF_ICMPEQ, l1);
            mv.visitInsn(RETURN);
            mv.visitLabel(l1);
            mv.visitLineNumber(70, l1);
            mv.visitFrame(F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "net/minecraft/client/gui/advancements/GuiAdvancementTab", "type", "Lnet/minecraft/client/gui/advancements/AdvancementTabType;");
            mv.visitVarInsn(ILOAD, 1);
            mv.visitVarInsn(ILOAD, 2);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "net/minecraft/client/gui/advancements/GuiAdvancementTab", "index", "I");
            mv.visitVarInsn(ALOAD, 3);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "net/minecraft/client/gui/advancements/GuiAdvancementTab", "icon", "Lnet/minecraft/item/ItemStack;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/gui/advancements/AdvancementTabType", "drawIcon", "(IIILnet/minecraft/client/renderer/RenderItem;Lnet/minecraft/item/ItemStack;)V", false);
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLineNumber(71, l2);
            mv.visitInsn(RETURN);
            Label l3 = new Label();
            mv.visitLabel(l3);
            mv.visitLocalVariable("this", "Lnet/minecraft/client/gui/advancements/GuiAdvancementTab;", null, l0, l3, 0);
            mv.visitLocalVariable("p_191796_1_", "I", null, l0, l3, 1);
            mv.visitLocalVariable("p_191796_2_", "I", null, l0, l3, 2);
            mv.visitLocalVariable("p_191796_3_", "Lnet/minecraft/client/renderer/RenderItem;", null, l0, l3, 3);
            mv.visitMaxs(4, 4);
            mv.visitEnd();
        }
        {
            cn.methods.remove(cn.methods.stream().filter(m -> m.name.equals("isMouseOver")).findFirst().orElse(null));
            MethodVisitor mv = cn.visitMethod(ACC_PUBLIC, "isMouseOver", "(IIII)Z", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(77, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "net/minecraft/client/gui/advancements/GuiAdvancementTab", "screen", "Lnet/minecraft/client/gui/advancements/GuiScreenAdvancements;");
            mv.visitFieldInsn(GETFIELD, "net/minecraft/client/gui/advancements/GuiScreenAdvancements", "selectedPage", "I");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "net/minecraft/client/gui/advancements/GuiAdvancementTab", "page", "I");
            Label l1 = new Label();
            mv.visitJumpInsn(IF_ICMPEQ, l1);
            mv.visitInsn(ICONST_0);
            mv.visitInsn(IRETURN);
            mv.visitLabel(l1);
            mv.visitLineNumber(78, l1);
            mv.visitFrame(F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "net/minecraft/client/gui/advancements/GuiAdvancementTab", "type", "Lnet/minecraft/client/gui/advancements/AdvancementTabType;");
            mv.visitVarInsn(ILOAD, 1);
            mv.visitVarInsn(ILOAD, 2);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "net/minecraft/client/gui/advancements/GuiAdvancementTab", "index", "I");
            mv.visitVarInsn(ILOAD, 3);
            mv.visitVarInsn(ILOAD, 4);
            mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/gui/advancements/AdvancementTabType", "isMouseOver", "(IIIII)Z", false);
            mv.visitInsn(IRETURN);
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLocalVariable("this", "Lnet/minecraft/client/gui/advancements/GuiAdvancementTab;", null, l0, l2, 0);
            mv.visitLocalVariable("p_191793_1_", "I", null, l0, l2, 1);
            mv.visitLocalVariable("p_191793_2_", "I", null, l0, l2, 2);
            mv.visitLocalVariable("p_191793_3_", "I", null, l0, l2, 3);
            mv.visitLocalVariable("p_191793_4_", "I", null, l0, l2, 4);
            mv.visitMaxs(5, 5);
            mv.visitEnd();
        }

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        cn.accept(cw);
        //dump("d:\\dumper\\GuiAdvancementTab.class", cw.toByteArray());
        return cw.toByteArray();
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

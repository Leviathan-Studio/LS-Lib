package com.leviathanstudio.lib.asm;

import java.util.Iterator;
import java.util.List;

import com.leviathanstudio.lib.common.asm.ASMHelper;
import com.leviathanstudio.lib.common.asm.ASMHelper.Access;
import com.leviathanstudio.lib.common.asm.LSLClassTransformer;
import com.leviathanstudio.lib.common.asm.MappingHelper;
import com.leviathanstudio.lib.common.config.simple.ConfigFileManager;
import com.leviathanstudio.lib.common.config.simple.ConfigFileMeta;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodNode;

public class LSLLibClassTransformer extends LSLClassTransformer
{

    public LSLLibClassTransformer()
    {
        super(LSLLoadingPlugin.location);
    }

    // Initialize system
    static
    {
        transformers.put("net.minecraft.inventory.ContainerPlayer", LSLLibClassTransformer::patchContainerPlayer);
        transformers.put("net.minecraft.inventory.ContainerWorkbench", LSLLibClassTransformer::patchContainerWorkbench);

        catchers.add(LSLLibClassTransformer::catchAnnotation);
    }

    private static void catchAnnotation(String name, byte[] basicClass)
    {
        ClassNode cnode = new ClassNode();
        ClassReader cr = new ClassReader(basicClass);
        cr.accept(cnode, ClassReader.EXPAND_FRAMES);

        // ConfigFile
        List<FieldNode> classFieldNode = cnode.fields;
        for (FieldNode fNode : classFieldNode)
        {
            List<AnnotationNode> fieldAnnotationNode = fNode.visibleAnnotations;
            if (fieldAnnotationNode != null)
            {
                for (AnnotationNode aNode : fieldAnnotationNode)
                {
                    if (aNode.desc.equalsIgnoreCase("Lcom/leviathanstudio/lib/common/config/simple/ConfigFile;"))
                    {
                        String className = fNode.desc.substring(1, fNode.desc.length() - 1).replace("/", ".");
                        ConfigFileMeta meta = new ConfigFileMeta(className, aNode.values);
                        ConfigFileManager.add(meta);
                    }
                }
            }
        }
    }

    private static byte[] patchContainerPlayer(String name, byte[] basicClass)
    {
        ClassNode cnode = ASMHelper.readClass(basicClass, ClassReader.EXPAND_FRAMES);

        // Class
        String classIIventory = MappingHelper.getName("net.minecraft.inventory.IInventory");
        String classPlayer = MappingHelper.getName("net.minecraft.entity.player.EntityPlayer");
        String classWorld = MappingHelper.getName("net.minecraft.world.World");
        String classInventoryCrafting = MappingHelper.getName("net.minecraft.inventory.InventoryCrafting");
        String classItemStack = MappingHelper.getName("net.minecraft.item.ItemStack");
        String classHook = "com/leviathanstudio/lib/asm/LSLHook";

        // Field
        String fieldPlayer = MappingHelper.getName("net.minecraft.inventory.ContainerPlayer.thePlayer");
        String fieldWorld = MappingHelper.getName("net.minecraft.entity.Entity.worldObj");
        String fieldCraftMatrix = MappingHelper.getName("net.minecraft.inventory.ContainerPlayer.craftMatrix");

        // Method
        String methodOnCraft = MappingHelper.getName("net.minecraft.inventory.Container.onCraftMatrixChanged");
        String methodHook = "onCraftMatrixChanged";

        // Patch method onCraftMatrixChanged

        // Get the method
        MethodNode mn = ASMHelper.findMethod(cnode, methodOnCraft, "(L" + classIIventory + ";)V");

        // Get instructions
        InsnList in = mn.instructions;

        // Remove useless instructions
        Iterator<AbstractInsnNode> ite = in.iterator();
        int corrects = 0;
        while (ite.hasNext())
        {
            AbstractInsnNode insn = ite.next();
            if (corrects > 0 && corrects < 7)
            {
                ite.remove();
                corrects++;
            }
            // First instruction to remove
            if (insn.getOpcode() == Opcodes.INVOKESTATIC)
            {
                ite.remove();
                corrects++;
            }
        }

        // Get the first instruction to add the new ones
        AbstractInsnNode insn = ASMHelper.getFirstInstructionWithOpcode(mn, Opcodes.ICONST_0);
        InsnList out = new InsnList();

        // Get player
        out.add(ASMHelper.aLoad(0));
        out.add(ASMHelper.getField(name, fieldPlayer, "L" + classPlayer + ";"));

        // Get world
        out.add(ASMHelper.aLoad(0));
        out.add(ASMHelper.getField(name, fieldPlayer, "L" + classPlayer + ";"));
        out.add(ASMHelper.getField(classPlayer, fieldWorld, "L" + classWorld + ";"));

        // Get craftMatrix
        out.add(ASMHelper.aLoad(0));
        out.add(ASMHelper.getField(name, fieldCraftMatrix, "L" + classInventoryCrafting + ";"));

        // Call event factory
        String descOnCraftMatrixChanged = "(L" + classPlayer + ";L" + classWorld + ";L" + classInventoryCrafting + ";)L"
                + classItemStack + ";";
        out.add(ASMHelper.invokeStatic(classHook, methodHook, descOnCraftMatrixChanged));

        // Add new instructions
        in.insert(insn, out);

        // Write class and Return ByteCode
        return ASMHelper.writeClass(cnode, ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
    }

    private static byte[] patchContainerWorkbench(String name, byte[] basicClass)
    {
        ClassNode cnode = ASMHelper.readClass(basicClass, ClassReader.EXPAND_FRAMES);

        // Class
        String classInventoryPlayer = MappingHelper.getName("net.minecraft.entity.player.InventoryPlayer");
        String classIIventory = MappingHelper.getName("net.minecraft.inventory.IInventory");
        String classWorld = MappingHelper.getName("net.minecraft.world.World");
        String classBlockPos = MappingHelper.getName("net.minecraft.util.math.BlockPos");
        String classInventoryCrafting = MappingHelper.getName("net.minecraft.inventory.InventoryCrafting");
        String classItemStack = MappingHelper.getName("net.minecraft.item.ItemStack");
        String classPlayer = MappingHelper.getName("net.minecraft.entity.player.EntityPlayer");
        String classHook = "com/leviathanstudio/lib/asm/LSLHook";

        // Field
        String fieldPlayerI = MappingHelper.getName("net.minecraft.entity.player.InventoryPlayer.player");
        String fieldPlayer = "player";
        String fieldWorld = MappingHelper.getName("net.minecraft.inventory.ContainerWorkbench.worldObj");
        String fieldCraftMatrix = MappingHelper.getName("net.minecraft.inventory.ContainerWorkbench.craftMatrix");

        // Method
        String methodOnCraft = MappingHelper.getName("net.minecraft.inventory.Container.onCraftMatrixChanged");
        String methodHook = "onCraftMatrixChanged";

        // Patch fields : add new one

        cnode.fields.add(ASMHelper.field(Access.PRIVATE, true, fieldPlayer, "L" + classPlayer + ";"));

        // Patch constructor : initialize added field

        // Get the constructor
        String cdesc = classInventoryPlayer + ";L" + classWorld + ";L" + classBlockPos + ";";
        MethodNode cn = ASMHelper.findConstructor(cnode, "(L" + cdesc + ")V");

        // Get the first instruction to add the new ones
        AbstractInsnNode insc = ASMHelper.getLastInstruction(cn, Opcodes.PUTFIELD);

        // Get instructions
        InsnList cin = cn.instructions;

        // New instructions list
        InsnList outc = new InsnList();

        // Get playerInventory
        outc.add(ASMHelper.label());
        outc.add(ASMHelper.aLoad(0));

        // Get player
        outc.add(ASMHelper.aLoad(1));
        outc.add(ASMHelper.getField(classInventoryPlayer, fieldPlayerI, "L" + classPlayer + ";"));

        // Set player
        outc.add(ASMHelper.putField(name, fieldPlayer, "L" + classPlayer + ";"));

        // Add new instructions
        cin.insert(insc, outc);

        // Patch method onCraftMatrixChanged

        // Get the method
        MethodNode mn = ASMHelper.findMethod(cnode, methodOnCraft, "(L" + classIIventory + ";)V");

        // Get instructions
        InsnList in = mn.instructions;

        // Remove useless instructions
        Iterator<AbstractInsnNode> ite = in.iterator();
        int corrects = 0;
        while (ite.hasNext())
        {
            AbstractInsnNode insn = ite.next();
            if (corrects > 0 && corrects < 6)
            {
                ite.remove();
                corrects++;
            }
            // First instruction to remove
            if (insn.getOpcode() == Opcodes.INVOKESTATIC)
            {
                ite.remove();
                corrects++;
            }
        }

        // Get the first instruction to add the new ones
        AbstractInsnNode insn = ASMHelper.getFirstInstructionWithOpcode(mn, Opcodes.ICONST_0);

        // New instructions list
        InsnList out = new InsnList();

        // Get player
        out.add(ASMHelper.aLoad(0));
        out.add(ASMHelper.getField(name, fieldPlayer, "L" + classPlayer + ";"));

        // Get world
        out.add(ASMHelper.aLoad(0));
        out.add(ASMHelper.getField(name, fieldWorld, "L" + classWorld + ";"));

        // Get craftMatrix
        out.add(ASMHelper.aLoad(0));
        out.add(ASMHelper.getField(name, fieldCraftMatrix, "L" + classInventoryCrafting + ";"));

        // Call event factory
        String descOnCraftMatrixChanged = "(L" + classPlayer + ";L" + classWorld + ";L" + classInventoryCrafting + ";)L"
                + classItemStack + ";";
        out.add(ASMHelper.invokeStatic(classHook, methodHook, descOnCraftMatrixChanged));

        // Add new instructions
        in.insert(insn, out);

        // Write class and Return ByteCode
        return ASMHelper.writeClass(cnode, ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
    }

}

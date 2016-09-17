package com.leviathanstudio.lib.common.asm;

import java.util.Iterator;

import javax.annotation.Nullable;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LineNumberNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class ASMHelper
{
    public static enum Access
    {
        PRIVATE(Opcodes.ACC_PRIVATE), PROTECTED(Opcodes.ACC_PROTECTED), PUBLIC(Opcodes.ACC_PUBLIC);

        public final int opcode;

        Access(int opcode)
        {
            this.opcode = opcode;
        }

        Access()
        {
            this(0);
        }
    }

    /**
     * Setup a class node with a reader
     * 
     * @param basicClass
     *            The class code
     * @param readerMode
     *            The reader mode
     * @return The ClassNode
     */
    public static ClassNode readClass(byte[] basicClass, int readerMode)
    {
        ClassNode cnode = new ClassNode();
        ClassReader cr = new ClassReader(basicClass);
        cr.accept(cnode, readerMode);
        return cnode;
    }

    /**
     * Setup a class node with a reader
     * 
     * @param cnode
     *            The ClassNode
     * @param writeMode
     *            The writer mode
     * @return The new class code
     */
    public static byte[] writeClass(ClassNode cnode, int writeMode)
    {
        ClassWriter cw = new ClassWriter(writeMode);
        cnode.accept(cw);
        return cw.toByteArray();
    }

    /**
     * Find the constructor for the given description
     * 
     * @param cnode
     *            The class node
     * @param desc
     *            The constructor description
     * @return The constructor if found or null
     */
    public static @Nullable MethodNode findConstructor(ClassNode cnode, String desc)
    {
        return findMethod(cnode, "<init>", desc);
    }

    /**
     * Find the constructor for the given description
     * 
     * @param cnode
     *            The class node
     * @param name
     *            The method name
     * @param desc
     *            The method description
     * @return The method if found or null
     */
    public static @Nullable MethodNode findMethod(ClassNode cnode, String name, String desc)
    {
        for (MethodNode m : cnode.methods)
        {
            if (name.equals(m.name) && desc.equals(m.desc))
                return m;
        }
        return null;
    }

    /**
     * Get the first instruction with the given opcode
     * 
     * @param mn
     *            The method
     * @param opcode
     *            The opcode
     * @return The instruction node if found or null
     */
    public static @Nullable AbstractInsnNode getFirstInstructionWithOpcode(MethodNode mn, int opcode)
    {
        Iterator<AbstractInsnNode> ite = mn.instructions.iterator();
        while (ite.hasNext())
        {
            AbstractInsnNode n = ite.next();
            if (n.getOpcode() == opcode)
                return n;
        }
        return null;
    }

    /**
     * Get the first instruction after the given opcodes
     * 
     * @param mn
     *            The method
     * @param opcode
     *            The opcodes set
     * @return The instruction node if found or null
     */
    public static @Nullable AbstractInsnNode getLastInstruction(MethodNode mn, int opcode)
    {
        AbstractInsnNode res = null;
        Iterator<AbstractInsnNode> ite = mn.instructions.iterator();
        while (ite.hasNext())
        {
            AbstractInsnNode n = ite.next();

            if (n.getOpcode() == opcode)
            {
                res = n;
            }
        }
        return res;
    }

    /**
     * Get the label for the given line
     * 
     * @param mn
     *            The method
     * @param line
     *            The line number
     * @return The Label if found or null
     */
    public static @Nullable LabelNode findLineLabel(MethodNode mn, int line)
    {
        Iterator<AbstractInsnNode> ite = mn.instructions.iterator();
        while (ite.hasNext())
        {
            AbstractInsnNode n = ite.next();
            if (n instanceof LineNumberNode && ((LineNumberNode) n).line == line)
                return ((LineNumberNode) n).start;
        }
        return null;
    }

    /**
     * Create a ALOAD instruction
     * 
     * @param index
     *            The operand for the instruction
     * @return The instruction
     */
    public static VarInsnNode aLoad(int index)
    {
        return new VarInsnNode(Opcodes.ALOAD, index);
    }

    /**
     * Create a GETFIELD instruction
     * 
     * @param owner
     *            The class owner
     * @param field
     *            The field name
     * @param desc
     *            The field description
     * @return The instruction
     */
    public static FieldInsnNode getField(String owner, String field, String desc)
    {
        return new FieldInsnNode(Opcodes.GETFIELD, owner, field, desc);
    }

    /**
     * Create a PUTFIELD instruction
     * 
     * @param owner
     *            The class owner
     * @param field
     *            The field name
     * @param desc
     *            The field description
     * @return The instruction
     */
    public static FieldInsnNode putField(String owner, String field, String desc)
    {
        return new FieldInsnNode(Opcodes.PUTFIELD, owner, field, desc);
    }

    /**
     * Create a INVOKESTATIC instruction
     * 
     * @param owner
     *            The class owner
     * @param method
     *            The method name
     * @param desc
     *            The method description
     * @return The instruction
     */
    public static MethodInsnNode invokeStatic(String owner, String method, String desc)
    {
        return invokeStatic(owner, method, desc, false);
    }

    /**
     * Create a INVOKESTATIC instruction
     * 
     * @param owner
     *            The class owner
     * @param method
     *            The method name
     * @param desc
     *            The method description
     * @param itf
     *            The owner is an interface
     * @return The instruction
     */
    public static MethodInsnNode invokeStatic(String owner, String method, String desc, boolean itf)
    {
        return new MethodInsnNode(Opcodes.INVOKESTATIC, owner, method, desc, itf);
    }

    /**
     * Create a Label Node
     * 
     * @param var
     *            The operand for the instruction
     * @return The instruction
     */
    public static LabelNode label(Label var)
    {
        return new LabelNode(var);
    }

    /**
     * Create a Label Node
     * 
     * @return The instruction
     */
    public static LabelNode label()
    {
        return new LabelNode();
    }

    /**
     * Create a Field Node
     * 
     * @param access
     *            The opcode to define the field (private/protected/public,
     *            static, final, etc)
     * @param field
     *            The field name
     * @param desc
     *            The field description
     * @param signature
     *            The signature
     * @param value
     *            The value
     * @return The Node
     */
    public static FieldNode field(int access, String field, String desc, String signature, Object value)
    {
        return new FieldNode(access, field, desc, signature, value);
    }

    /**
     * Create a Field Node
     * 
     * @param access
     *            The opcode to define the field (private/protected/public,
     *            static, etc)
     * @param isFinal
     *            The field is final
     * @param field
     *            The field name
     * @param desc
     *            The field description
     * @param signature
     *            The signature
     * @param value
     *            The value
     * @return The Node
     */
    public static FieldNode field(int access, boolean isFinal, String field, String desc, String signature,
            Object value)
    {
        int aceesF = access | (isFinal ? Opcodes.ACC_FINAL : 0);
        return field(aceesF, field, desc, signature, value);
    }

    /**
     * Create a Field Node
     * 
     * @param access
     *            The access of the field (private/protected/public)
     * @param isFinal
     *            The field is final
     * @param field
     *            The field name
     * @param desc
     *            The field description
     * @param signature
     *            The signature
     * @param value
     *            The value
     * @return The Node
     */
    public static FieldNode field(Access access, boolean isFinal, String field, String desc, String signature,
            Object value)
    {
        return field(access.opcode, isFinal, field, desc, signature, value);
    }

    /**
     * Create a Field Node
     * 
     * @param access
     *            The opcode to define the field (private/protected/public,
     *            static, etc)
     * @param isFinal
     *            The field is final
     * @param field
     *            The field name
     * @param desc
     *            The field description
     * @param value
     *            The value of the field
     * @return The Node
     */
    public static FieldNode field(Access access, boolean isFinal, String field, String desc, Object value)
    {
        return field(access, isFinal, field, desc, null, value);
    }

    /**
     * Create a Field Node
     * 
     * @param access
     *            The opcode to define the field (private/protected/public,
     *            static, etc)
     * @param isFinal
     *            The field is final
     * @param field
     *            The field name
     * @param desc
     *            The field description
     * @return The Node
     */
    public static FieldNode field(Access access, boolean isFinal, String field, String desc)
    {
        return field(access, isFinal, field, desc, null);
    }
}

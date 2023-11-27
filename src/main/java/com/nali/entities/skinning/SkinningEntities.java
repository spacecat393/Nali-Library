package com.nali.entities.skinning;

import com.nali.entities.data.SkinningData;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;

public abstract class SkinningEntities extends EntityLivingBase
{
    public NonNullList<ItemStack> hands_itemstack_nonnulllist = NonNullList.<ItemStack>withSize(2, ItemStack.EMPTY);
    public NonNullList<ItemStack> armor_itemstack_nonnulllist = NonNullList.<ItemStack>withSize(4, ItemStack.EMPTY);

    @SideOnly(Side.CLIENT)
    public Object client_object;

    public SkinningEntities(World world)
    {
        super(world);

        if (world.isRemote)
        {
            this.client_object = this.getClientObject();
        }
    }

    @Override
    public void entityInit()
    {
        super.entityInit();

        DataParameter<Byte>[] byte_dataparameter_array = this.getByteDataParameterArray();
        for (DataParameter<Byte> bytedataparameter : byte_dataparameter_array)
        {
            this.dataManager.register(bytedataparameter, (byte) 0);
        }

        DataParameter<Integer>[] integer_dataparameter_array = this.getIntegerDataParameterArray();
        for (DataParameter<Integer> integerdataparameter : integer_dataparameter_array)
        {
            this.dataManager.register(integerdataparameter, 0);
        }

        DataParameter<Float>[] float_dataparameter_array = this.getFloatDataParameterArray();
        for (DataParameter<Float> floatdataparameter : float_dataparameter_array)
        {
            this.dataManager.register(floatdataparameter, 0.0F);
        }
    }

    //NBTBase.NBT_TYPES
    @Override
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);

        EntityDataManager entitydatamanager = this.getDataManager();

        DataParameter<Byte>[] byte_dataparameter_array = this.getByteDataParameterArray();
        for (int i = 0; i < byte_dataparameter_array.length; ++i)
        {
            nbttagcompound.setByte("byte_" + i, entitydatamanager.get(byte_dataparameter_array[i]));
        }

        NBTTagList nbttaglist = new NBTTagList();
        for (ItemStack itemstack : this.armor_itemstack_nonnulllist)
        {
            NBTTagCompound new_nbttagcompound = new NBTTagCompound();

            if (!itemstack.isEmpty())
            {
                itemstack.writeToNBT(new_nbttagcompound);
            }

            nbttaglist.appendTag(new_nbttagcompound);
        }

        nbttagcompound.setTag("ArmorItems", nbttaglist);
        nbttaglist = new NBTTagList();

        for (ItemStack itemstack1 : this.hands_itemstack_nonnulllist)
        {
            NBTTagCompound new_nbttagcompound = new NBTTagCompound();

            if (!itemstack1.isEmpty())
            {
                itemstack1.writeToNBT(new_nbttagcompound);
            }

            nbttaglist.appendTag(new_nbttagcompound);
        }

        nbttagcompound.setTag("HandItems", nbttaglist);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);

        EntityDataManager entitydatamanager = this.getDataManager();

        DataParameter<Byte>[] byte_dataparameter_array = this.getByteDataParameterArray();
        for (int i = 0; i < byte_dataparameter_array.length; ++i)
        {
            entitydatamanager.set(byte_dataparameter_array[i], nbttagcompound.getByte("byte_" + i));
        }

        if (nbttagcompound.hasKey("ArmorItems", 9))
        {
            NBTTagList nbttaglist = nbttagcompound.getTagList("ArmorItems", 10);

            for (int i = 0; i < this.armor_itemstack_nonnulllist.size(); ++i)
            {
                this.armor_itemstack_nonnulllist.set(i, new ItemStack(nbttaglist.getCompoundTagAt(i)));
            }
        }

        if (nbttagcompound.hasKey("HandItems", 9))
        {
            NBTTagList nbttaglist1 = nbttagcompound.getTagList("HandItems", 10);

            for (int j = 0; j < this.hands_itemstack_nonnulllist.size(); ++j)
            {
                this.hands_itemstack_nonnulllist.set(j, new ItemStack(nbttaglist1.getCompoundTagAt(j)));
            }
        }
    }

    @Override
    public Iterable<ItemStack> getHeldEquipment()
    {
        return this.hands_itemstack_nonnulllist;
    }

    @Override
    public Iterable<ItemStack> getArmorInventoryList()
    {
        return this.armor_itemstack_nonnulllist;
    }

    @Override
    public ItemStack getItemStackFromSlot(EntityEquipmentSlot entityequipmentslot)
    {
        switch (entityequipmentslot.getSlotType())
        {
            case HAND:
                return this.hands_itemstack_nonnulllist.get(entityequipmentslot.getIndex());
            case ARMOR:
                return this.armor_itemstack_nonnulllist.get(entityequipmentslot.getIndex());
            default:
                return ItemStack.EMPTY;
        }
    }

    @Override
    public void setItemStackToSlot(EntityEquipmentSlot entityequipmentslot, ItemStack itemstack)
    {
        switch (entityequipmentslot.getSlotType())
        {
            case HAND:
                this.hands_itemstack_nonnulllist.set(entityequipmentslot.getIndex(), itemstack);
                break;
            case ARMOR:
                this.armor_itemstack_nonnulllist.set(entityequipmentslot.getIndex(), itemstack);
        }
    }

    @Override
    public EnumHandSide getPrimaryHand()
    {
        return EnumHandSide.RIGHT;
    }

//    @Override
//    public boolean processInteract(EntityPlayer entityplayer, EnumHand enumhand)
//    {
//        if (entityplayer.isSneaking() && entityplayer.getHeldItemMainhand().isEmpty())
//        {
//            if (this.getEntityWorld().isRemote)
//            {
//                Nali.COMMONPROXY.openInventoryGui(this, entityplayer);
//            }
//            else
//            {
//                if (entityplayer.openContainer != entityplayer.inventoryContainer)
//                {
//                    entityplayer.closeScreen();
//                }
//
//                EntityPlayerMP entityplayermp = (EntityPlayerMP)entityplayer;
//                entityplayermp.getNextWindowId();
////                entityplayermp.connection.sendPacket(new SPacketOpenWindow(entityplayermp.currentWindowId, "EntityHorse", inventoryIn.getDisplayName(), inventoryIn.getSizeInventory(), horse.getEntityId()));
////                entityplayermp.openContainer = new ContainerHorseInventory(entityplayermp.inventory, inventoryIn, horse, entityplayermp);
//                entityplayermp.openContainer = new InventoryContainer(this, entityplayermp);
//                entityplayermp.openContainer.windowId = entityplayermp.currentWindowId;
//                entityplayermp.openContainer.addListener(entityplayermp);
//            }
////            entityplayer.openGui(Main.MAIN, 0, entityplayer.getEntityWorld(), 0, 0, 0);
//            return true;
//        }
//
//        return super.processInteract(entityplayer, enumhand);
//    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if (this.getEntityWorld().isRemote)
        {
            this.updateClientObject();
            this.setInvisibility(this.client_object);
        }
    }

    @Override
    public double getYOffset()
    {
        return 0.3D;
    }

    @SideOnly(Side.CLIENT)
    public void setInvisibility(Object object)
    {
        if (this.isInvisible() || this.isInvisibleToPlayer(Minecraft.getMinecraft().player))
        {
            SkinningData skinningdata = (SkinningData)object;
            Arrays.fill(skinningdata.model_boolean_array, false);
        }
        else
        {
            this.setBooleanArraylist(object);
        }
    }

    public void updateClientObject()
    {
        SkinningData skinningdata = (SkinningData)this.client_object;
        EntityDataManager entitydatamanager = this.getDataManager();

        skinningdata.float_array[0] = entitydatamanager.get(this.getFloatDataParameterArray()[0]);

        DataParameter<Integer>[] integer_dataparameter = this.getIntegerDataParameterArray();
        for (int i = 0; i < skinningdata.texture_index_int_array.length; ++i)
        {
            skinningdata.texture_index_int_array[i] = entitydatamanager.get(integer_dataparameter[i]);
        }

        for (int i = 0; i < skinningdata.frame_int_array.length; ++i)
        {
            skinningdata.frame_int_array[i] = entitydatamanager.get(integer_dataparameter[this.getMaxPart() + i]);
        }
    }

    public abstract int getMaxPart();
    @SideOnly(Side.CLIENT)
    public abstract int getStepModels();
    @SideOnly(Side.CLIENT)
    public abstract void setBooleanArraylist(Object object);
    @SideOnly(Side.CLIENT)
    public abstract void setGlow(Object object);
    @SideOnly(Side.CLIENT)
    public abstract void setUniform(Object[] object_array);
    public abstract DataParameter<Byte>[] getByteDataParameterArray();
    public abstract DataParameter<Integer>[] getIntegerDataParameterArray();
    public abstract DataParameter<Float>[] getFloatDataParameterArray();
    @SideOnly(Side.CLIENT)
    public abstract Object getClientObject();
}

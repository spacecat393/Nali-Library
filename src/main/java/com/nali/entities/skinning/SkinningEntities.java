package com.nali.entities.skinning;

import net.minecraft.entity.EntityCreature;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class SkinningEntities extends EntityCreature
{
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
        for (int i = 0; i < byte_dataparameter_array.length; ++i)
        {
            this.dataManager.register(byte_dataparameter_array[i], (byte)0);
        }

        DataParameter<Integer>[] integer_dataparameter_array = this.getIntegerDataParameterArray();
        for (int i = 0; i < integer_dataparameter_array.length; ++i)
        {
            this.dataManager.register(integer_dataparameter_array[i], 0);
        }

        DataParameter<Float>[] float_dataparameter_array = this.getFloatDataParameterArray();
        for (int i = 0; i < float_dataparameter_array.length; ++i)
        {
            this.dataManager.register(float_dataparameter_array[i], 0.0F);
        }
    }

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
    public double getYOffset()
    {
        return 0.3D;
    }

    public abstract int getMaxPart();
    public abstract int getStepModels();
    public abstract void setBooleanArraylist(Object object);
    public abstract void setGlow(Object object);
    public abstract void setUniform(Object[] object_array);
    public abstract DataParameter<Byte>[] getByteDataParameterArray();
    public abstract DataParameter<Integer>[] getIntegerDataParameterArray();
    public abstract DataParameter<Float>[] getFloatDataParameterArray();
    @SideOnly(Side.CLIENT)
    public abstract Object getClientObject();
}

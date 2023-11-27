package com.nali.entities.object;

import com.nali.entities.data.ObjectData;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class ObjectEntities extends Entity
{
    public Object client_object;

    public ObjectEntities(World worldIn)
    {
        super(worldIn);

        if (world.isRemote)
        {
            this.client_object = this.getClientObject();
        }
    }

    @Override
    public void entityInit()
    {
        DataParameter<Byte>[] byte_dataparameter_array = this.getByteDataParameterArray();
        for (DataParameter<Byte> byteDataParameter : byte_dataparameter_array)
        {
            this.dataManager.register(byteDataParameter, (byte) 0);
        }

        DataParameter<Integer>[] integer_dataparameter_array = this.getIntegerDataParameterArray();
        for (DataParameter<Integer> integerDataParameter : integer_dataparameter_array)
        {
            this.dataManager.register(integerDataParameter, 0);
        }

        DataParameter<Float>[] float_dataparameter_array = this.getFloatDataParameterArray();
        for (DataParameter<Float> floatDataParameter : float_dataparameter_array)
        {
            this.dataManager.register(floatDataParameter, 0.0F);
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
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
        EntityDataManager entitydatamanager = this.getDataManager();

        DataParameter<Byte>[] byte_dataparameter_array = this.getByteDataParameterArray();
        for (int i = 0; i < byte_dataparameter_array.length; ++i)
        {
            entitydatamanager.set(byte_dataparameter_array[i], nbttagcompound.getByte("byte_" + i));
        }
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if (this.getEntityWorld().isRemote)
        {
            this.updateClientObject();
        }
    }

    @Override
    public double getYOffset()
    {
        return 0.3D;
    }

    public void updateClientObject()
    {
        ObjectData objectdata = (ObjectData)this.client_object;
        EntityDataManager entitydatamanager = this.getDataManager();

        objectdata.float_array[0] = entitydatamanager.get(this.getFloatDataParameterArray()[0]);

        DataParameter<Integer>[] integer_dataparameter = this.getIntegerDataParameterArray();
        for (int i = 0; i < objectdata.texture_index_int_array.length; ++i)
        {
            objectdata.texture_index_int_array[i] = entitydatamanager.get(integer_dataparameter[i]);
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

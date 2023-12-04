package com.nali.entities.skinning;

import com.google.common.collect.Lists;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IInventoryChangedListener;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

import java.util.List;

public class SkinningInventory implements IInventory
{
    public int slotsCount = 27;
    public NonNullList<ItemStack> hands_itemstack_nonnulllist = NonNullList.<ItemStack>withSize(2, ItemStack.EMPTY);
    public NonNullList<ItemStack> armor_itemstack_nonnulllist = NonNullList.<ItemStack>withSize(4, ItemStack.EMPTY);
    public NonNullList<ItemStack> inventoryContents;
    public List<IInventoryChangedListener> changeListeners;

    public SkinningInventory()
    {
        this.inventoryContents = NonNullList.<ItemStack>withSize(this.slotsCount, ItemStack.EMPTY);
    }

    public void addInventoryChangeListener(IInventoryChangedListener listener)
    {
        if (this.changeListeners == null)
        {
            this.changeListeners = Lists.<IInventoryChangedListener>newArrayList();
        }

        this.changeListeners.add(listener);
    }

    public void removeInventoryChangeListener(IInventoryChangedListener listener)
    {
        this.changeListeners.remove(listener);
    }

    public ItemStack getStackInSlot(int index)
    {
        int contents_size = this.inventoryContents.size();
        int hands_size = this.hands_itemstack_nonnulllist.size();
        return index >= 0 &&
                index < contents_size ? this.inventoryContents.get(index) :
                index < contents_size + hands_size ? this.hands_itemstack_nonnulllist.get(index - contents_size) :
                index < contents_size + hands_size + this.armor_itemstack_nonnulllist.size() ? this.armor_itemstack_nonnulllist.get(index - contents_size - hands_size) : ItemStack.EMPTY;
    }

    public ItemStack decrStackSize(int index, int count)
    {
        ItemStack itemstack = null;
        int hands_size = this.hands_itemstack_nonnulllist.size();
        int contents_size = this.inventoryContents.size();

        if (index >= contents_size + hands_size)
        {
            itemstack = ItemStackHelper.getAndSplit(this.armor_itemstack_nonnulllist, index - contents_size - hands_size, count);
        }
        else if (index >= contents_size)
        {
            itemstack = ItemStackHelper.getAndSplit(this.hands_itemstack_nonnulllist, index - contents_size, count);
        }
        else
        {
            itemstack = ItemStackHelper.getAndSplit(this.inventoryContents, index, count);
        }

        if (!itemstack.isEmpty())
        {
            this.markDirty();
        }

        return itemstack;
    }

    public ItemStack addItem(ItemStack stack)
    {
        ItemStack itemstack = stack.copy();

        for (int i = 0; i < this.getSizeInventory(); ++i)
        {
            ItemStack itemstack1 = this.getStackInSlot(i);

            if (itemstack1.isEmpty())
            {
                this.setInventorySlotContents(i, itemstack);
                this.markDirty();
                return ItemStack.EMPTY;
            }

            if (ItemStack.areItemsEqual(itemstack1, itemstack))
            {
                int j = Math.min(this.getInventoryStackLimit(), itemstack1.getMaxStackSize());
                int k = Math.min(itemstack.getCount(), j - itemstack1.getCount());

                if (k > 0)
                {
                    itemstack1.grow(k);
                    itemstack.shrink(k);

                    if (itemstack.isEmpty())
                    {
                        this.markDirty();
                        return ItemStack.EMPTY;
                    }
                }
            }
        }

        if (itemstack.getCount() != stack.getCount())
        {
            this.markDirty();
        }

        return itemstack;
    }

    public ItemStack removeStackFromSlot(int index)
    {
        ItemStack itemstack = this.inventoryContents.get(index);

        if (itemstack.isEmpty())
        {
            return ItemStack.EMPTY;
        }
        else
        {
            int hands_size = this.hands_itemstack_nonnulllist.size();
            int contents_size = this.inventoryContents.size();

            if (index >= contents_size + hands_size)
            {
                this.armor_itemstack_nonnulllist.set(index - contents_size - hands_size, ItemStack.EMPTY);
            }
            else if (index >= contents_size)
            {
                this.hands_itemstack_nonnulllist.set(index - contents_size, ItemStack.EMPTY);
            }
            else
            {
                this.inventoryContents.set(index, ItemStack.EMPTY);
            }
            return itemstack;
        }
    }

    public void setInventorySlotContents(int index, ItemStack stack)
    {
        int hands_size = this.hands_itemstack_nonnulllist.size();
        int contents_size = this.inventoryContents.size();

        if (index >= contents_size + hands_size)
        {
            this.armor_itemstack_nonnulllist.set(index - contents_size - hands_size, stack);
        }
        else if (index >= contents_size)
        {
            this.hands_itemstack_nonnulllist.set(index - contents_size, stack);
        }
        else
        {
            this.inventoryContents.set(index, stack);
        }

        if (!stack.isEmpty() && stack.getCount() > this.getInventoryStackLimit())
        {
            stack.setCount(this.getInventoryStackLimit());
        }

        this.markDirty();
    }

    public int getSizeInventory()
    {
        return this.slotsCount + this.hands_itemstack_nonnulllist.size() + this.armor_itemstack_nonnulllist.size();
    }

    public boolean isEmpty()
    {
        for (ItemStack itemstack : this.inventoryContents)
        {
            if (!itemstack.isEmpty())
            {
                return false;
            }
        }

        for (ItemStack itemstack : this.hands_itemstack_nonnulllist)
        {
            if (!itemstack.isEmpty())
            {
                return false;
            }
        }

        for (ItemStack itemstack : this.armor_itemstack_nonnulllist)
        {
            if (!itemstack.isEmpty())
            {
                return false;
            }
        }

        return true;
    }

    public String getName()
    {
        return "";
    }

    public boolean hasCustomName()
    {
        return false;
    }

    public ITextComponent getDisplayName()
    {
        return new TextComponentString(this.getName());
    }

    public int getInventoryStackLimit()
    {
        return 64;
    }

    public void markDirty()
    {
        if (this.changeListeners != null)
        {
            for (IInventoryChangedListener changeListener : this.changeListeners)
            {
                changeListener.onInventoryChanged(this);
            }
        }
    }

    public boolean isUsableByPlayer(EntityPlayer entityplayer)
    {
        return true;
    }

    public void openInventory(EntityPlayer entityplayer)
    {
    }

    public void closeInventory(EntityPlayer entityplayer)
    {
    }

    public boolean isItemValidForSlot(int index, ItemStack itemstack)
    {
        return true;
    }

    public int getField(int id)
    {
        return 0;
    }

    public void setField(int id, int value)
    {
    }

    public int getFieldCount()
    {
        return 0;
    }

    public void clear()
    {
        this.inventoryContents.clear();
        this.hands_itemstack_nonnulllist.clear();
        this.armor_itemstack_nonnulllist.clear();
    }
}

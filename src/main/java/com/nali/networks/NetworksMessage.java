package com.nali.networks;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public abstract class NetworksMessage implements IMessage
{
    public byte[] data;

    @Override
    public void fromBytes(ByteBuf bytebuf)
    {
        int length = bytebuf.readableBytes();
        this.data = new byte[length];
        bytebuf.readBytes(this.data);
    }

    @Override
    public void toBytes(ByteBuf bytebuf)
    {
        bytebuf.writeBytes(this.data);
    }
}

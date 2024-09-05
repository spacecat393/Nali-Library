package com.nali.dumb;

import java.util.concurrent.Semaphore;

public class DSemaphore extends Semaphore
{
    public DSemaphore()
    {
        super(0);
    }

    @Override
    public boolean tryAcquire()
    {
        return true;
    }

    @Override
    public void release()
    {
    }
}

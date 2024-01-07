package com.nali.data;

import com.nali.data.BothData;

public class SakuraData implements BothData
{
    @Override
    public int MaxPart()
    {
        return 1;
    }

    @Override
    public int StepModels()
    {
        return 0;
    }

    @Override
    public float Width()
    {
        return 1.0F;
    }

    @Override
    public float Height()
    {
        return 1.0F;
    }

    @Override
    public float Scale()
    {
        return 1.0F;
    }

    @Override
    public int MaxFrame()
    {
        return 0;
    }
}

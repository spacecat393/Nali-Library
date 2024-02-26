package com.nali.data;

public class GuiObjectData implements BothData
{
    public int max_part;
    public int step_models;

    public GuiObjectData(int step_models, int max_part)
    {
        this.step_models = step_models;
        this.max_part = max_part;
    }

    @Override
    public int MaxPart()
    {
        return this.max_part;
    }

    @Override
    public int StepModels()
    {
        return this.step_models;
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
        return -1;
    }

    @Override
    public int MaxSync()
    {
        return -1;
    }
}

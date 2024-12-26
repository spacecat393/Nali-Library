package com.nali.list.da;

import com.nali.da.IBothDaO;

import static com.nali.list.data.NaliData.MODEL_STEP;

public class BothDaSky implements IBothDaO
{
	public static BothDaSky IDA;

	@Override
	public int O_StartPart()
	{
		return MODEL_STEP;
	}

	@Override
	public int O_EndPart()
	{
		return MODEL_STEP + 11;
	}
}
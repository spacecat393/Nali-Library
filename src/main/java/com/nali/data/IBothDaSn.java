package com.nali.data;

import com.nali.sound.ISoundN;

public interface IBothDaSn<SD extends ISoundN> extends IBothDaOn<SD>
{
    byte MaxFrame();
}

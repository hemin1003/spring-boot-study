package com.md.core.leafid.id;

import com.md.core.leafid.Result;

public interface IDGen {
    Result get(String key);
    boolean init();
}

package com.md.core.leafid;

import com.md.core.leafid.id.IDGen;

public class ZeroIDGen implements IDGen {
	
    @Override
    public Result get(String key) {
        return new Result(0, Status.SUCCESS);
    }

    @Override
    public boolean init() {
        return true;
    }
}

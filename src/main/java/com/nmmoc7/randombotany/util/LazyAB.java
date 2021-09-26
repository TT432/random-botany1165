package com.nmmoc7.randombotany.util;

import net.minecraftforge.common.util.Lazy;

public class LazyAB<A, B> {
    final Lazy<A> aObj;
    final Lazy<B> bObj;

    public LazyAB(Lazy<A> aObj, Lazy<B> bObj) {
        this.aObj = aObj;
        this.bObj = bObj;
    }

    public A getA() {
        return aObj.get();
    }

    public B getB() {
        return bObj.get();
    }
}

package com.jl.categoryproducts.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RGBColourUtilTest {

    @Test
    public void rgbColourRepresentationTest() {

        assertEquals("#FFFFFF", RGBColourUtil.get("White"));
        assertEquals("#0000FF", RGBColourUtil.get("Blue"));
        assertEquals("#808080", RGBColourUtil.get("Grey"));
        assertEquals("#800080", RGBColourUtil.get("Purple"));

    }
}

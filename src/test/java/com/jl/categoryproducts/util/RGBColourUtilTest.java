package com.jl.categoryproducts.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import org.junit.Test;

public class RGBColourUtilTest {

    @Test
    public void rgbColourRepresentationTest() {

        assertEquals("#FFFFFF", RGBColourUtil.get("White"));
        assertEquals("#0000FF", RGBColourUtil.get("Blue"));
        assertEquals("#808080", RGBColourUtil.get("Grey"));
        assertEquals("#800080", RGBColourUtil.get("Purple"));

    }

    @Test
    public void testConstructorIsPrivate() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<RGBColourUtil> constructor = RGBColourUtil.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }
}

package com.jl.categoryproducts.util;

import java.util.HashMap;
import java.util.Map;

public class RGBColourUtil {

    private RGBColourUtil() {

    }

    public static String get(String basicColor) {

        Map<String, String> coloursMap = new HashMap<>();
        coloursMap.put("Black", "#000000");
        coloursMap.put("White", "#FFFFFF");
        coloursMap.put("Red", "#FF0000");
        coloursMap.put("Lime", "#00FF00");
        coloursMap.put("Blue", "#0000FF");
        coloursMap.put("Yellow", "#FFFF00");
        coloursMap.put("Cyan", "#00FFFF");
        coloursMap.put("Aqua", "#00FFFF");
        coloursMap.put("Magenta", "#FF00FF");
        coloursMap.put("Fuchsia", "#FF00FF");
        coloursMap.put("Silver", "#C0C0C0");
        coloursMap.put("Grey", "#808080");
        coloursMap.put("Maroon", "#800000");
        coloursMap.put("Olive", "#808000");
        coloursMap.put("Green", "#008000");
        coloursMap.put("Purple", "#800080");
        coloursMap.put("Teal", "#008080");
        coloursMap.put("Navy", "#000080");
        coloursMap.put("Pink", "#FFC0CB");


        return coloursMap.get(basicColor);
    }
}

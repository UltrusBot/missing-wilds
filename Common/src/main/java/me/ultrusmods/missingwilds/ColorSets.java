package me.ultrusmods.missingwilds;

import java.awt.*;
import java.util.HashMap;

public class ColorSets {
    public static final HashMap<String, Color[]> COLOR_SETS = new HashMap<>();

    static {
        COLOR_SETS.put("pride", new Color[]{new Color(228, 3, 3), new Color(255, 140, 0), new Color(255, 237, 0), new Color(0, 128, 38), new Color(36, 64, 142), new Color(115, 41, 130)});
        COLOR_SETS.put("trans", new Color[]{new Color(91, 206, 250), new Color(245, 169, 184), new Color(255, 255, 255)});
        COLOR_SETS.put("bi", new Color[]{new Color(214, 2, 112), new Color(155, 79, 150), new Color(0, 56, 168)});
        COLOR_SETS.put("pan", new Color[]{new Color(255, 33, 140), new Color(255, 216, 0), new Color(33, 177, 255)});
        COLOR_SETS.put("lesbian", new Color[]{new Color(213, 45, 0), new Color(239, 118, 39), new Color(255, 154, 86), new Color(255, 255, 255), new Color(209, 98, 164), new Color(181, 86, 144), new Color(163, 2, 98)});
        COLOR_SETS.put("genderfluid", new Color[]{new Color(255, 118, 164), new Color(255, 255, 255), new Color(192, 17, 215), new Color(0, 0, 0), new Color(47, 60, 190)});
        COLOR_SETS.put("asexual", new Color[]{new Color(0, 0, 0), new Color(163, 163, 163), new Color(255, 255, 255), new Color(128, 0, 128)});
        COLOR_SETS.put("aromantic", new Color[]{new Color(61, 165, 66), new Color(167, 211, 121), new Color(255, 255, 255), new Color(169, 169, 169), new Color(0, 0, 0)});
        COLOR_SETS.put("nonbinary", new Color[]{new Color(252, 244, 52), new Color(255, 255, 255), new Color(156, 89, 209), new Color(0, 0, 0)});

    }

}

package me.ultrusmods.missingwilds;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public class ColorSets {
    public static final HashMap<String, ColorSet> COLOR_SETS = new HashMap<>();
    private static final Gson GSON = new GsonBuilder().create();

    static {
        addColorSet("pride", color(228, 3, 3), color(255, 140, 0), color(255, 237, 0), color(0, 128, 38), color(36, 64, 142), color(115, 41, 130));
        addColorSet("trans", color(91, 206, 250), color(245, 169, 184), color(255, 255, 255));
        addColorSet("bi", color(214, 2, 112), color(155, 79, 150), color(0, 56, 168));
        addColorSet("pan", color(255, 33, 140), color(255, 216, 0), color(33, 177, 255));
        addColorSet("lesbian", color(213, 45, 0), color(239, 118, 39), color(255, 154, 86), color(255, 255, 255), color(209, 98, 164), color(181, 86, 144), color(163, 2, 98));
        addColorSet("genderfluid", color(255, 118, 164), color(255, 255, 255), color(192, 17, 215), color(0, 0, 0), color(47, 60, 190));
        addColorSet("asexual", color(0, 0, 0), color(163, 163, 163), color(255, 255, 255), color(128, 0, 128));
        addColorSet("aromantic", color(61, 165, 66), color(167, 211, 121), color(255, 255, 255), color(169, 169, 169), color(0, 0, 0));
        addColorSet("nonbinary", color(252, 244, 52), color(255, 255, 255), color(156, 89, 209), color(0, 0, 0));
    }

    /**
     * Loads colors from a gist on GitHub,
     */
    public static void addSpecialColors() {
        CompletableFuture.runAsync(() -> {
            try {
                Reader reader = new InputStreamReader(new URL("https://gist.githubusercontent.com/UltrusBot/1cc799aa2657d49d2a2cffbec8f242a8/raw/colors.json").openStream());
                ColorSet[] colors = GSON.fromJson(reader, ColorSet[].class);
                Arrays.stream(colors).forEach(ColorSet::addToMap);
            } catch (MalformedURLException e) {
                Constants.LOG.error("Malformed URL for color sets! Error: " + e.getMessage());
            } catch (IOException e) {
                Constants.LOG.error("Error reading color sets! Error: " + e.getMessage());
            } catch (Exception e) {
                Constants.LOG.error("Unknown error reading color sets! Error: " + e.getMessage());
            }
        });
    }
    private static void addColorSet(String name, Integer[]... colors) {
        COLOR_SETS.put(name, new ColorSet(name, colors));
    }

    /**
     * Just a utility method to create a color array, just to make the code look a bit cleaner than having new Integer[]{} everywhere
     * @param r Red value
     * @param g Green value
     * @param b Blue value
     * @return A 3-length integer array with the RGB values
     */
    private static Integer[] color(int r, int g, int b) {
        return new Integer[]{r, g, b};
    }


    public static class ColorSet {
        public String name;
        public Integer[][] colors;

        public ColorSet(String name, Integer[]... colors) {
            this.name = name;
            this.colors = colors;
        }
        private void addToMap() {
            COLOR_SETS.put(name, this);
        }
    }
}

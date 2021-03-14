package ca.cobiy.simple_jukebox.utils;

import org.bukkit.Material;

public class ItemChecker {

    public static String materialToAuthor(Material material){
        switch (material){
            case MUSIC_DISC_13: return "C418 - 13";
            case MUSIC_DISC_CAT: return "C418 - cat";
            case MUSIC_DISC_BLOCKS: return "C418 - block";
            case MUSIC_DISC_CHIRP: return "C418 - chrip";
            case MUSIC_DISC_FAR: return "C418 - far";
            case MUSIC_DISC_MALL: return "C418 - mall";
            case MUSIC_DISC_MELLOHI: return "C418 - mellohi";
            case MUSIC_DISC_STAL: return "C418 - stal";
            case MUSIC_DISC_STRAD: return "C418 - stard";
            case MUSIC_DISC_WARD: return "C418 - ward";
            case MUSIC_DISC_11: return "C418 - 11";
            case MUSIC_DISC_WAIT: return "C418 - wait";
            case MUSIC_DISC_PIGSTEP: return "Lena Raine - Pigstep";
            default: return "unknown";
        }
    }

}

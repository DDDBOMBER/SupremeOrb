package com.dddbomber.proton.rank;

import com.dddbomber.proton.assets.AssetLoader;
import com.dddbomber.proton.assets.Bitmap;

public class Rank {
	public static Bitmap[] rankIcons = AssetLoader.loadBitmapGroup("/ranks");
	
	public static String[] rankNames = {
		"Beacon I",
		"Beacon II",
		"Beacon III",
		"Beacon IV",
		"Beacon Supreme",
		"Beam I",
		"Beam II",
		"Beam Supreme",
		"Shining Beam Supreme",
		"Glowing Orb",
		"Eclipsing Shadow",
		"Universal Illuminator"
	};
}

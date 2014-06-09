package com.dddbomber.proton.assets;

import static com.dddbomber.proton.assets.AssetLoader.*;

public class Asset {
	public static Bitmap cursor = loadBitmap("/cursor.png");
	public static Bitmap rank_overlay = loadBitmap("/gui/rank_overlay.png");
	public static Bitmap color_overlay = loadBitmap("/gui/color_overlay.png");
	public static Bitmap loading = loadBitmap("/gui/loading.png");
	
	public static Bitmap ranks[] = {
		loadBitmap("/ranks/rank00.png"),
		loadBitmap("/ranks/rank01.png"),
		loadBitmap("/ranks/rank02.png"),
		loadBitmap("/ranks/rank03.png"),
		loadBitmap("/ranks/rank04.png"),
		loadBitmap("/ranks/rank05.png"),
		loadBitmap("/ranks/rank06.png"),
		loadBitmap("/ranks/rank07.png"),
		loadBitmap("/ranks/rank08.png"),
		loadBitmap("/ranks/rank09.png"),
		loadBitmap("/ranks/rank10.png"),
		loadBitmap("/ranks/rank11.png"),
		loadBitmap("/ranks/rank12.png"),
		loadBitmap("/ranks/rankbot.png")
	};

	public static void loadAssets(){
		
	}
}

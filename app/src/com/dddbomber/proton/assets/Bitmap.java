package com.dddbomber.proton.assets;

import java.util.ArrayList;

public class Bitmap {
	
	public final int width, height;
	public int[] pixels;
	
	public Bitmap(int width, int height){
		this.width = width;
		this.height = height;
		this.pixels = new int[width * height];
	}
	
	public static String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
			+ "abcdefghijklmnopqrstuvwxyz" + "0123456789*/$";

	public static Bitmap font = AssetLoader.loadBitmap("/font.png");

	public static int[] lightFX = new int[] { 0, 8, 2, 10, 12, 4, 14, 6, 3, 11, 1, 9, 15, 7, 13, 5, };

	public void drawLine(int x0, int y0, int x1, int y1, int col){
		boolean steep = Math.abs(y1 - y0) > Math.abs(x1 - x0);
		int temp;
		if(steep){
			temp = x0;
			x0 = y0;
			y0 = temp;

			temp = x1;
			x1 = y1;
			y1 = temp;
		}
		if(x0 > x1){
			temp = x0;
			x0 = x1;
			x1 = temp;

			temp = y0;
			y0 = y1;
			y1 = temp;
		}
		int deltax = x1 - x0;
		int deltay = Math.abs(y1 - y0);
		int error = deltax / 2;
		int ystep;
		int y = y0;
		if(y0 < y1){
			ystep = 1; 
		}else{
			ystep = -1;
		}
		for(int x = x0; x < x1; x++){
			if(steep){
				plot(y,x, col);
			}else{
				plot(x,y, col);
			}
			error -= deltay;
			if(error < 0){
				y += ystep;
				error += deltax;
			}
		}
	}

	private void plot(int x, int y, int col) {
		this.renderLight(x, y, 4, col);
	}

	public void overlay(Bitmap bitmap, int xa, int ya, int col, int trans) {
		int[] oPixels = bitmap.pixels;
		int i = 0;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				//if (oPixels[i] / 10 <= lightFX[((x + xa) & 3) + ((y + ya) & 3) * 4]) pixels[i] = merge(col, pixels[i], trans);
				pixels[i] = merge(0, pixels[i], (int)(100-(oPixels[i]/2.5)));
				i++;
			}

		}
	}

	public static ArrayList<Dither> dithers = loadDithers();

	public static ArrayList<Dither> loadDithers(){
		ArrayList<Dither> dithers = new ArrayList<Dither>();

		for(int i = 0; i < 9; i++){
			Dither d = new Dither(i);
			dithers.add(d);
		}
		
		return dithers;
	}
	
	public void renderOutline(int x, int y, int r, int col) {
		int x0 = x - r;
		int x1 = x + r;
		int y0 = y - r;
		int y1 = y + r;

		if (x0 < 0) x0 = 0;
		if (y0 < 0) y0 = 0;
		if (x1 > width) x1 = width;
		if (y1 > height) y1 = height;
		
		int rr = r * r;
		
		for (int yy = y0; yy < y1; yy++) {
			int yd = yy - y;
			yd = yd * yd;
			for (int xx = x0; xx < x1; xx++) {
				int pix = xx + yy * width;
				int xd = xx - x;
				int dist = xd * xd + yd;
				if (dist <= rr) {
					int br = 150 - dist * 150 / (rr);
					pixels[pix] = merge(pixels[pix], col, br);
				}
			}
		}
	}
	
	public void renderLight(int x, int y, int r, int col) {
		int x0 = x - r;
		int x1 = x + r;
		int y0 = y - r;
		int y1 = y + r;

		if (x0 < 0) x0 = 0;
		if (y0 < 0) y0 = 0;
		if (x1 > width) x1 = width;
		if (y1 > height) y1 = height;
		
		int rr = r * r;
		
		for (int yy = y0; yy < y1; yy++) {
			int yd = yy - y;
			yd = yd * yd;
			for (int xx = x0; xx < x1; xx++) {
				int pix = xx + yy * width;
				int xd = xx - x;
				int dist = xd * xd + yd;
				if (dist <= rr) {
					int br = dist * 150 / (rr);
					pixels[pix] = merge(pixels[pix], col, br);
				}
			}
		}
		renderGlow(x, y, r*2+2, col);
	}
	
	public void renderGlow(int x, int y, int r, int col) {
		int x0 = x - r;
		int x1 = x + r;
		int y0 = y - r;
		int y1 = y + r;

		if (x0 < 0) x0 = 0;
		if (y0 < 0) y0 = 0;
		if (x1 > width) x1 = width;
		if (y1 > height) y1 = height;
		
		int rr = r * r;
		
		for (int yy = y0; yy < y1; yy++) {
			int yd = yy - y;
			yd = yd * yd;
			for (int xx = x0; xx < x1; xx++) {
				int pix = xx + yy * width;
				int xd = xx - x;
				int dist = xd * xd + yd;
				if (dist >= (r/3)*(r/3) && dist <= rr) {
					int br = 10 - dist * 10 / (rr);
					pixels[pix] = merge(col, pixels[pix], br);
				}
			}
		}
	}
	
	public void renderFaint(int x, int y, int r, int col) {
		int x0 = x - r;
		int x1 = x + r;
		int y0 = y - r;
		int y1 = y + r;

		if (x0 < 0) x0 = 0;
		if (y0 < 0) y0 = 0;
		if (x1 > width) x1 = width;
		if (y1 > height) y1 = height;
		
		int rr = r * r;
		
		for (int yy = y0; yy < y1; yy++) {
			int yd = yy - y;
			yd = yd * yd;
			for (int xx = x0; xx < x1; xx++) {
				int pix = xx + yy * width;
				int xd = xx - x;
				int dist = xd * xd + yd;
				if (dist <= rr) {
					int br = 3 - dist * 3 / (rr);
					pixels[pix] = merge(col, pixels[pix], br);
				}
			}
		}
	}

	public void draw(Bitmap image, int xPos, int yPos, int xo, int yo, int w , int h){
		for(int y = 0; y < h; y++){
			int yPix = y+yPos;
			if(yPix < 0 || yPix >= height)continue;
			for(int x = 0; x < w; x++){
				int xPix = x+xPos;
				if(xPix < 0 || xPix >= width)continue;
				
				int src = image.pixels[(x+xo) + (y+yo) * image.width];
				if(src != -2)pixels[xPix + yPix * width] = src;
			}
		}
	}

	public void drawHighlighted(Bitmap image, int xPos, int yPos, int xo, int yo, int w , int h){
		for(int y = 0; y < h; y++){
			int yPix = y+yPos;
			if(yPix < 0 || yPix >= height)continue;
			for(int x = 0; x < w; x++){
				int xPix = x+xPos;
				if(xPix < 0 || xPix >= width)continue;
				
				int src = image.pixels[(x+xo) + (y+yo) * image.width];
				if(src > 4)pixels[xPix + yPix * width] = 0xbcbcbc;
			}
		}
	}
	
	public void drawDithered(Bitmap image, int xPos, int yPos, int xo, int yo, int w , int h, int dithering){
		for(int y = 0; y < h; y++){
			int yPix = y+yPos;
			if(yPix < 0 || yPix >= height)continue;
			for(int x = 0; x < w; x++){
				int xPix = x+xPos;
				if(xPix < 0 || xPix >= width)continue;
				
				int src = image.pixels[(x+xo) + (y+yo) * image.width];
				if(src != -2 && dithers.get(dithering).matrix[(xPix%3) + (yPix%3) * 3] == 0)pixels[xPix + yPix * width] = src;
			}
		}
	}
	
	public void drawFlipped(Bitmap image, int xPos, int yPos, int xo, int yo, int w , int h, int trans){
		for(int y = 0; y < h; y++){
			int yPix = y+yPos;
			if(yPix < 0 || yPix >= height)continue;
			for(int x = 0; x < w; x++){
				int xPix = x+xPos;
				if(xPix < 0 || xPix >= width)continue;
				
				int src = image.pixels[(w-x-1+xo) + (y+yo) * image.width];
				int col = src;
				if(trans != 100)col = merge(src, pixels[xPix + yPix * width], trans);
				if(src != -2)pixels[xPix + yPix * width] = col;
			}
		}
	}
	
	public void drawScaled(Bitmap image, int xPos, int yPos, int xo, int yo, int w , int h, double xScale, double yScale){
		w *= xScale;
		h *= yScale;
		for(int y = 0; y < h; y++){
			int yPix = y+yPos;
			if(yPix < 0 || yPix >= height)continue;
			for(int x = 0; x < w; x++){
				int xPix = x+xPos;
				if(xPix < 0 || xPix >= width)continue;

				int xDraw = (int) (x/xScale+xo);
				int yDraw = (int) (y/yScale+yo);
				int src = image.pixels[(int) (xDraw + yDraw * image.width)];
				if(src != -2)pixels[xPix + yPix * width] = src;
			}
		}
	}
	
	public void drawScaledAndCentered(Bitmap image, int xPos, int yPos, int xo, int yo, int w , int h, double xScale, double yScale){
		w *= xScale;
		h *= yScale;
		for(int y = 0; y < h; y++){
			int yPix = y+yPos-h/2;
			if(yPix < 0 || yPix >= height)continue;
			for(int x = 0; x < w; x++){
				int xPix = x+xPos-w/2;
				if(xPix < 0 || xPix >= width)continue;

				int xDraw = (int) (x/xScale+xo);
				int yDraw = (int) (y/yScale+yo);
				int src = image.pixels[(int) (xDraw + yDraw * image.width)];
				if(src != -2)pixels[xPix + yPix * width] = src;
			}
		}
	}
	
	public void drawScaledAndCentered(Bitmap image, int xPos, int yPos, int xo, int yo, int w , int h, double xScale, double yScale, int col){
		w *= xScale;
		h *= yScale;
		for(int y = 0; y < h; y++){
			int yPix = y+yPos-h/2;
			if(yPix < 0 || yPix >= height)continue;
			for(int x = 0; x < w; x++){
				int xPix = x+xPos-w/2;
				if(xPix < 0 || xPix >= width)continue;

				int xDraw = (int) (x/xScale+xo);
				int yDraw = (int) (y/yScale+yo);
				int src = image.pixels[(int) (xDraw + yDraw * image.width)];
				if(src != -2)pixels[xPix + yPix * width] = col;
			}
		}
	}
	
	public void drawRotated(Bitmap bitmap, int xOffs, int yOffs, int xo, int yo, int w, int h, int rotation) {
        final double radians = Math.toRadians(rotation);
        final double cos = Math.cos(radians);
        final double sin = Math.sin(radians);
        for(int y = 0; y < w; y++){
            int yPix = y + yo;
            for(int x = 0; x < h; x++){
                int xPix = x + xo;

                int centerX = w / 2;
                int centerY = h / 2;
                int m = x - centerX;
                int n = y - centerY;
                int j = ((int) (m * cos + n * sin)) + centerX;
                int k = ((int) (n * cos - m * sin)) + centerY;

                int src = bitmap.pixels[xPix + yPix * bitmap.width];

                int xDraw = xOffs+j;
                int yDraw = yOffs+k;

                if(xDraw < 0 || xDraw >= width-1 || yDraw < 0 || yDraw >= height-1)continue;
                if(src != -2){
                	pixels[xDraw + yDraw * width] = src;
                	if(x < w-1)pixels[xDraw + 1 + yDraw * width] = src;
                	if(y < h-1)pixels[xDraw + (yDraw + 1) * width] = src;
                }

            }
        }
    }
	
	public void drawRotated(Bitmap bitmap, int xOffs, int yOffs, int xo, int yo, int w, int h, int rotation, int xC, int yC) {
        final double radians = Math.toRadians(rotation);
        final double cos = Math.cos(radians);
        final double sin = Math.sin(radians);
        for(int y = 0; y < w; y++){
            int yPix = y + yo;
            for(int x = 0; x < h; x++){
                int xPix = x + xo;

                int centerX = xC;
                int centerY = yC;
                int m = x - centerX;
                int n = y - centerY;
                int j = ((int) (m * cos + n * sin)) + centerX;
                int k = ((int) (n * cos - m * sin)) + centerY;

                int src = bitmap.pixels[xPix + yPix * bitmap.width];

                int xDraw = xOffs+j;
                int yDraw = yOffs+k;

                if(xDraw < 0 || xDraw >= width-1 || yDraw < 0 || yDraw >= height-1)continue;
                if(src != -2){
                	pixels[xDraw + yDraw * width] = src;
                	if(x < w-1)pixels[xDraw + 1 + yDraw * width] = src;
                	if(y < h-1)pixels[xDraw + (yDraw + 1) * width] = src;
                }

            }
        }
    }
	
	public void drawRotatedTransEffected(Bitmap bitmap, int xOffs, int yOffs, int xo, int yo, int w, int h, int rotation, int amount) {
        final double radians = Math.toRadians(rotation);
        final double cos = Math.cos(radians)*1.25;
        final double sin = Math.sin(radians)*1.25;
        for(int y = 0; y < w; y++){
            int yPix = y + yo;
            for(int x = 0; x < h; x++){
                int xPix = x + xo;

                int centerX = w / 2;
                int centerY = h / 2;
                int m = x - centerX;
                int n = y - centerY;
                int j = ((int) (m * cos + n * sin)) + centerX;
                int k = ((int) (n * cos - m * sin)) + centerY;

                int src = bitmap.pixels[xPix + yPix * bitmap.width];
                if(src != -2){
                	src = 0xbcff00;
                }
                
                int xDraw = xOffs+j;
                int yDraw = yOffs+k;

                if(xDraw < 0 || xDraw >= width-1 || yDraw < 0 || yDraw >= height-1)continue;
                if(src != -2){
                	pixels[xDraw + yDraw * width] = merge(src, pixels[xDraw + yDraw * width], amount);
                	if(x < w-1)pixels[xDraw + 1 + yDraw * width] = merge(src, pixels[xDraw + 1 + yDraw * width], amount);
                	else if(y < h-1)pixels[xDraw + (yDraw + 1) * width] = merge(src, pixels[xDraw + (yDraw + 1) * width], amount);
                }

            }
        }
    }
	
	public void draw(String string, int x, int y, int col, double scale) {
        for (int i = 0; i < string.length(); i++) {
            int ch = chars.indexOf(string.charAt(i));
            if (ch < 0) continue;

            int xx = ch % 26;
            int yy = ch / 26;
            drawScaledString(font, x +(int)(i*6*scale), y, xx * 6, yy * 8, 5, 7, col, scale);
        }
    }
	
	public void drawScaledString(Bitmap image, int xPos, int yPos, int xo, int yo, int w, int h, int col, double scale) {
		w *= scale;
		h *= scale;
		for(int y = 0; y < h; y++){
			int yPix = y+yPos;
			if(yPix < 0 || yPix >= height)continue;
			for(int x = 0; x < w; x++){
				int xPix = x+xPos;
				if(xPix < 0 || xPix >= width)continue;

				int xDraw = (int) (x/scale+xo);
				int yDraw = (int) (y/scale+yo);
				int src = image.pixels[(int) (xDraw + yDraw * image.width)];
				if(src != 0xffffffff && src != -2)pixels[xPix + yPix *width] = col;
			}
		}
    }
	
	public void drawLight(String string, int x, int y, int col, double scale) {
		string = string.toUpperCase();
        for (int i = 0; i < string.length(); i++) {
            int ch = chars.indexOf(string.charAt(i));
            if (ch < 0) continue;

            int xx = ch % 26;
            int yy = ch / 26;
            drawScaledStringLight(font, x +(int)(i*6*scale), y, xx * 6, yy * 8, 5, 7, col, scale);
        }
    }
	
	public void drawScaledStringLight(Bitmap image, int xPos, int yPos, int xo, int yo, int w, int h, int col, double scale) {
		w *= scale;
		h *= scale;
		for(int y = 0; y < h; y++){
			int yPix = y+yPos;
			if(yPix < 0 || yPix >= height)continue;
			for(int x = 0; x < w; x++){
				int xPix = x+xPos;
				if(xPix < 0 || xPix >= width)continue;

				int xDraw = (int) (x/scale+xo);
				int yDraw = (int) (y/scale+yo);
				int src = image.pixels[(int) (xDraw + yDraw * image.width)];
				if(src != 0xffffffff && src != -2)renderLight(xPix, yPix, 0, col);
			}
		}
    }
	
	
	public void drawTrans(Bitmap image, int xPos, int yPos, int xo, int yo, int w , int h, int amount){
		for(int y = 0; y < h; y++){
			int yPix = y+yPos;
			if(yPix < 0 || yPix >= height)continue;
			for(int x = 0; x < w; x++){
				int xPix = x+xPos;
				if(xPix < 0 || xPix >= width)continue;

				int src = image.pixels[(x+xo) + (y+yo) * image.width];
				int merged = merge(src, pixels[xPix + yPix * width], amount);
				if(src != -2)pixels[xPix + yPix * width] = merged;
			}
		}
	}
	
	public void fill(int xPos, int yPos, int w, int h, int col){
		for(int y = 0; y < h; y++){
			int yPix = y+yPos;
			if(yPix < 0 || yPix >= height)continue;
			
			for(int x = 0; x < w; x++){
				int xPix = x+xPos;
				if(xPix < 0 || xPix >= width)continue;
				pixels[xPix + yPix * width] = col;
			}
		}
		
	}
	
	public void fill(int xPos, int yPos, int w, int h, int col, int amount){
		for(int y = 0; y < h; y++){
			int yPix = y+yPos;
			if(yPix < 0 || yPix >= height)continue;
			
			for(int x = 0; x < w; x++){
				int xPix = x+xPos;
				if(xPix < 0 || xPix >= width)continue;
				int merged = col;
				if(amount != 100 && pixels[xPix + yPix * width] != col){
					merged = merge(col, pixels[xPix + yPix * width], amount);
				}
				pixels[xPix + yPix * width] = merged;
			}
		}
		
	}
	
	public int merge(int color, int color2, int amount) {

		if(amount > 100)amount = 100;
		if(amount < 0)amount = 0;

        int r = (color >> 16) & 0xff;
        int g = (color >> 8) & 0xff;
        int b = (color) & 0xff;

        int r2 = (color2 >> 16) & 0xff;
        int g2 = (color2 >> 8) & 0xff;
        int b2 = (color2) & 0xff;

        int a = 100 - amount;

        int tr = (r * amount + r2 * a) / 100;
        int tg = (g * amount + g2 * a) / 100;
        int tb = (b * amount + b2 * a) / 100;

        return tr << 16 | tg << 8 | tb;
    }

	public void blur(int xPos, int yPos, int w, int h) {
		for(int y = 0; y < h; y++){
			int yPix = y+yPos;
			if(yPix < 0 || yPix >= height-1)continue;
			if(y% 3 == 0)continue;
			for(int x = 0; x < w; x++){
				int xPix = x+xPos;
				if(xPix < 0 || xPix >= width-3)continue;
				int merged = merge(pixels[xPix + 1 + yPix * width], pixels[xPix + yPix * width], 75);
				merged = merge(pixels[xPix + 2 + yPix * width], merged, 75);
				merged = merge(pixels[xPix + (yPix + 1) * width], merged, 75);
				pixels[xPix + yPix * width] = merged;
			}
		}
		
	}
}

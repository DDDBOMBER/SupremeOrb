package com.dddbomber.proton.assets;

public class Lightmap extends Bitmap{
	
	public Lightmap(int width, int height) {
		super(width, height);
	}
	
	public void drawFlipped(Bitmap image, int xPos, int yPos, int xo, int yo, int w , int h, int col){
		for(int y = 0; y < h; y++){
			int yPix = y+yPos;
			if(yPix < 0 || yPix >= height)continue;
			for(int x = 0; x < w; x++){
				int xPix = x+xPos;
				if(xPix < 0 || xPix >= width)continue;
				
				int src = image.pixels[(x+xo) + (h-y-1+yo) * image.width];
				if(src != -2 && col > pixels[xPix + yPix * width] && pixels[xPix + yPix * width] > -1)pixels[xPix + yPix * width] = col;
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
				if(src > pixels[xPix + yPix * width] && pixels[xPix + yPix * width] > -1)pixels[xPix + yPix * width] = src;
			}
		}
	}
	
	public void drawScaled(Bitmap image, int xPos, int yPos, int xo, int yo, int w , int h, double xScale, double yScale, int i){
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
				if(i > pixels[xPix + yPix * width] && src != -2 && pixels[xPix + yPix * width] > -1)pixels[xPix + yPix * width] = i;
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
				if(src > pixels[xPix + yPix * width] && pixels[xPix + yPix * width] > -1)pixels[xPix + yPix * width] = src;
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
				if(src > pixels[xPix + yPix * width] && pixels[xPix + yPix * width] > -1)pixels[xPix + yPix * width] = col;
			}
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
				if(src != 0xffffffff && col > pixels[xPix + yPix * width] && pixels[xPix + yPix * width] > -1)pixels[xPix + yPix * width] = col;
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
				if(col > pixels[xPix + yPix * width] && pixels[xPix + yPix * width] > -1)pixels[xPix + yPix * width] = col;
			}
		}
	}
	
	public void fillAlways(int xPos, int yPos, int w, int h, int col){
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
}

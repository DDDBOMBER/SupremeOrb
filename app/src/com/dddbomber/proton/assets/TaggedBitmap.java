package com.dddbomber.proton.assets;

import java.util.ArrayList;

public class TaggedBitmap extends Bitmap{
	
	public static ArrayList<TaggedBitmap> tagged = new ArrayList<TaggedBitmap>();
	public String tag;
	
	public TaggedBitmap(int width, int height, String tag){
		super(width, height);
		this.tag = tag;
		if(!exists(tag)){
			tagged.add(this);
		}else{
			try{
				throw new RuntimeException("Image Tag Already Exists, Is The Game Modified?");
			}catch(Exception e){
				
			}
		}
	}
	
	public static TaggedBitmap get(String tag){
		for(TaggedBitmap t : tagged){
			if(t.tag.equals(tag)){
				return t;
			}
		}
		try{
			throw new RuntimeException("Image Not Found: " +tag);
		}catch(Exception e){
			
		}
		return null;
	}
	
	public static boolean exists(String tag){
		for(TaggedBitmap t : tagged){
			if(t.tag.equals(tag)){
				return true;
			}
		}
		return false;
	}
	
}

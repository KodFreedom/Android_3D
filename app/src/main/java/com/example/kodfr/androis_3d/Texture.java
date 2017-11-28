package com.example.kodfr.androis_3d;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.khronos.opengles.GL10;


// テクスチャ
public class Texture
{
	Context context;		// コンテキスト
	int textureIndex;		// テクスチャ番号
	
	
	// コンストラクタ
	Texture( Context context )
	{
		this.context = context;		
	}
	
	
	// テクスチャ読込
	public void load( String filename, GL10 gl )
	{

		try
		{
			// assetsから画像ファイル読込
			InputStream stream = context.getResources().getAssets().open( filename );
			Bitmap bitmap = BitmapFactory.decodeStream( stream );
			
			// テクスチャ生成
            int[] texture = new int[1];
            gl.glGenTextures( 1, texture, 0 );
            textureIndex = texture[0];
            
            gl.glBindTexture( GL10.GL_TEXTURE_2D, textureIndex );
             
            // フィルタ設定
            gl.glTexParameterf( GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR );
            gl.glTexParameterf( GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR );
            
            // ラップモード設定
            gl.glTexParameterf( GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT );
            gl.glTexParameterf( GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT );
            
			GLUtils.texImage2D( GL10.GL_TEXTURE_2D, 0, bitmap, 0 );
			
			bitmap.recycle();
		}
		catch( IOException e1 )
		{
			e1.printStackTrace();
		}			
	}
	
	
	// テクスチャ設定
	public void set( GL10 gl )
	{
        gl.glEnable( GL10.GL_TEXTURE_2D );            
		gl.glBindTexture( GL10.GL_TEXTURE_2D, textureIndex );    
	}

}

package com.example.kodfr.androis_3d;

import android.content.Context;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

// フィールド
public class Field
{
	Context context;			// コンテキスト
	Texture texture;			// テクスチャ
	ByteBuffer vertexBuffer;	// 頂点座標バッファ
	ByteBuffer texCoordBuffer;	// テクスチャ座標バッファ
	float offset;				// テクスチャ座標オフセット
	
	
	// コンストラクタ
	Field( Context context )
	{
		this.context = context;
	}	

	// 初期化
	public void init( GL10 gl )
	{
		
		// 頂点座標配列
		float vertex[] =
		{
			40.0f, 0.0f , -1000.0f,
			-40.0f, 0.0f , -1000.0f,
			40.0f, 0.0f , 50.0f,
			-40.0f, 0.0f , 50.0f,
		};

		// 頂点座標バッファ生成
		vertexBuffer = ByteBuffer.allocateDirect( vertex.length * 4 );
		vertexBuffer.order( ByteOrder.nativeOrder() );
	    vertexBuffer.asFloatBuffer().put( vertex );
	    vertexBuffer.position(0);
	    
	    
	    // テクスチャ座標配列
		float texCoord[] =
		{
			1.0f, 0.0f ,
			0.0f, 0.0f,
			1.0f, 10.0f,
			0.0f, 10.0f,
		};

		// テクスチャ座標バッファ
		texCoordBuffer = ByteBuffer.allocateDirect( texCoord.length * 4 );
		texCoordBuffer.order( ByteOrder.nativeOrder() );
		texCoordBuffer.asFloatBuffer().put( texCoord );
		texCoordBuffer.position(0);
		
		
		// テクスチャ読込
		texture = new Texture( context );
		texture.load( "road.png", gl );
		gl.glTexParameterf(GL10.GL_TEXTURE_2D,
				GL10.GL_TEXTURE_WRAP_S,
				GL10.GL_REPEAT);
	}
	
	
	// 更新
	public void update()
	{
		offset -= 0.1f;
		
		float texCoord[] =
		{
			1.0f, 0.0f + offset,
			0.0f, 0.0f + offset,
			1.0f, 10.0f + offset,
			0.0f, 10.0f + offset,
		};

		texCoordBuffer = ByteBuffer.allocateDirect( texCoord.length * 4 );
		texCoordBuffer.order( ByteOrder.nativeOrder() );
		texCoordBuffer.asFloatBuffer().put( texCoord );
		texCoordBuffer.position(0);	
	}

	
	// 描画
	public void draw( GL10 gl )
	{
		// マトリクス設定
		gl.glMatrixMode( GL10.GL_MODELVIEW );
		gl.glPushMatrix();

		// 頂点座標バッファ設定
		gl.glEnableClientState( GL10.GL_VERTEX_ARRAY );
		gl.glVertexPointer( 3, GL10.GL_FLOAT, 0, vertexBuffer );
		
		// テクスチャ座標バッファ設定
		gl.glEnableClientState( GL11.GL_TEXTURE_COORD_ARRAY );
		gl.glTexCoordPointer( 2, GL11.GL_FLOAT, 0, texCoordBuffer );

		// テクスチャ設定
		texture.set( gl );
		
		// ポリゴン描画
		gl.glDrawArrays( GL10.GL_TRIANGLE_STRIP, 0, 4 );
		
		
		gl.glDisableClientState( GL10.GL_VERTEX_ARRAY );
		gl.glDisableClientState( GL11.GL_TEXTURE_COORD_ARRAY );
		
		
		// マトリクス復帰
	    gl.glMatrixMode( GL10.GL_MODELVIEW );
	    gl.glPopMatrix();	    
	}

}

package com.example.kodfr.androis_3d;

import android.content.Context;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.microedition.khronos.opengles.GL10;

// フィールド
public class Field
{
	Context context;			// コンテキスト
	ByteBuffer vertexBuffer;	// 頂点座標バッファ
	
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

	}
	
	
	// 更新
	public void update()
	{
	
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

		// ポリゴン描画
		gl.glDrawArrays( GL10.GL_TRIANGLE_STRIP, 0, 4 );
		
		gl.glDisableClientState( GL10.GL_VERTEX_ARRAY );

		// マトリクス復帰
	    gl.glMatrixMode( GL10.GL_MODELVIEW );
	    gl.glPopMatrix();	    
	}

}

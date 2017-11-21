package com.example.kodfr.androis_3d;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;

// レンダラー
public class GLRenderer implements Renderer
{
	Context context;	// コンテキスト
	
	Camera camera;		// カメラ
	Field field;		//　フィールド
	
	// コンストラクタ
	public GLRenderer( Context context )
	{
		this.context = context;
		
		// ゲームオブジェクト生成
		camera = new Camera();
		field = new Field( context );		
	}

	// サーフェイス生成イベント
	@Override
	public void onSurfaceCreated( GL10 gl, EGLConfig config )
	{
		// クリア色設定
	    gl.glClearColor( 0.0f, 0.5f, 0.0f, 1.0f );
	    
	    
	    // ゲームオブジェクト初期化
	    field.init( gl );
	}
	
	
	// サーフェイス変更イベント
	@Override
	public void onSurfaceChanged( GL10 gl, int width, int height )
	{
		// ビューポートサイズ設定
		gl.glViewport( 0, 0, width, height );
		
		// アスペクト比設定
		camera.setAspect( (float)width / height );
	}	
	
	
	// 描画イベント
	@Override
	public void onDrawFrame( GL10 gl )
	{	
		// 背景クリア
	    gl.glClear( GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT );
	    
	    
	    // ゲームオブジェクト描画
		camera.set( gl );
		field.draw( gl );
	}

	


	


}

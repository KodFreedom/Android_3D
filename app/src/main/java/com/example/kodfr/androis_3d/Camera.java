package com.example.kodfr.androis_3d;

import android.opengl.GLU;

import javax.microedition.khronos.opengles.GL10;

// カメラ
public class Camera
{
	float aspect;		// アスペクト比
	
	// アスペクト比設定
	void setAspect( float aspect )
	{
		this.aspect = aspect;
	}
	
	// カメラ設定
	void set( GL10 gl )
	{
		// プロジェクションマトリクス設定
		gl.glMatrixMode( GL10.GL_PROJECTION );
		gl.glLoadIdentity();
		GLU.gluPerspective( gl, 45.0f, aspect, 10.0f, 1000.0f );
		
		// ビューマトリクス設定
		gl.glMatrixMode( GL10.GL_MODELVIEW );
		gl.glLoadIdentity();
		GLU.gluLookAt( gl, 0.0f, 90.0f, 120.0f,		// 位置
							0.0f, 0.0f, -80.0f,		// 注視点
							0.0f, 1.0f, 0.0f );		// 上方向
	}
}

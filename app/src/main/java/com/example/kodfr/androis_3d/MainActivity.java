package com.example.kodfr.androis_3d;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.app.Activity;

//メインアクティビティ
public class MainActivity extends Activity
{

	GLSurfaceView glSurfaceView;
	GLRenderer glRenderer;

	// 初期化処理
	@Override
	public void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		
		// フルスクリーン設定
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		Window window = getWindow();
		window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		// サーフェイスビュー・レンダラー生成
		glSurfaceView = new GLSurfaceView( this );
		glRenderer = new GLRenderer( this );
		glSurfaceView.setRenderer( glRenderer );
	
		setContentView( glSurfaceView );
	}

	// ポーズイベント
	@Override
	protected void onPause()
	{
		super.onPause();
		glSurfaceView.onPause();
	}

	// レジュームイベント
	@Override
	protected void onResume()
	{
		super.onResume();
		glSurfaceView.onResume();
	}

}

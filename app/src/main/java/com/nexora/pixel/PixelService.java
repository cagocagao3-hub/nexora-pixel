package com.nexora.pixel;

import android.app.Service;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

public class PixelService extends Service {
    private WindowManager wm;
    private View overlayView;

    @Override
    public void onCreate() {
        super.onCreate();
        wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        
        overlayView = new View(this) {
            private final Paint paint = new Paint();
            @Override
            protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);
                
                // Deixa a tela com um tom escuro e chapado para destacar os inimigos e aliviar a GPU
                paint.setColor(Color.argb(70, 0, 0, 0)); // 70 de opacidade
                canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
            }
        };

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE 
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                PixelFormat.TRANSLUCENT);
        
        params.gravity = Gravity.TOP | Gravity.START;
        wm.addView(overlayView, params);
    }

    @Override
    public IBinder onBind(Intent intent) { return null; }
}

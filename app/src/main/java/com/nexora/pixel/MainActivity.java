package com.nexora.pixel;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundColor(Color.parseColor("#0a0a0c"));
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(50, 50, 50, 50);

        TextView title = new TextView(this);
        title.setText("NEXORA PIXEL");
        title.setTextColor(Color.parseColor("#00FF00"));
        title.setTextSize(30f);
        title.setGravity(Gravity.CENTER);
        layout.addView(title);

        Button btnOverlay = new Button(this);
        btnOverlay.setText("1. Dar Permissão de Overlay");
        btnOverlay.setOnClickListener(v -> 
            startActivity(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()))));

        Button btnStart = new Button(this);
        btnStart.setText("2. ATIVAR MODO PIXEL");
        btnStart.setOnClickListener(v -> {
            if (Settings.canDrawOverlays(this)) {
                startService(new Intent(this, PixelService.class));
                Toast.makeText(this, "Modo Pixel Ativado!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Dê a permissão de Overlay primeiro!", Toast.LENGTH_SHORT).show();
            }
        });

        layout.addView(btnOverlay);
        layout.addView(btnStart);
        setContentView(layout);
    }
}

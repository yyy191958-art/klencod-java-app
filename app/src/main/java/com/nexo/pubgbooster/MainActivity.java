
package com.nexo.pubgbooster;

import android.app.*;
import android.os.*;
import android.content.*;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {

    LinearLayout main;
    int green = Color.rgb(166,255,117);
    int dark = Color.rgb(8,13,10);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showSubscription();
    }

    void showSubscription() {
        new AlertDialog.Builder(this)
            .setTitle("PUBG BOOSTER PRO")
            .setMessage("𓆩 𝑵𝑬𝑿𝑶 𓆪\n\nتابع قنوات المطور للوصول إلى التطبيق.")
            .setPositiveButton("قناة التليغرام", (d,w) ->
                openLink("https://t.me/az_4oi"))
            .setNeutralButton("قناة التيك توك", (d,w) ->
                openLink("https://www.tiktok.com/@wdd4k?_r=1&_t=ZS-9BKGZiBpvin"))
            .setNegativeButton("متابعة", (d,w) -> showHome())
            .setCancelable(false)
            .show();
    }

    void openLink(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(i);
    }

    void showHome() {
        main = new LinearLayout(this);
        main.setOrientation(LinearLayout.VERTICAL);
        main.setPadding(22,25,22,20);
        main.setBackgroundColor(dark);

        TextView title = new TextView(this);
        title.setText("PUBG BOOSTER PRO");
        title.setTextColor(green);
        title.setTextSize(25);
        title.setTypeface(null, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        main.addView(title);

        TextView developer = new TextView(this);
        developer.setText("DEVELOPED BY 𓆩 𝑵𝑬𝑿𝑶 𓆪");
        developer.setTextColor(Color.WHITE);
        developer.setGravity(Gravity.CENTER);
        developer.setPadding(0,10,0,25);
        main.addView(developer);

        addButton("🧹 تنظيف الجهاز", () ->
            showMessage("فحص مساحة التطبيق والملفات المؤقتة المتاحة"));

        addButton("🎮 تشغيل ببجي", this::launchPUBG);

        addButton("⚡ أداء الجهاز", () ->
            showMessage("RAM: " +
            (Runtime.getRuntime().maxMemory()/1024/1024) +
            " MB كحد ذاكرة Java للتطبيق"));

        addButton("🎯 إعدادات ببجي", () ->
            showMessage("استخدم إعدادات الرسومات ومعدل الإطارات المدعومة داخل اللعبة"));

        addButton("📰 أخبار ببجي", () ->
            openLink("https://www.pubgmobile.com/"));

        addButton("💎 حاسبة UC", this::showCalculator);

        addButton("📢 قناة المطور", () ->
            openLink("https://t.me/az_4oi"));

        setContentView(main);
    }

    void addButton(String text, Runnable action) {
        Button b = new Button(this);
        b.setText(text);
        b.setTextColor(green);
        b.setTextSize(16);
        b.setAllCaps(false);
        b.setBackgroundColor(Color.rgb(23,35,26));

        LinearLayout.LayoutParams p =
            new LinearLayout.LayoutParams(
                -1, 65);
        p.setMargins(0,7,0,7);

        main.addView(b,p);
        b.setOnClickListener(v -> action.run());
    }

    void launchPUBG() {
        Intent i = getPackageManager()
            .getLaunchIntentForPackage("com.tencent.ig");

        if (i != null) {
            startActivity(i);
        } else {
            showMessage("لم يتم العثور على نسخة PUBG Mobile العالمية.");
        }
    }

    void showCalculator() {
        EditText input = new EditText(this);
        input.setHint("أدخل عدد UC");
        input.setInputType(2);

        new AlertDialog.Builder(this)
            .setTitle("حاسبة UC")
            .setView(input)
            .setPositiveButton("احسب", (d,w) -> {
                try {
                    int uc = Integer.parseInt(
                        input.getText().toString());
                    showMessage("عدد UC: " + uc);
                } catch(Exception e) {
                    showMessage("أدخل رقماً صحيحاً");
                }
            })
            .setNegativeButton("إلغاء",null)
            .show();
    }

    void showMessage(String msg) {
        new AlertDialog.Builder(this)
            .setMessage(msg)
            .setPositiveButton("حسناً",null)
            .show();
    }
}

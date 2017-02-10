package com.fjz.androidlittlesamples.tts;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fjz.androidlittlesamples.R;

import java.util.Locale;

public class TTSDemo extends AppCompatActivity implements TextToSpeech.OnInitListener {


    private EditText edt;
    private Button speak;
    private TextToSpeech mTts;
    private static final int CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ttsdemo);
        checkTTS();
        initViewsAndEvents();
    }

    /**
     * 初始化控件及监听事件
     */
    private void initViewsAndEvents() {
        edt = (EditText) findViewById(R.id.edt);
        speak = (Button) findViewById(R.id.speak);
        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edtStr = edt.getText().toString().trim();
                if (!TextUtils.isEmpty(edtStr)) {
                    // 朗读我们输入的文字
                    mTts.speak(edtStr, TextToSpeech.QUEUE_ADD, null);
                }
            }
        });
    }

    /**
     * 检查TTS是否可以使用
     */
    private void checkTTS() {
        Intent in = new Intent();
        in.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(in, CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE) {
            switch (resultCode) {
                case TextToSpeech.Engine.CHECK_VOICE_DATA_PASS:
                    Toast.makeText(this, "恭喜您，TTS可用", Toast.LENGTH_SHORT).show();
                    mTts = new TextToSpeech(this, this);
                    break;
                case TextToSpeech.Engine.CHECK_VOICE_DATA_FAIL:
                    // 下载TTS对应的资源
                    Intent dataIntent = new Intent(
                            TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                    startActivity(dataIntent);
                    break;
            }
        }
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.ERROR) {
            // 提示安装所需要的数据
            installTTS();
        } else {
            // 完成TTS的初始化
            if (status == TextToSpeech.SUCCESS) {

                // 设置发音语言Locale.CHINA
                int result = mTts.setLanguage(Locale.ENGLISH);

                // 检查语言是否可用
                if (result == TextToSpeech.LANG_MISSING_DATA
                        || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    speak.setEnabled(false);
                    Toast.makeText(this, "语言功能不可用", Toast.LENGTH_SHORT).show();
                } else {
                    speak.setEnabled(true);
                }
            } else {
                Toast.makeText(this, "TTS初始化失败", Toast.LENGTH_SHORT).show();
            }
        }
    }


    /**
     * 安装语音相关资源包
     */
    private void installTTS() {
        AlertDialog.Builder alertInstall = new AlertDialog.Builder(this)
                .setTitle("缺少语音包")
                .setMessage("下载语音包")
                .setPositiveButton("去下载",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // 下载eyes-free的语音数据包
                                String ttsDataUrl = "http://eyes-free.googlecode.com/files/tts_3.1_market.apk";
                                Uri ttsDataUri = Uri.parse(ttsDataUrl);
                                Intent ttsIntent = new Intent(
                                        Intent.ACTION_VIEW, ttsDataUri);
                                startActivity(ttsIntent);
                            }
                        })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        alertInstall.create().show();
    }
}

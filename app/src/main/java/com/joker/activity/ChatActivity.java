package com.joker.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.joker.R;
import com.joker.adapter.ChatAdapter;
import com.joker.http.HttpGet;
import com.joker.model.ChatBeen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyue on 2016/5/24.
 */
public class ChatActivity extends AppCompatActivity implements View.OnClickListener {
//    private List<ChatBeen> chatBeens = new ArrayList<>();
    private ListView listView;
    private EditText editText;
    private ImageView mSendView;
    private ChatAdapter chatAdapter;
    private String result, input_context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);
        listView = (ListView) findViewById(R.id.my_chat_list);
        editText = (EditText) findViewById(R.id.input_edit);
        mSendView = (ImageView) findViewById(R.id.input_send);
        mSendView.setOnClickListener(this);
        chatAdapter = new ChatAdapter(this);
        listView.setAdapter(chatAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.input_send:
                input_context = editText.getText().toString().trim();
                if (TextUtils.isEmpty(input_context)) {
                    return;
                } else {
                    ChatBeen chatBeen = new ChatBeen(1, input_context, "",0);
                    refresh(chatBeen);
                    new Thread(load).start();
                }
                editText.setText("");
                break;
        }
    }

    private void refresh(ChatBeen b) {
        chatAdapter.notifys(b);
    }

    private Runnable load = new Runnable() {
        @Override
        public void run() {
            String url = "http://op.juhe.cn/robot/index?info="+input_context+"&key=b7c08fe5632e0e7a2f2349da8237ef1d";
            result = HttpGet.httpGet(url);
            handler.obtainMessage(1).sendToTarget();
        }
    };
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    refresh(ChatBeen.getObj(result));
                    break;
            }
        }
    };
}

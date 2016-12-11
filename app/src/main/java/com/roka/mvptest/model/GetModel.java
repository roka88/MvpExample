package com.roka.mvptest.model;

import android.os.Handler;
import android.os.Message;

import com.roka.mvptest.interactor.GetInteractor;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by roka on 2016. 12. 11..
 */

public class GetModel implements GetInteractor {
    private GetInteractor.Interact mInteractor;

    public GetModel(GetInteractor.Interact interactor) {
        this.mInteractor = interactor;
    }

    @Override
    public void getData(String address, String params) {

        StringBuilder dataBuilder = new StringBuilder();
        Message message = new Message();

        Runnable runnable = () -> {
            try {
                URL u = new URL(address + params);
                HttpURLConnection uc = (HttpURLConnection) u.openConnection();
                InputStream raw = uc.getInputStream();
                Reader reader = new InputStreamReader(new BufferedInputStream(raw));

                int c;
                while ((c = reader.read()) != -1) {
                    dataBuilder.append((char) c);
                }
                message.what = 0;
                message.obj = dataBuilder.toString();
                handler.sendMessage(message);
            } catch (Exception e) {
                message.what = 1;
                message.obj = e.toString();
                handler.sendMessage(message);
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what) {
                case 0:
                    mInteractor.successCallBackGetData((String)msg.obj);
                    break;
                case 1:
                    mInteractor.failCallBackGetData((String)msg.obj);
                    break;
                default:
                    break;
            }
        }
    };
}

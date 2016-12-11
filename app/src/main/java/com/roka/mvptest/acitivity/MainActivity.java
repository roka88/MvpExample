package com.roka.mvptest.acitivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.roka.mvptest.R;
import com.roka.mvptest.impl.DataNoneImpl;
import com.roka.mvptest.impl.DataParserImpl;
import com.roka.mvptest.presenter.DataNonePresent;
import com.roka.mvptest.presenter.DataParserPresent;
import com.roka.mvptest.presenter.PerentPresent;
import com.roka.rokamethodstream.RokaMethodStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, PerentPresent.View{

    private Button mData1Btn, mDate2Btn;
    private TextView mContentTv;



    private EditText mHttpAddressEt;

    private DataParserPresent mDataParserPresent;
    private DataNonePresent mDataNonePresent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _InitUi();
        mDataParserPresent = new DataParserImpl(this, this);
        mDataNonePresent = new DataNoneImpl(this, this);

        // TODO : 메서드를 스트림에 저장한다.
        RokaMethodStream.init().attach(mNoneDataFunc, "noneData").attach(mParsedDataFunc, "parsedData");
    }


    private void _InitUi() {
        mData1Btn = (Button)findViewById(R.id.activity_main_data1_btn);
        mData1Btn.setOnClickListener(this);
        mDate2Btn = (Button)findViewById(R.id.activity_main_data2_btn);
        mDate2Btn.setOnClickListener(this);
        mContentTv = (TextView) findViewById(R.id.activity_main_content_tv);
        mHttpAddressEt = (EditText) findViewById(R.id.activity_main_web_address_et);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main_data1_btn:
                mDataParserPresent.getParserData(mHttpAddressEt.getText().toString());
                break;
            case R.id.activity_main_data2_btn:
                mDataNonePresent.getNoneParserData(mHttpAddressEt.getText().toString());
                break;
            default:
                break;

        }
    }

    @Override
    public void setData(Object data, String key) {
        // TODO : 데이터를 넣고 키로 메서드를 실행시킨다.
        RokaMethodStream.init().run(data, key);
    }

    private RokaMethodStream.Func mNoneDataFunc = (Object obj) -> {
        mContentTv.setText((String)obj);
    };

    private RokaMethodStream.Func mParsedDataFunc = (Object obj) -> {
        mContentTv.setText((String)obj);
    };


    @Override
    protected void onDestroy() {
        // TODO : 메서드 스트림에서 메서드를 제거한다.
        RokaMethodStream.init().detach("noneData").detach("parsedData");
        super.onDestroy();

    }

}

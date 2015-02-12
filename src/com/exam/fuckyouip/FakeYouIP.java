package com.exam.fuckyouip;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class FakeYouIP extends Activity {

	private TextView fakeInfo; // TextView
	private FakeInfo fakeModel; // Model

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		bindViews();
		setViews();
	}

	private void setViews() {
		// 请求的地址
		 MTask task = new MTask();
		 task.execute();
	}

	private void bindViews() {
		fakeInfo = (TextView) findViewById(R.id.fake_info);
	}

	private class MTask extends AsyncTask<String, Void, Void> {
		@Override
		protected Void doInBackground(String... params) {
			String request = Constance.host + HttpUtil.getNetIp(Constance.iphost);
			
			String result = HttpUtil.sendRequest(request);
			
			fakeModel = HttpUtil.parseJSON(result);
			
			if (fakeModel != null) {
				mHandler.sendEmptyMessage(Constance.SUCCESS);
			} else {
				mHandler.sendEmptyMessage(Constance.FAILURE);
			}

			return null;
		}
	}
	
	private Handler mHandler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case Constance.SUCCESS:
				String result = fakeModel.getIp()+"\n\n" + 
								fakeModel.getCountry()+"\n\n" +
								fakeModel.getCarrier()+"\n\n" +
								fakeModel.getProvince()+"省 " + 
								fakeModel.getCity()+"市 " + 
								fakeModel.getDistrict()+"区/县\n\n";
				
				fakeInfo.setText(result);
				break;
			
			case Constance.FAILURE:
				fakeInfo.setText("无法连接互联网....");
			default:
				fakeInfo.setText("发生未知错误....");;
			}
		};
	};
}

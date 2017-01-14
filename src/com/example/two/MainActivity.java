package com.example.two;


import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button login;
	private CheckBox rememberPsdBox, autoLoginBox;
	private EditText name, psd;
	private TextView userInfo;
	SharedPreferences loginPreferences, accessPreferences;
	SharedPreferences.Editor loginEditor, accessEditor;
	String userName;
	String userPsd;
	boolean isSavePsd,isAutoLogin;


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		loginPreferences=getSharedPreferences("login",Context.MODE_PRIVATE);
		accessPreferences=getSharedPreferences("access",Context.MODE_WORLD_READABLE);
		int count=accessPreferences.getInt("count",1);
		Toast.makeText(MainActivity.this,"��ӭ�������ǵ�"+count+"�η��ʣ�",
				Toast.LENGTH_LONG).show();
		loginEditor=loginPreferences.edit();
		accessEditor=accessPreferences.edit();
		accessEditor.putInt("couont",++count);
		accessEditor.commit();
	    userName=loginPreferences.getString("name",null);
	    userPsd=loginPreferences.getString("psd",null);
	    isSavePsd=loginPreferences.getBoolean("isSavePsd",false);
	    isAutoLogin=loginPreferences.getBoolean("isAutoLogin",false);
	    if(isAutoLogin){
	    	this.setContentView(R.layout.activity_welcome);
	    	userInfo.setText("��ӭ����"+userName+",��¼�ɹ���");
	    }else{
	    	loadActivity();
	    	}
	    }
	public void loadActivity(){
		this.setContentView(R.layout.activity_main);
		login=(Button)findViewById(R.id.login);
		rememberPsdBox=(CheckBox)findViewById(R.id.rememberPsd);
		autoLoginBox=(CheckBox)findViewById(R.id.autoLogin);
		name=(EditText)findViewById(R.id.name);
		psd=(EditText)findViewById(R.id.psd);
		if(isSavePsd){
			psd.setText(userPsd);
			name.setText(userName);
			rememberPsdBox.setChecked(true);
			
		}
		login.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				loginEditor.putString("name",name.getText().toString());
				loginEditor.putString("psd",psd.getText().toString());
				loginEditor.putBoolean("isSavePsd",rememberPsdBox.isChecked());
				loginEditor.putBoolean("isAutoLogin",autoLoginBox.isChecked());
				loginEditor.commit();
				MainActivity.this.setContentView(R.layout.activity_welcome);
				userInfo=(TextView)findViewById(R.id.userInfo);
				userInfo.setText("��ӭ����"+name.getText().toString()+",��¼�ɹ���");
			}
			
		});
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_settings://����ע���˵�
			loginEditor.putBoolean("isAutoLogin", false);
			loginEditor.commit();
			onCreate(null);
			break;
		case R.id.exit://�����˳���ť
			this.finish();
			break;
		default:
			break;
		}
		return true;
	}

	
}

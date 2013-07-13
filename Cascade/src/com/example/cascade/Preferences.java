package com.example.cascade;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Preferences extends PreferenceActivity{

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//should extend PreferenceFragments but that is only
		//supported by API 11 and our version is 8
		addPreferencesFromResource(R.xml.preferences);
	}	
}

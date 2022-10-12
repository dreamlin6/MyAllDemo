/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.apis.app;

import com.example.android.apis.R;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * Demonstration of hiding and showing fragments.
 */
public class FragmentHideShow extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_hide_show);

        // The content view embeds two fragments; now retrieve them and attach
        // their "hide" button.
        FragmentManager fm = getFragmentManager();
        Fragment fragment1 = fm.findFragmentById(R.id.fragment1);
        addShowHideListener(R.id.frag1hide, fragment1);
        final Button button1 = (Button)findViewById(R.id.frag1hide);
        button1.setText(fragment1.isHidden() ? "Show" : "Hide");
        Fragment fragment2 = fm.findFragmentById(R.id.fragment2);
        addShowHideListener(R.id.frag2hide, fragment2);
        final Button button2 = (Button)findViewById(R.id.frag2hide);
        button2.setText(fragment2.isHidden() ? "Show" : "Hide");
    }

    void addShowHideListener(int buttonId, final Fragment fragment) {
        final Button button = (Button)findViewById(buttonId);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                //开启Fragment事务(添加，删除，替换Fragment操作)
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                //设置Fragment切换动画
                ft.setCustomAnimations(android.R.animator.fade_in,
                        android.R.animator.fade_out);
                if (fragment.isHidden()) {
                    Log.i("BUTTON","Show");
                    ft.show(fragment);
                    button.setText("Hide");
                } else {
                    Log.i("BUTTON","Hide");
                    ft.hide(fragment);
                    button.setText("Show");
                }
                ft.commit();//提交事务
            }
        });
    }

    public static class FirstFragment extends Fragment {
        TextView mTextView;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.labeled_text_edit, container, false);
            View tv = v.findViewById(R.id.msg);
            ((TextView)tv).setText("The fragment saves and restores this text.");

            // Retrieve the text editor, and restore the last saved state if needed.
            mTextView = (TextView)v.findViewById(R.id.saved);
            if (savedInstanceState != null) {
                mTextView.setText(savedInstanceState.getCharSequence("text"));
            }
            return v;
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);

            // Remember the current text, to restore if we later restart.
            outState.putCharSequence("text", mTextView.getText());
        }
    }

    public static class SecondFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.labeled_text_edit, container, false);
            View tv = v.findViewById(R.id.msg);
            ((TextView)tv).setText("The TextView saves and restores this text.");

            // Retrieve the text editor and tell it to save and restore its state.
            // Note that you will often set this in the layout XML, but since
            // we are sharing our layout with the other fragment we will customize
            // it here.
            ((TextView)v.findViewById(R.id.saved)).setSaveEnabled(true);
            return v;
        }
    }
}
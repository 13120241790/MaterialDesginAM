package com.material.am.dialogplus;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.material.am.R;

/*
* Copyright 2014 Orhan Obut

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
* */

/**
 * @author Orhan Obut
 */
public class ViewHolder implements Holder {

  private static final int INVALID = -1;

  private int backgroundColor;

  private ViewGroup headerContainer;
  private View headerView;

  private ViewGroup footerContainer;
  private View footerView;

  private View.OnKeyListener keyListener;

  private View contentView;
  private int viewResourceId = INVALID;

  public ViewHolder(int viewResourceId) {
    this.viewResourceId = viewResourceId;
  }

  public ViewHolder(View contentView) {
    this.contentView = contentView;
  }

  @Override
  public void addHeader(View view) {
    if (view == null) {
      return;
    }
    headerContainer.addView(view);
    headerView = view;
  }

  @Override
  public void addFooter(View view) {
    if (view == null) {
      return;
    }
    footerContainer.addView(view);
    footerView = view;
  }

  @Override
  public void setBackgroundColor(int colorResource) {
    this.backgroundColor = colorResource;
  }

  @Override
  public View getView(LayoutInflater inflater, ViewGroup parent) {
    View view = inflater.inflate(R.layout.dialog_view, parent, false);
    ViewGroup contentContainer = (ViewGroup) view.findViewById(R.id.view_container);
    contentContainer.setBackgroundColor(parent.getResources().getColor(getBackgroundColor()));
    contentContainer.setOnKeyListener(new View.OnKeyListener() {
      @Override
      public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyListener == null) {
          throw new NullPointerException("keyListener should not be null");
        }
        return keyListener.onKey(v, keyCode, event);
      }
    });
    addContent(inflater, parent, contentContainer);
    headerContainer = (ViewGroup) view.findViewById(R.id.header_container);
    footerContainer = (ViewGroup) view.findViewById(R.id.footer_container);
    return view;
  }

  private void addContent(LayoutInflater inflater, ViewGroup parent, ViewGroup container) {
    if (viewResourceId != INVALID) {
      contentView = inflater.inflate(viewResourceId, parent, false);
    } else {
      ViewGroup parentView = (ViewGroup) contentView.getParent();
      if (parentView != null) {
        parentView.removeView(contentView);
      }
    }

    container.addView(contentView);
  }

  @Override
  public void setOnKeyListener(View.OnKeyListener keyListener) {
    this.keyListener = keyListener;
  }

  @Override
  public View getInflatedView() {
    return contentView;
  }

  @Override
  public View getHeader() {
    return headerView;
  }

  @Override
  public View getFooter() {
    return footerView;
  }

  private int getBackgroundColor() {
    if (backgroundColor == 0) {
      backgroundColor = android.R.color.white;
    }
    return backgroundColor;
  }
}

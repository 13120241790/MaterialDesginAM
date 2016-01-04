package com.materialdesignam.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.material.am.dialogplus.DialogPlus;
import com.material.am.dialogplus.GridHolder;
import com.material.am.dialogplus.Holder;
import com.material.am.dialogplus.ListHolder;
import com.material.am.dialogplus.OnBackPressListener;
import com.material.am.dialogplus.OnCancelListener;
import com.material.am.dialogplus.OnClickListener;
import com.material.am.dialogplus.OnDismissListener;
import com.material.am.dialogplus.OnItemClickListener;
import com.material.am.dialogplus.ViewHolder;
import com.materialdesignam.R;


/**
 * Created by AMing on 15/12/18.
 * Company RongCloud
 * https://github.com/orhanobut/dialogplus
 */
public class DialogPlusActivity extends Activity {

    private RadioGroup radioGroup;
    private CheckBox headerCheckBox;
    private CheckBox footerCheckBox;
    private CheckBox expandedCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_plus_activity);

        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        headerCheckBox = (CheckBox) findViewById(R.id.header_check_box);
        footerCheckBox = (CheckBox) findViewById(R.id.footer_check_box);
        expandedCheckBox = (CheckBox) findViewById(R.id.expanded_check_box);

        findViewById(R.id.button_bottom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(
                        radioGroup.getCheckedRadioButtonId(),
                        Gravity.BOTTOM,
                        headerCheckBox.isChecked(),
                        footerCheckBox.isChecked(),
                        expandedCheckBox.isChecked()
                );
            }
        });

        findViewById(R.id.button_center).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(
                        radioGroup.getCheckedRadioButtonId(),
                        Gravity.CENTER,
                        headerCheckBox.isChecked(),
                        footerCheckBox.isChecked(),
                        expandedCheckBox.isChecked()
                );
            }
        });

        findViewById(R.id.button_top).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(
                        radioGroup.getCheckedRadioButtonId(),
                        Gravity.TOP,
                        headerCheckBox.isChecked(),
                        footerCheckBox.isChecked(),
                        expandedCheckBox.isChecked()
                );
            }
        });

        View contentView = getLayoutInflater().inflate(R.layout.content2, null);

        DialogPlus dialogPlus = DialogPlus.newDialog(this)
                .setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new String[]{"asdfa"}))
                .setCancelable(true)
                .setOnDismissListener(new OnDismissListener() {
                    @Override
                    public void onDismiss(DialogPlus dialog) {

                    }
                })
                .setOnCancelListener(new OnCancelListener() {
                    @Override
                    public void onCancel(DialogPlus dialog) {

                    }
                })
                .setOnBackPressListener(new OnBackPressListener() {
                    @Override
                    public void onBackPressed(DialogPlus dialogPlus) {

                    }
                })
                .create();

        dialogPlus.show();
    }

    private void showDialog(int holderId, int gravity, boolean showHeader, boolean showFooter, boolean expanded) {
        boolean isGrid;
        Holder holder;
        switch (holderId) {
            case R.id.basic_holder_radio_button:
                holder = new ViewHolder(R.layout.content);
                isGrid = false;
                break;
            case R.id.list_holder_radio_button:
                holder = new ListHolder();
                isGrid = false;
                break;
            default:
                holder = new GridHolder(3);
                isGrid = true;
        }

        OnClickListener clickListener = new OnClickListener() {
            @Override
            public void onClick(DialogPlus dialog, View view) {
                //        switch (view.getId()) {
                //          case R.id.header_container:
                //            Toast.makeText(MainActivity.this, "Header clicked", Toast.LENGTH_LONG).show();
                //            break;
                //          case R.id.like_it_button:
                //            Toast.makeText(MainActivity.this, "We're glad that you like it", Toast.LENGTH_LONG).show();
                //            break;
                //          case R.id.love_it_button:
                //            Toast.makeText(MainActivity.this, "We're glad that you love it", Toast.LENGTH_LONG).show();
                //            break;
                //          case R.id.footer_confirm_button:
                //            Toast.makeText(MainActivity.this, "Confirm button clicked", Toast.LENGTH_LONG).show();
                //            break;
                //          case R.id.footer_close_button:
                //            Toast.makeText(MainActivity.this, "Close button clicked", Toast.LENGTH_LONG).show();
                //            break;
                //        }
                //        dialog.dismiss();
            }
        };

        OnItemClickListener itemClickListener = new OnItemClickListener() {
            @Override
            public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
                TextView textView = (TextView) view.findViewById(R.id.text_view);
                String clickedAppName = textView.getText().toString();
                //        dialog.dismiss();
                //        Toast.makeText(MainActivity.this, clickedAppName + " clicked", Toast.LENGTH_LONG).show();
            }
        };

        OnDismissListener dismissListener = new OnDismissListener() {
            @Override
            public void onDismiss(DialogPlus dialog) {
                //        Toast.makeText(MainActivity.this, "dismiss listener invoked!", Toast.LENGTH_SHORT).show();
            }
        };

        OnCancelListener cancelListener = new OnCancelListener() {
            @Override
            public void onCancel(DialogPlus dialog) {
                //        Toast.makeText(MainActivity.this, "cancel listener invoked!", Toast.LENGTH_SHORT).show();
            }
        };

        SimpleAdapter adapter = new SimpleAdapter(DialogPlusActivity.this, isGrid);
        if (showHeader && showFooter) {
            showCompleteDialog(holder, gravity, adapter, clickListener, itemClickListener, dismissListener, cancelListener,
                    expanded);
            return;
        }

        if (showHeader && !showFooter) {
            showNoFooterDialog(holder, gravity, adapter, clickListener, itemClickListener, dismissListener, cancelListener,
                    expanded);
            return;
        }

        if (!showHeader && showFooter) {
            showNoHeaderDialog(holder, gravity, adapter, clickListener, itemClickListener, dismissListener, cancelListener,
                    expanded);
            return;
        }

        showOnlyContentDialog(holder, gravity, adapter, itemClickListener, dismissListener, cancelListener, expanded);
    }

    private void showCompleteDialog(Holder holder, int gravity, BaseAdapter adapter,
                                    OnClickListener clickListener, OnItemClickListener itemClickListener,
                                    OnDismissListener dismissListener, OnCancelListener cancelListener,
                                    boolean expanded) {
        final DialogPlus dialog = DialogPlus.newDialog(this)
                .setContentHolder(holder)
                .setHeader(R.layout.header)
                .setFooter(R.layout.footer)
                .setCancelable(true)
                .setGravity(gravity)
                .setAdapter(adapter)
                .setOnClickListener(clickListener)
                .setOnItemClickListener(itemClickListener)
                .setOnDismissListener(dismissListener)
                .setExpanded(expanded)
                .setContentWidth(800)
                .setContentHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setOnCancelListener(cancelListener)
                        //                .setOutMostMargin(0, 100, 0, 0)
                .create();
        dialog.show();
    }

    private void showNoFooterDialog(Holder holder, int gravity, BaseAdapter adapter,
                                    OnClickListener clickListener, OnItemClickListener itemClickListener,
                                    OnDismissListener dismissListener, OnCancelListener cancelListener,
                                    boolean expanded) {
        final DialogPlus dialog = DialogPlus.newDialog(this)
                .setContentHolder(holder)
                .setHeader(R.layout.header)
                .setCancelable(true)
                .setGravity(gravity)
                .setAdapter(adapter)
                .setOnClickListener(clickListener)
                .setOnItemClickListener(itemClickListener)
                .setOnDismissListener(dismissListener)
                .setOnCancelListener(cancelListener)
                .setExpanded(expanded)
                .create();
        dialog.show();
    }

    private void showNoHeaderDialog(Holder holder, int gravity, BaseAdapter adapter,
                                    OnClickListener clickListener, OnItemClickListener itemClickListener,
                                    OnDismissListener dismissListener, OnCancelListener cancelListener,
                                    boolean expanded) {
        final DialogPlus dialog = DialogPlus.newDialog(this)
                .setContentHolder(holder)
                .setFooter(R.layout.footer)
                .setCancelable(true)
                .setGravity(gravity)
                .setAdapter(adapter)
                .setOnClickListener(clickListener)
                .setOnItemClickListener(itemClickListener)
                .setOnDismissListener(dismissListener)
                .setOnCancelListener(cancelListener)
                .setExpanded(expanded)
                .create();
        dialog.show();
    }

    private void showOnlyContentDialog(Holder holder, int gravity, BaseAdapter adapter,
                                       OnItemClickListener itemClickListener, OnDismissListener dismissListener,
                                       OnCancelListener cancelListener, boolean expanded) {
        final DialogPlus dialog = DialogPlus.newDialog(this)
                .setContentHolder(holder)
                .setGravity(gravity)
                .setAdapter(adapter)
                .setOnItemClickListener(itemClickListener)
                .setOnDismissListener(dismissListener)
                .setOnCancelListener(cancelListener)
                .setExpanded(expanded)
                .setCancelable(true)
                .create();
        dialog.show();
    }

    public class SimpleAdapter extends BaseAdapter {

        private LayoutInflater layoutInflater;
        private boolean isGrid;

        public SimpleAdapter(Context context, boolean isGrid) {
            layoutInflater = LayoutInflater.from(context);
            this.isGrid = isGrid;
        }

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            View view = convertView;

            if (view == null) {
                if (isGrid) {
                    view = layoutInflater.inflate(R.layout.simple_grid_item, parent, false);
                } else {
                    view = layoutInflater.inflate(R.layout.simple_list_item, parent, false);
                }

                viewHolder = new ViewHolder();
                viewHolder.textView = (TextView) view.findViewById(R.id.text_view);
                viewHolder.imageView = (ImageView) view.findViewById(R.id.image_view);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }

            Context context = parent.getContext();
            switch (position) {
                case 0:
                    viewHolder.textView.setText(context.getString(R.string.google_plus_title));
                    viewHolder.imageView.setImageResource(R.drawable.ic_google_plus_icon);
                    break;
                case 1:
                    viewHolder.textView.setText(context.getString(R.string.google_maps_title));
                    viewHolder.imageView.setImageResource(R.drawable.ic_google_maps_icon);
                    break;
                default:
                    viewHolder.textView.setText(context.getString(R.string.google_messenger_title));
                    viewHolder.imageView.setImageResource(R.drawable.ic_google_messenger_icon);
                    break;
            }

            return view;
        }

        class ViewHolder {
            TextView textView;
            ImageView imageView;
        }
    }
}

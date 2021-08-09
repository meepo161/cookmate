package ru.avem.cookmate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SettingsListAdapter extends BaseExpandableListAdapter {

    private final LayoutInflater inflater;
    private final ArrayList<Category> mParent;
    private final ExpandableListView accordion;
    public int lastExpandedGroupPosition;


    public SettingsListAdapter(Context context, ArrayList<Category> parent, ExpandableListView accordion) {
        mParent = parent;
        inflater = LayoutInflater.from(context);
        this.accordion = accordion;
    }

    @Override
    public int getGroupCount() {
        return mParent.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return mParent.get(i).children.size();
    }

    @Override
    public Object getGroup(int i) {
        return mParent.get(i).name;
    }

    @Override
    public Object getChild(int i, int i1) {
        return mParent.get(i).children.get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = inflater.inflate(R.layout.settings_list_item_parent, viewGroup, false);
        }
        view.setTag(getGroup(i).toString());

        TextView textView = view.findViewById(R.id.list_item_text_view);
        textView.setText(getGroup(i).toString());

        TextView sub = view.findViewById(R.id.list_item_text_subscriptions);

        if (mParent.get(i).selection.size() > 0) {
            sub.setText(mParent.get(i).selection.toString());
        } else {
            sub.setText("");
        }
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.settings_list_item_child, viewGroup, false);
        }
        CheckedTextView textView = view.findViewById(R.id.list_item_text_child);
        textView.setText(mParent.get(i).children.get(i1).name);

        textView.setChecked(mParent.get(i).selection.contains(textView.getText().toString()));
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

        if (groupPosition != lastExpandedGroupPosition) {
            accordion.collapseGroup(lastExpandedGroupPosition);
        }
        super.onGroupExpanded(groupPosition);
        lastExpandedGroupPosition = groupPosition;
    }
}

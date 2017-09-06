package noh.seung.hwa.baseball.classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import noh.seung.hwa.baseball.R;

/**
 * Created by fontjuna on 2017-09-05.
 */

public class ElementAdapter extends BaseAdapter {
    private Context mContext;
    private List<Element> mElements;

    public ElementAdapter(Context context, List<Element> elements) {
        mContext = context;
        mElements = elements;
    }

    @Override
    public int getCount() {
        return mElements.size();
    }

    @Override
    public Object getItem(int position) {
        return mElements.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView= LayoutInflater.from(mContext).inflate(R.layout.element_list,parent,false);

            TextView mLeftTextView = convertView.findViewById(R.id.left_text);
            TextView mRightTextView = convertView.findViewById(R.id.right_text);

            holder = new ViewHolder();
            holder.mLeftTextView = mLeftTextView;
            holder.mRightTextView = mRightTextView;

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Element element = (Element) getItem(position);
        holder.mLeftTextView.setText(element.getLeft());
        holder.mRightTextView.setText(element.getRight());

        return convertView;
    }

    static class ViewHolder {
        private TextView mLeftTextView;
        private TextView mRightTextView;
    }
}

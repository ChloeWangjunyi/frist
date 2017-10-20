package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.administrator.myapplication.Bean;
import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 列表adapter
 * Created by wangjunyi on 2017/9/19.
 */

public class ListAdapter extends BaseAdapter {


    List<Bean> list = new ArrayList<>();
    private Context context;

    public ListAdapter(Context context, List<Bean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (holder == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.list_info, parent, false);
            holder.name = (TextView) convertView.findViewById(R.id.text);
            holder.age = (TextView) convertView.findViewById(R.id.text2);
            holder.pic = (ImageView) convertView.findViewById(R.id.pic);
            holder.see = (TextView) convertView.findViewById(R.id.see);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            reset(holder);
        }
        Bean bean = list.get(position);
        holder.name.setText(bean.getName());
        holder.age.setText(bean.getAge());
        if (bean.getPic() == "" || bean.getPic() == null) {
            holder.pic.setImageResource(R.mipmap.head);
        } else {
            final ViewHolder finalHolder = holder;
            Glide.with(context).load(bean.getPic()).into(new SimpleTarget<GlideDrawable>() {
                @Override
                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                    finalHolder.pic.setImageDrawable(resource);
                }
            });
        }
        if (bean.getIsSee().equals(1)) {
            holder.see.setVisibility(View.VISIBLE);
            holder.see.setText("seeing");
        }

        return convertView;
    }

    private void reset(ViewHolder viewHolder) {
        viewHolder.name = null;
        viewHolder.age = null;
    }

    class ViewHolder {
        private TextView name;
        private TextView age;
        private ImageView pic;
        private TextView see;
    }
}

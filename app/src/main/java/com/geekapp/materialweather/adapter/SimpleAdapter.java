/*
 * Copyright 2015 "Henry Tao <hi@henrytao.me>"
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.geekapp.materialweather.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.geekapp.materialweather.R;
import com.geekapp.materialweather.model.DailyWeatherRespond;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class SimpleAdapter<T> extends RecyclerView.Adapter<SimpleAdapter.ViewHolder> {

    private final OnItemClickListener mOnItemClickListener;
    private List<DailyWeatherRespond.CityEntity> mData;
    private int lastPosition = -1;
    private Context mContext;

    public SimpleAdapter(Context context, List<DailyWeatherRespond.CityEntity> data, OnItemClickListener<T> onItemClickListener) {
        mData = data;
        mContext = context;
        mOnItemClickListener = onItemClickListener;
    }

    public void setData(List<DailyWeatherRespond.CityEntity> data) {
        this.mData = data;
        lastPosition = -1;
        this.notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(getItem(position));
        // Here you apply the animation when the view is bound
        setAnimation(holder.container, position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()), parent, mOnItemClickListener);
    }

    public DailyWeatherRespond.CityEntity getItem(int position) {
        return mData != null && position >= 0 && position < mData.size() ? mData.get(position) : null;
    }

    /**
     * Here is the key method to apply the animation
     */
    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
            animation.setDuration(500);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    public interface OnItemClickListener<I> {

        void onItemClick(I data);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.container)
        RelativeLayout container;
        @Bind(R.id.description)
        TextView description;
        @Bind(R.id.temp)
        TextView temp;
        @Bind(R.id.date)
        TextView date;

        private DailyWeatherRespond.CityEntity mData;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent, final OnItemClickListener onItemClickListener) {
            super(createView(inflater, parent, R.layout.simple_adapter_item_simple));
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(mData);
                    }
                }
            });
        }

        private static View createView(LayoutInflater inflater, ViewGroup parent, @LayoutRes int layoutId) {
            return inflater.inflate(layoutId, parent, false);
        }

        public void bind(DailyWeatherRespond.CityEntity data) {
            mData = data;
            description.setText(mData.weather.get(0).description);
            temp.setText((int) mData.temp.min + "℃~" + (int) mData.temp.max + "℃");
        }
    }
}

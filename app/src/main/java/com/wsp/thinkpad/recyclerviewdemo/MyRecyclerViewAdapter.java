package com.wsp.thinkpad.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${吴心良}
 * on 2017/4/22.
 * description:
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<String> list=new ArrayList<>();
    Context context;

    public static int RECYCLER_HEAD=0;
    public static int RECYCLER_FIRST=1;
    public static int RECYCLER_SECOND=2;
    public static int RECYCLER_FOOT=3;

    public MyRecyclerViewAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==RECYCLER_HEAD){
            return 0;
        }
        if (position==RECYCLER_FIRST){
            return 1;
        }
        if (position==RECYCLER_SECOND){
            return 2;
        }
        if (position==getItemCount()-1){
            return RECYCLER_FOOT;
        }
        return 4;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==RECYCLER_HEAD){
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.head_one,parent,false);
            HeadViewHolder holder=new HeadViewHolder(view);
            return holder;
        } else if (viewType==RECYCLER_FIRST){
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.head_two,parent,false);
            HeadTwoViewHolder holder=new HeadTwoViewHolder(view);
            return holder;
        }else if (viewType==RECYCLER_SECOND){
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.head_three,parent,false);
            HeadThreeViewHolder holder=new HeadThreeViewHolder(view);
            return holder;
        }else if (viewType==RECYCLER_FOOT){
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_view,parent,false);
            FooterViewHolder holder=new FooterViewHolder(view);
            return holder;
        }
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_normal,parent,false);
            MyViewHolder holder =new MyViewHolder(view);
            return holder;

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if(manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (position==RECYCLER_HEAD){
                        return 2;//这里返回几就是占几格
                    }
                    if (position==RECYCLER_FIRST){
                        return 2;
                    }
                    if (position==RECYCLER_SECOND){
                        return 2;
                    }
                    if (position==getItemCount()-1){
                        return 2;
                    }
                    return 1;
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeadViewHolder){
            //设置banner样式
            ((HeadViewHolder)holder).banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
            //设置图片集合
            List<String> images=new ArrayList<String>();
            images.add("http://inthecheesefactory.com/uploads/source/nestedfragment/fragments.png");
            images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492861364500&di=e1224f18ecaf77f17bb1533137f33432&imgtype=0&src=http%3A%2F%2Fd.5857.com%2Fjurdbm_160518%2F001.jpg");
            images.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3487910287,288553425&fm=23&gp=0.jpg");
            ((HeadViewHolder)holder).banner.setImages(images).setImageLoader(new GlideImageLoader());
            //设置banner动画效果
            ((HeadViewHolder)holder).banner.setBannerAnimation(Transformer.DepthPage);
            //设置标题集合（当banner样式有显示title时）
            List<String> titles=new ArrayList<String>();
            titles.add("打合适的啊加很多");
            titles.add("就副局长截止阀就想");
            titles.add("加弹夹辣椒水发");
            ((HeadViewHolder)holder).banner.setBannerTitles(titles);
            //设置自动轮播，默认为true
            ((HeadViewHolder)holder).banner.isAutoPlay(true);
            //设置轮播时间
            ((HeadViewHolder)holder).banner.setDelayTime(2000);
            //设置指示器位置（当banner模式中有指示器时）
            ((HeadViewHolder)holder).banner.setIndicatorGravity(BannerConfig.CENTER);
            //banner设置方法全部调用完毕时最后调用
            ((HeadViewHolder)holder).banner.start();
        }
        if (holder instanceof HeadTwoViewHolder){
            ((HeadTwoViewHolder)holder).textView.setText("--------我是第一个啥啥啥");
        }
        if (holder instanceof HeadThreeViewHolder){
            Glide.with(context).load("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3356833165,4017320252&fm=23&gp=0.jpg")
                    .into(((HeadThreeViewHolder)holder).imageView);
        }
        if (holder instanceof FooterViewHolder){
            ((FooterViewHolder)holder).textView.setText("---------我是最后一个");
        }
        if (holder instanceof MyViewHolder){
            ((MyViewHolder)holder).textView.setText(list.get(position-3));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class HeadViewHolder extends RecyclerView.ViewHolder{
        Banner banner;
        public HeadViewHolder(View itemView) {
            super(itemView);
            banner= (Banner) itemView.findViewById(R.id.banner);
        }
    }
    class HeadTwoViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public HeadTwoViewHolder(View itemView) {
            super(itemView);
            textView= (TextView) itemView.findViewById(R.id.textview);
        }
    }
    class HeadThreeViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public HeadThreeViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
        }
    }
    class FooterViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public FooterViewHolder(View itemView) {
            super(itemView);
            textView= (TextView) itemView.findViewById(R.id.foot_textview);
        }
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView= (TextView) itemView.findViewById(R.id.item_tv);
        }
    }
}


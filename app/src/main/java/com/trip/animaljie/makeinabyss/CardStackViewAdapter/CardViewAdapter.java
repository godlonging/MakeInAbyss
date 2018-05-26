package com.trip.animaljie.makeinabyss.CardStackViewAdapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopeer.cardstack.CardStackView;
import com.loopeer.cardstack.StackAdapter;
import com.trip.animaljie.makeinabyss.R;

import java.util.ArrayList;
import java.util.List;

public class CardViewAdapter extends StackAdapter {
    private ArrayList<Integer> card_images;

    public CardViewAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.cardview_layout;
    }

    public void updateData(List data, ArrayList<Integer> images) {
        this.card_images = images;
        updateData(data);
    }

    @Override
    public void bindView(Object data, int position, CardStackView.ViewHolder holder) {
        if (holder instanceof ColorItemViewHolder) {
            ColorItemViewHolder h = (ColorItemViewHolder) holder;
            h.onBind((Integer)data, position,card_images);

        }
    }

    @Override
    protected CardStackView.ViewHolder onCreateView(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
//            case R.layout.cardview_layout:
//                view = getLayoutInflater().inflate(R.layout.cardview_layout, parent, false);
//                return new ColorItemLargeHeaderViewHolder(view);
//            case R.layout.cardview_layout:
//                view = getLayoutInflater().inflate(R.layout.cardview_layout, parent, false);
//                return new ColorItemWithNoHeaderViewHolder(view);
            default:
                view = getLayoutInflater().inflate(R.layout.cardview_layout, parent, false);
                return new ColorItemViewHolder(view);
        }
    }

    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }


    public static class ColorItemViewHolder extends CardStackView.ViewHolder {
        View mLayout;
        View mContainerContent;
        TextView mTextTitle;
        ImageView imageView;

        public ColorItemViewHolder(View view) {
            super(view);
            mLayout = view.findViewById(R.id.frame_list_card_item);
            mContainerContent = view.findViewById(R.id.container_list_content);
            mTextTitle = view.findViewById(R.id.text_list_card_title);
            imageView = view.findViewById(R.id.trip_image);
        }

        @Override
        public void onItemExpand(boolean b) {
            mContainerContent.setVisibility(b ? View.VISIBLE : View.GONE);
        }
        public void onBind(Integer data, int position,ArrayList<Integer> card_images) {
            mLayout.getBackground().setColorFilter(ContextCompat.getColor(getContext(), data), PorterDuff.Mode.SRC_IN);
            mTextTitle.setText(String.valueOf(position));
            imageView.setImageResource((Integer)card_images.get(0));

        }

    }
//    public static class ColorItemWithNoHeaderViewHolder extends CardStackView.ViewHolder {
//        View mLayout;
//        TextView mTextTitle;
//
//        public ColorItemWithNoHeaderViewHolder(View view) {
//            super(view);
//            mLayout = view.findViewById(R.id.frame_list_card_item);
//            mTextTitle = (TextView) view.findViewById(R.id.text_list_card_title);
//        }
//
//        @Override
//        public void onItemExpand(boolean b) {
//        }
//
//        public void onBind(Integer data, int position) {
//            mLayout.getBackground().setColorFilter(ContextCompat.getColor(getContext(), data), PorterDuff.Mode.SRC_IN);
//            mTextTitle.setText(String.valueOf(position));
//        }
//
//    }






//    static class ColorItemLargeHeaderViewHolder extends CardStackView.ViewHolder {
//        View mLayout;
//        View mContainerContent;
//        TextView mTextTitle;
//
//        public ColorItemLargeHeaderViewHolder(View view) {
//            super(view);
//            mLayout = view.findViewById(R.id.frame_list_card_item);
//            mContainerContent = view.findViewById(R.id.container_list_content);
//            mTextTitle = (TextView) view.findViewById(R.id.text_list_card_title);
//        }
//
//        @Override
//        public void onItemExpand(boolean b) {
//            mContainerContent.setVisibility(b ? View.VISIBLE : View.GONE);
//        }
//
//        @Override
//        protected void onAnimationStateChange(int state, boolean willBeSelect) {
//            super.onAnimationStateChange(state, willBeSelect);
//            if (state == CardStackView.ANIMATION_STATE_START && willBeSelect) {
//                onItemExpand(true);
//            }
//            if (state == CardStackView.ANIMATION_STATE_END && !willBeSelect) {
//                onItemExpand(false);
//            }
//        }
//
//
//        public void onBind(Integer data, int position) {
//            mLayout.getBackground().setColorFilter(ContextCompat.getColor(getContext(), data), PorterDuff.Mode.SRC_IN);
//            mTextTitle.setText(String.valueOf(position));
//
//            itemView.findViewById(R.id.text_view).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    ((CardStackView) itemView.getParent()).performItemClick(ColorItemLargeHeaderViewHolder.this);
//                }
//            });
//        }
//    }
}

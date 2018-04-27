package com.santukis.spellbook.presentation.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.santukis.spellbook.R;

public class BaseViewHolder extends RecyclerView.ViewHolder {

    private RelativeLayout backgroundView, foregroundView;

    public BaseViewHolder(View itemView) {
        super(itemView);

        backgroundView = itemView.findViewById(R.id.rl_background);
        foregroundView = itemView.findViewById(R.id.rl_foreground);
    }

    public RelativeLayout getBackgroundView() {
        return backgroundView;
    }

    public RelativeLayout getForegroundView() {
        return foregroundView;
    }
}

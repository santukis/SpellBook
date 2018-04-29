package com.santukis.spellbook.presentation.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.santukis.spellbook.R;
import com.santukis.spellbook.domain.model.Avatar;
import com.santukis.spellbook.presentation.components.click.OnAvatarClick;

import java.util.ArrayList;
import java.util.List;

public class AvatarsAdapter extends RecyclerView.Adapter<AvatarsAdapter.ViewHolder> {

    private Context context;
    private List<Avatar> avatars = new ArrayList<>();

    private OnAvatarClick onAvatarClick;

    public AvatarsAdapter(Context context) {
        this.context = context;
    }

    public void setOnAvatarClick(OnAvatarClick onAvatarClick) {
        this.onAvatarClick = onAvatarClick;
    }

    @NonNull
    @Override
    public AvatarsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.element_avatar, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AvatarsAdapter.ViewHolder holder, int position) {
        final Avatar avatar = avatars.get(position);

        holder.nameView.setText(avatar.getName());
        holder.professionView.setText(context.getResources().getString(avatar.getProfession().getName()));
        holder.professionImage.setBackgroundResource(avatar.getProfession().getImage());


        holder.spellsButton.setOnClickListener(v -> {
            onAvatarClick.onClick(avatar);
        });
    }

    @Override
    public int getItemCount() {
        return avatars.size();
    }

    public Avatar getAvatar(int position) {
        return avatars.get(position);
    }

    public void updateAvatars(List<Avatar> spells) {
        this.avatars.clear();
        this.avatars.addAll(spells);
        notifyDataSetChanged();
    }

    public void removeAvatar(int position) {
        avatars.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreAvatar(int position, Avatar avatar) {
        avatars.add(position, avatar);
        notifyItemInserted(position);
    }

    static class ViewHolder extends BaseViewHolder {

        private TextView nameView;
        private ImageView professionImage;
        private TextView professionView;
        private ImageButton spellsButton;

        ViewHolder(View itemView) {
            super(itemView);

            nameView = itemView.findViewById(R.id.tv_name);
            professionImage = itemView.findViewById(R.id.iv_profession);
            professionView = itemView.findViewById(R.id.tv_profession);
            spellsButton = itemView.findViewById(R.id.bt_spells);
        }
    }
}

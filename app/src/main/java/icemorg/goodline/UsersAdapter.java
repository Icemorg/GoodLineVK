package icemorg.goodline;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.vk.sdk.api.model.VKApiUserFull;
import com.vk.sdk.api.model.VKList;

import java.lang.annotation.Target;


public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private Context context;
    private VKList<VKApiUserFull> userList;

    public UsersAdapter(Context context,VKList<VKApiUserFull> userList) {
        this.context = context;
        this.userList = userList;
    }

    public void setNewList(VKList<VKApiUserFull> userList){
        this.userList = userList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_user, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView rv) {
        super.onAttachedToRecyclerView(rv);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.firstName.setText(userList.get(position).first_name);
        holder.lastName.setText(userList.get(position).last_name);
//        holder.avatar.setImageURI(Uri.parse(userList.get(position).photo_50));
        Glide.with(context).load(userList.get(position).photo_50).into(holder.avatar);
    }

    @Override
    public int getItemCount() {
//        return userList == null ? 0 : userList.size();
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView firstName;
        public TextView lastName;
        public ImageView avatar;

        public ViewHolder(@NonNull View v) {
            super(v);
            firstName = v.findViewById(R.id.firstName);
            lastName = v.findViewById(R.id.lastName);
            avatar = v.findViewById(R.id.avatar);
        }
    }
}

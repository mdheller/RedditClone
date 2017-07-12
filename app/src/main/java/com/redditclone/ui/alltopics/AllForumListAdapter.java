package com.redditclone.ui.alltopics;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.redditclone.BaseApplication;
import com.redditclone.R;
import com.redditclone.data.model.Forum;
import com.redditclone.ui.detail.DetailActivity;
import com.redditclone.util.Logger;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Tosin Onikute.
 */

public class AllForumListAdapter
        extends RecyclerView.Adapter<AllForumListAdapter.ViewHolder> {

    private final Logger logger = Logger.getLogger(getClass());
    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private ArrayList<Forum> mForum;
    private Context mContext;

    private String ctitle;
    private String cDesc;


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public final TextView title;
        public final TextView description;


        public final ImageView upvoteButton;
        public final ImageView downvoteButton;
        public final TextView upvoteText;
        public final TextView downvoteText;

        public final ViewGroup layout;

        public ViewHolder(View view) {
            super(view);
            mView = view;


            title = (TextView) view.findViewById(R.id.title);
            description = (TextView) view.findViewById(R.id.description);
            upvoteButton = (ImageView) view.findViewById(R.id.upvote_button);
            downvoteButton = (ImageView) view.findViewById(R.id.downvote_button);
            upvoteText = (TextView) view.findViewById(R.id.upvote_text);
            downvoteText = (TextView) view.findViewById(R.id.downvote_text);
            layout = (ViewGroup) view.findViewById(R.id.tag_list);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    public String getValueAt(int position) {
        return String.valueOf("");
    }

    public AllForumListAdapter(Context context, ArrayList<Forum> mForum) {
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mContext = context;
        mBackground = mTypedValue.resourceId;
        this.mForum = mForum;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_forum, parent, false);
        view.setBackgroundResource(mBackground);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        /* Set your values */
        final Forum model = (Forum) mForum.get(position);


        ctitle = "";
        cDesc = "";


        if (model.getPostTitle() != null) {
            ctitle = model.getPostTitle();
        }

        if (model.getPostDesc() != null) {
            cDesc = model.getPostDesc();
        }

        holder.title.setText(ctitle);
        holder.description.setText(cDesc);
        if(cDesc.length() > 50) {
            holder.description.setText(cDesc.substring(0, 50) + "...");
        }

        holder.upvoteText.setText(String.valueOf(model.getUpvotes()));
        holder.downvoteText.setText(String.valueOf(model.getDownvotes()));

        holder.upvoteButton.setTag(position);
        holder.downvoteButton.setTag(position);


        holder.upvoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(holder.upvoteText.getText().toString());
                num = num + 1;
                holder.upvoteText.setText(String.valueOf(num));
                ((BaseApplication) mContext).getForum().get(position).setUpvotes(num);
            }
        });


        holder.downvoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(holder.downvoteText.getText().toString());
                num = num + 1;
                holder.downvoteText.setText(String.valueOf(num));
                ((BaseApplication) mContext).getForum().get(position).setDownvotes(num);

            }
        });


        logger.debug(String.valueOf(position));

        // launch the edit task activity to show Task information
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("position", holder.getAdapterPosition());
                intent.putExtra("mForum", mForum);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != mForum ? mForum.size() : 0);
    }

    public void addAll(List<Forum> data){
        notifyDataSetChanged();
    }

    public void add(Forum data){
        notifyDataSetChanged();
        mForum.add(data);
    }


    public Forum getItemPos(int pos){
        return mForum.get(pos);
    }

    public void clear(){
        mForum.clear();
    }

}


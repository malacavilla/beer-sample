package beersample.nickerson.com.beersample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import beersample.nickerson.com.beersample.models.CheckinItem;
import beersample.nickerson.com.beersample.network.VolleySingleton;

/**
 * Adapter for displaying cards of a user's recent checkins.
 */
public class CheckinAdapter extends RecyclerView.Adapter<CheckinAdapter.ViewHolder> {
    private final ArrayList<CheckinItem> mDataset;
    private final ImageLoader mImageLoader;

    public CheckinAdapter(final Context context, final ArrayList<CheckinItem> dataset) {
        mDataset = dataset;
        mImageLoader = VolleySingleton.getInstance(context).getImageLoader();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cardView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_holder_checkin_card, parent, false);

        ViewHolder viewHolder = new ViewHolder(cardView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final CheckinItem checkinItem = mDataset.get(position);

        holder.mBeerName.setText(checkinItem.beer.name);
        holder.mBeerStyle.setText(checkinItem.beer.style);
        holder.mBreweryName.setText(checkinItem.brewery.name);

        holder.mImageView.setImageUrl(checkinItem.beer.label, mImageLoader);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public NetworkImageView mImageView;
        public TextView mBeerName;
        public TextView mBeerStyle;
        public TextView mBreweryName;

        public ViewHolder(final View view) {
            super(view);

            mImageView = (NetworkImageView) view.findViewById(R.id.checkin_image);
            mBeerName = (TextView) view.findViewById(R.id.beer_name);
            mBeerStyle = (TextView) view.findViewById(R.id.beer_style);
            mBreweryName = (TextView) view.findViewById(R.id.brewery_name);
        }
    }
}

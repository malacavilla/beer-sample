package beersample.nickerson.com.beersample;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import beersample.nickerson.com.beersample.models.CheckinItem;

/**
 * Fragment to display a user's recent checkins.
 */
public final class UserCheckinsFragment extends Fragment {

    private static final String ARG_ITEMS = UserCheckinsFragment.class.getName() + "items";

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private CheckinAdapter mAdapter;

    public static void getArguments(final Bundle bundle, final ArrayList<CheckinItem> items) {
        bundle.putParcelableArrayList(ARG_ITEMS, items);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_checkins, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mRecyclerView = (RecyclerView) getView().findViewById(R.id.checkins_recycler_view);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        final ArrayList<CheckinItem> items = getArguments().getParcelableArrayList(ARG_ITEMS);

        mAdapter = new CheckinAdapter(getActivity(), items);
        mRecyclerView.setAdapter(mAdapter);
    }
}

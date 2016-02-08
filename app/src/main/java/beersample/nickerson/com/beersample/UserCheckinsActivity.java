package beersample.nickerson.com.beersample;

import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.RequestQueue;
import com.android.volley.Response;

import java.util.ArrayList;

import javax.inject.Inject;

import beersample.nickerson.com.beersample.models.CheckinItem;
import beersample.nickerson.com.beersample.models.QueryFullResponse;
import beersample.nickerson.com.beersample.network.GsonRequest;
import beersample.nickerson.com.beersample.network.UntappdRequestFactory;

public class UserCheckinsActivity extends AppCompatActivity {
    @Inject
    RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((BeerSampleApplication)getApplication()).getComponent().inject(this);

        setContentView(R.layout.activity_user_checkins);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        queryUserCheckins();
    }

    @VisibleForTesting
    void queryUserCheckins() {
        final GsonRequest<QueryFullResponse> queryRequest = UntappdRequestFactory
                .getRecentCheckins(this, queryResponseListener);

        mRequestQueue.add(queryRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addUserCheckinFragment(final ArrayList<CheckinItem> items) {
        final UserCheckinsFragment fragment = new UserCheckinsFragment();
        final Bundle arguments = new Bundle();
        UserCheckinsFragment.getArguments(arguments, items);
        fragment.setArguments(arguments);

        getSupportFragmentManager().beginTransaction().add(R.id.content, fragment,
                UserCheckinsFragment.class.getName()).commit();
    }

    @VisibleForTesting
    final Response.Listener<QueryFullResponse> queryResponseListener = new Response
            .Listener<QueryFullResponse>() {

        @Override
        public void onResponse(final QueryFullResponse response) {
            addUserCheckinFragment(response.response.checkins.items);
        }
    };
}

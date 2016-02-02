package beersample.nickerson.com.beersample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Response;

import java.util.ArrayList;

import beersample.nickerson.com.beersample.models.CheckinItem;
import beersample.nickerson.com.beersample.models.QueryFullResponse;
import beersample.nickerson.com.beersample.network.GsonRequest;
import beersample.nickerson.com.beersample.network.UntappdRequestFactory;
import beersample.nickerson.com.beersample.network.VolleySingleton;

public class UserCheckinsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_checkins);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        final GsonRequest<QueryFullResponse> queryRequest = UntappdRequestFactory.getUserFeed(this,
                "malacavilla", queryResponseListener);

        VolleySingleton.getInstance(this).addToRequestQueue(queryRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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

    private final Response.Listener<QueryFullResponse> queryResponseListener = new Response
            .Listener<QueryFullResponse>() {


        @Override
        public void onResponse(final QueryFullResponse response) {
            addUserCheckinFragment(response.response.checkins.items);
        }
    };
}

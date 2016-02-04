package beersample.nickerson.com.beersample;

import android.app.Application;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Singleton;

import beersample.nickerson.com.beersample.fixtures.QueryFullResponseFixture;
import beersample.nickerson.com.beersample.util.DaggerActivityTestRule;
import dagger.Component;
import dagger.Module;
import dagger.Provides;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Tests {@Link UserCheckinsActivity}.
 */
@RunWith(AndroidJUnit4.class)
public final class UserCheckinsActivityTest {
    private TestRequestQueueComponent mTestRequestQueueComponent;

    private boolean mSendMockResponse;

    @Rule
    public ActivityTestRule<UserCheckinsActivity> mActivityRule =
            new DaggerActivityTestRule<>(UserCheckinsActivity.class, new DaggerActivityTestRule
                    .OnBeforeActivityLaunchedListener<UserCheckinsActivity>() {
                @Override
                public void beforeActivityLaunched(Application application, UserCheckinsActivity
                        activity) {
                    mTestRequestQueueComponent =
                            DaggerUserCheckinsActivityTest_TestRequestQueueComponent.builder()
                                    .testAppModule(new TestAppModule(application))
                                    .testRequestQueueModule(new TestRequestQueueModule())
                                    .build();
                    ((BeerSampleApplication) application).setTestComponent
                            (mTestRequestQueueComponent);
                }
            });


    @Test
    public void displayUserCheckins() {
        if (mSendMockResponse) {
            mActivityRule.getActivity().queryResponseListener.onResponse(QueryFullResponseFixture
                    .getFullObject());
        }
        onView(withText("Sample Beer")).check(matches(isDisplayed()));
    }

    private class UserCheckinsActivityUnderTest extends UserCheckinsActivity {
        @Override
        void queryUserCheckins(String userName) {
            queryResponseListener.onResponse(QueryFullResponseFixture.getFullObject());
        }
    }

    @Singleton
    @Component(modules = {TestAppModule.class, TestRequestQueueModule.class})
    interface TestRequestQueueComponent extends RequestQueueComponent {
        void inject(UserCheckinsActivity activity);
    }

    @Module
    class TestAppModule {

        Application mApplication;

        public TestAppModule(Application application) {
            mApplication = application;
        }

        @Provides
        @Singleton
        Application providesApplication() {
            return mApplication;
        }
    }

    @Module
    class TestRequestQueueModule {

        @Singleton
        @Provides
        RequestQueue provideRequestQueue(Application application) {
            return new RequestQueueMock();
        }
    }

    private class RequestQueueMock extends RequestQueue {
        public RequestQueueMock() {
            super(null, null, 1);
        }

        @Override
        public <T> Request<T> add(Request<T> request) {
            UserCheckinsActivityTest.this.sendResult();

            return null;
        }
    }

    private void sendResult() {
        mSendMockResponse = true;
    }
}

package beersample.nickerson.com.beersample;

import android.app.Application;
import android.support.annotation.VisibleForTesting;

import com.android.volley.RequestQueue;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Beer sample application.
 */
public class BeerSampleApplication extends Application {
    private RequestQueueComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = createComponent();
    }

    protected RequestQueueComponent createComponent() {
        return DaggerRequestQueueComponent.builder()
                .appModule(new AppModule(this))
                .requestQueueModule(new RequestQueueModule())
                .build();
    }

    /**
     * Visible only for testing purposes.
     */
    @VisibleForTesting
    public void setTestComponent(RequestQueueComponent requestQueueComponent) {
        component = requestQueueComponent;
    }

    public RequestQueueComponent getComponent() {
        return component;
    }
}

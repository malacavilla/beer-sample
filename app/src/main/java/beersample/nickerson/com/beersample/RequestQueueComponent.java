package beersample.nickerson.com.beersample;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules={AppModule.class, RequestQueueModule.class})
public interface RequestQueueComponent {
    void inject(UserCheckinsActivity activity);
}

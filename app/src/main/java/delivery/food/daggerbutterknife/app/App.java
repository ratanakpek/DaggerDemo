package delivery.food.daggerbutterknife.app;

import android.app.Activity;
import android.app.Application;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import delivery.food.daggerbutterknife.dagger.component.AppComponent;
import delivery.food.daggerbutterknife.dagger.component.DaggerAppComponent;
import delivery.food.daggerbutterknife.dagger.component.subcomponent.DaggerUserComponent;

public class App extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        AppComponent appComponent=DaggerAppComponent
                .builder()
                .application(this)
                .build();
        appComponent.inject(this);

        DaggerUserComponent
                .builder()
                .appComponent(appComponent).build().inject(this);

    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }
}

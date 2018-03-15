package delivery.food.daggerbutterknife.app;

import android.app.Activity;
import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import delivery.food.daggerbutterknife.dagger.component.AppComponent;
import delivery.food.daggerbutterknife.dagger.component.DaggerAppComponent;


public class App extends Application implements HasActivityInjector {

    private static App _instance;


    public static App get() {
        return _instance;
    }



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

     /*   DaggerUserComponent
                .builder()
                .appComponent(appComponent).build().inject(this);
*/

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }

        _instance = (App) getApplicationContext();


    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }
}

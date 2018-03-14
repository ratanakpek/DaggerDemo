package delivery.food.daggerbutterknife.dagger.component;


import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import delivery.food.daggerbutterknife.app.App;
import delivery.food.daggerbutterknife.dagger.binder.ActivityBuilder;
import delivery.food.daggerbutterknife.dagger.module.AppModule;

@Component(modules = {AndroidSupportInjectionModule.class, AppModule.class,  ActivityBuilder.class})
@Singleton
public interface AppComponent {


    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(App app);
}

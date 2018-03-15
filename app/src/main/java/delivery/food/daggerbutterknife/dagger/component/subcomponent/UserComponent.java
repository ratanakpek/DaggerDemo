package delivery.food.daggerbutterknife.dagger.component.subcomponent;


import android.app.Application;

import dagger.BindsInstance;
import dagger.Component;
import delivery.food.daggerbutterknife.app.App;
import delivery.food.daggerbutterknife.dagger.component.AppComponent;
import delivery.food.daggerbutterknife.dagger.module.activity.MainModule;
import delivery.food.daggerbutterknife.dagger.scope.UserScope;

@Component(dependencies = AppComponent.class, modules = {MainModule.class})
@UserScope
public interface UserComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        UserComponent.Builder application(Application application);

        UserComponent build();
    }

    void inject(App app);
}

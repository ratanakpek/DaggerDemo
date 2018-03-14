package delivery.food.daggerbutterknife.dagger.binder;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import delivery.food.daggerbutterknife.MainActivity;
import delivery.food.daggerbutterknife.dagger.module.AppModule;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = AppModule.class)
    abstract MainActivity bindMainActivity();
}

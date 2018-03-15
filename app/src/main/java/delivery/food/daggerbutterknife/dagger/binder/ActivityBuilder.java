package delivery.food.daggerbutterknife.dagger.binder;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import delivery.food.daggerbutterknife.LoginActivity;
import delivery.food.daggerbutterknife.LogoutActivity;
import delivery.food.daggerbutterknife.MainActivity;
import delivery.food.daggerbutterknife.dagger.module.activity.LoginModule;
import delivery.food.daggerbutterknife.dagger.module.activity.MainModule;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = {LoginModule.class})
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector
    abstract LogoutActivity bindLogoutActivity();
}

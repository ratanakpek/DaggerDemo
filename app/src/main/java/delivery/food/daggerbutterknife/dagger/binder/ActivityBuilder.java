package delivery.food.daggerbutterknife.dagger.binder;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import delivery.food.daggerbutterknife.LoginActivity;
import delivery.food.daggerbutterknife.LogoutActivity;
import delivery.food.daggerbutterknife.MainActivity;
import delivery.food.daggerbutterknife.dagger.module.activity.LoginModule;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = {LoginModule.class})
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector
    abstract LogoutActivity bindLogoutActivity();
}

package delivery.food.daggerbutterknife.dagger.component.subcomponent;


import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ContributesAndroidInjector;
import delivery.food.daggerbutterknife.LoginActivity;
import delivery.food.daggerbutterknife.dagger.module.activity.LoginModule;
import delivery.food.daggerbutterknife.dagger.scope.ActivityScope;

@ActivityScope
@Subcomponent(modules = LoginModule.class)
public interface LoginComponent {

    LoginActivity inject(LoginActivity activity);



}

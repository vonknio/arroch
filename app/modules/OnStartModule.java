package modules;

import com.google.inject.AbstractModule;

public class OnStartModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(DatabasePreloader.class).asEagerSingleton();
    }
}
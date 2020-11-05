package com.macfred.viewmodel;

import com.macfred.util.function.BiConsumer;
import com.macfred.util.function.Consumer;
import com.macfred.util.function.Supplier;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public abstract class AbstractViewModel<ViewType extends IView, ModelType> implements IViewModel<ViewType, ModelType> {
    private Set<ViewType> mViews = new CopyOnWriteArraySet<>();
    private boolean mIsSetup;

    public final boolean isSetup() {
        return mIsSetup;
    }

    @Override
    public final void setup() {
        if (isSetup()) {
            return;
        }
        onSetup();
        mIsSetup = true;
    }

    @Override
    public final void dispose() {
        if (!isSetup()) {
            return;
        }
        unbindAll();
        onDispose();
        mIsSetup = false;
    }

    protected void onSetup() {

    }

    protected void onDispose() {

    }

    @Override
    public void refresh() {
        for (ViewType view : getView()) {
            refresh(view);
        }
    }

    @Override
    public void bindView(ViewType view) {
        if (!isSetup()) {
            throw new RuntimeException(String.format("Bind view while %s is not setup", getClass().getSimpleName()));
        }
        if (mViews.add(view)) {
            view.onBind();
            refresh(view);
        }
    }

    @Override
    public void unbindAll() {
        for (ViewType viewType : mViews) {
            viewType.onUnbind();
        }
        mViews.clear();
    }

    @Override
    public void unBindView(ViewType view) {
        if (mViews.remove(view)) {
            view.onUnbind();
        }
    }

    protected Collection<ViewType> getView() {
        return mViews;
    }

    protected void forEachView(Consumer<ViewType> operation) {
        if (!isSetup()) {
            return;
        }
        for (ViewType view : getView()) {
            operation.accept(view);
        }
    }

    protected <T> void forEachView(T value, BiConsumer<T, ViewType> operation) {
        forEachView(view -> operation.accept(value, view));
    }

    protected <T> void forEachView(BiConsumer<T, ViewType> operation, Supplier<T> valueSupplier) {
        forEachView(valueSupplier.get(), operation);
    }

}

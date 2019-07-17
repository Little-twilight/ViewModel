package com.zhongyou.viewmodel;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractViewModel<ViewType extends IView, ModelType> implements IViewModel<ViewType, ModelType> {
	private Set<ViewType> mViews = new HashSet<>();

	@Override
	public void bindView(ViewType view) {
		if (mViews.add(view)) {
			view.onBind();
		}
		refresh();
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

}

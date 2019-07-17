package com.zhongyou.viewmodel;

public interface IViewModel<ViewType extends IView, ModelType> {

	ModelType getModel();

	void bindView(ViewType view);

	void unbindAll();

	void unBindView(ViewType view);

	void refresh();

	void setup();

	void dispose();

	default void runOnUiThread(Runnable action) {
		UiRunner.runOnUI(action);
	}

}

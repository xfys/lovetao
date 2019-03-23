package com.jess.arms.base;


import com.jess.arms.di.component.AppComponent;

import androidx.annotation.NonNull;

/**
 * ================================================
 * 框架要求框架中的每个 {@link android.app.Application} 都需要实现此类, 以满足规范
 *
 * @see BaseApplication
 * ================================================
 */
public interface App {
    @NonNull
    AppComponent getAppComponent();
}

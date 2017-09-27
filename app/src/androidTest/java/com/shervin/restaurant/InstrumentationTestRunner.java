package com.shervin.restaurant;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

public class InstrumentationTestRunner extends AndroidJUnitRunner {

    @Override public Application newApplication(ClassLoader cl, String className, Context context)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, TestMainApplication.class.getName(), context);
    }
}

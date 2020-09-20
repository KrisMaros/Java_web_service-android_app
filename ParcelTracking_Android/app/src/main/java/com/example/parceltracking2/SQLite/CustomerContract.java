package com.example.parceltracking2.SQLite;

import android.provider.BaseColumns;

public final class CustomerContract {

    private CustomerContract() {}

    public static class CustomersTable implements BaseColumns {
        public static final String TABLE_NAME = "customers";
        public static final String COLUMN_NAME = "custName";
        public static final String COLUMN_PWD = "custPwd";
        public static final String COLUMN_ADDRESS = "custAddress";
    }
}

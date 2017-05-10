package com.thread.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SyncCollectionDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        List<String> synchronizedList = Collections.synchronizedList(list);

    }
}

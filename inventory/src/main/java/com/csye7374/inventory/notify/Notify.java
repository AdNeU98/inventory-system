package com.csye7374.inventory.notify;

import com.csye7374.inventory.model.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Notify {
    private final List<ObserverAPI> subscribers = new ArrayList();

    public Notify() {
    }

    public void setState(Product product) {
        this.notifyAllSubscribers(product);
    }

    public void attach(ObserverAPI sub) {
        this.subscribers.add(sub);
    }

    public void notifyAllSubscribers(Product product) {
        Iterator iterator = this.subscribers.iterator();

        while(iterator.hasNext()) {
            ObserverAPI observer = (ObserverAPI)iterator.next();
            observer.update(product);
        }

    }
}

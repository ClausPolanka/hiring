package com.gildedrose;

public interface UpdateStrategy {
    boolean matches(Item item);
    int getNewQuality(Item item);
    int getNewSellIn(Item item);
}

package com.gildedrose;

public class DefaultUpdateStrategy implements UpdateStrategy {

    @Override
    public boolean matches(Item item) {
        return true;
    }

    @Override
    public int getNewQuality(Item item) {
        if (item.quality <= 0) {
            return item.quality;
        } else if (item.sellIn <= 0) {
            return item.quality - 2;
        } else {
            return item.quality - 1;
        }
    }

    @Override
    public int getNewSellIn(Item item) {
        return item.sellIn - 1;
    }
}

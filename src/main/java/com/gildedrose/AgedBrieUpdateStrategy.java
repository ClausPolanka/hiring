package com.gildedrose;

public class AgedBrieUpdateStrategy implements UpdateStrategy {

    @Override
    public boolean matches(Item item) {
        return item.name.equals("Aged Brie");
    }

    @Override
    public int getNewQuality(Item item) {
        if (item.quality >= 50) {
            return item.quality;
        } else if (item.sellIn <= 0) {
            return item.quality + 2;
        } else {
            return item.quality + 1;
        }
    }

    @Override
    public int getNewSellIn(Item item) {
        return item.sellIn - 1;
    }
}

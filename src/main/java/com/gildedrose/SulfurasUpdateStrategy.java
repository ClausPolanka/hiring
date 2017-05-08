package com.gildedrose;

public class SulfurasUpdateStrategy implements UpdateStrategy {

    @Override
    public boolean matches(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    @Override
    public int getNewQuality(Item item) {
        return item.quality;
    }

    @Override
    public int getNewSellIn(Item item) {
        return item.sellIn;
    }
}

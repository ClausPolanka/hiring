package com.gildedrose;

import java.util.Arrays;
import java.util.List;

class GildedRose {

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        final List<UpdateStrategy> strategyList = Arrays.asList(
                new AgedBrieUpdateStrategy(),
                new BackstagePassUpdateStrategy(),
                new SulfurasUpdateStrategy(),
                new DefaultUpdateStrategy()
        );

        for (Item item : items) {
            // TODO Streams
            for (UpdateStrategy strategy : strategyList) {
                if (!strategy.matches(item)) {
                    continue;
                }

                item.quality = strategy.getNewQuality(item);
                item.sellIn = strategy.getNewSellIn(item);

                break;
            }
        }
    }
}
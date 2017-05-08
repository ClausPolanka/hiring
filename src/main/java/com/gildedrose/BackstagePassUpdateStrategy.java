package com.gildedrose;

public class BackstagePassUpdateStrategy implements UpdateStrategy {

    @Override
    public boolean matches(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    @Override
    public int getNewQuality(Item item) {
        if (item.sellIn <= 0) {
            return 0;
        } else if (item.sellIn > 0 && item.sellIn <= 5) {
            return item.quality + 3;
        } else if (item.sellIn > 5 && item.sellIn <= 10) {
            return item.quality + 2;
        } else { // sellIn > 11
            return item.quality + 1;
        }
    }

    @Override
    public int getNewSellIn(Item item) {
        return item.sellIn - 1;
    }
}

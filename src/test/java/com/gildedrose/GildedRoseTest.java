package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GildedRoseTest {

    private static final String ITEM_DEFAULT_NAME = "foo";
    private static final int ITEM_DEFAULT_SELLIN = 10;
    private static final int ITEM_DEFAULT_QUALITY = 10;
    private static final int ITEM_OVERDUE_SELLIN = 0;
    private static final int ITEM_LONG_OVERDUE_SELLIN = -50;
    private Item item;
    private GildedRose app;

    @Before
    public void setUp() throws Exception {
        item = new Item(ITEM_DEFAULT_NAME, ITEM_DEFAULT_SELLIN, 10);
        final Item[] items = new Item[] {item};
        app = new GildedRose(items);
    }

    @Test
    public void updateQuality_doesNotAlterName() {
        app.updateQuality();
        assertEquals(ITEM_DEFAULT_NAME, app.items[0].name);
    }

    @Test
    public void updateQuality_doesDecreaseSellIn() throws Exception {
        app.updateQuality();
        assertEquals(ITEM_DEFAULT_SELLIN - 1, app.items[0].sellIn);
    }

    @Test
    public void updateQuality_doesDecreaseQuality() throws Exception {
        app.updateQuality();
        assertEquals(ITEM_DEFAULT_QUALITY - 1, app.items[0].quality);
    }

    @Test
    public void updateQuality_withPassedSellIn_doesDecreaseQuality() throws Exception {
        item.sellIn = ITEM_OVERDUE_SELLIN;
        app.updateQuality();
        assertEquals(ITEM_DEFAULT_QUALITY - 2, app.items[0].quality);
    }

    @Test
    public void updateQuality_doesNotDecreaseQualityBelowZero() throws Exception {
        item.quality = 0;
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    public void updateQuality_withNegativeQuality_doesNotDecreaseQuality() throws Exception {
        item.quality = -1;
        app.updateQuality();
        assertEquals(-1, app.items[0].quality);
    }

    @Test
    public void updateQuality_withAgedBrie_doesIncreaseQuality() throws Exception {
        item.name = "Aged Brie";
        app.updateQuality();
        assertEquals(ITEM_DEFAULT_QUALITY + 1, app.items[0].quality);
    }

    @Test
    public void updateQuality_withAgedBrieAfterSellIn_doesIncreaseQualityTwice() throws Exception {
        item.name = "Aged Brie";
        item.sellIn = ITEM_OVERDUE_SELLIN;
        app.updateQuality();
        assertEquals(ITEM_DEFAULT_QUALITY + 2, app.items[0].quality);
    }

    @Test
    public void updateQuality_withLongOverdueAgedBrie_doesIncreaseQualityTwice() throws Exception {
        item.name = "Aged Brie";
        item.sellIn = ITEM_LONG_OVERDUE_SELLIN;
        app.updateQuality();
        assertEquals(ITEM_DEFAULT_QUALITY + 2, app.items[0].quality);
    }
}

package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GildedRoseTest {

    private static final String ITEM_DEFAULT_NAME = "foo";
    private static final int ITEM_DEFAULT_SELLIN = 10;
    private static final int ITEM_DEFAULT_QUALITY = 15;
    private static final int ITEM_OVERDUE_SELLIN = 0;
    private static final int ITEM_LONG_OVERDUE_SELLIN = -50;
    public static final int ITEM_QUALITY_MAX = 50;
    private Item item;
    private GildedRose app;

    @Before
    public void setUp() {
        item = new Item(ITEM_DEFAULT_NAME, ITEM_DEFAULT_SELLIN, ITEM_DEFAULT_QUALITY);
        final Item[] items = new Item[] {item};
        app = new GildedRose(items);
    }

    @Test
    public void doesNotAlterName() {
        app.updateQuality();
        assertEquals(ITEM_DEFAULT_NAME, app.items[0].name);
    }

    @Test
    public void doesDecreaseSellIn() {
        app.updateQuality();
        assertEquals(ITEM_DEFAULT_SELLIN - 1, app.items[0].sellIn);
    }

    @Test
    public void doesDecreaseQuality() {
        app.updateQuality();
        assertEquals(ITEM_DEFAULT_QUALITY - 1, app.items[0].quality);
    }

    @Test
    public void withPassedSellIn_doesDecreaseQuality() {
        item.sellIn = ITEM_OVERDUE_SELLIN;
        app.updateQuality();
        assertEquals(ITEM_DEFAULT_QUALITY - 2, app.items[0].quality);
    }

    @Test
    public void doesNotDecreaseQualityBelowZero() {
        item.quality = 0;
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    public void withNegativeQuality_doesNotDecreaseQuality() {
        item.quality = -1;
        app.updateQuality();
        assertEquals(-1, app.items[0].quality);
    }

    @Test
    public void withAgedBrie_doesIncreaseQuality() {
        item.name = "Aged Brie";
        app.updateQuality();
        assertEquals(ITEM_DEFAULT_QUALITY + 1, app.items[0].quality);
    }

    @Test
    public void withAgedBrieAfterSellIn_doesIncreaseQualityTwice() {
        item.name = "Aged Brie";
        item.sellIn = ITEM_OVERDUE_SELLIN;
        app.updateQuality();
        assertEquals(ITEM_DEFAULT_QUALITY + 2, app.items[0].quality);
    }

    @Test
    public void withLongOverdueAgedBrie_doesIncreaseQualityTwice() {
        item.name = "Aged Brie";
        item.sellIn = ITEM_LONG_OVERDUE_SELLIN;
        app.updateQuality();
        assertEquals(ITEM_DEFAULT_QUALITY + 2, app.items[0].quality);
    }

    @Test
    public void withAgedBrie_doesNotIncreaseQualityOverMaximum() {
        item.name = "Aged Brie";
        item.quality = ITEM_QUALITY_MAX;
        app.updateQuality();
        assertEquals(ITEM_QUALITY_MAX, app.items[0].quality);
    }

    @Test
    public void withSulfuras_doesNotChangeQuality() {
        item.name = "Sulfuras, Hand of Ragnaros";
        app.updateQuality();
        assertEquals(ITEM_DEFAULT_QUALITY, app.items[0].quality);
    }

    @Test
    public void withSulfuras_doesNotChangeSellIn() {
        item.name = "Sulfuras, Hand of Ragnaros";
        app.updateQuality();
        assertEquals(ITEM_DEFAULT_SELLIN, app.items[0].sellIn);
    }
}

package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GildedRoseTest {

    private static final String ITEM_DEFAULT_NAME = "foo";
    private static final int ITEM_DEFAULT_SELLIN = 15;
    private static final int ITEM_DEFAULT_QUALITY = 15;
    private static final int ITEM_OVERDUE_SELLIN = 0;
    private static final int ITEM_LONG_OVERDUE_SELLIN = -50;
    private static final int ITEM_QUALITY_MAX = 50;
    private static final String ITEM_NAME_BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
    private static final String ITEM_NAME_AGED_BRIE = "Aged Brie";
    private static final String ITEM_NAME_SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final int ITEM_QUALITY_MIN = 0;
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
    public void withPassedSellIn_doesDecreaseQualityByTwo() {
        item.sellIn = ITEM_OVERDUE_SELLIN;
        app.updateQuality();
        assertEquals(ITEM_DEFAULT_QUALITY - 2, app.items[0].quality);
    }

    @Test
    public void doesNotDecreaseQualityBelowZero() {
        item.quality = ITEM_QUALITY_MIN;
        app.updateQuality();
        assertEquals(ITEM_QUALITY_MIN, app.items[0].quality);
    }

    @Test
    public void withNegativeQuality_doesNotDecreaseQuality() {
        item.quality = -1;
        app.updateQuality();
        assertEquals(-1, app.items[0].quality);
    }

    @Test
    public void withAgedBrie_doesIncreaseQuality() {
        item.name = ITEM_NAME_AGED_BRIE;
        app.updateQuality();
        assertEquals(ITEM_DEFAULT_QUALITY + 1, app.items[0].quality);
    }

    @Test
    public void withAgedBrieAfterSellIn_doesIncreaseQualityTwice() {
        item.name = ITEM_NAME_AGED_BRIE;
        item.sellIn = ITEM_OVERDUE_SELLIN;
        app.updateQuality();
        assertEquals(ITEM_DEFAULT_QUALITY + 2, app.items[0].quality);
    }

    @Test
    public void withLongOverdueAgedBrie_doesIncreaseQualityTwice() {
        item.name = ITEM_NAME_AGED_BRIE;
        item.sellIn = ITEM_LONG_OVERDUE_SELLIN;
        app.updateQuality();
        assertEquals(ITEM_DEFAULT_QUALITY + 2, app.items[0].quality);
    }

    @Test
    public void withAgedBrie_doesNotIncreaseQualityOverMaximum() {
        item.name = ITEM_NAME_AGED_BRIE;
        item.quality = ITEM_QUALITY_MAX;
        app.updateQuality();
        assertEquals(ITEM_QUALITY_MAX, app.items[0].quality);
    }

    @Test
    public void withSulfuras_doesNotChangeQuality() {
        item.name = ITEM_NAME_SULFURAS;
        app.updateQuality();
        assertEquals(ITEM_DEFAULT_QUALITY, app.items[0].quality);
    }

    @Test
    public void withSulfuras_doesNotChangeSellIn() {
        item.name = ITEM_NAME_SULFURAS;
        app.updateQuality();
        assertEquals(ITEM_DEFAULT_SELLIN, app.items[0].sellIn);
    }

    @Test
    public void withBackstagePassToBeSoldInElevenDays_increasesQualityByOne() {
        item.name = ITEM_NAME_BACKSTAGE_PASS;
        item.sellIn = 11;
        app.updateQuality();
        assertEquals(ITEM_DEFAULT_QUALITY + 1, app.items[0].quality);
    }

    @Test
    public void withBackstagePassToBeSoldInTenDays_increasesQualityByTwo() {
        item.name = ITEM_NAME_BACKSTAGE_PASS;
        item.sellIn = 10;
        app.updateQuality();
        assertEquals(ITEM_DEFAULT_QUALITY + 2, app.items[0].quality);
    }

    @Test
    public void withBackstagePassToBeSoldInSixDays_increasesQualityByTwo() {
        item.name = ITEM_NAME_BACKSTAGE_PASS;
        item.sellIn = 6;
        app.updateQuality();
        assertEquals(ITEM_DEFAULT_QUALITY + 2, app.items[0].quality);
    }

    @Test
    public void withBackstagePassToBeSoldInFiveDays_increasesQualityByThree() {
        item.name = ITEM_NAME_BACKSTAGE_PASS;
        item.sellIn = 5;
        app.updateQuality();
        assertEquals(ITEM_DEFAULT_QUALITY + 3, app.items[0].quality);
    }

    @Test
    public void withBackstagePassToBeSoldInOneDays_increasesQualityByThree() {
        item.name = ITEM_NAME_BACKSTAGE_PASS;
        item.sellIn = 1;
        app.updateQuality();
        assertEquals(ITEM_DEFAULT_QUALITY + 3, app.items[0].quality);
    }

    @Test
    public void withBackstagePassToBeSoldInZeroDays_setsQualityToZero() {
        item.name = ITEM_NAME_BACKSTAGE_PASS;
        item.sellIn = 0;
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }
}
